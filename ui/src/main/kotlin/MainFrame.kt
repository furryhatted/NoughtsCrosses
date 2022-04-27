import java.awt.Color
import java.awt.Dimension
import java.awt.GridBagConstraints
import java.awt.GridBagConstraints.BOTH
import java.awt.GridBagConstraints.CENTER
import java.awt.GridBagLayout
import javax.swing.JFrame


class MainFrame(
    pixels: Dimension = Dimension(800, 800),
    fields: Dimension = Dimension(3, 3),
    title: String? = "Main Frame"
) : JFrame(title) {

    init {
        this.layout = GridBagLayout()
        this.minimumSize = Dimension(1100, 1100)
        val c = GridBagConstraints().apply {
            this.anchor = CENTER
            this.fill = BOTH
            this.gridx = 1
            this.gridy = 1
            this.gridwidth = 8
            this.gridheight = 8
        }
        this.add(Board(pixels, fields), c)
        this.defaultCloseOperation = EXIT_ON_CLOSE
        this.background = Color.BLACK
        this.isVisible = true
    }

}