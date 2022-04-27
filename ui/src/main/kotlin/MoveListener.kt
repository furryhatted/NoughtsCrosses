import java.awt.event.ActionEvent
import java.awt.event.ActionListener

class MoveListener(private val tags: List<String>) : ActionListener {
    private var _state = -1

    override fun actionPerformed(e: ActionEvent) {
        val source = e.source as Field
        if (source.state != null) return
        _state = (_state + 1) % tags.size
        println("Player$_state (${tags[_state]})")
        source.state = tags[_state]
        source.repaint()
    }
}