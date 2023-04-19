package dto;

public class Product {

    private int id;
    private String productName;
    private Float weight;
    private String info;
    private Float price;
    private Integer quantity;
    private Float purchasePrice;

    public Product(String productName, Float weight, String info, Float price, Integer quantity, Float purchasePrice) {
        this.productName = productName;
        this.weight = weight;
        this.info = info;
        this.price = price;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public Float getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Float purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return
                "productName='" + productName + '\'' +
                ", weight=" + weight +
                ", description='" + info + '\'' +
                ", price=" + price +
                ", quantity=" + quantity;
    }
}
