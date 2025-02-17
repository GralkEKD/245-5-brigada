package KGSAPR.lab3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener, KeyListener {
    private JTextField display;
    private String currentOperator = "";
    private double firstOperand = 0;
    private boolean isNewInput = true;

    public Calculator() {
        // Настройка окна
        setTitle("Калькулятор");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Поле отображения
        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // Панель с кнопками
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);

        // Добавление KeyListener к основному окну
        display.addKeyListener(this);
        this.addKeyListener(this);
        setFocusable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        handleInput(command);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();

        // Проверка ввода с клавиатуры
        if (Character.isDigit(key)) {
            handleInput(String.valueOf(key));
        } else if ("+-*/".indexOf(key) != -1) {
            handleInput(String.valueOf(key));
        } else if (key == KeyEvent.VK_ENTER) {
            handleInput("=");
        } else if (key == KeyEvent.VK_BACK_SPACE) {
            handleInput("C");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Ничего не делаем
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Ничего не делаем
    }

    private void handleInput(String input) {
        if ("0123456789".contains(input)) {
            if (isNewInput) {
                display.setText(input);
                isNewInput = false;
            } else {
                display.setText(display.getText() + input);
            }
        } else if ("/-*+".contains(input)) {
            currentOperator = input;
            firstOperand = Double.parseDouble(display.getText());
            isNewInput = true;
        } else if ("=".equals(input)) {
            double secondOperand = Double.parseDouble(display.getText());
            double result = 0;

            switch (currentOperator) {
                case "+": result = firstOperand + secondOperand; break;
                case "-": result = firstOperand - secondOperand; break;
                case "*": result = firstOperand * secondOperand; break;
                case "/":
                    if (secondOperand != 0) {
                        result = firstOperand / secondOperand;
                    } else {
                        JOptionPane.showMessageDialog(this, "Деление на ноль невозможно");
                        isNewInput = true;
                        return;
                    }
                    break;
            }
            display.setText(String.valueOf(result));
            isNewInput = true;
        } else if ("C".equals(input)) {
            display.setText("0");
            currentOperator = "";
            firstOperand = 0;
            isNewInput = true;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculator calculator = new Calculator();
            calculator.setVisible(true);
        });
    }
}