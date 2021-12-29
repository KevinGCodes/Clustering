package View;

import javax.swing.*;
import Model.*;

import java.awt.*;

public class MainFrame extends JFrame {
    Subject dataset;

    public MainFrame(Subject dataset){
        super("Clustering");
        this.dataset = dataset;

        this.setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(800, 800));
        this.setBackground(Color.WHITE);
        this.add(new DataPlot(this.dataset, this));

    }
}
