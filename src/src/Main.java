import Model.DataSet;
import Model.Point;
import View.DataPlot;
import View.MainFrame;

import javax.xml.crypto.Data;
import java.util.List;

public class Main {
    public static void main(String[] args){
        DataSet dataset = new DataSet();
        var plot = new DataPlot(dataset);
        MainFrame frame = new MainFrame(dataset, plot);
        dataset.attachObserver(plot);
    }
}
