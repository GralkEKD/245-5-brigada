package KGSAPR.lab2;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;

public class Lab2 extends JFrame {
    @Override
    public void paint(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;
        g2.setColor(Color.BLUE);
        g2.setStroke(new BasicStroke(
                3.0F,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_ROUND,
                10.0F,
                new float[]{24.0F, 12.0F},
                0)
        );
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawOval(30, 85, 1200, 600);
        g2.setStroke(new BasicStroke(
                3.0F,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_ROUND));
        CubicCurve2D curve2D = new CubicCurve2D.Double(400, 300, 800, 800, 600, 90, 900,300);
        g2.draw(curve2D);
        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke(
                24.0F,
                BasicStroke.CAP_SQUARE,
                BasicStroke.JOIN_MITER
        ));
        GeneralPath gp = new GeneralPath();
        gp.moveTo(200, 100);
        gp.lineTo(450, 100);
        gp.lineTo(450, 350);
        gp.lineTo(700, 350);
        g2.draw(gp);
        g2.setStroke(new BasicStroke(
                12.0F,
                BasicStroke.CAP_SQUARE,
                BasicStroke.JOIN_ROUND
        ));
        g2.setColor(new Color(255, 145, 0));
        Ellipse2D circle = new Ellipse2D.Double(750, 300, 200, 200);
        g2.draw(circle);
        circle = new Ellipse2D.Double(800, 350, 100, 100);
        g2.setColor(Color.BLUE);
        g2.setStroke(new BasicStroke(
                3.0F,
                BasicStroke.CAP_SQUARE,
                BasicStroke.JOIN_ROUND
        ));
        g2.draw(circle);
        g2.setPaint(new GradientPaint(850F, 350F, new Color(187, 0, 0), 850F, 450F, new Color(255, 88, 0)));
        g2.fill(circle);
    }
    public static void main(String[] args) {
        Lab2 lab2 = new Lab2();
        lab2.setSize(1280, 720);
        lab2.setTitle("Вариант 5");
        lab2.setResizable(false);
        lab2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lab2.setVisible(true);
    }
}
