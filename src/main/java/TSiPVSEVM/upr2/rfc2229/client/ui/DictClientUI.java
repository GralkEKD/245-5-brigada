package TSiPVSEVM.upr2.rfc2229.client.ui;

import TSiPVSEVM.upr2.rfc2229.client.handler.ResponseHandler;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class DictClientUI {

    private String query;
    private final ResponseHandler responseHandler;

    private final JTextArea resultArea = new JTextArea(17, 62);

    public String getQuery() {
        return query;
    }

    public void setDefinition(String definition) {
        resultArea.setText(definition);
    }

    public DictClientUI(ResponseHandler responseHandler) {
        this.responseHandler = responseHandler;
        try {
            responseHandler.post("show db\n");
            responseHandler.parseResponse();
            String[] dataBases = responseHandler.getComment();
            responseHandler.parseResponse();

            responseHandler.post("show strat\n");
            responseHandler.parseResponse();
            String[] strategies = responseHandler.getComment();
            responseHandler.parseResponse();

            createAndShowGUI(dataBases, strategies);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public void createAndShowGUI(String[] dataBases, String[] strategies) {
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
        JComboBox<String> strategyBox = new JComboBox<>(strategies);
        frame.add(strategyBox, gbc);

        // Выпадающий список баз данных
        gbc.gridy = 4;
        frame.add(new JLabel("Выберите базу данных:"), gbc);

        gbc.gridy = 5;
        JComboBox<String> databaseBox = new JComboBox<>(dataBases);
        frame.add(databaseBox, gbc);

        // Кнопки "Запрос" и "Сброс"
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        JButton queryButton = new JButton("Запрос");
        frame.add(queryButton, gbc);

        queryButton.addActionListener(l -> {
            if (wordField.getText().isBlank()) return;
            String sb = responseHandler.getStatus()[strategyBox.getSelectedIndex()] + " " +
                    responseHandler.getStatus()[databaseBox.getSelectedIndex()] + " " +
                    wordField.getText() + "\r\n";
            query = sb.toLowerCase();
            try {
                responseHandler.post(query);
                responseHandler.parseResponse();
                resultArea.setText(responseHandler.getStatus()[0]);
                responseHandler.parseResponse();
                System.out.println("Request sent: " + query);
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
