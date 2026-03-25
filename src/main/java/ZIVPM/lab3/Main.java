package ZIVPM.lab3;

import ZIVPM.lab3.ui.FeistelNetworkForm;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FeistelNetworkForm::new);
    }
}
