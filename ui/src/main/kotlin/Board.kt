import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.awt.Dimension
import java.awt.GridLayout
import java.awt.event.ActionListener
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import javax.swing.JPanel

class Board(
    val dimension: Dimension,
    listener: ActionListener? = null
) : JPanel(GridLayout(dimension.width, dimension.height), true) {
    //TODO: Add global constant for initial field state


    init {
        repeat(dimension.width * dimension.height) { this.add(Field(it, listener)) }
        this.addComponentListener(object : ComponentAdapter() {
            override fun componentResized(e: ComponentEvent) {
                IconCache.sideScale =
                    minOf(e.component.width / dimension.width, e.component.height / dimension.height)
                super.componentResized(e)
            }
        })
    }

    internal fun updateState(index: Int, state: Int) {
        with(components[index] as Field) {
            this.actionListeners.forEach { this.removeActionListener(it) }
            this.state = state
        }
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(Board::class.java)
    }
}