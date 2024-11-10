package KGSAPR.lab3;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
public class CentrFrame {
    CentrFrame(){
// Определение размеров окна, в котором
// будет отображаться фрейм (дисплея)//
// Получаем объект класса Toolkit
        Toolkit tk = Toolkit.getDefaultToolkit();
// Определяем размерность дисплея
        Dimension screenSize = tk.getScreenSize();
// Определяем высоту и ширину дисплея в пикселах
        int height = screenSize.height;
        int width = screenSize.width;
// Создаём контейнер верхнего уровня JFrame
        JFrame jf = new JFrame("Фрейм в центре окна");
// Устанавливаем размеры фрейма
        jf.setSize(width/2, height/2);
// Устанавливаем положение фрейма на дисплее
        jf.setLocation(width/4, height/4);
// Завершение программы при закрытии пользователем
// окна приложения
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// Отображаем фрейм
        jf.setVisible(true);
    }
    public static void main(String[] args) {
// Создание фрейма в потоке обработки событий
        SwingUtilities.invokeLater(CentrFrame::new);
    }
}
