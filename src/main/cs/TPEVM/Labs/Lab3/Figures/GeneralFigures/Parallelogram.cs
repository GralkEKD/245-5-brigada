using System.Text;

namespace Figures;

public class Parallelogram : Figure
{
    private const string Name = "Параллелограм";

    public AngleUnit Unit { set; get; } = AngleUnit.Degrees;

    public Parallelogram()
    {
    }

    public Parallelogram(double sideA, double sideB, double angle)
    {
        SideA = sideA;
        SideB = sideB;
        _angle = angle;
    }
    
    public Parallelogram(double sideA, double sideB, double angle, AngleUnit angleUnit)
    {
        SideA = sideA;
        SideB = sideB;
        _angle = angle;
        Unit = angleUnit;
    }

    public double SideA { set; get; }
    public double SideB { set; get; }
    private double _angle;

    public double Angle 
    {
        get => _angle;
        set
        {
            switch (Unit) 
            {
                case AngleUnit.Degrees when value is > 0 and < 180: 
                case AngleUnit.Radians when value is > 0 and < Math.PI: 
                    _angle = value;
                    break;
                default:
                    throw new ArgumentOutOfRangeException("Unit " + Unit + " is undefined");
            }
        }
    }

    public override double Perimeter => 2 * SideA + 2 * SideB;

    public override double Area
    {
        get
        {
            return Unit switch
            {
                AngleUnit.Degrees => SideA * SideB * Math.Sin(_angle * Math.PI / 180),
                AngleUnit.Radians => SideA * SideB * Math.Sin(_angle),
                _ => throw new ArgumentOutOfRangeException("Unit " + Unit + " is undefined")
            };
        }
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