import java.awt.Color.BLACK
import java.awt.Color.WHITE
import java.awt.Image.SCALE_SMOOTH
import java.awt.Point
import javax.imageio.ImageIO
import javax.swing.ImageIcon
import javax.swing.JButton
import javax.swing.border.LineBorder
import kotlin.random.Random

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
            val image = ImageIO.read({}::class.java.getResource(if (Random.nextBoolean()) "x.png" else "o.png"))
            this.icon = ImageIcon(image.getScaledInstance(this.width, this.height, SCALE_SMOOTH))
        }
    }
}
