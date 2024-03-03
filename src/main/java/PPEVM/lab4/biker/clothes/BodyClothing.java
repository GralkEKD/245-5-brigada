package PPEVM.lab4.biker.clothes;

import PPEVM.lab4.biker.Size;

import java.util.Objects;

public class BodyClothing extends Clothes{
    private BodyClothingPieces type;
    public BodyClothing(Material material, Size size, double price, BodyClothingPieces type) {
        super.material = material;
        super.size = size;
        super.price = price;
        this.type = type;
    }

    public BodyClothingPieces getType() {
        return type;
    }

    public void setType(BodyClothingPieces type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BodyClothing that = (BodyClothing) o;
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
