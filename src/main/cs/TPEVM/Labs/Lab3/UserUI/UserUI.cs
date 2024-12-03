using Figures;
using UserUI.Components;

namespace UserUI;

public static class UserUI
{
    public static void Main()
    {
        FiguresList figuresList = new();
        FiguresList.SampleFill(figuresList);
        
        Console.WriteLine("Фигуры:");
        Console.WriteLine(figuresList.FiguresInfo());
    }
}
