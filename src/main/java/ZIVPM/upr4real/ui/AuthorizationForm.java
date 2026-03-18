package ZIVPM.upr4real.ui;

import ZIVPM.upr4real.Registration;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class AuthorizationForm extends JFrame {
    private JTextField userNameTextField;
    private JPasswordField passwordField;
    private JButton submitButton;
    private JButton backToRegistrationButton;
    private JPanel mainPanel;

    public AuthorizationForm() {
        setTitle("Авторизация");
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        submitButton.addActionListener(e -> onSubmit());
        mainPanel.registerKeyboardAction(
                e -> onSubmit(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        backToRegistrationButton.addActionListener(e -> onBackToRegistration());

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
        String userName = userNameTextField.getText(),
                password = new String(passwordField.getPassword());
        File file = new File("src/main/java/ZIVPM/upr4real/resources/passwords.json");
        try {
             if (Registration.authorize(userName, password, file))
                 Popup.showInfo(mainPanel, "Авторизация успешна!");
             else Popup.showError(mainPanel, "Неверные имя пользователя или пароль");
        } catch (IOException e) {
            Popup.showError(mainPanel, "Ошибка записи файла паролей");
        }
    }

    private void onBackToRegistration() {
        SwingUtilities.invokeLater(RegistrationForm::new);
        dispose();
    }
}
