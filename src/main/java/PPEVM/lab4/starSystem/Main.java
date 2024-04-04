package PPEVM.lab4.starSystem;
import java.util.Scanner;

public class Main {
    public static void print() {
        System.out.println("""
                Выберите необходимое действие:
                n - получить количество планет в звездной системе
                s - получить название звезды
                a - добавить планету в систему
                q - окончание работы""");
    }

    public static void main(String[] args) {
        System.out.println("Задание 1:");
        Scanner sc = new Scanner(System.in);
        System.out.println("Выберите пункт из меню:");
        boolean flag = true;
        StarSystem star = new StarSystem(new Star("Sun"));
        while (flag) {
            print();
            try {
                PPEVM.lab4.starSystem.Menu userChoice = Menu.valueOf(sc.next());
                switch (userChoice) {
                    case n -> System.out.println("Количество планет: " + star.printNumberOfPlanets());
                    case s -> System.out.println("Название звезды: " + star.getStarName());
                    case a -> {
                        System.out.print("Введите название для новой планеты: ");
                        String name = sc.next();
                        star.addPlanet(name);
                    }
                    case q -> flag = false;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: неверно введено значение");
            }
        }
    }
}
