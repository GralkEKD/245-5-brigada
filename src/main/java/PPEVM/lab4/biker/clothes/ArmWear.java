package PPEVM.lab4.biker.clothes;

import PPEVM.lab4.biker.Size;

import java.util.Objects;

public class ArmWear extends Clothes implements java.io.Serializable{
    private ArmWearPieces type;
    public ArmWear() {}

    public ArmWear(Material material, Size size, double price, ArmWearPieces type) {
        super.material = material;
        super.size = size;
        super.price = price;
        this.type = type;
    }

    public ArmWearPieces getType() {
        return type;
    }

    public void setType(ArmWearPieces type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ArmWear armWear = (ArmWear) o;
        return type == armWear.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type);
    }

    @Override
    public String toString() {
        return "ArmWear{" +
                "type=" + type +
                ", material=" + material +
                ", size=" + size +
                ", price=" + price +
                '}';
    }
}
