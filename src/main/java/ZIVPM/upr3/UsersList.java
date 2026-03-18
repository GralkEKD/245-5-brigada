package ZIVPM.upr3;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class UsersList {

    private final HashMap<Integer, User> userHashMap = new HashMap<>();

    public void readFromJson(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        Map<Integer, User> map = mapper.readValue(
                new File(filePath),
                new TypeReference<>() {
                }
        );

        userHashMap.clear();
        userHashMap.putAll(map);
    }

    public void saveToJson(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper(JsonFactory.builder().build());
        String json = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(userHashMap);
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
            writer.write(json);
        }
    }

    public HashMap<Integer, User> getUserHashMap() {
        return userHashMap;
    }
}