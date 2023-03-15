package ro.unibuc.hello.data;

import java.util.List;

public class DoneeProductsEntity {
  public String doneeId;
  public List<DoneeProductEntity> products;

  public DoneeProductsEntity() {
  }

  public DoneeProductsEntity(String doneeId, List<DoneeProductEntity> products) {
    this.doneeId = doneeId;
    this.products = products;
  }
}
