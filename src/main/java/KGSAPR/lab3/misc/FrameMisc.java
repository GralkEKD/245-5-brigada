package KGSAPR.lab3.misc;

import javax.swing.*;

public class FrameMisc {

    FrameMisc() {
        JFrame mainFrame = new JFrame("Фрейм с компонентами");



        mainFrame.setResizable(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1280, 720);
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FrameMisc::new);
    }
}
