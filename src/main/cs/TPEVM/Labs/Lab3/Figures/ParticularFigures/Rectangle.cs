using System.Text;

namespace Figures;

public class Rectangle : Parallelogram
{
    private const string Name = "Прямоугольник";
    
    public Rectangle() {}

    public Rectangle(double sideA, double sideB) : base(sideA, sideB, 90.0)
    {
    }

    public Rectangle(double sideA, double sideB, bool isAngleInDegrees) : base(sideA, sideB, 90.0, isAngleInDegrees)
    {
    }

    public override double Area => SideA * SideB;

    private StringBuilder BuildFigureInfo()
    {
        return base.BuildFigureInfo(Name);
    }

    protected new virtual string ToString()
    {
        return BuildFigureInfo().ToString();
    }
}