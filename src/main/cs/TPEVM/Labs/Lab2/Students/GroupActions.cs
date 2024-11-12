using System.Text;

namespace Students
{
    public static class GroupActions
    {
        public static Dictionary<string, Group> InitializeGroupsMap()
        {
            Dictionary<string, Group> groups = [];
            Student[] students = [
                new ("Бурцев", 
                "Никита", 
                "Васильевич", 
                "ул. Циолковского, д.5к1, кв. 227",
                new DateOnly(2004, 4, 14),
                "89169241755"),
                new ("Кочеткова",
                "Ирина",
                "Олеговна",
                "ул. Стройкова, д. 25, кв. 28",
                new DateOnly(2004, 5, 24),
                "89605706404"),
                new ("Москвитин",
                "Дмитрий",
                "Владимирович",
                "ул. Весенняя, д. 18к1, кв. 42",
                new DateOnly(2005, 3, 27),
                "89106109919")
            ];
            groups.Add("245", new("245", students));
            
            students = [
                new ("Коршунова", 
                "Мария", 
                "Геннадьевна", 
                "Её адреса я не знаю", 
                new DateOnly(2004, 1, 16), 
                "89537331323"), 
                new ("Онущенко", 
                "Павел",
                "Батькович",
                "Тоже где-то в общаге",
                new DateOnly(2004, 3, 2),
                "89269694189"),
                new ("Князев",
                "Даниил",
                "Отцович",
                "Где-то на Касимовском",
                new DateOnly(2004, 6, 3),
                "89956542588")
            ];
            groups.Add("2415", new("2415", students));
            return groups;
        }

        public static string GroupNumbersList(Dictionary<string, Group> groups)
        {
            return string.Join(", ", [.. groups.Keys]);
        }
    }
}