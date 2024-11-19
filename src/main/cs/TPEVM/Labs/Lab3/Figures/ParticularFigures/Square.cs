using System.Text;

namespace Figures;

public class Square : RegularPolygon
{
    private const string Name = "Квадрат";

    public Square()
    {
    }

    public Square(double sideLength) : base(4, sideLength)
    {
    }
    
    public sealed override int SidesNumber
    { 
        set { }
        get => 4; 
    }

    public new double Perimeter => 4 * SideLength;

    public new double Area => Math.Pow(SideLength, 2);
    
    private StringBuilder BuildFigureInfo()
    {
        return base.BuildFigureInfo(Name);
    }

    public new string ToString()
    {
        return BuildFigureInfo().ToString();
    }
}