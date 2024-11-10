package KGSAPR.lab3;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Zabava implements ChangeListener {

    private final JLabel label;

    public Zabava(JLabel label) {
        this.label = label;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        label.setText(String.format("%d", ( (JSlider) e.getSource() ).getValue()));
    }
}
