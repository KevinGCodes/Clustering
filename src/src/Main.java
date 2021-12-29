import Model.DataSet;
import Model.Point;
import View.DataPlot;
import View.MainFrame;

import java.util.List;

public class Main {
    public static void main(String[] args){
        DataSet dataset = new DataSet();
        MainFrame frame = new MainFrame(dataset);
        DataPlot plot = new DataPlot(dataset, frame);
        dataset.attachObserver(plot);
    }
}
