import jetbrains.letsPlot.intern.Plot
import java.awt.Dimension
import java.awt.GridLayout
import javax.swing.BorderFactory
import javax.swing.Box
import javax.swing.BoxLayout
import javax.swing.ButtonGroup
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.JRadioButton
import javax.swing.SwingUtilities

class Drawer {
    fun draw(plots: Map<String, Plot>) {
        val selectedPlotKey = plots.keys.first()
        val controller = Controller(
            plots,
            selectedPlotKey,
            false
        )

        val window = JFrame("Sort test")
        window.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        window.contentPane.layout = BoxLayout(window.contentPane, BoxLayout.Y_AXIS)

        // Add controls
        val controlsPanel = addControl(plots, controller)

        window.contentPane.add(controlsPanel)

        // Add plot panel
        val plotContainerPanel = JPanel(GridLayout())
        window.contentPane.add(plotContainerPanel)

        controller.plotContainerPanel = plotContainerPanel
        controller.rebuildPlotComponent()

        SwingUtilities.invokeLater {
            window.pack()
            window.size = Dimension(WIDTH, HEIGHT)
            window.setLocationRelativeTo(null)
            window.isVisible = true
        }
    }

    private fun addControl(plots: Map<String, Plot>, controller: Controller): Box {
        val selectedPlotKey = plots.keys.first()
        val controlsPanel = Box.createHorizontalBox().apply {
            // Plot selector
            val plotButtonGroup = ButtonGroup()
            for (key in plots.keys) {
                plotButtonGroup.add(
                    JRadioButton(key, key == selectedPlotKey).apply {
                        addActionListener {
                            controller.plotKey = this.text
                        }
                    }
                )
            }

            this.add(Box.createHorizontalBox().apply {
                border = BorderFactory.createTitledBorder("Plot")
                for (elem in plotButtonGroup.elements) {
                    add(elem)
                }
            })

            // Preserve aspect ratio selector
            val aspectRadioButtonGroup = ButtonGroup()
            aspectRadioButtonGroup.add(JRadioButton("Original", false).apply {
                addActionListener {
                    controller.preserveAspectRadio = true
                }
            })
            aspectRadioButtonGroup.add(JRadioButton("Fit container", true).apply {
                addActionListener {
                    controller.preserveAspectRadio = false
                }
            })

            this.add(Box.createHorizontalBox().apply {
                border = BorderFactory.createTitledBorder("Aspect ratio")
                for (elem in aspectRadioButtonGroup.elements) {
                    add(elem)
                }
            })
        }

        return controlsPanel
    }

    companion object {
        const val WIDTH = 850
        const val HEIGHT = 400
    }
}
