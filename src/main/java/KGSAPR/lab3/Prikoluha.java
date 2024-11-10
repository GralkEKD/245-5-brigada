package KGSAPR.lab3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class Prikoluha implements MouseListener {

    private final JLabel label;
    private final JFrame jFrame;

    private int chaseCount = 0;

    Random random = new Random(System.nanoTime());

    public Prikoluha(JLabel label, JFrame jFrame) {
        this.label = label;
        this.jFrame = jFrame;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        label.setText("Все еще красавица");
        JButton thisButton = (JButton) e.getSource();
        thisButton.setForeground(Color.BLACK);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JButton thisButton = (JButton) e.getSource();
        if (chaseCount < 3) {
            chaseCount++;
            thisButton.setForeground(Color.RED);
            int x = random.nextInt(0, Integer.MAX_VALUE) % (jFrame.getWidth() * 4 / 5);
            int y = random.nextInt(0, Integer.MAX_VALUE) % (jFrame.getHeight() * 4 / 5);
            thisButton.getGraphics().setColor(Color.RED);
            thisButton.setLocation(x, y);
            System.out.printf("%d %d\n", x, y);
        }
        else {
            chaseCount = 0;
            thisButton.setForeground(Color.GREEN);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
}
