package ZIVPM.upr3.ui;

import ZIVPM.upr3.User;
import ZIVPM.upr3.UsersList;

import javax.swing.*;
import java.awt.event.*;

public class UserAddDialog extends JDialog {
    private JPanel userAddDialogPane;
    private JButton buttonOK;
    private JLabel usernameText;
    private JTextField usernameTextField;
    private final UsersList usersList;

    public UserAddDialog(JFrame parent, UsersList usersList, int userNum) {
        super(parent, true);
        this.usersList = usersList;
        setContentPane(userAddDialogPane);
        getRootPane().setDefaultButton(buttonOK);
        setUndecorated(true);
        setLocationRelativeTo(parent);
        setSize(300, 150);
        usernameText.setText(usernameText.getText() + " " + userNum);

        buttonOK.addActionListener(e -> onOK(userNum));

        userAddDialogPane.registerKeyboardAction(
                e -> onOK(userNum),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        setVisible(true);
    }

    private void onOK(int userNum) {
        String textBoxText = this.usernameTextField.getText();
        if (textBoxText.isEmpty()) {
            Popup.showError(userAddDialogPane, "Введите имя пользователя");
        } else {
            usersList.getUserHashMap().put(userNum, new User(textBoxText, false));
            dispose();
        }
    }
}
