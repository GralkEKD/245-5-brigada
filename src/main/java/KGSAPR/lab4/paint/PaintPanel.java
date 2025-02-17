package KGSAPR.lab4.paint;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serial;
import javax.imageio.ImageIO;
import javax.swing.*;

//Класс, расширяющий JPanel и реализующий интерфейсы
//ActionListener - слушатель событий действия
//MouseListener - слушатель событий мыши
//MouseMotionListener - слушатель событий перемещения мыши
public class PaintPanel extends JPanel implements MouseListener,
        MouseMotionListener, MouseWheelListener {
    @Serial
    private static final long serialVersionUID = 1L;
    private float wl = 5.0F;
    protected int lastX, lastY, w, h;
    protected Color curColor = Color.BLACK;
    protected JFrame f;
    protected JLabel textField;
    Image otvalIcon;

    //Конструктор. Принимает в качестве параметров фрейм, на котором будет размещена
    //панель и размеры панели
    public PaintPanel(JFrame frame, int width, int height) {
        super();
        f = frame;
        w = width;
        h = height;
        setLayout(new FlowLayout());
        textField = new JLabel();
        add(textField, 0);
    }

    public void setCurColor(Color color) {
        this.curColor = color;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (otvalIcon == null) try {
            BufferedImage otval = ImageIO.read(
                    new File("/home/gralkekd/Загрузки/otval.jpg")
            );
            otvalIcon = otval.getScaledInstance(
                    otval.getWidth() / 4,
                    otval.getHeight() / 4,
                    BufferedImage.SCALE_FAST);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        g.drawImage(otvalIcon, 0, 150, this);
    }

    //Обработчик события перемещения мыши с нажатой кнопкой
    @Override
    public void mouseDragged(MouseEvent me) {
        //С поммощью вызова метода this.getGraphics() получаем графический контекст нашей панели
        //и приводим его к Graphics2D
        Graphics2D g2 = (Graphics2D) this.getGraphics();
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );
        //Устанавливаем текущую ширина штриха (Stroke) в wl пикселей
        g2.setStroke(new BasicStroke(
                wl,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND
        ));
        //Если при перемещении нажата левая кнопка мыши
        if ((me.getModifiersEx() & MouseEvent.BUTTON1_DOWN_MASK) ==
                MouseEvent.BUTTON1_DOWN_MASK) {
            //Устанавливаем текущий цвет рисования
            g2.setColor(curColor);
        }
        // Если при перемещении нажата правая кнопка мыши
        if ((me.getModifiersEx() & MouseEvent.BUTTON3_DOWN_MASK) ==
                MouseEvent.BUTTON3_DOWN_MASK) {
            // Устанавливаем цвет фона (считаем, что используем ластик)
            g2.setColor(getBackground());
        }
        //Рисуем текущим штрихом и цветом прямую линию от предыдущего
        //положения мыши до текущего
        g2.drawLine(lastX, lastY, me.getX(), me.getY());
        //Телаем текущее положение мыши предыдущим
        lastX = me.getX();
        lastY = me.getY();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getWheelRotation() > 0 && wl < 50.0f) {
            wl += 0.1f;
        }
        if (e.getWheelRotation() < 0 && wl > 1.0f) {
            wl -= 0.1f;
        }
        textField.setText(String.valueOf(wl));
    }

    //Это событие не обработано
    @Override
    public void mouseMoved(MouseEvent e) {

    }

    //Это событие не обработано
    @Override
    public void mouseClicked(MouseEvent arg0) {
    }

    //Это событие не обработано
    @Override
    public void mouseEntered(MouseEvent arg0) {
    }

    //Это событие не обработано
    @Override
    public void mouseExited(MouseEvent arg0) {
    }

    //Обработчик события нажания мыши
    @Override
    public void mousePressed(MouseEvent me) {
        //Если нажата левая или правая кнопка мыши
        if (
                ((me.getModifiersEx() & MouseEvent.BUTTON1_DOWN_MASK) == MouseEvent.BUTTON1_DOWN_MASK) ||
                        ((me.getModifiersEx() & MouseEvent.BUTTON3_DOWN_MASK) == MouseEvent.BUTTON3_DOWN_MASK)
        ) {
            //устанавливаем предыдущие координаты мыши в текущие
            lastX = me.getX();
            lastY = me.getY();
        }
    }

    //Это событие не обработано
    @Override
    public void mouseReleased(MouseEvent arg0) {
    }
}