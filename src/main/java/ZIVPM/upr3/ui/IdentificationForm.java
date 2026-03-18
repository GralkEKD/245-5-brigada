package ZIVPM.upr3.ui;

import ZIVPM.upr3.UsersList;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class IdentificationForm extends JFrame {
    private JPanel identificationFormPanel;
    private JTextField usernameTextField;
    private JButton submitButton;
    private JLabel enterUsernameLabel;
    private JButton exitButton;

    public IdentificationForm() {
        setTitle("Идентификация");
        setContentPane(identificationFormPanel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        submitButton.addActionListener(e -> onSubmit());
        exitButton.addActionListener(e -> dispose());
        identificationFormPanel.registerKeyboardAction(
                e -> onSubmit(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        pack();
        setVisible(true);
    }

    private void onSubmit() {
        String username = usernameTextField.getText();
        if (username.isEmpty())
        {
            Popup.showError(identificationFormPanel, "Введите имя пользователя");
            return;
        }

        UsersList usersList = new UsersList();
        try {
            usersList.readFromJson("src/main/java/ZIVPM/upr3/resources/usersInserted.json");
        } catch (IOException e) {
            Popup.showError(identificationFormPanel, "Ошибка при чтении файла пользователей");
            System.exit(-4);
        }
        int userId = usersList
                .getUserHashMap()
                .keySet()
                .stream()
                .filter(key -> usersList.getUserHashMap().get(key).getUsername().equals(username))
                .findFirst()
                .orElse(-1);
        if (userId == -1)
        {
            Popup.showError(identificationFormPanel, "Неверное имя пользователя");
            return;
        }
        if (usersList.getUserHashMap().get(userId).isAdmin())
            SwingUtilities.invokeLater(() -> new AdminAccessMatrixForm(userId));
        else
            SwingUtilities.invokeLater(() -> new UserAccessMatrixForm(userId));

        dispose();
    }
}
