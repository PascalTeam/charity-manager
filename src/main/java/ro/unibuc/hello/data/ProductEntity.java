package ro.unibuc.hello.data;

import org.springframework.data.annotation.Id;

public class ProductEntity {
  @Id
  public String id;

  public String name;
  public int quantity;

  public ProductEntity() {
  }

  public ProductEntity(String name, int quantity) {
    this.name = name;
    this.quantity = quantity;
  }

  public ProductEntity(String id, String name, int quantity) {
    this.id = id;
    this.name = name;
    this.quantity = quantity;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return String.format(
        "Product[id=%s, name='%s', quantity='%s']",
        id, name, quantity);
  }
}
