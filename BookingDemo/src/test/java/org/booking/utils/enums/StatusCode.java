package org.booking.utils.enums;

public enum StatusCode {
  OK(200),
  CREATED(201),
  BAD_REQUEST(400),
  RESOURCE(404),
  INTERNAL_SERVER_ERROR(500);

  public final int val;

  private StatusCode(int value) {
    this.val = value;
  }

  public int value() {
    return this.val;
  }

}
