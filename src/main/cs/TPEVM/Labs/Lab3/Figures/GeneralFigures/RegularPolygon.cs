using System.Text;

namespace Figures;

public class RegularPolygon : Figure
{
    private const string Name = "Правильный многоугольник";

    private int _sidesNumber = 3;
    private double _sideLength = 1.0;
    public virtual int SidesNumber
    { 
        set
        {
            if (value > 2) _sidesNumber = value;
        }
        get => _sidesNumber; 
    }
    
    public double SideLength 
    { 
        set
        {
            if (value > 0) _sideLength = value;
        } 
        get => _sideLength; 
    }

    public RegularPolygon()
    {
    }

    public RegularPolygon(int sidesNumber, double sideLength)
    {
        _sidesNumber = sidesNumber;
        _sideLength = sideLength;
    }

    public double AngleDeg => (_sidesNumber - 2) * 180.0 / _sidesNumber;

    public double AngleRad => (_sidesNumber - 2) * Math.PI / _sidesNumber;

    public override double Perimeter => _sidesNumber * _sideLength;

    public override double Area => // Формула из Википедии
        _sidesNumber / 4.0 * Math.Pow(_sideLength, 2) * (1.0 / Math.Tan(Math.PI / _sidesNumber)); 

    private StringBuilder BuildFigureInfo()
    {
        return base.BuildFigureInfo(Name);
    }

    public new string ToString()
    {
        return BuildFigureInfo().ToString();
    }
}