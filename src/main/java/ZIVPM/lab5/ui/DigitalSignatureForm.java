package ZIVPM.lab5.ui;

import ZIVPM.lab5.DigitalSignature;
import ZIVPM.lab5.Key;

import javax.swing.*;

public class DigitalSignatureForm extends JFrame {
    private JPanel mainPanel;
    private JTextPane documentTextPane;
    private JTextPane signatureTextPane;
    private JButton signButton;
    private JButton checkSignatureButton;
    private JLabel signatureValidityLabel;
    private Key key;

    public DigitalSignatureForm() {
        super();
        setTitle("ЭЦП");
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        signButton.addActionListener(e -> onSign());
        checkSignatureButton.addActionListener(e -> onCheckSignature());
        pack();
        setVisible(true);
    }

    private void onSign() {
        String document = documentTextPane.getText();
        key = DigitalSignature.generateKey();
        String signature = DigitalSignature.sign(document, key).toString();
        signatureTextPane.setText(signature);
    }

    private void onCheckSignature() {
        String document = documentTextPane.getText();
        String signature = signatureTextPane.getText();

        if (DigitalSignature.checkSignature(document, signature, key))
            signatureValidityLabel.setText("Подпись валидна");
        else
            signatureValidityLabel.setText("Подпись невалидна!");

    }

}
