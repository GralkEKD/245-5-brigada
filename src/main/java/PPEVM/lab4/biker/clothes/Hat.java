package PPEVM.lab4.biker.clothes;

import PPEVM.lab4.biker.Size;

public class Hat extends Clothes{
    public Hat(Material material, Size size, double price) {
        super.price = price;
        super.size = size;
        super.material = material;
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
