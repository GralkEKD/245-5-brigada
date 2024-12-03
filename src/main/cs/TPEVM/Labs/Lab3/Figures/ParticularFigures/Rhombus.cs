using System.Text;

namespace Figures;

public class Rhombus : Parallelogram
{
    private const string Name = "Ромб";
    
    public Rhombus() {}

    public Rhombus(double sideA, double angle) : base(sideA, sideA, angle)
    {
    }

    public Rhombus(double sideA, double angle, AngleUnit angleUnit) : base(sideA, sideA, angle, angleUnit)
    {
    }

    public new double SideB
    {
        set { }
        get => SideA;
    }

    public override double Perimeter => 4 * SideA;
    
    private StringBuilder BuildFigureInfo()
    {
        return base.BuildFigureInfo(Name);
    }

    public override string ToString()
    {
        return BuildFigureInfo().ToString();
    }
}