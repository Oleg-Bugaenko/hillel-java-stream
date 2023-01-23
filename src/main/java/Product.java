import java.time.LocalDate;

public class Product {
    private String type;
    private Double price;
    private boolean discount;
    private LocalDate createDate;

    public Product(String type, Double price) {
        this.type = type;
        this.price = price;
    }

    public Product(String type, Double price, boolean discount) {
        this.type = type;
        this.price = price;
        this.discount = discount;
    }

    public Product(String type, Double price, boolean discount, LocalDate dateCreating) {
        this.type = type;
        this.price = price;
        this.discount = discount;
        this.createDate = dateCreating;
    }

    public String getType() {
        return type;
    }

    public Double getPrice() {
        return price;
    }

    public boolean isDiscount() {
        return discount;
    }


    public void setPrice(Double price) {
        this.price = price;
    }


    public LocalDate getCreateDate() {
        return createDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "type='" + type + '\'' +
                ", price=" + price +
                '}';
    }

    public String printAllData() {
        return "{" + "type: '" + type + '\'' +
                ", price= " + price +
                ", discount: " + discount +
                ", createDate: " + createDate + "}";
    }

}
