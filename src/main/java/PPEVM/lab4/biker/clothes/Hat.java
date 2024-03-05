package PPEVM.lab4.biker.clothes;

import PPEVM.lab4.biker.Size;

import java.util.Objects;

public class Hat extends Clothes implements java.io.Serializable{

    private Hats type;

    public Hat() {
    }

    public Hat(Material material, Size size, double price, Hats type) {
        super.price = price;
        super.size = size;
        super.material = material;
        this.type = type;
    }

    public Hats getType() {
        return type;
    }

    public void setType(Hats type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Hat hat = (Hat) o;
        return type == hat.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type);
    }

    @Override
    public String toString() {
        return "Hat{" +
                "material=" + material +
                ", size=" + size +
                ", price=" + price +
                '}';
    }
}