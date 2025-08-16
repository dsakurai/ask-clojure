import javax.swing.JFrame
import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.SwingUtilities

class App : JFrame() {
    val button = JButton("Click Me")
    val panel = JPanel()

    init {
        title = "My Kotlin GUI App"
        setSize(400, 300)
        defaultCloseOperation = EXIT_ON_CLOSE
        setupUI()
    }

    private fun setupUI() {
        button.addActionListener { println("Button clicked!") }
        panel.add(button)
        contentPane.add(panel)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SwingUtilities.invokeLater {
                App().isVisible = true
            }
        }
    }
}