import java.awt.Color.BLACK
import java.awt.Color.WHITE
import java.awt.Graphics
import java.awt.Image.SCALE_SMOOTH
import java.awt.Point
import java.awt.event.ActionListener
import javax.imageio.ImageIO
import javax.swing.ImageIcon
import javax.swing.JButton
import javax.swing.border.LineBorder

class Field(
    val position: Point,
    listener: ActionListener
) : JButton() {
    var state: String? = null //Use this to check if field already tagged by player

    init {
        this.background = WHITE
        this.foreground = BLACK
        this.border = LineBorder(BLACK, 2)
        this.addActionListener(listener)
    }

    override fun paintComponent(g: Graphics?) {
        this.icon = state?.let { MainFrame::class.java.getResource("$it.png") }
            ?.let { ImageIO.read(it) }
            ?.let { ImageIcon(it.getScaledInstance(this.width, this.height, SCALE_SMOOTH)) }
        super.paintComponent(g)
    }
}
