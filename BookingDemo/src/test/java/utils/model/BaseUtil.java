package utils.model;

public enum BaseUtil {
  EMPTY_STRING("");

  private final String val;

  BaseUtil(String value) {
    this.val = value;
  }

  public String val() {
    return this.val;
  }
}
