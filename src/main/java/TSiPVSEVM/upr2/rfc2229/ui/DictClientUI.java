package TSiPVSEVM.upr2.rfc2229.ui;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class DictClientUI {

    private static String query;

    private static JTextArea resultArea;

    public static String getQuery() {
        return query;
    }

    public static void setDefinition(String definition) {
        DictClientUI.resultArea.setText(definition);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DictClientUI::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("DICT Protocol Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 450);
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
            if (wordField.getText().isBlank()) return;
            StringBuilder stringBuilder = new StringBuilder();
            switch (strategyBox.getSelectedIndex()) {
                case 0: {
                    stringBuilder.append("DEFINE").append(' ');
                    break;
                }

                case 1: {
                    stringBuilder.append("MATCH").append(' ');
                    break;
                }

                case 2: {
                    stringBuilder.append("MATCH PREFIX").append(' ');
                    break;
                }

                case 3: {
                    stringBuilder.append("MATCH SUBSTRING").append(' ');
                    break;
                }

                case 4: {
                    stringBuilder.append("MATCH REGEX").append(' ');
                    break;
                }

                default: throw new IllegalArgumentException("Invalid Strategy selected");
            }
            if (databaseBox.getSelectedIndex() != 0) stringBuilder.append(
                    Objects.requireNonNull(databaseBox.getSelectedItem())
                            .toString()
                            .replaceAll(" ", "-")
            ).append(' ');
            stringBuilder.append(wordField.getText()).append(" ");
            query = stringBuilder.toString().toLowerCase();
            System.out.println(query);
        });

        gbc.gridx = 1;
        JButton resetButton = new JButton("Сброс");
        frame.add(resetButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        frame.add(new JLabel("Определение слова:"), gbc);

        gbc.gridy = 8;
        resultArea = new JTextArea(5, 30);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        frame.add(scrollPane, gbc);

        // Обработчик кнопки "Сброс"
        resetButton.addActionListener(e -> {
            wordField.setText("");
            strategyBox.setSelectedIndex(0);
            databaseBox.setSelectedIndex(0);
            resultArea.setText("");
        });

        frame.setVisible(true);
    }
}
