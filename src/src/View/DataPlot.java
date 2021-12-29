package View;
import Model.Point
import javax.swing.*;
import java.util.List;

public class DataPlot extends JPanel {
    private List<Point> data;

    public DataPlot(List<Point> data){
        this.data = data;
    }
}
