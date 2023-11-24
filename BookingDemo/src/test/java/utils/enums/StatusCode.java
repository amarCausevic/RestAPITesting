package utils.enums;

public enum StatusCode {
  OK(200),
  CREATED(201),
  BAD_REQUEST(400),
  RESOURCE(404),
  INTERNAL_SERVER_ERROR(500);

  private final int val;

  private StatusCode(int value) {
    this.val = value;
  }

  public int val() {
    return this.val;
  }
}
