package KGSAPR.lab1;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class Lab1 extends JFrame {
    @Serial
    private static final long serialVersionUID = 1L;
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(new Color(255,0,0));
        g.drawOval(5,70,590,200);
        g.fillPolygon(new int[]{530,540,560,570,560,540}, new int[]{175,155,155,175,195,195}, 6);

        Color color2 = new Color(208, 0, 255);
        g.setColor(color2);
        for (int i=205; i>120; i=i-20){
            g.fillPolygon(new int[]{245,280,315}, new int[]{i,i-45,i}, 3);
        }
        g.setColor(new Color(0,0,0));
        g.drawRoundRect(200,140,40,30,15,15);
        g.setColor(new Color(0, 0, 255));
        g.fillRoundRect(155,140,40,30,15,15);
        g.fillRoundRect(200,175,40,30,15,15);
        g.setColor(new Color(0,0,0));
        g.drawRoundRect(155,175,40,30,15,15);
        g.drawRoundRect(180,105,40,30,15,15);
        g.setFont(new Font("Arial", Font.BOLD, 28));
        g.drawString("ЛЕС", 350,200);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Lab1 gd = new Lab1();
            // Определяем заголовок окна
            gd.setTitle("Вариант 5");
            // Определяем размер окна
            gd.setSize(600, 300);
            // Запрещаем пользователю изменять размеры окна
            gd.setResizable(false);
            // Определяем, что при закрытии окна заканчивается работа
            // программы
            gd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // Делаем окно видимым
            gd.setVisible(true);
        });
    }
}
