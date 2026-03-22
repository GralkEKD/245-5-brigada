package ZIVPM.lab1.ui;

import ZIVPM.lab1.SubstitutionCipher;

import javax.swing.*;

public class SubstitutionCipherForm extends JFrame {
    private JPanel mainPanel;
    private JTextPane messageTextPane;
    private JTextPane cipherTextPane;
    private JButton encryptButton;
    private JButton decryptButton;

    public SubstitutionCipherForm() {
        setTitle("Авторизация");
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        encryptButton.addActionListener(e -> onEncrypt());
        decryptButton.addActionListener(e -> onDecrypt());

        pack();
        setVisible(true);
    }

    private void onEncrypt() {
        try {
            String message = messageTextPane.getText();
            cipherTextPane.setText(SubstitutionCipher.encrypt(message));
        } catch (IllegalArgumentException e) {
            Popup.showError(mainPanel, e.getMessage());
        }
    }

    private void onDecrypt() {
        try {
            String cipher = cipherTextPane.getText();
            messageTextPane.setText(SubstitutionCipher.decrypt(cipher));
        } catch (IllegalArgumentException e) {
            Popup.showError(mainPanel, e.getMessage());
        }
    }
}
