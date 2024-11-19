using Figures;

namespace UserUI.Components;

public class FiguresList
{
    private readonly List<Figure> _figures = [];
    
    public FiguresList() {}

    public Figure this[int index] => _figures[index];
    
    public void AddFigure(Figure figure)
    {
        _figures.Add(figure);
    }
    
    
}