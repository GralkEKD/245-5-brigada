package ZIVPM.upr4real.ui;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class OTPGeneratorForm extends JFrame {
    private JTextField timeTextField;
    private JTextField speedTextField;
    private JTextField exponentTextField;
    private JTextPane OtpTextPane;
    private JButton generateButton;
    private JComboBox<String> timeComboBox;
    private JComboBox<String> speedComboBox;
    private JPanel mainPanel;

    public OTPGeneratorForm() {
        setTitle("Генерация одноразового пароля");
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        generateButton.addActionListener(e -> onGenerate());
        mainPanel.registerKeyboardAction(
                e -> onGenerate(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        pack();
        setVisible(true);

        String[] times = {"Дни", "Недели", "Месяцы"};
        DefaultComboBoxModel<String> timeComboBoxModel = new DefaultComboBoxModel<>(times);
        String[] speeds = {"В минуту", "В час", "В день"};
        DefaultComboBoxModel<String> speedComboBoxModel = new DefaultComboBoxModel<>(speeds);
        timeComboBox.setModel(timeComboBoxModel);
        speedComboBox.setModel(speedComboBoxModel);
    }

    private void onGenerate() {
        double time = Double.parseDouble(timeTextField.getText());
        double speed = Double.parseDouble(speedTextField.getText());

        int timeMeasure = timeComboBox.getSelectedIndex(),
                speedMeasure = speedComboBox.getSelectedIndex();
        if (timeMeasure == 1) time *= 7;
        else if (timeMeasure == 2) time *= 30;

        if (speedMeasure == 1) speed *= 60;
        else if (speedMeasure == 2) speed *= (60 * 24);

        int exponent = Math.abs(Integer.parseInt(exponentTextField.getText()));

        OTPGenerator otpGenerator = new OTPGenerator(time, speed, exponent);

        String otp = otpGenerator.generateOTP();
        OtpTextPane.setText(otp);
    }
}
