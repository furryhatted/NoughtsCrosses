import java.awt.Dimension
import java.awt.GridLayout
import java.awt.event.ActionListener
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import javax.swing.JPanel

class Board(
    val players: Int,
    val dimension: Dimension
) : JPanel(GridLayout(dimension.width, dimension.height), true) {
    //TODO: Add global constant for initial field state
    private var currentPlayer = -1
        set(value) {
            field = value % players
        }
    private val inputListener =
        ActionListener { this.updateState((it.source as Field).id) }

    init {
        repeat(dimension.width * dimension.height) { this.add(Field(it, inputListener)) }
        this.addComponentListener(object : ComponentAdapter() {
            override fun componentResized(e: ComponentEvent) {
                IconCache.sideScale = minOf(e.component.width / dimension.width, e.component.height / dimension.height)
                super.componentResized(e)
            }
        })
    }

    fun updateState(fieldIndex: Int) {
        with(this.components[fieldIndex] as Field) {
            this.state = ++currentPlayer
            this.removeActionListener(inputListener)
            this.repaint()
        }

        //FIXME: remove this line!!! This just tags "O" random field after "X" move
        if (currentPlayer == 0) updateState(this.components.withIndex().filter { (it.value as Field).state == -1 }
            .random().index)
    }

}