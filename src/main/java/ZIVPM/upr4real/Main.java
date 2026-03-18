package ZIVPM.upr4real;

import ZIVPM.upr4real.ui.RegistrationForm;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(RegistrationForm::new);
    }
}
