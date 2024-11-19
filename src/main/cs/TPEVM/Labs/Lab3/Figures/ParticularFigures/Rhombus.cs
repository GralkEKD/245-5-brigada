using System.Text;

namespace Figures;

public class Rhombus : Parallelogram
{
    private const string Name = "Ромб";
    
    public Rhombus() {}

    public Rhombus(double sideA, double angle) : base(sideA, sideA, angle)
    {
    }

    public Rhombus(double sideA, double angle, bool isAngleInDegrees) : base(sideA, sideA, angle, isAngleInDegrees)
    {
    }

    public new double SideB
    {
        set { }
        get => SideA;
    }

    public override double Perimeter => 4 * SideA;

    public override double Area => Math.Pow(SideA, 2) * Math.Sin(IsAngleInDegrees ? (Angle * Math.PI / 180.0) : Angle);
    
    private StringBuilder BuildFigureInfo()
    {
        return base.BuildFigureInfo(Name);
    }

    protected new virtual string ToString()
    {
        return BuildFigureInfo().ToString();
    }
}