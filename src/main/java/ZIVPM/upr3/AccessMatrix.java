package ZIVPM.upr3;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class AccessMatrix {

    private final Set<AccessRight>[][] accessMatrix;

    @SuppressWarnings("unchecked")
    public AccessMatrix(Map<Integer, User> users, int filesCount) {
        accessMatrix = new HashSet[users.size()][filesCount];
        long seed = System.currentTimeMillis();
        seed = (seed >>> 48) ^ seed;
        Random random = new Random(seed);
        users.forEach((i, u) -> {
            if (u.isAdmin()) {
                for (int j = 0; j < accessMatrix[i].length; j++) {
                    accessMatrix[i][j] = new HashSet<>();
                    accessMatrix[i][j].add(AccessRight.READ);
                    accessMatrix[i][j].add(AccessRight.WRITE);
                    accessMatrix[i][j].add(AccessRight.GRANT);
                }
            } else {
                for (int j = 0; j < accessMatrix[i].length; j++) {
                    int rightsMask = random.nextInt();
                    accessMatrix[i][j] = new HashSet<>();
                    if ((rightsMask & 0x01) == 0x01) accessMatrix[i][j].add(AccessRight.READ);
                    if ((rightsMask & 0x02) == 0x02) accessMatrix[i][j].add(AccessRight.WRITE);
                    if ((rightsMask & 0x04) == 0x04) accessMatrix[i][j].add(AccessRight.GRANT);
                }

            }
        });
    }

    public boolean hasPrivilege(int userNumber, int fileNumber, AccessRight action) {
        return accessMatrix[userNumber][fileNumber].contains(action);
    }

    public void saveToJson(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(new File(filePath), accessMatrix);
    }

    public String rightsAsString(Set<AccessRight> rights) {
        if (rights.isEmpty()) return "Запрет";
        else if (rights.containsAll(Set.of(AccessRight.READ, AccessRight.WRITE, AccessRight.GRANT))) {
            return "Полный доступ";
        }
        else {
            String r = "";
            if (rights.contains(AccessRight.READ)) r += "Чтение";
            if (rights.contains(AccessRight.WRITE)) r += r.isEmpty() ? "Запись" : ", Запись";
            if (rights.contains(AccessRight.GRANT)) r += r.isEmpty() ? "Передача прав" : ", Передача прав";
            return r;
        }
    }

    public Set<AccessRight>[][] getAccessMatrix() {
        return accessMatrix;
    }

    @SuppressWarnings("unchecked")
    private AccessMatrix(AccessRight[][][] accessRightMatrix) {
        HashSet[][] accessMatrix = new HashSet[accessRightMatrix.length][accessRightMatrix[0].length];
        for (int i = 0; i < accessRightMatrix.length; i++) {
            for (int j = 0; j < accessRightMatrix[i].length; j++) {
                accessMatrix[i][j] = new HashSet<AccessRight>();
                accessMatrix[i][j].addAll(Set.of(accessRightMatrix[i][j]));
            }
        }
        this.accessMatrix = accessMatrix;
    }

    public static AccessMatrix readFromJson(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return new AccessMatrix(mapper.readValue(new File(filePath), AccessRight[][][].class));
    }
}
