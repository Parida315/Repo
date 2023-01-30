package objectClasses;

public class OrderFields {

    private String productName,quantity, unitPrice,discount,shipping,totalTax;

    public OrderFields(String quantity, String unitPrice, String discount, String shipping, String totalTax, String productName) {
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.shipping = shipping;
        this.totalTax = totalTax;
        this.productName = productName;
    }

    public OrderFields(String productName) {
        this.productName = productName;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getUnitePrice() {
        return unitPrice;
    }

    public String getDiscount() {
        return discount;
    }

    public String getShipping() {
        return shipping;
    }

    public String getTotalTax() {
        return totalTax;
    }

    public String getProductName() {
        return productName;
    }
}
