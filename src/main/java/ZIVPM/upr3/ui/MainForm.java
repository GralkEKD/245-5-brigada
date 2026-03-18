package ZIVPM.upr3.ui;

import ZIVPM.upr3.AccessMatrix;
import ZIVPM.upr3.UsersList;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;

public class MainForm extends JFrame {
    private JPanel mainPanel;
    private JPanel workingPanel;
    private JTextField usersNumberTextField;
    private JTextField objectsNumberTextField;
    private JLabel usersNumberLabel;
    private JLabel objectsNumberLabel;
    private JButton submitButton;

    public MainForm() {
        setTitle("Матрица доступа");
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        submitButton.addActionListener(e -> onSubmit());
        workingPanel.registerKeyboardAction(
                e -> onSubmit(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        pack();
        setVisible(true);
    }

    private void onSubmit() {
        String usersNumber, objectsNumber;
        usersNumber = usersNumberTextField.getText();
        if (usersNumber.isEmpty())
        {
            Popup.showError(workingPanel, "Введите число пользователей");
            return;
        }
        objectsNumber = objectsNumberTextField.getText();
        if (objectsNumber.isEmpty())
        {
            Popup.showError(workingPanel, "Введите число объектов");
            return;
        }
        createAccessMatrix(Integer.parseInt(usersNumber), Integer.parseInt(objectsNumber));

        SwingUtilities.invokeLater(IdentificationForm::new);
        dispose();
    }

    private void createAccessMatrix(int usersNumber, int objectsNumber) {
        UsersList usersList = new UsersList();
        for (int i = 0; i < usersNumber; i++) {
            new UserAddDialog(this, usersList, i);
        }
        long seed = System.currentTimeMillis();
        seed = (seed >>> 48) ^ seed;
        Random random = new Random(seed);
        int adminId = random.nextInt(usersNumber);
        usersList.getUserHashMap().get(adminId).setAdmin(true);
        try {
            usersList.saveToJson("src/main/java/ZIVPM/upr3/resources/usersInserted.json");
        } catch (IOException e) {
            Popup.showError(workingPanel, "Ошибка при записи файла пользователей");
            System.exit(-1);
        }
        AccessMatrix matrix = new AccessMatrix(usersList.getUserHashMap(), objectsNumber);
        try {
            matrix.saveToJson("src/main/java/ZIVPM/upr3/resources/accessMatrix.json");
        } catch (IOException e) {
            Popup.showError(workingPanel, "Ошибка при записи файла матрицы");
            System.exit(-2);
        }
    }
}
