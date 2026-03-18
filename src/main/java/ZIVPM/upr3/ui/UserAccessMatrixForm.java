package ZIVPM.upr3.ui;

import ZIVPM.upr3.AccessMatrix;
import ZIVPM.upr3.AccessRight;
import ZIVPM.upr3.UsersList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.Set;

public class UserAccessMatrixForm extends JFrame{
    private JPanel accessMatrixPanel;
    private JTable accessMatrixTable;
    private JComboBox<Integer> readFileComboBox;
    private JComboBox<Integer> writeFileComboBox;
    private JComboBox<Integer> grantComboBox;
    private JButton readButton;
    private JButton writeButton;
    private JButton grantButton;
    private JComboBox<String> userComboBox;
    private JComboBox<AccessRight> rightComboBox;
    private JLabel welcomeLabel;
    private JButton returnButton;
    private AccessMatrix accessMatrix;
    private UsersList usersList = new UsersList();
    private final int userID;

    public UserAccessMatrixForm(int userID) {
        this.userID = userID;
        setTitle("Матрица доступа");
        setContentPane(accessMatrixPanel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        fillContentPane(userID);
        readButton.addActionListener(e -> onRead());
        writeButton.addActionListener(e-> onWrite());
        grantButton.addActionListener(e -> onGrant());
        returnButton.addActionListener(e -> onReturn());

        pack();
        setVisible(true);
    }

    private void fillContentPane(int userID) {
        accessMatrix = null;
        try {
            accessMatrix = AccessMatrix.readFromJson("src/main/java/ZIVPM/upr3/resources/accessMatrix.json");
        } catch (IOException e) {
            Popup.showError(accessMatrixPanel, e.getMessage());
            System.exit(-3);
        }
        try {
            usersList.readFromJson("src/main/java/ZIVPM/upr3/resources/usersInserted.json");
        } catch (IOException e) {
            Popup.showError(accessMatrixPanel, "Ошибка при чтении файла пользователей");
            System.exit(-4);
        }
        welcomeLabel.setText("Добро пожаловать, " + usersList.getUserHashMap().get(userID).getUsername());
        createTable();
        fillComboBoxes();
    }

    private void createTable() {
        Set<AccessRight>[][] matrix = accessMatrix.getAccessMatrix();
        int rows = matrix.length;
        int cols = matrix[0].length + 1;

        String[] columnNames = new String[cols];
        columnNames[0] = "Пользователь";
        for (int c = 1; c < cols; c++) {
            columnNames[c] = "Файл " + (c - 1);
        }

        String[][] data = new String[rows][cols];

        for (int r = 0; r < rows; r++) {
            data[r][0] = usersList.getUserHashMap().get(r).getUsername();
            for (int c = 1; c < cols; c++) {
                Set<AccessRight> cell = matrix[r][c - 1];
                data[r][c] = accessMatrix.rightsAsString(cell);
            }
        }
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        accessMatrixTable.setModel(model);
    }

    private void fillComboBoxes() {
        Integer[] files = new Integer[accessMatrix.getAccessMatrix()[0].length];
        String[] users = new String[accessMatrix.getAccessMatrix().length];
        AccessRight[] rights = {AccessRight.READ, AccessRight.WRITE};
        for (int i = 0; i < accessMatrix.getAccessMatrix().length; i++) {
            users[i] = usersList.getUserHashMap().get(i).getUsername();
        }
        for (int i = 0; i < accessMatrix.getAccessMatrix()[0].length; i++) {
            files[i] = i;
        }
        DefaultComboBoxModel<Integer> readModel = new DefaultComboBoxModel<>(files);
        DefaultComboBoxModel<Integer> writeModel = new DefaultComboBoxModel<>(files);
        DefaultComboBoxModel<Integer> grantModel = new DefaultComboBoxModel<>(files);
        DefaultComboBoxModel<String> userComboBoxModel = new DefaultComboBoxModel<>(users);
        DefaultComboBoxModel<AccessRight> rightComboBoxModel = new DefaultComboBoxModel<>(rights);
        readFileComboBox.setModel(readModel);
        writeFileComboBox.setModel(writeModel);
        grantComboBox.setModel(grantModel);
        userComboBox.setModel(userComboBoxModel);
        rightComboBox.setModel(rightComboBoxModel);
    }

    private void onRead() {
        Integer file = readFileComboBox.getItemAt(readFileComboBox.getSelectedIndex());
        if (this.accessMatrix.getAccessMatrix()[userID][file].contains(AccessRight.READ))
            Popup.showInfo(this.accessMatrixPanel, "Успешно!");
        else
            Popup.showError(this.accessMatrixPanel, "У вас недостаточно прав");
    }

    private void onWrite() {
        Integer file = readFileComboBox.getItemAt(readFileComboBox.getSelectedIndex());
        if (this.accessMatrix.getAccessMatrix()[userID][file].contains(AccessRight.WRITE))
            Popup.showInfo(this.accessMatrixPanel, "Успешно!");
        else
            Popup.showError(this.accessMatrixPanel, "У вас недостаточно прав");
    }

    private void onGrant() {
        AccessRight accessRight = (AccessRight) rightComboBox.getSelectedItem();
        Integer file = grantComboBox.getItemAt(grantComboBox.getSelectedIndex());
        if (!this.accessMatrix.getAccessMatrix()[userID][file].contains(AccessRight.GRANT) ||
                !this.accessMatrix.getAccessMatrix()[userID][file].contains(accessRight)
        )
            Popup.showError(this.accessMatrixPanel, "У вас недостаточно прав");
        else {
            int userID = userComboBox.getSelectedIndex();
            accessMatrix.getAccessMatrix()[userID][file].add(accessRight);
            accessMatrix.getAccessMatrix()[this.userID][file].remove(accessRight);
            try {
                accessMatrix.saveToJson("src/main/java/ZIVPM/upr3/resources/accessMatrix.json");
                Popup.showInfo(this.accessMatrixPanel, "Успешно!");
                createTable();
            } catch (IOException e) {
                Popup.showError(this.accessMatrixPanel, "Ошибка записи файла матрицы");
                System.exit(-2);
            }
        }
    }

    private void onReturn() {
        SwingUtilities.invokeLater(IdentificationForm::new);
        dispose();
    }
}
