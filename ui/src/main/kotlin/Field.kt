import java.awt.Color.BLACK
import java.awt.Color.WHITE
import java.awt.Graphics
import java.awt.event.ActionListener
import javax.swing.JButton
import javax.swing.border.LineBorder

class Field(
    val id: Int,
    listener: ActionListener,
) : JButton() {
    //TODO: Add global constant for initial field state
    var state: Int = -1

    init {
        this.icon = IconCache[state]
        this.background = WHITE
        this.foreground = BLACK
        this.border = LineBorder(BLACK, 2)
        this.addActionListener(listener)
    }

    override fun paintComponent(g: Graphics?) {
        this.icon = IconCache[this.state]
        super.paintComponent(g)
    }
}
