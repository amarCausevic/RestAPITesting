package org.booking.enums;

public enum Base {
  BASE_URI("https://restful-booker.herokuapp.com");
  private final String val;

  Base(String value) {
    this.val = value;
  }

  public String val() {
    return this.val;
  }
}
