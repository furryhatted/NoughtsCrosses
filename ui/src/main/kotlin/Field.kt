import java.awt.Color.BLACK
import java.awt.Color.WHITE
import java.awt.Image.SCALE_SMOOTH
import java.awt.Point
import javax.imageio.ImageIO
import javax.swing.ImageIcon
import javax.swing.JButton
import javax.swing.border.LineBorder

class Field(
    val position: Point,
    var state: Int = -1 //Use this to check if field already tagged by player
) : JButton() {
    init {
        this.background = WHITE
        this.foreground = BLACK
        this.border = LineBorder(BLACK, 2)
        //TODO: Replace with proper onClick listener
        this.addActionListener {
            state = (state + 1) % 2
            val resource = when (state) {
                0 -> MainFrame::class.java.getResource("x.png")
                1 -> MainFrame::class.java.getResource("o.png")
                else -> throw IllegalArgumentException("Unknown field state: $state")
            }
            val image = ImageIO.read(resource)
            this.icon = ImageIcon(image.getScaledInstance(this.width, this.height, SCALE_SMOOTH))
        }
    }
}
