import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import javax.swing.JFrame

class MainFrameTest {
    @Test
    fun shouldShow() {
        val mainFrame: JFrame = MainFrame()
        assertTrue(mainFrame.isShowing, "Main frame is not shown!")

    }
}