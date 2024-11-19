using Students;

namespace UserUI
{
    public static class UserUI
    {
        public static void Main()
        {
            var groups = GroupActions.InitializeGroupsMap();
#pragma warning disable CS8601, CS8602, CS8604 // Да знаю я, что там null может быть, ни разу с ним не встретился
            var input = string.Empty;
            while (!input.Equals("7"))
            {
                Console.Clear();
                Console.WriteLine("""
                Доступные действия:
                1. Просмотреть список групп
                2. Просмотреть информацию о группе
                3. Добавить группу
                4. Добавить студента в группу
                5. Удалить студента из группы
                6. Просмотреть информацию о студенте
                7. Выход
                """);
                input = Console.ReadLine();
                switch (input)
                {
                    case "1":
                    {
                        Console.WriteLine("Доступные группы: " + GroupActions.GroupNumbersList(groups));
                        break;
                    }
                    case "2":
                    {
                        Console.Write("Введите номер группы: ");
                        try
                        {
                            Console.WriteLine(groups[Console.ReadLine()].GetInfo());
                        }
                        catch (KeyNotFoundException)
                        {
                            Console.WriteLine("Группа не найдена");
                        }
                        break;
                    }
                    case "3":
                    {
                        Console.Write("Введите номер группы: ");
                        input = Console.ReadLine();
                        if (groups.TryAdd(input, new Group(input))) Console.WriteLine("Группа " + input + " добавлена");
                        else Console.WriteLine("Группа " + input + " уже существует");
                        break;
                    }
                    case "4":
                    {
                        Console.Write("Введите номер группы: ");
                        try
                        {
                            var toAdd = groups[Console.ReadLine()];
                            Student toBeAdded = new();
                            Console.WriteLine("Номер зачетной книжки: " + toBeAdded.Id);
                            Console.Write("Фамилия: ");
                            toBeAdded.LastName = Console.ReadLine();
                            Console.Write("Имя: ");
                            toBeAdded.FirstName = Console.ReadLine();
                            Console.Write("Отчество: ");
                            toBeAdded.SecondName = Console.ReadLine();
                            Console.Write("Адрес: ");
                            toBeAdded.Address = Console.ReadLine();
                            Console.Write("Год рождения: ");
                            var year = Convert.ToInt32(Console.ReadLine());
                            Console.Write("Месяц рождения: ");
                            var month = Convert.ToInt32(Console.ReadLine());
                            Console.Write("День рождения: ");
                            var day = Convert.ToInt32(Console.ReadLine());
                            toBeAdded.BirthDate = new(year, month, day);
                            Console.Write("Телефон: ");
                            toBeAdded.PhoneNumber = Console.ReadLine();
                            Console.WriteLine(toAdd.Add(toBeAdded)
                                ? "Студент добавлен!"
                                : "Произошла ошибка при попытке добавления студента");
                        }
                        catch (KeyNotFoundException)
                        {
                            Console.WriteLine("Группа не найдена");
                        }
                        break;
                    }
                    case "5":
                    {
                        Console.Write("Введите номер группы: ");
                        try
                        {
                            var toBeRemovedFrom = groups[Console.ReadLine()];
                            Console.Write("Введите номер зачетной книжки студента: ");
                            Console.WriteLine(toBeRemovedFrom.Remove(Convert.ToUInt32(Console.ReadLine()))
                                ? "Студент удален :("
                                : "Студент не найден");
                        }
                        catch (KeyNotFoundException)
                        {
                            Console.WriteLine("Группа не найдена");
                        }
                        break;
                    }
                    case "6":
                    {
                        Console.Write("Введите номер группы: ");
                        try
                        {
                            var toBeLooked = groups[Console.ReadLine()];
                            Console.Write("Введите номер зачетной книжки студента: ");
                            var id = Convert.ToUInt32(Console.ReadLine());
                            Console.WriteLine(toBeLooked[id]);
                        }
                        catch (KeyNotFoundException)
                        {
                            Console.WriteLine("Студент или группа не найден(а)");
                        }
                        break;
                    }
                    case "7": return;
                    default: 
                    {
                        Console.WriteLine("Неверный ввод (харам)");
                        break;
                    }
                }
                Console.Write("Нажмите Enter...");
                Console.ReadLine();
            }
#pragma warning restore CS8601, CS8602, CS8604
        }
    }
}