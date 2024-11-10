package KGSAPR.lab3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Smeshnyavka implements ActionListener {
    private final JLabel label;
    private final JFrame frame;

    private int pressCount = 0;

    public Smeshnyavka(JLabel label, JFrame frame) {
        this.label = label;
        this.frame = frame;
    }

    public JLabel getLabel() {return  this.label;}

    @Override
    public void actionPerformed(ActionEvent e) {
        label.setText(String.format("Меня нажали %d раз\n", ++pressCount));
        System.out.printf("%d %d\n", frame.getWidth(), frame.getHeight());
    }
}
