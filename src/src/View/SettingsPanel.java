package View;

import Algorithms.KMeansClustering;
import Algorithms.PAMClustering;
import Model.DataSet;
import Model.Observer;
import Model.Subject;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel implements Observer {
    Subject dataset;
    public SettingsPanel(Subject dataset){
        this.dataset = dataset;
        setDimensions();
        setBackground(Color.darkGray);
        var layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        this.setLayout(layout);

        add(Box.createRigidArea(new Dimension(0, 10)));
        JLabel label = new JLabel("Select number of Clusters:");
        label.setForeground(Color.white);
        label.setFont(new Font("Helvetica", 0, 15));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(label);
        add(Box.createRigidArea(new Dimension(0, 10)));
        JSlider slider = createSlider();
        add(slider);
        add(Box.createRigidArea(new Dimension(0, 20)));

        JComboBox<String> dropDown= new JComboBox<>(new String[]{"K-Means-Clustering", "PAM-Clustering"});
        dropDown.setPreferredSize(new Dimension(150, 20));
        dropDown.setMaximumSize(new Dimension(150, 20));
        dropDown.addActionListener(e -> {
            String val = (String)dropDown.getSelectedItem();
            var set = ((DataSet)this.dataset);
            switch(val){
                case "K-Means-Clustering":
                    set.setClusteringAlg(new KMeansClustering(set.getData()));
                    break;
                case "PAM-Clustering":
                    set.setClusteringAlg(new PAMClustering(set.getData()));
                    break;
            }
            System.out.println();
        });
        add(dropDown);

        add(Box.createRigidArea(new Dimension(0, 20)));


        JButton clusterButton = createButton("Cluster with k clusters");
        clusterButton.addActionListener(e -> this.dataset.applyClustering(slider.getValue()));
        add(clusterButton);
        add(Box.createRigidArea(new Dimension(0, 20)));
        JButton optimalCluster = createButton("cluster using Elbow method");
        optimalCluster.addActionListener(e -> this.dataset.applyOptimalClustering());
        add(optimalCluster);

        add(Box.createRigidArea(new Dimension(0, 20)));
        JTextField csvInput = createTextfield();
        add(csvInput);
        add(Box.createRigidArea(new Dimension(0, 20)));
        JButton loadCSV = createButton("load csv file!");
        add(loadCSV);
        loadCSV.addActionListener(e -> ((DataSet)dataset).loadData(csvInput.getText().strip()));

        add(Box.createRigidArea(new Dimension(0, 20)));
        JButton reset = createButton("reset");
        reset.addActionListener(e -> this.dataset.reset());
        add(reset);

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
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }

    private JTextField createTextfield(){
        JTextField textfield = new JTextField();
        textfield.setBorder(BorderFactory.createEmptyBorder());
        textfield.setPreferredSize(new Dimension(150, 25));
        textfield.setMaximumSize(new Dimension(150, 25));
        textfield.setMinimumSize(new Dimension(150,25));

        return textfield;

    }

    private JSlider createSlider(){
        int maxClusterCount = dataset.getData().size();
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 10, 5);
        slider.setMinorTickSpacing(1);
        slider.setMajorTickSpacing(2);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setBackground(Color.darkGray);
        Font font = new Font("Helvetica",0, 12);
        slider.setFont(font);
        slider.setForeground(Color.white);
        return slider;
    }

    @Override
    public void updateObserver() {
        paintImmediately(0, 0, getWidth(), getHeight());
    }
}
