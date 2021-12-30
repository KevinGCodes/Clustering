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

    public DataPlot(Subject dataset){
        this.dataset = dataset;
        setMinimumSize(new Dimension(400, 400));
        setPreferredSize(new Dimension(900, 900));
        setMaximumSize(new Dimension(1200, 1200));
        this.addMouseListener(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        setBackground(Color.darkGray);
        drawCoordinateSystem(g);
        drawPoints(g);
    }

    private void drawPoints(Graphics g){
        List<Point> data = dataset.getData();
        int lineWidth = getWidth()- 2*PADDING;
        int lineHeight = getHeight() - 2*PADDING;

        for(Point p : data){
            int px = (int)Math.round(p.getX()*lineWidth);
            int py = (int)Math.round(p.getY()*lineHeight);
            double step = (double)p.getCol()/((DataSet)dataset).getClusterCount();
            g.setColor(Color.getHSBColor((float)step, 0.5f, 0.5f));
            g.fillOval(PADDING + px - 5, PADDING + lineHeight - py - 5, 10, 10);
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
        int lineWidth = getWidth()- 2*PADDING;
        int lineHeight = getHeight() - 2*PADDING;
        if(e.getX() < PADDING || e.getY() < PADDING) return;
        if(e.getX() > getWidth() - PADDING || e.getY() > getHeight() - PADDING) return;
        double x = ((double)e.getX() - PADDING)/lineWidth;
        double y = (getHeight() - (double)e.getY() - PADDING)/lineHeight;
        dataset.addPoint(x, y);
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void updateObserver() {
        paintImmediately(0,0, getWidth(), getHeight());
    }
}
