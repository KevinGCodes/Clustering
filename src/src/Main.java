import Model.DataSet;
import View.DataPlot;
import View.MainFrame;

public class Main {
    public static void main(String[] args){
        DataSet dataset = new DataSet();
        dataset.addPoint(0f, 0f);
        MainFrame frame = new MainFrame(dataset);
        DataPlot plot = new DataPlot(dataset, frame);
        dataset.attachObserver(plot);


    }
}
