import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class MainFrameTest {
    @Test
    fun shouldShow() {
        val mainFrame = MainFrame()
        assertTrue(mainFrame.isShowing, "Main frame is not shown!")
    }

    @Test
    fun shouldDrawBoard() {
        val mainFrame = MainFrame()
        assertTrue(mainFrame.components[0].isShowing, "Main frame is not shown!")

    }

}