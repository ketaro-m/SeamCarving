package image;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class DragRegion extends JFrame implements MouseMotionListener, MouseListener {

    private Picture picture;
    private int radius;

    public DragRegion(String title, Picture picture) {
        super(title);
        setContentPane(picture.getJLabel());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        pack();

        this.picture = picture;
        radius = 0;

        addMouseMotionListener(this);
        addMouseListener(this);
    }

    public void setRadius(int r) {
        radius = r;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX(), y = e.getY();
        picture.mark(x, y, radius);
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
