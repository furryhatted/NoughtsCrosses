import java.awt.Color.BLACK
import java.awt.Dimension
import java.awt.GridLayout
import java.awt.Point
import javax.swing.JPanel

class Board(
    pixels: Dimension? = null,
    fields: Dimension = Dimension(3, 3)
) : JPanel(GridLayout(fields.width, fields.height), true) {
    private val moveListener = MoveListener(listOf("X", "O"))

    init {
        //this.layout =
        this.preferredSize = pixels
        this.background = BLACK

        repeat(fields.height) { x ->
            repeat(fields.width) { y ->
                this.add(Field(Point(x, y), moveListener))
            }
        }
    }
}