package ZIVPM.lab5;

import ZIVPM.lab5.ui.DigitalSignatureForm;

import javax.swing.*;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
//        Key key = DigitalSignature.generateKey();
//        System.out.println(key);
//        String message = "Hallo!!:3!! HAIIIII :333333";
//        Map<String, Signature> messageSignature = Map.of(message, DigitalSignature.sign(message, key));
//        System.out.println(DigitalSignature.checkSignature(
//                message,
//                messageSignature.get(message).toString(),
//                key)
//        );
        SwingUtilities.invokeLater(DigitalSignatureForm::new);
    }
}
