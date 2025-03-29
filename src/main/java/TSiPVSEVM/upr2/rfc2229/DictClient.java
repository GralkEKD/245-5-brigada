package TSiPVSEVM.upr2.rfc2229;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class DictClient {

    // По стандарту RFC2229 стандартный прослушиваемый порт для протокола - 2628
    private final static int PORT = 2628;

    // Адрес подключения к серверу. Может быть как IP, так и доменным именем
    private static InetAddress INET_ADDRESS;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Два способа запуска программы
        switch (args.length) {
            // Без параметров запуска: адрес сервера запрашивается при выполнении
            case 0: {
                System.out.print("IP address: ");
                String address = input.nextLine();
                try {
                    INET_ADDRESS = InetAddress.getByName(address);
                } catch (UnknownHostException e) {
                    System.out.printf("Error: connection refused to address %s\n", address);
                    System.exit(e.hashCode());
                }
                break;
            }

            // С одним параметром запуска - адресом сервера
            case 1: {
                try {
                    INET_ADDRESS = InetAddress.getByName(args[0]);
                } catch (UnknownHostException e) {
                    System.out.printf("Error: connection refused to address %s\n", args[0]);
                    System.exit(e.hashCode());
                }
                break;
            }

            // Иначе ошибка выполнения
            default: throw new IllegalArgumentException(
                    "The format for arguments is [SERVER]"
            );
        }

        // Инициализация числа принятых байт и буфера для обмена информацией
        int bytesRecv;
        int totalBytesRecv = 0;
        byte[] buffer = new byte[1024]; // По стандарту RFC2229 размер данных не превышает 1 Кб

        // Открытие клиентского сокета
        try (Socket socket = new Socket(INET_ADDRESS, PORT)) {
            // Получаем потоки ввода и вывода сокета
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            // Создаем объекты, хранящие тело запроса и ответа
            StringBuilder requestBody = new StringBuilder();
            StringBuilder responseBody = new StringBuilder();

            /*
            Перед началом работы получаем от сервера код 220 - успешное подключение и готовность принимать данные.

             */
            while ((bytesRecv = is.read(buffer, totalBytesRecv, buffer.length)) != -1) {
                totalBytesRecv += bytesRecv;
                responseBody.append(new String(buffer, StandardCharsets.UTF_8));
            }

            String responseCode = showResponseBody(responseBody);

            while (!responseCode.equals("221")) {
                while ((bytesRecv = is.read(buffer, totalBytesRecv, buffer.length)) != -1) {
                    totalBytesRecv += bytesRecv;
                    responseBody.append(new String(buffer, StandardCharsets.UTF_8));
                }
                switch (responseBody.charAt(0)) {
                    case '1': {

                    }
                    case '2': {

                    }
                    case '3': {

                    }
                    case '4': {

                    }
                    case '5': {

                    }
                    default: {

                    }
                }

            }
        } catch (IOException e) {
            System.out.println("I/O error occurred: " + e.getMessage());
            System.exit(e.hashCode());
        }

    }

    /** Метод принимает {@code responseBody} - тело ответа сервера, выводит его содержимое на экран
     * и возвращает первую подстроку тела - код ответа. Так же метод очищает объект {@code responseBody} для
     * следующего использования.
     */
    private static String showResponseBody(StringBuilder responseBody) {
        String[] tokens = responseBody.toString().split(" ");
        for (String token : tokens) {
            System.out.print(token + " ");
        }
        System.out.println();
        responseBody.delete(0, responseBody.length());
        return tokens[0];
    }
}
