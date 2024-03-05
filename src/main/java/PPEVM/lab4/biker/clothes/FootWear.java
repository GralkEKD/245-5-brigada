package PPEVM.lab4.biker.clothes;

import PPEVM.lab4.biker.Size;

import java.util.Objects;

public class FootWear extends Clothes implements java.io.Serializable{
    private FootWearPieces type;
    public FootWear() {}
    public FootWear(Material material, Size size, double price, FootWearPieces type) {
        super.material = material;
        super.size = size;
        super.price = price;
        this.type = type;
    }

    public FootWearPieces getType() {
        return type;
    }

    public void setType(FootWearPieces type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FootWear footWear = (FootWear) o;
        return type == footWear.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type);
    }

    @Override
    public String toString() {
        return "FootWear{" +
                "type=" + type +
                ", material=" + material +
                ", size=" + size +
                ", price=" + price +
                '}';
    }
}
