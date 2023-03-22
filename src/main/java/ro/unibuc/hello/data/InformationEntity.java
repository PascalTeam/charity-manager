package ro.unibuc.hello.data;

import org.springframework.data.annotation.Id;

public class InformationEntity {

  @Id
  public String id;

  public String title;
  public String description;

  public InformationEntity() {
  }

  public InformationEntity(String title, String description) {
    this.title = title;
    this.description = description;
  }

  @Override
  public String toString() {
    return String.format(
        "Information[title='%s', description='%s']",
        id, title, description);
  }

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

}