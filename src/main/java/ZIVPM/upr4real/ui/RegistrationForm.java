package ZIVPM.upr4real.ui;

import ZIVPM.upr4real.Registration;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class RegistrationForm extends JFrame {
    private JTextField userNameTextField;
    private JPasswordField passwordField;
    private JButton submitButton;
    private JPanel mainPanel;
    private JPasswordField confirmPasswordField;

    public RegistrationForm() {
        setTitle("Регистрация");
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        submitButton.addActionListener(e -> onSubmit());
        mainPanel.registerKeyboardAction(
                e -> onSubmit(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        pack();
        setVisible(true);
    }

    private void onSubmit() {
        if (userNameTextField.getText().isBlank()) {
            Popup.showError(mainPanel, "Введите имя пользователя");
            return;
        }
        if (new String(passwordField.getPassword()).isBlank()) {
            Popup.showError(mainPanel, "Введите пароль");
            return;
        }
        if (!Arrays.equals(passwordField.getPassword(), confirmPasswordField.getPassword())) {
            Popup.showError(mainPanel, "Пароли не совпадают");
            return;
        }
        String userName = userNameTextField.getText(),
            password = new String(passwordField.getPassword());
        File file = new File("src/main/java/ZIVPM/upr4real/resources/passwords.json");
        try {
            Registration.register(userName, password, file);
            SwingUtilities.invokeLater(AuthorizationForm::new);
            dispose();
        } catch (IOException e) {
            Popup.showError(mainPanel, "Ошибка записи файла паролей");
        } catch (RuntimeException e) {
            Popup.showError(mainPanel, e.getMessage());
        }
    }
}
