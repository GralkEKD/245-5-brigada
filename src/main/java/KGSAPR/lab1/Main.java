package KGSAPR.lab1;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Hello, Window!");
        jFrame.setSize(1280, 720);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Button Button = new Button("Hello, Button!");
        JPanel jPanel = new JPanel();
        jPanel.add(Button);
        jFrame.add(jPanel);
        jFrame.setVisible(true);
    }

    private static class Button extends JButton {
        private Button(String text) {super(text);}

        @Override
        public void paint(Graphics graphics) {
            super.paint(graphics);
            graphics.setColor(new Color(255, 0, 0));
            graphics.fillOval(5, 5, 10, 10);
        }
    }
}
