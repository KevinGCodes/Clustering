package View;

import Model.DataSet;
import Model.Subject;
import io.CSVLoader;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class SettingsPanel extends JPanel {
    Subject dataset;
    public SettingsPanel(Subject dataset){
        this.dataset = dataset;
        setBackground(Color.darkGray);
        setDimensions();

        var layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JButton clusterButton = createButton("Cluster");
        clusterButton.addActionListener(e -> this.dataset.applyClustering());
        JButton reset = createButton("reset");
        reset.addActionListener(e -> this.dataset.reset());

        JTextField csvInput = new JTextField();
        csvInput.setBorder(BorderFactory.createEmptyBorder());
        csvInput.setPreferredSize(new Dimension(150, 25));
        csvInput.setMaximumSize(new Dimension(150, 25));
        csvInput.setMinimumSize(new Dimension(150,25));
        JButton loadCSV = createButton("load Csv file!");
        loadCSV.addActionListener(e -> ((DataSet)dataset).loadData(csvInput.getText().strip()));

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(reset).addComponent(csvInput))
                .addGroup(layout.createParallelGroup().addComponent(clusterButton).addComponent(loadCSV)));
        layout.setVerticalGroup(layout.createSequentialGroup().addGroup(
                layout.createParallelGroup().addComponent(reset).addComponent(clusterButton))
                .addComponent(csvInput)
                .addComponent(loadCSV));
    }

    private void setDimensions(){
        this.setMinimumSize(new Dimension(200, 200));
        this.setPreferredSize(new Dimension(200, 800));
        this.setMaximumSize(new Dimension(200, 800));
    }

    private JButton createButton(String text){
        JButton button = new JButton(text);
        button.setBorderPainted(false);
        button.setBackground(new Color(161, 188, 201));
        button.setFocusable(false);
        return button;
    }
}
