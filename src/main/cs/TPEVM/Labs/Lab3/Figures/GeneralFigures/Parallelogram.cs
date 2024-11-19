using System.Text;

namespace Figures;

public class Parallelogram : Figure
{
    private const string Name = "Параллелограм";

    public bool IsAngleInDegrees { set; get; } = true;

    public Parallelogram()
    {
    }

    public Parallelogram(double sideA, double sideB, double angle)
    {
        SideA = sideA;
        SideB = sideB;
        _angle = angle;
    }
    
    public Parallelogram(double sideA, double sideB, double angle, bool isAngleInDegrees)
    {
        SideA = sideA;
        SideB = sideB;
        _angle = angle;
        IsAngleInDegrees = isAngleInDegrees;
    }

    public double SideA { set; get; }
    public double SideB { set; get; }
    private double _angle;

    public double Angle 
    {
        get => _angle;
        set
        {
            switch (IsAngleInDegrees) // Вот за что я люблю JetBrains
            {
                case true when value is > 0 and < 180: // Я даже не понимаю, что здесь происходит, а он взял и написал
                case false when value is > 0 and < Math.PI: // SQL какой-то...
                    _angle = value;
                    break;
            }
        }
    }

    public override double Perimeter => 2 * SideA + 2 * SideB;

    public override double Area => !IsAngleInDegrees ? 
        SideA * SideB * Math.Sin(_angle) : 
        SideA * SideB * Math.Sin(_angle * Math.PI / 180);

    private StringBuilder BuildFigureInfo()
    {
        return base.BuildFigureInfo(Name);
    }

    public new string ToString()
    {
        return BuildFigureInfo().ToString();
    }
}