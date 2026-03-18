package TSiPVSEVM.upr2.rfc2229.client.ui;

import TSiPVSEVM.upr2.rfc2229.client.handler.ResponseHandler;

import javax.swing.*;
import java.awt.*;

public class DictClientUI {

    private String query;
    private String[] dataBases;
    private String[] strategies;
    private final ResponseHandler responseHandler;

    private final JTextArea resultArea = new JTextArea(17, 62);

    public void setDefinition(String definition) {
        resultArea.setText(definition);
    }

    public DictClientUI(ResponseHandler responseHandler) {
        this.responseHandler = responseHandler;
        try {
            responseHandler.post("show db\n");
            responseHandler.parseResponse();
            String[] dataBasesOptions = responseHandler.getComment();
            dataBases = responseHandler.getStatus();
            responseHandler.parseResponse();

            responseHandler.post("show strat\n");
            responseHandler.parseResponse();
            String[] strategiesOptions = new String[responseHandler.getComment().length + 1];
            strategiesOptions[0] = "Define word";
            for (int i = 1; i <= strategiesOptions.length - 1; i++) {
                strategiesOptions[i] = responseHandler.getComment()[i - 1];
            }
            strategies = new String[strategiesOptions.length];
            strategies[0] = "";
            for (int i = 1; i < strategies.length; i++) {
                strategies[i] = responseHandler.getStatus()[i - 1];
            }

            responseHandler.parseResponse();

            createAndShowGUI(dataBasesOptions, strategiesOptions);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public void createAndShowGUI(String[] dataBasesOptions, String[] strategiesOptions) {
        JFrame frame = new JFrame("DICT Protocol Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
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
        JComboBox<String> strategyBox = new JComboBox<>(strategiesOptions);
        frame.add(strategyBox, gbc);

        // Выпадающий список баз данных
        gbc.gridy = 4;
        frame.add(new JLabel("Выберите базу данных:"), gbc);

        gbc.gridy = 5;
        JComboBox<String> databaseBox = new JComboBox<>(dataBasesOptions);
        frame.add(databaseBox, gbc);

        // Кнопки "Запрос" и "Сброс"
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        JButton queryButton = new JButton("Запрос");
        frame.add(queryButton, gbc);

        queryButton.addActionListener(l -> {
            if (wordField.getText().isBlank()) return;
            if (strategyBox.getSelectedIndex() == 0) {
                query = "define " +
                    dataBases[strategyBox.getSelectedIndex()] +
                    " \"" + wordField.getText().replace(' ', '-') + "\"\r\n";
            } else {
                query = "match " +
                    dataBases[databaseBox.getSelectedIndex()] + " " +
                    strategies[strategyBox.getSelectedIndex()] +
                    " \"" + wordField.getText().replace(' ', '-') + "\"\r\n";
            }
            try {
                responseHandler.post(query.toLowerCase());
                responseHandler.parseResponse();
                setDefinition(responseHandler.getComment()[0]);
                responseHandler.parseResponse();
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        });

        gbc.gridx = 1;
        JButton resetButton = new JButton("Сброс");
        frame.add(resetButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        frame.add(new JLabel("Определение слова:"), gbc);

        gbc.gridy = 8;
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
