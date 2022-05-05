import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.RENDEZVOUS
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.awt.Dimension
import java.awt.event.ActionListener
import javax.swing.JOptionPane

class Game(
    val players: Int = 2,
    val dimension: Dimension = Dimension(3, 3)
) {
    private val mailbox: Channel<Int> = Channel(RENDEZVOUS)

    private val inputListener = ActionListener {
        runBlocking { mailbox.send((it.source as Field).id) }
    }

    val board: Board = Board(dimension, inputListener)

    private val byRow: List<List<Int>> = board.components.indices.chunked(dimension.width)

    private val byColumn: List<List<Int>> = board.components.indices.groupBy { it % dimension.width }.map { it.value }

    val isFinished: Boolean
        get() = board.components.none { (it as Field).state == -1 } ||
                byRow.any { row -> row.count { (board.components[it] as Field).state == 0 } == dimension.width } ||
                byRow.any { row -> row.count { (board.components[it] as Field).state == 1 } == dimension.width } ||
                byColumn.any { col -> col.count { (board.components[it] as Field).state == 0 } == dimension.width } ||
                byColumn.any { col -> col.count { (board.components[it] as Field).state == 1 } == dimension.width }

    //arrayListOf(false, false, false).reduce { result, bool -> result || bool }

    private var turn = -1
        set(value) {
            field = value % players
        }


    fun playerMove(field: Int) {
        board.updateState(field, ++turn)


        //FIXME: remove this line!!! This just tags "O" random field after "X" move
        if (turn == 0) (board.components.filter { (it as Field).state == -1 }
            .random() as Field).doClick()
    }

    suspend fun run() = coroutineScope {
        while (!isFinished)
            board.updateState(mailbox.receive(), ++turn)
        JOptionPane.showMessageDialog(null, "Eggs are not supposed to be green.")
        board.isVisible = false
    }


    companion object {
        private val logger: Logger = LoggerFactory.getLogger(Game::class.java)
    }

}