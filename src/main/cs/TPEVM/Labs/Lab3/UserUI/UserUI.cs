using Figures;

namespace UserUI;

public static class UserUI
{
    public static void Main()
    {
        Ellipse ellipse = new(1 / Math.PI, 7 / Math.PI);
        Console.WriteLine(ellipse.ToString());
        Circle circle = new(1 / Math.PI);
        Console.WriteLine(circle.ToString());
    }
}
