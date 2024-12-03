using System.Text;

namespace Figures;

public class EquilateralTriangle : RegularPolygon
{
    private const string Name = "Правильный треугольник";

    public sealed override int SidesNumber
    {
        set { }
        get => 3;
    }

    public EquilateralTriangle() { }

    public EquilateralTriangle(double sideLength) : base(3, sideLength) { }

    public new double Perimeter => 3 * SideLength;

    public new double Area => Math.Sqrt(3) / 4 * Math.Pow(SideLength, 2);
    
    private StringBuilder BuildFigureInfo()
    {
        return base.BuildFigureInfo(Name);
    }

    public override string ToString()
    {
        return BuildFigureInfo().ToString();
    }
}