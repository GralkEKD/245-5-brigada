package KGSAPR.lab3;

import javax.swing.*;
import java.awt.*;

public class FrameDemo {
    FrameDemo(){
// Создание контейнера верхнего уровня JFrame
        JFrame jfrm = new JFrame("Простой фрейм");// Установка начальных размеров фрейма
        jfrm.setSize(275, 100);
// Завершение программы при закрытии пользователем
// окна приложения
        JButton button = new JButton("Иришка");
        button.addActionListener((x) -> System.out.println("Красавица"));
        JLabel labelIrina = new JLabel();
        jfrm.add(labelIrina);
        jfrm.setLayout(new FlowLayout());
        jfrm.add(button);
        button.addMouseListener(new Prikoluha(labelIrina, jfrm));
        JButton mitr = new JButton("Митрошин - препод, волосы вверх");
        JLabel labelMitr = new JLabel();
        mitr.addActionListener(new Smeshnyavka(labelMitr, jfrm));
        jfrm.add(labelMitr);
        jfrm.add(mitr);
        JLabel labelScroll = new JLabel();
        JSlider slider = new JSlider(1, 10, 1);
        slider.addChangeListener(new Zabava(labelScroll));
        labelScroll.add(slider);
        jfrm.add(labelScroll);
        jfrm.add(slider);
        jfrm.setLayout(new FlowLayout());
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// Отображение фрейма
        jfrm.setVisible(true);
    }
    public static void main(String[] args) {
// Создание фрейма в потоке обработки событий
        SwingUtilities.invokeLater(FrameDemo::new);
    }
}
