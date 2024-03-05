package PPEVM.lab4.biker.clothes;

import PPEVM.lab4.biker.Size;

import java.util.Objects;

public class BodyWear extends Clothes implements java.io.Serializable{
    private BodyWearPieces type;

    public BodyWear() {
    }

    public BodyWear(Material material, Size size, double price, BodyWearPieces type) {
        super.material = material;
        super.size = size;
        super.price = price;
        this.type = type;
    }

    public BodyWearPieces getType() {
        return type;
    }

    public void setType(BodyWearPieces type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BodyWear that = (BodyWear) o;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type);
    }

    @Override
    public String toString() {
        return "BodyClothing{" +
                "type=" + type +
                ", material=" + material +
                ", size=" + size +
                ", price=" + price +
                '}';
    }
}
