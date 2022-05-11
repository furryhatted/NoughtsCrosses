import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.awt.Color.BLACK
import java.awt.Color.WHITE
import java.awt.Graphics
import java.awt.event.ActionListener
import java.io.File
import java.io.FileNotFoundException
import javax.swing.JButton
import javax.swing.border.BevelBorder
import javax.swing.border.LineBorder

class FieldButton(
    val id: Int,
    listener: ActionListener? = null
) : JButton() {
    var state: Int = -1

    init {
        this.icon = IconCache[state]
        this.background = WHITE
        this.foreground = BLACK
        this.border = BevelBorder(0, WHITE, BLACK, WHITE, BLACK)
        if (listener != null) this.addActionListener(listener)
    }

    override fun paintComponent(g: Graphics?) {
        this.icon = IconCache[this.state]
        super.paintComponent(g)
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(FieldButton::class.java)
    }
}
