package ZIVPM.lab1;

public class SubstitutionCipher {

    private static char substitute(char initial) {
        switch (initial) {
            case 'A', 'a' -> {
                return 'V';
            }
            case 'B', 'b' -> {
                return 'W';
            }
            case 'C', 'c' -> {
                return 'X';
            }
            case 'D', 'd' -> {
                return 'Y';
            }
            case 'E', 'e' -> {
                return 'Z';
            }
            case 'F', 'f' -> {
                return ' ';
            }
            case 'G', 'g' -> {
                return '.';
            }
            case 'H', 'h' -> {
                return ',';
            }
            case 'I', 'i' -> {
                return '!';
            }
            case 'J', 'j' -> {
                return ':';
            }
            case 'K', 'k' -> {
                return ';';
            }
            case 'L', 'l' -> {
                return '?';
            }
            case 'M', 'm' -> {
                return '-';
            }
            case 'N', 'n' -> {
                return 'K';
            }
            case 'O', 'o' -> {
                return 'L';
            }
            case 'P', 'p' -> {
                return 'M';
            }
            case 'Q', 'q' -> {
                return 'N';
            }
            case 'R', 'r' -> {
                return 'O';
            }
            case 'S', 's' -> {
                return 'P';
            }
            case 'T', 't' -> {
                return 'Q';
            }
            case 'U', 'u' -> {
                return 'R';
            }
            case 'V', 'v' -> {
                return 'S';
            }
            case 'W', 'w' -> {
                return 'T';
            }
            case 'X', 'x' -> {
                return 'U';
            }
            case 'Y', 'y' -> {
                return 'A';
            }
            case 'Z', 'z' -> {
                return 'B';
            }
            case ' ' -> {
                return 'C';
            }
            case '.' -> {
                return 'D';
            }
            case ',' -> {
                return 'E';
            }
            case '!' -> {
                return 'F';
            }
            case ':' -> {
                return 'G';
            }
            case ';' -> {
                return 'H';
            }
            case '?' -> {
                return 'I';
            }
            case '-' -> {
                return 'J';
            }
            case '\n' -> {
                return '\n';
            }
            case '\r' -> {
                return '\r';
            }
            default -> throw new IllegalArgumentException("Неверный символ: \"" + initial + "\"");
        }
    }

    private static char substituteBack(char initial) {
        switch (initial) {
            case 'A', 'a' -> {
                return 'Y';
            }
            case 'B', 'b' -> {
                return 'Z';
            }
            case 'C', 'c' -> {
                return ' ';
            }
            case 'D', 'd' -> {
                return '.';
            }
            case 'E', 'e' -> {
                return ',';
            }
            case 'F', 'f' -> {
                return '!';
            }
            case 'G', 'g' -> {
                return ':';
            }
            case 'H', 'h' -> {
                return ';';
            }
            case 'I', 'i' -> {
                return '?';
            }
            case 'J', 'j' -> {
                return '-';
            }
            case 'K', 'k' -> {
                return 'N';
            }
            case 'L', 'l' -> {
                return 'O';
            }
            case 'M', 'm' -> {
                return 'P';
            }
            case 'N', 'n' -> {
                return 'Q';
            }
            case 'O', 'o' -> {
                return 'R';
            }
            case 'P', 'p' -> {
                return 'S';
            }
            case 'Q', 'q' -> {
                return 'T';
            }
            case 'R', 'r' -> {
                return 'U';
            }
            case 'S', 's' -> {
                return 'V';
            }
            case 'T', 't' -> {
                return 'W';
            }
            case 'U', 'u' -> {
                return 'X';
            }
            case 'V', 'v' -> {
                return 'A';
            }
            case 'W', 'w' -> {
                return 'B';
            }
            case 'X', 'x' -> {
                return 'C';
            }
            case 'Y', 'y' -> {
                return 'D';
            }
            case 'Z', 'z' -> {
                return 'E';
            }
            case ' ' -> {
                return 'F';
            }
            case '.' -> {
                return 'G';
            }
            case ',' -> {
                return 'H';
            }
            case '!' -> {
                return 'I';
            }
            case ':' -> {
                return 'J';
            }
            case ';' -> {
                return 'K';
            }
            case '?' -> {
                return 'L';
            }
            case '-' -> {
                return 'M';
            }
            case '\n' -> {
                return '\n';
            }
            case '\r' -> {
                return '\r';
            }
            default -> throw new IllegalArgumentException("Неверный символ: \"" + initial + "\"");
        }
    }

    public static String encrypt(String message) {
        StringBuilder cipher = new StringBuilder();
        message.chars().forEach(c -> cipher.append(substitute((char) c)));
        return cipher.toString();
    }

    public static String decrypt(String cipher) {
        StringBuilder message = new StringBuilder();
        cipher.chars().forEach(c -> message.append(substituteBack((char) c)));
        return message.toString();
    }
}
