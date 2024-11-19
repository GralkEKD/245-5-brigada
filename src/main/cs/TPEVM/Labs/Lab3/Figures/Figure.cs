using System.Drawing;
using System.Text;

namespace Figures;

public abstract class Figure
{
    public abstract double Perimeter { get; }
    public abstract double Area { get; }
    public Color Color { set; get; } = Color.Black;

    private new string ToString()
    {
        StringBuilder stringBuilder = new();
        stringBuilder.Append(" Цвет: ");
        stringBuilder.Append(Color.ToKnownColor());
        stringBuilder.Append(" Периметр: ");
        stringBuilder.Append(Perimeter);
        stringBuilder.Append(" Площадь: ");
        stringBuilder.Append(Area);
        return stringBuilder.ToString();
    }

    protected StringBuilder BuildFigureInfo(string name)
    {
        StringBuilder stringBuilder = new("Фигура: ");
        stringBuilder.Append(name);
        stringBuilder.Append(ToString());
        return stringBuilder;
    }
}
