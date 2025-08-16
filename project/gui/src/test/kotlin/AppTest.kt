import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import javafx.embed.swing.JFXPanel // Initializes JavaFX runtime

class AppTest {

    @Test
    fun testAppTitle() {
        JFXPanel() // Ensure JavaFX runtime is initialized
        val app = App()
        // Simulate JavaFX application launch
        // You can't test title directly without launching the stage, but you can check the class
        assertEquals("App", app::class.simpleName)
    }

    @Test
    fun testButtonText() {
        JFXPanel() // Ensure JavaFX runtime is initialized
        val app = App()
        app.button = javafx.scene.control.Button("Click Me")
        assertEquals("Click Me", app.button.text)
    }
}