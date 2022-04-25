import javax.swing.JFrame

class MainFrame(title: String? = null) : JFrame(title) {
    init {
        this.defaultCloseOperation = EXIT_ON_CLOSE
        this.isVisible = true
    }
}