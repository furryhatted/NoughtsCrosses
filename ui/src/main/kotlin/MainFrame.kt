import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.awt.*
import java.awt.GridBagConstraints.*
import java.awt.event.WindowEvent
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.SwingConstants


class MainFrame(
    width: Int = 600,
    height: Int = 600,
    title: String? = "Main Frame"
) : JFrame(title) {
    private val boardConstraints = GridBagConstraints().apply {
        this.anchor = SOUTH
        this.gridx = 0
        this.gridy = 1
        this.gridwidth = 3
        this.gridheight = 3
        this.weightx = 0.9
        this.weighty = 0.9
        this.insets = Insets(10, 10, 10, 10)
    }

    init {

        this.layout = GridBagLayout()
        this.size = Dimension(width, height)
        val labelConstraints = GridBagConstraints().apply {
            this.anchor = CENTER
            this.fill = BOTH
            this.gridx = 0
            this.gridy = 0
            this.gridwidth = 3
            this.gridheight = 1
            this.weightx = 0.1
            this.weighty = 0.1
            this.insets = Insets(10, 10, 0, 10)
        }
        val moveLabel = JLabel().apply {
            this.text = "Current move"
            this.font = Font("Comic Sans MS", Font.BOLD, 22)
            this.horizontalAlignment = SwingConstants.CENTER

        }

        this.add(moveLabel, labelConstraints)
        this.defaultCloseOperation = EXIT_ON_CLOSE
        this.isVisible = true
    }

    fun createGame(players: Int = 2, dimension: Dimension = Dimension(3, 3)) {
        val game = Game(players, dimension)
        this.add(game.board, boardConstraints)
        this.isVisible = true
        runBlocking {
            val job = CoroutineScope(Dispatchers.Default).launch { game.run() }
            job.join()
            this@MainFrame.dispatchEvent(WindowEvent(this@MainFrame, WindowEvent.WINDOW_CLOSING))
        }

    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(MainFrame::class.java)
    }
}