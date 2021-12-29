package View;

import Model.DataSet;
import Model.Subject;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel {
    Subject dataset;
    public SettingsPanel(Subject dataset){
        this.dataset = dataset;
        this.setMinimumSize(new Dimension(200, 200));
        this.setPreferredSize(new Dimension(400, 400));
        this.setMaximumSize(new Dimension(800, 800));
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        setBackground(Color.red);
        var layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JButton clusterButton = new JButton("Cluster");
        clusterButton.addActionListener(e -> this.dataset.applyClustering());
        JButton reset = new JButton("reset");
        reset.addActionListener(e -> this.dataset.reset());

        layout.setHorizontalGroup(layout.createSequentialGroup().addComponent(reset).addComponent(clusterButton));
        layout.setVerticalGroup(layout.createParallelGroup().addComponent(reset).addComponent(clusterButton));

    }
}
