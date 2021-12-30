package View;

import javax.swing.*;
import Model.*;

import java.awt.*;

public class MainFrame extends JFrame {
    Subject dataset;

    public MainFrame(Subject dataset){
        super("Clustering");
        this.dataset = dataset;
        var dataplot = new DataPlot(dataset);
        dataset.attachObserver(dataplot);
        var settingsPanel = new SettingsPanel(this.dataset);
        dataset.attachObserver(settingsPanel);

        this.setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(800, 800));

        var contentPane = this.getContentPane();
        contentPane.setBackground(Color.black);
        var layout = new GroupLayout(contentPane);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup().addComponent(dataplot)
                                                                .addComponent(settingsPanel));
        layout.setVerticalGroup(layout.createParallelGroup().addComponent(dataplot)
                                                            .addComponent(settingsPanel));

    }
}
