package ZIVPM.upr4real;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Registration {

    public static void register(String user, String password, File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(file);
        ObjectNode objectNode = (ObjectNode) node;
        if (!Objects.isNull(objectNode.findValue(user))) {
            throw new RuntimeException("Пользователь " + user + " уже существует");
        }
        objectNode.put(user, password);
        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(file, objectNode);
    }

    public static boolean authorize(String user, String password, File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(file);
        ObjectNode objectNode = (ObjectNode) node;
        if (Objects.isNull(objectNode.findValue(user))) {
            throw new RuntimeException("Пользователь " + user + " уже существует");
        }
        return Objects.equals(objectNode.get(user).textValue(), password);
    }
}
