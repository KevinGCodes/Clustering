package View;
import Model.*;
import Model.Point;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class DataPlot extends JPanel implements Observer, MouseListener {
    private Subject dataset;
    private JFrame frame;
    private final int WIDTH = 600;
    private final int HEIGHT = 600;

    public DataPlot(Subject dataset, JFrame frame){
        this.frame = frame;
        this.dataset = dataset;
        setSize(new Dimension(400, 400));
        this.addMouseListener(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        System.out.print("painting");
        List<Point> data = dataset.getData();
        setBackground(Color.darkGray);

        for(Point p : data){
            int px = Math.round(p.getX()*getWidth());
            int py = Math.round(p.getY()*getHeight());
            System.out.println(px + " and " +py);
            g.setColor(Color.WHITE);
            g.fillOval(px - 5, py - 5, 10, 10);
        }

    }

    @Override
    public void updateObserver() {
        this.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("mouse-coordinates: " + e.getX() + " + " + e.getY());
        System.out.println(dataset.getData());
        dataset.addPoint((float)e.getX()/getWidth(),(float)e.getY()/getHeight());
        this.updateObserver();
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
