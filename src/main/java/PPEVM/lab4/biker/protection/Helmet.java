package PPEVM.lab4.biker.protection;

public class Helmet extends Protection implements java.io.Serializable{
    public Helmet() { super(); }

    public Helmet(Material material, Size size, double price, double weight) {
        super(material, size, price, weight);
    }

    @Override
    public String toString() {
        return "Helmet{" +
                "material=" + material +
                ", size=" + size +
                ", price=" + price +
                ", weight=" + weight +
                '}';
    }
}
