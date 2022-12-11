public class ProductsPageObject {
    private String productName,  productCode,  stockLevel,
     stockLevelWarning,  UPCCode,  EANCode, JANCode, ISBNCode,  GTINCode, MPNCode;

    private String salePrice,productWidth,   productHeight,  productDepth, productWeight;

    public ProductsPageObject(String productName, String stockLevel, String stockLevelWarning, String UPCCode,
                              String EANCode, String JANCode, String ISBNCode, String GTINCode, String MPNCode, String salePrice,
                              String productWidth, String productHeight, String productDepth, String productWeight, String productCode) {
        this.productName = productName;
        this.stockLevel = stockLevel;
        this.stockLevelWarning = stockLevelWarning;
        this.UPCCode = UPCCode;
        this.EANCode = EANCode;
        this.JANCode = JANCode;
        this.ISBNCode = ISBNCode;
        this.GTINCode = GTINCode;
        this.MPNCode = MPNCode;
        this.salePrice = salePrice;
        this.productWidth = productWidth;
        this.productHeight = productHeight;
        this.productDepth = productDepth;
        this.productWeight = productWeight;
        this.productCode = productCode;
    }

    public ProductsPageObject(String productName, String salePrice, String productWidth, String productHeight, String productDepth) {
        this.productName = productName;
        this.salePrice = salePrice;
        this.productWidth = productWidth;
        this.productHeight = productHeight;
        this.productDepth = productDepth;
    }

    public ProductsPageObject(String productName, String salePrice) {
        this.productName = productName;
        this.salePrice = salePrice;
    }


    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductWidth() {
        return productWidth;
    }

    public String getProductHeight() {
        return productHeight;
    }

    public String getProductDepth() {
        return productDepth;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public String getProductCode() {return productCode;}

    public String getStockLevel() {return stockLevel;}

    public String getStockLevelWarning() {return stockLevelWarning;}

    public String getUPCCode() {return UPCCode;}

    public String getEANCode() {return EANCode;}

    public String getJANCode() {return JANCode;}

    public String getISBNCode() {return ISBNCode;}

    public String getGTINCode() {return GTINCode;}

    public String getMPNCode() {return MPNCode;}

    public String getProductWeight() {return productWeight;}
}
