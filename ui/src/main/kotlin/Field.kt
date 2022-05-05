import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.awt.Color.BLACK
import java.awt.Color.WHITE
import java.awt.Graphics
import java.awt.event.ActionListener
import javax.swing.JButton
import javax.swing.border.LineBorder

class Field(
    val id: Int,
    listener: ActionListener? = null
) : JButton() {
    //TODO: Add global constant for initial field state
    var state: Int = -1
        set(value) {
            field = value
            logger.debug("#${this.id} changed state: $field")
        }

    init {
        this.icon = IconCache[state]
        this.background = WHITE
        this.foreground = BLACK
        this.border = LineBorder(BLACK, 2)
        if (listener != null) this.addActionListener(listener)
    }

    override fun paintComponent(g: Graphics?) {
        this.icon = IconCache[this.state]
        super.paintComponent(g)
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(Field::class.java)
    }
}
