package AdminManager;

public class Product {
    String productName;
    String productCategory;
    String productDescription;
    double productPrice;

    public Product(String productName, String productCategory, String productDescription, double productPrice){
        this.productName = productName;
        this.productCategory = productCategory;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }

    public String getProductName(){
        return productName;
    }

    public void setProductName(String productName){
        this.productName = productName;
    }

    public String getProductCategory(){
        return productCategory;
    }

    public void setProductCategory(String productCategory){
        this.productCategory = productCategory;
    }

    public String getProductDescription(){
        return productDescription;
    }

    public void setProductDescription(String productDescription){
        this.productDescription = productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice){
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "This is a Product";
    }
}