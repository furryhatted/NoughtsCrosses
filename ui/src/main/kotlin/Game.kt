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

    private var winner: Int = -1

    private val byRow: List<List<Int>> = board.components.indices.chunked(dimension.width)

    private val byColumn: List<List<Int>> = board.components.indices.groupBy { it % dimension.width }.map { it.value }

    private val byDiag: List<List<Int>> = listOf(byRow.mapIndexed { index, ints -> ints[index] },
        byRow.mapIndexed { index, ints -> ints[dimension.width - index - 1] })

    private fun isWinner(player: Int): Boolean =
        byRow.any { it.count { i -> (board.components[i] as Field).state == player } == dimension.width } ||
                byColumn.any { it.count { i -> (board.components[i] as Field).state == player } == dimension.width } ||
                byDiag.any { it.count { i -> (board.components[i] as Field).state == player } == dimension.width }


    val isFinished: Boolean
        get() = board.components.none { (it as Field).state == -1 } ||
                (0 until players).map { isWinner(it).apply { if (this) winner = it } }
                    .reduce { result, bool -> result || bool }

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
        JOptionPane.showMessageDialog(board, "Eggs are not supposed to be green... \n Well... khm... Player $winner won...")
        board.isVisible = false
    }


    companion object {
        private val logger: Logger = LoggerFactory.getLogger(Game::class.java)
    }

}