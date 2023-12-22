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
        double x = Main.x[0];
        while (x <= Main.x[Main.n - 1]) {
            System.out.println("x = " + x + " " + "y = " + lagrange.eval(x) + "(Лагранж)");
            System.out.println("x = " + x + " " + "y = " + aitken.eval(x) + "(Эйткен)");
            System.out.println("x = " + x + " " + "y = " + splines.eval(x) + "(Сплайны)\n");
            seriesL.add(x, lagrange.eval(x));
            seriesA.add(x, aitken.eval(x) + 2);
            seriesS.add(x, splines.eval(x));
            x += 0.0625;
        }
        var dataset = new XYSeriesCollection();
        dataset.addSeries(seriesL);
        dataset.addSeries(seriesA);
        dataset.addSeries(seriesS);
        return dataset;
    }
    private JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Графики",
                "x",
                "y",
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
