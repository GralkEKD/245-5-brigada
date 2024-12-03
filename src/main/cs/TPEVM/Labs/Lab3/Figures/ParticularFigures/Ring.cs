using System.Text;

namespace Figures;

public class Ring : Ellipse
{
    private const string Name = "Кольцо";

    private readonly Ellipse _innerArea = new();
    
    public Ring() {}

    public Ring(double outerMinorSemiAxis, double outerMajorSemiAxis) : base(outerMinorSemiAxis, outerMajorSemiAxis)
    {
    }
    
    public Ring(double outerMinorSemiAxis, 
        double outerMajorSemiAxis,
        double innerMinorSemiAxis, 
        double innerMajorSemiAxis) : base(outerMinorSemiAxis, outerMajorSemiAxis)
    {
        _innerArea.MinorSemiAxis = innerMinorSemiAxis;
        _innerArea.MajorSemiAxis = innerMajorSemiAxis;
    }
    
    public double InnerMajorSemiAxis
    {
        set
        {
            if (value > 0) _innerArea.MajorSemiAxis = value;
        }
        get => _innerArea.MajorSemiAxis;
    }

    public double InnerMinorSemiAxis 
    {
        set 
        {
            if (value > 0) _innerArea.MinorSemiAxis = value;
        }
        get => _innerArea.MinorSemiAxis;
    }
    
    public override double Perimeter => // P внутреннего эллипса + P внешнего эллипса
        _innerArea.Perimeter + 
        2 * Math.PI * Math.Sqrt(Math.Pow(MajorSemiAxis, 2) + Math.Pow(MinorSemiAxis, 2) / 2);

    public override double Area => // S внешнего эллипса - S внутренней области
        Math.PI * MinorSemiAxis * MajorSemiAxis - _innerArea.Area;
    
    private StringBuilder BuildFigureInfo()
    {
        return base.BuildFigureInfo(Name);
    }

    public override string ToString()
    {
        return BuildFigureInfo().ToString();
    }
}