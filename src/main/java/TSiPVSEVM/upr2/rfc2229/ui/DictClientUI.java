package TSiPVSEVM.upr2.rfc2229.ui;

import javax.swing.*;
import java.awt.*;

public class DictClientUI {

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("DICT Protocol Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Поле ввода слова
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(new JLabel("Введите слово:"), gbc);

        gbc.gridy = 1;
        JTextField wordField = new JTextField();
        frame.add(wordField, gbc);

        // Выпадающий список стратегий поиска
        gbc.gridy = 2;
        frame.add(new JLabel("Выберите стратегию поиска:"), gbc);

        gbc.gridy = 3;
        String[] strategies = {"Определить слово",
                "Сравнить целиком",
                "Сравнить по префиксу",
                "Сравнить по подстроке",
                "Сравнить по регулярному выражению"};
        JComboBox<String> strategyBox = new JComboBox<>(strategies);
        frame.add(strategyBox, gbc);

        // Выпадающий список баз данных
        gbc.gridy = 4;
        frame.add(new JLabel("Выберите базу данных:"), gbc);

        gbc.gridy = 5;
        String[] databases = {"--Выбрать базу данных--",
                "Italian Brainrot",
                "Anime Characters",
                "Pokedex"};
        JComboBox<String> databaseBox = new JComboBox<>(databases);
        frame.add(databaseBox, gbc);

        // Кнопки "Запрос" и "Сброс"
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        JButton queryButton = new JButton("Запрос");
        frame.add(queryButton, gbc);
        queryButton.addActionListener(l -> {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(wordField.getText()).append(" ");

        });

        gbc.gridx = 1;
        JButton resetButton = new JButton("Сброс");
        frame.add(resetButton, gbc);

        // Обработчик кнопки "Сброс"
        resetButton.addActionListener(e -> {
            wordField.setText("");
            strategyBox.setSelectedIndex(0);
            databaseBox.setSelectedIndex(0);
        });

        frame.setVisible(true);
    }
}
