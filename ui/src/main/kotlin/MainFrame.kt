import java.awt.*
import java.awt.GridBagConstraints.BOTH
import java.awt.GridBagConstraints.CENTER
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.SwingConstants


class MainFrame(
    pixels: Dimension = Dimension(800, 800),
    fields: Dimension = Dimension(3, 3),
    title: String? = "Main Frame"
) : JFrame(title) {

    init {
        this.layout = GridBagLayout()
        this.minimumSize = Dimension(1100, 1100)

        val boardConstraints = GridBagConstraints().apply {
            this.anchor = CENTER
            this.fill = BOTH
            this.gridx = 1
            this.gridy = 1
            this.gridwidth = 8
            this.gridheight = 8
        }
        this.add(Board(pixels, fields), boardConstraints)

        val labelConstraints = GridBagConstraints().apply {
            this.anchor = CENTER
            this.fill = BOTH
            this.gridx = 0
            this.gridy = 0
            this.gridwidth = 10
            this.gridheight = 1
        }
        val moveLabel = JLabel().apply {
            this.text = "Current move"
            this.font = Font("Comic Sans MS", Font.BOLD, 22)
            this.horizontalAlignment = SwingConstants.CENTER
        }

        this.add(moveLabel, labelConstraints)
        this.defaultCloseOperation = EXIT_ON_CLOSE
        this.background = Color.BLACK
        this.isVisible = true
    }

}