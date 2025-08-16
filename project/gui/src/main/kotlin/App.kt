import javax.swing.JFrame
import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.SwingUtilities

class App : JFrame() {
    init {
        title = "My Kotlin GUI App"
        setSize(400, 300)
        defaultCloseOperation = EXIT_ON_CLOSE
        setupUI()
    }

    private fun setupUI() {
        val panel = JPanel()
        val button = JButton("Click Me")
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