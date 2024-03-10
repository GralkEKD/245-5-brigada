package PPEVM.lab4.biker;

import PPEVM.lab4.biker.protection.*;

import java.util.ArrayList;
import java.util.List;


public class Biker {
    private final List<Protection> protectionList = new ArrayList<>();

    public Biker(Helmet helmet, ElbowPads elbowPads, KneePads kneePads) {
        protectionList.add(helmet);
        protectionList.add(elbowPads);
        protectionList.add(kneePads);
    }

    public List<Protection> getProtectionList() { return protectionList; }
    public static String getProtectionByName(List<Protection> protectionList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Protection elem :
                protectionList) {
            if (elem instanceof Helmet) {
                stringBuilder.append("Шлем\n");
            } else if (elem instanceof ElbowPads) {
                stringBuilder.append("Налокотники\n");
            } else if (elem instanceof KneePads) {
                stringBuilder.append("Наколенники\n");
            }
        }
        return stringBuilder.toString();
    }

    public Helmet getHelmet() {
        for (Protection elem : protectionList) {
            if (elem instanceof Helmet) return (Helmet) elem;
        }
        return null;
    }

    public ElbowPads getElbowPads() {
        for (Protection elem : protectionList) {
            if (elem instanceof ElbowPads) return (ElbowPads) elem;
        }
        return null;
    }

    public KneePads getKneePads() {
        for (Protection elem : protectionList) {
            if (elem instanceof KneePads) return (KneePads) elem;
        }
        return null;
    }
}
