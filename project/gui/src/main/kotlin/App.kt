import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.StackPane
import javafx.stage.Stage
import kotlinx.coroutines.*
import kotlinx.coroutines.javafx.JavaFx

class App : Application() {
    lateinit var button: Button
    override fun start(primaryStage: Stage) {
        button = Button("Click Me")
        button.setOnAction {
            // Launch a coroutine on the JavaFX thread
            CoroutineScope(Dispatchers.JavaFx).launch {
                button.isDisable = true
                button.text = "Working..."
                // Simulate async work
                val result = asyncWork()
                button.text = result
                button.isDisable = false
            }
        }

        val root = StackPane(button)
        primaryStage.scene = Scene(root, 400.0, 300.0)
        primaryStage.title = "Modern Kotlin GUI App"
        primaryStage.show()
    }

    // Simulate an async operation
    private suspend fun asyncWork(): String = withContext(Dispatchers.IO) {
        delay(2000) // pretend to do work
        "Done!"
    }
}

fun main(args: Array<String>) {
    Application.launch(App::class.java, *args)
}