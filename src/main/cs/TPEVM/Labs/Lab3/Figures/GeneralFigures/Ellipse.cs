using System.Text;

namespace Figures;

public class Ellipse : Figure
{
    private const string Name = "Эллипс";

    // Большая и малая полуоси определяются при обращении к свойствам. Фактически, какая полуось больше - та и будет
    // считаться большой, а другая - малой.
    protected double A; // Первая полуось
    protected double B; // Вторая полуось

    public Ellipse() {}

    public Ellipse(double a, double b)
    {
        A = a;
        B = b;
    }

    public virtual double MajorSemiAxis
    {
        set
        {
            if (value > 0) A = value;
        }
        get => A > B ? A : B;
    }

    public virtual double MinorSemiAxis 
    {
        set 
        {
            if (value > 0) B = value;
        }
        get => A > B ? B : A;
    }

    public override double Perimeter => 2 * Math.PI * Math.Sqrt((A * A + B * B) / 2);

    public override double Area => Math.PI * A * B;

    private StringBuilder BuildFigureInfo()
    {
        return base.BuildFigureInfo(Name);
    }

    public new string ToString()
    {
        return BuildFigureInfo().ToString();
    }
}