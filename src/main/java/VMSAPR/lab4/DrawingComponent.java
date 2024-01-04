package VMSAPR.lab4;

import org.jfree.chart.*;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.*;

public class DrawingComponent extends JFrame{
    public DrawingComponent(Lagrange lagrange, Aitken aitken, Splines splines) {
        initUI(lagrange, aitken, splines);
    }
    private void initUI(Lagrange lagrange, Aitken aitken, Splines splines) {
        XYDataset dataset = createDataset(lagrange, aitken, splines);
        JFreeChart chart = createChart(dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.WHITE);
        add(chartPanel);

        pack();
        setTitle("График какой-то там, построенный по коду индуса с сайта zetcode.com");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private XYDataset createDataset(Lagrange lagrange, Aitken aitken, Splines splines){
        var seriesL = new XYSeries("Лагранж");
        var seriesA = new XYSeries("Эйткен (+2)");
        var seriesS = new XYSeries("Сплайны");
        var seriesKnots = new XYSeries("Узлы интерполяции");
        double x = Main.x[0];
        long t = System.currentTimeMillis();
        while (x <= Main.x[Main.n - 1]) {
            seriesL.add(x, lagrange.eval(x));
            x += ((double) 1 /32);
        }
        System.out.println("Время выполнения (метод Лагранжа): " + (System.currentTimeMillis() - t + " мс"));
        t = System.currentTimeMillis();
        x = Main.x[0];
        while (x <= Main.x[Main.n - 1]) {
            seriesA.add(x, aitken.eval(x));
            x += ((double) 1 /32);
        }
        System.out.println("Время выполнения (метод Эйткена): " + (System.currentTimeMillis() - t + " мс"));
        t = System.currentTimeMillis();
        x = Main.x[0];
        while (x <= Main.x[Main.n - 1]) {
            seriesS.add(x, splines.eval(x));
            x += ((double) 1 /32);
        }
        System.out.println("Время выполнения (метод Сплайнов): " + (System.currentTimeMillis() - t + " мс"));
        for (int i = 0; i < Main.n; i++) {
            seriesKnots.add(Main.x[i], Main.y[i]);
        }
        var dataset = new XYSeriesCollection();
        dataset.addSeries(seriesL);
        dataset.addSeries(seriesA);
        dataset.addSeries(seriesS);
        dataset.addSeries(seriesKnots);
        return dataset;
    }
    private JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Графики",
                "x",
                "y = f(x) = 1/(1 + 25*x^2) (Функция Рунге)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        XYPlot plot = chart.getXYPlot();
        var renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);
        chart.getLegend().setFrame(BlockBorder.NONE);
        chart.setTitle(new TextTitle("Графики", new Font("Serif", java.awt.Font.BOLD, 18)));
        return chart;
    }
}
