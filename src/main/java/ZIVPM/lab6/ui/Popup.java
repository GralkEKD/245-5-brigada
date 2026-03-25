package ZIVPM.lab6.ui;

import javax.swing.*;
import java.awt.*;

public class Popup {

    public static void showInfo(JPanel parent, String message) {

        JDialog dialog = new JDialog(new Frame(), true); // modal
        dialog.setUndecorated(true);                // без верхней панели
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        // Основная панель
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
        panel.setBackground(Color.WHITE);

        // Текст ошибки
        JLabel label = new JLabel(message, SwingConstants.CENTER);
        label.setForeground(Color.GREEN);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(label, BorderLayout.CENTER);

        // Кнопка OK
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> dialog.dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(okButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setContentPane(panel);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }

    public static void showError(JPanel parent, String message) {

        JDialog dialog = new JDialog(new Frame(), true); // modal
        dialog.setUndecorated(true);                // без верхней панели
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        // Основная панель
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        panel.setBackground(Color.WHITE);

        // Текст ошибки
        JLabel label = new JLabel(message, SwingConstants.CENTER);
        label.setForeground(Color.RED);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(label, BorderLayout.CENTER);

        // Кнопка OK
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> dialog.dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(okButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setContentPane(panel);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }
}
