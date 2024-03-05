package PPEVM.lab4.biker;

import PPEVM.lab4.biker.protection.*;

import java.util.ArrayList;
import java.util.List;


public class Biker {
    private final String name;
    private final List<Protection> protectionList = new ArrayList<>();

    public Biker(String name, Helmet helmet, ElbowPads elbowPads, KneePads kneePads) {
        this.name = name;
        protectionList.add(helmet);
        protectionList.add(elbowPads);
        protectionList.add(kneePads);
    }

    public String getName() {
        return name;
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
