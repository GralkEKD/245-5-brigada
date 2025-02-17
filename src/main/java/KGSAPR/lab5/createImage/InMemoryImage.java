package KGSAPR.lab5.createImage;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.MemoryImageSource;
import java.util.Random;
import javax.swing.JFrame;
public class InMemoryImage extends JFrame {
    private static final long serialVersionUID = 1L;
    //Размер создаваемого тзображения
    private final int w =100;
    private final int h =100;
    //Массив для создания изображения
    private final int[] pix = new int[w*h];
    private Image img;
    InMemoryImage(String s) {
        super(s);
//Запоняем массив для изображения
        int i = 0;
        long seed = System.nanoTime();
        seed = Long.rotateLeft(seed, 48) ^ seed;
        Random random = new Random(seed);
        for (int y = 0; y < h; y++) {
            int green = (y * random.nextInt(0, 256)) / (h - 1);
            for (int x = 0; x < w; x++) {
                int blue = (y * random.nextInt(0, 256)) / (w - 1);
                pix[i++] = (255 << 24) | (green << 8) | blue;
            }
        }
        this.setSize(200, 200);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void paint(Graphics g) {
        if (img == null)
            img = this.createImage(new MemoryImageSource(w, h, pix, 0, w));
        g.drawImage(img, 50, 50, this);
    }
    public static void main(String[] args) {
        new InMemoryImage("Изображение, созданное в памяти");
    }
}