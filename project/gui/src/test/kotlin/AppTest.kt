import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AppTest {

    @Test
    fun testAppTitle() {
        val app = App()
        assertEquals("My Kotlin GUI App", app.title)
    }

    @Test
    fun testButtonText() {
        val app = App()
        assertEquals("Click Me", app.button.text)
    }
}