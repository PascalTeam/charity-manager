package ro.unibuc.hello.dto;

public class Response<T> {
  public String message;
  public T data;

  public Response(String message, T data) {
    this.message = message;
    this.data = data;
  }

  public String getMessage() {
    return message;
  }

  public T getData() {
    return data;
  }

}
