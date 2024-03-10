package PPEVM.lab4.biker.protection;

public class KneePads extends Protection implements java.io.Serializable{
    public KneePads() { super(); }

    public KneePads(Material material, Size size, double price, double weight) {
        super(material, size, price, weight);
    }

    @Override
    public String toString() {
        return "KneePads{" +
                "material=" + material +
                ", size=" + size +
                ", price=" + price +
                ", weight=" + weight +
                '}';
    }
}
