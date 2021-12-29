package View;

import javax.swing.*;
import Model.*;

import java.awt.*;

public class MainFrame extends JFrame {
    Subject dataset;

    public MainFrame(Subject dataset, DataPlot dataplot){
        super("Clustering");
        this.dataset = dataset;

        this.setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(800, 800));
        this.setBackground(Color.WHITE);
        var settingsPanel = new SettingsPanel(this.dataset);

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
