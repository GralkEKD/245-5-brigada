package PPEVM.lab4.biker.protection;

import java.util.Objects;

public abstract class Protection {
    protected Material material;
    protected Size size;
    protected double price;
    protected double weight;

    protected Protection() {}
    protected Protection(Material material, Size size, double price, double weight) {
        this.material = material;
        this.size = size;
        this.price = price;
        this.weight = weight;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Protection that = (Protection) o;
        return Double.compare(that.price, price) == 0 && Double.compare(that.weight, weight) == 0 && material == that.material && size == that.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(material, size, price, weight);
    }
}
