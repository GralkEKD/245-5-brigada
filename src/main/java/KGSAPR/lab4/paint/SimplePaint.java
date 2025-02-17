package KGSAPR.lab4.paint;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serial;
import javax.imageio.ImageIO;
import javax.swing.*;

public class SimplePaint extends JFrame{
    @Serial
    private static final long serialVersionUID = 1L;

    //Конструктор класса
    public SimplePaint(String s) {
        //Вызываем конструктор суперкласса, то есть класса JFrame
        //и передаем в него строку заголовка окна
        super(s);
        Toolkit tk = Toolkit.getDefaultToolkit();
        //Запрещаем менять размеры окна
        this.setResizable(false);
        //Устанавливаем размеры окна
        this.setSize(tk.getScreenSize());
        //Создаем объект класса PaintPanel, который описан ниже
        //Добавляем к созданному объекту обработчики событий
        //В нашем случае этот объект сам будет обрабатывать свои события
        PaintPanel panel = new PaintPanel(this, 800, 800);
        panel.addMouseListener(panel);
        panel.addMouseMotionListener(panel);
        panel.addMouseWheelListener(panel);
        //Создаем скроллируемую панель, чтобы посмотреть, как это делается
        //Скроллировать эта панель будет панель для рисования panel, её и
        //передаем в конструктор JScrollPane
        JScrollPane pane = new JScrollPane(panel);
        //Определяем, чтобы вертикальная и горизонтальная полосы прокрутки
        // панели показывались всегда
        pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            //Добавляем панель для рисования в центральную область нашего окна
            //this - ссылка на самого себя, то есть в нашем случае объект
            //класса SimplePaint
        this.add(pane, BorderLayout.CENTER);
        //Создаем новую панель
        JPanel p = new JPanel();
        //Добавляем эту панель в нижнюю часть нашего (южную) окна
        this.add(p, BorderLayout.SOUTH);
        var colorChooser = new JColorChooser(Color.BLACK);
        colorChooser.getSelectionModel()
                .addChangeListener(
                e -> panel.setCurColor(
                        colorChooser.getColor()
                )
        );
        p.add(colorChooser, BorderLayout.SOUTH);
        //Создаем несколько кнопок, каждую из которых добавляем на панель
        //Определяем обработчика событий для каждой кнопки
        JButton b5 = new JButton("Очистить") {
            private int startingWidth = -1;
            private int startingHeight = -1;
            BufferedImage otval;
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                if (otval == null) try  {
                    otval = ImageIO.read(
                            new File("/home/gralkekd/Загрузки/otval.jpg")
                    );
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (startingHeight == -1 && startingWidth == -1) {
                    startingHeight = getHeight();
                    startingWidth = getWidth();
                }
                this.setIcon(new ImageIcon(
                        otval.getScaledInstance(startingWidth, startingHeight, 1)
                ));
            }
        };
        p.add(b5, BorderLayout.SOUTH);
        b5.addActionListener(e -> panel.repaint());
        //Определяем действие при закрытии окна
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //Делаем окно видимым
        this.setVisible(true);
    }
    public static void main(String[] args) {
        //Создаем окно как безымянный объект, потому что имя его нам не нужно
        new SimplePaint("Очень простой редактор");
    }
}