import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val frame = MainFrame()
    while (true)
        frame.newGame()
}