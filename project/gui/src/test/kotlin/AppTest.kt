import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import javax.swing.SwingUtilities

class AppTest {

    @Test
    fun testAppInitialization() {
        val app = App()
        SwingUtilities.invokeAndWait {
            app.initialize()
        }
        assertEquals("Expected Title", app.frame.title)
    }

    @Test
    fun testButtonFunctionality() {
        val app = App()
        SwingUtilities.invokeAndWait {
            app.initialize()
            app.button.doClick()
        }
        assertEquals("Expected Result", app.label.text)
    }
}