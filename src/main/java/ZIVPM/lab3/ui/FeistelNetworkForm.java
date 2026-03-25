package ZIVPM.lab3.ui;

import ZIVPM.lab3.FeistelNetwork;

import javax.swing.*;

public class FeistelNetworkForm extends JFrame {
    private JPanel mainPanel;
    private JTextArea messageTextArea;
    private JTextArea cipherTextArea;
    private JTextField keyTextField;
    private JButton encryptButton;
    private JButton decryptButton;
    private JTextField roundsTextField;

    public FeistelNetworkForm() {
        setTitle("Сети Фейштеля");
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
            String message = messageTextArea.getText();
            long key = Long.parseLong(keyTextField.getText());
            int rounds = Integer.parseInt(roundsTextField.getText());
            String cipher = FeistelNetwork.encrypt(message, key, rounds);
            cipherTextArea.setText(cipher);
        } catch (NumberFormatException e) {
            Popup.showError(mainPanel, "Неверный формат ключа или числа раундов");
        } catch (NullPointerException e) {
            Popup.showError(mainPanel, "Введите сообщение");
        }
    }

    private void onDecrypt() {
        try {
            String cipher = cipherTextArea.getText();
            long key = Long.parseLong(keyTextField.getText());
            int rounds = Integer.parseInt(roundsTextField.getText());
            String message = FeistelNetwork.decrypt(cipher, key, rounds);
            messageTextArea.setText(message);
        } catch (NumberFormatException e) {
            Popup.showError(mainPanel, "Неверный формат ключа или числа раундов");
        } catch (NullPointerException e) {
            Popup.showError(mainPanel, "Введите сообщение");
        }
    }

}
