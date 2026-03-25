package ZIVPM.lab4.ui;

import ZIVPM.lab4.Key;
import ZIVPM.lab4.RSAMath;

import javax.swing.*;
import java.math.BigInteger;

public class RSAEncryptDecryptForm extends JFrame {
    private JTextField pTextField;
    private JTextField qTextFiled;
    private JButton encryptButton;
    private JButton decryptButton;
    private JTextPane messageTextPane;
    private JTextPane cipherTextPane;
    private JTextField eTextField;
    private JTextField dTextField;
    private JPanel mainPanel;
    private Key key;

    public RSAEncryptDecryptForm() {
        super();
        setTitle("RSA");
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        encryptButton.addActionListener(e -> onEncrypt());
        decryptButton.addActionListener(e -> onDecrypt());
        pack();
        setVisible(true);
    }

    private void onEncrypt() {
        String p = pTextField.getText(),
                q = qTextFiled.getText();
        String e = eTextField.getText(),
                d = dTextField.getText();
        if (d.isBlank() || e.isBlank())  {
            key = RSAMath.generateKey(
                    Long.parseLong(p),
                    Long.parseLong(q)
            );
            eTextField.setText(key.e().toString());
            dTextField.setText(key.d().toString());
        } else key = RSAMath.generateKey(
                Long.parseLong(p),
                Long.parseLong(q),
                Long.parseLong(e),
                Long.parseLong(d)
        );
        String message = messageTextPane.getText();
        String cipher = RSAMath.encrypt(this.key, message);
        cipherTextPane.setText(cipher);
    }

    private void onDecrypt() {
        BigInteger p = new BigInteger(pTextField.getText()),
                q = new BigInteger(qTextFiled.getText()),
                e = new BigInteger(eTextField.getText()),
                d = new BigInteger(dTextField.getText());
        key = new Key(e, d, p.multiply(q));
        String cipher = cipherTextPane.getText();
        String message = RSAMath.decrypt(this.key, cipher);
        messageTextPane.setText(message);
    }
}
