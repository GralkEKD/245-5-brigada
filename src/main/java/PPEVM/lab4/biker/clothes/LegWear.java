package PPEVM.lab4.biker.clothes;

import PPEVM.lab4.biker.Size;

import java.util.Objects;

public class LegWear extends Clothes implements java.io.Serializable{
    private LegWearPieces type;

    public LegWear() {
    }

    public LegWear(Material material, Size size, double price, LegWearPieces type) {
        super.material = material;
        super.size = size;
        super.price = price;
        this.type = type;
    }

    public LegWearPieces getType() {
        return type;
    }

    public void setType(LegWearPieces type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LegWear legWear = (LegWear) o;
        return type == legWear.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type);
    }

    @Override
    public String toString() {
        return "LegWear{" +
                "type=" + type +
                ", material=" + material +
                ", size=" + size +
                ", price=" + price +
                '}';
    }
}
