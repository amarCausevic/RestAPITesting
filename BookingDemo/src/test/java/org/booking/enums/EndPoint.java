package org.booking.enums;

public enum EndPoint {
  AUTH("/auth"),
  BOOKING("/booking"),
  PING("/ping");

  private final String val;

  EndPoint(String value) {
    this.val = value;
  }

  public String val() {
    return this.val;
  }
}
