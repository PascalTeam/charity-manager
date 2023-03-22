package ro.unibuc.hello.data;

public class DoneeProductEntity {
  public String productId;
  public int quantity;

  public DoneeProductEntity(String productId, int quantity) {
    this.productId = productId;
    this.quantity = quantity;
  }

  public String getProductId() {
    return productId;
  }

  public int getQuantity() {
    return quantity;
  }
}
