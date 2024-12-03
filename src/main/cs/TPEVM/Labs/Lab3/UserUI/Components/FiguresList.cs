using System.Text;
using Figures;

namespace UserUI.Components;

public class FiguresList
{
    private readonly List<Figure> _figures = [];

    public Figure this[int index] => _figures[index];
    
    public void AddFigure(Figure figure)
    {
        _figures.Add(figure);
    }

    public static void SampleFill(FiguresList list)
    {
        list.AddFigure(new Ellipse(10, 15));
        list.AddFigure(new Parallelogram(10, 12, 30, AngleUnit.Degrees));
        list.AddFigure(new RegularPolygon(5, 12));
        list.AddFigure(new Circle(5));
        list.AddFigure(new EquilateralTriangle(12));
        list.AddFigure(new Rectangle(6, 8));
        list.AddFigure(new Rhombus(12, 30, AngleUnit.Degrees));
        list.AddFigure(new Ring(10, 12, 5, 6));
        list.AddFigure(new Square(6));
    }

    public string FiguresInfo()
    {
        StringBuilder stringBuilder = new();
        for (int i = 0; i < _figures.Count; i++)
        {
            
            stringBuilder.Append(i);
            stringBuilder.Append(": ");
            stringBuilder.Append(this[i]);
            stringBuilder.Append('\n');
        }

        return stringBuilder.ToString();
    }
}
