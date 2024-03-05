package PPEVM.lab4.biker.protection;

public class ElbowPads extends Protection implements java.io.Serializable{
    public ElbowPads() { super(); }

    public ElbowPads(Material material, Size size, double price, double weight) {
        super(material, size, price, weight);
    }

    @Override
    public String toString() {
        return "ElbowPads{" +
                "material=" + material +
                ", size=" + size +
                ", price=" + price +
                ", weight=" + weight +
                '}';
    }
}
