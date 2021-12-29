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
    private final int WIDTH = 600;
    private final int HEIGHT = 600;
    private final int PADDING = 75;
    private Graphics graphic;

    public DataPlot(Subject dataset){
        this.dataset = dataset;
        setMinimumSize(new Dimension(400, 400));
        setPreferredSize(new Dimension(600, 600));
        setMaximumSize(new Dimension(800, 800));
        this.addMouseListener(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        graphic = g;
        setBackground(Color.darkGray);
        drawCoordinateSystem(g);
        List<Point> data = dataset.getData();

        for(Point p : data){
            int px = (int)Math.round(p.getX()*getWidth());
            int py = (int)Math.round(p.getY()*getHeight());
            double step = (double)p.getCol()/((DataSet)dataset).clusterCount;
            g.setColor(Color.getHSBColor((float)step, 0.5f, 0.5f));
            g.fillOval(px - 5, py - 5, 10, 10);
        }

    }

    private void drawCoordinateSystem(Graphics g){
        int lineWidth = getWidth()- 2*PADDING;
        int lineHeight = getHeight() - 2*PADDING;
        g.setColor(Color.WHITE);
        g.drawLine(PADDING, PADDING, PADDING, PADDING + lineHeight);
        g.drawLine(PADDING, PADDING + lineHeight, PADDING + lineWidth, PADDING + lineHeight);
        final float stepSize = 0.2f;
        g.drawString("0", PADDING - 5, PADDING + lineHeight + 12);
        for(int i = 1; i <= 5; i++){
            long round = Math.round(PADDING + i * 0.2 * lineWidth);
            g.drawString(Float.toString(i*0.2f),(int)round - 7, PADDING + lineHeight + 12);
            g.drawString(Float.toString(i*0.2f),PADDING - 20,(int)Math.round(PADDING + lineHeight - i * 0.2 * lineHeight));
        }

    }


    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("mouse-coordinates: " + e.getX() + " + " + e.getY());
        System.out.println(dataset.getData());
        if(e.getX() < PADDING || e.getY() < PADDING) return;
        dataset.addPoint((float)e.getX()/getWidth(),(float)e.getY()/getHeight());
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void updateObserver() {
        repaint(0);
    }
}
