package KGSAPR.lab2;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
public class Zalivki extends JFrame {
    private static final long serialVersionUID = 1L;
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        Ellipse2D ellipse = new Ellipse2D.Double(100,100,100,100);
        g2.setPaint(new GradientPaint(100,150,
                new Color(255, 255, 0),200,150, new Color(0, 255, 0)));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fill(ellipse);
    }
    public static void main(String[] args) {
        Zalivki gd = new Zalivki();
        gd.setTitle("Градиентная заливка");
        gd.setSize(300, 300);
        gd.setResizable(false);
        gd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gd.setVisible(true);
    }
}