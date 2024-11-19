using System.Text;

namespace Figures;

public class Circle : Ellipse
{
    private const string Name = "Круг";

    public double Radius
    {
        set
        {
            base.MajorSemiAxis = value; // При a = b эллипс вырождается в окружность
            base.MinorSemiAxis = value; // Методы расчета площади и периметра вырождаются в таковые для окружности
        }
        get => A;
    }
    public sealed override double MajorSemiAxis
    {
        set { }
    }

    public sealed override double MinorSemiAxis
    {
        set { }
    }

    public Circle() {}

    public Circle(double radius)
    {
        A = radius;
        B = radius;
    }

    private StringBuilder BuildFigureInfo()
    {
        return base.BuildFigureInfo(Name);
    }
    public new string ToString()
    {
       return BuildFigureInfo().ToString();
    }
}