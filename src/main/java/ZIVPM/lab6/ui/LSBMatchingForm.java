package ZIVPM.lab6.ui;

import ZIVPM.lab6.LSBMatching;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class LSBMatchingForm extends JFrame {
    private JPanel mainPanel;
    private JTextPane messageTextPane;
    private JButton encryptButton;
    private JButton decryptButton;
    private JFileChooser fileChooser;

    public LSBMatchingForm() {
        setTitle("Стеганография LSB-Matching");
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        encryptButton.addActionListener(e -> onEncrypt());
        decryptButton.addActionListener(e -> onDecrypt());

        pack();
        setVisible(true);
    }

    private void onEncrypt() {
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setFileFilter(new FileNameExtensionFilter("PNG Images", "png"));
        fileChooser.showOpenDialog(this);
        File file = fileChooser.getSelectedFile();
        try {
            BufferedImage image = ImageIO.read(file);
            String message = messageTextPane.getText();
            BufferedImage stegoImage = LSBMatching.encrypt(image, message);
            File newFile = new File(file.getParent() + "/new file.png");
            ImageIO.write(stegoImage, "png", newFile);
        } catch (IOException e) {
            Popup.showError(mainPanel, "Ошибка c файловым вводом/выводом");
        }
    }

    private void onDecrypt() {
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setFileFilter(new FileNameExtensionFilter("PNG Images", "png"));
        fileChooser.showOpenDialog(this);
        File file = fileChooser.getSelectedFile();
        try {
            BufferedImage stegoimage = ImageIO.read(file);
            String message = LSBMatching.decrypt(stegoimage);
            if (Objects.isNull(message)) Popup.showError(mainPanel, "Неверный формат");
            else messageTextPane.setText(message);
        } catch (IOException e) {
            Popup.showError(mainPanel, "Ошибка c файловым вводом/выводом");
        }
    }
}
