package com.mobiquity.exception;

import lombok.Data;

@Data
public class APIException extends Exception {

  private String line;
  private long lineNumber;

  public APIException(String message, Exception e) {
    super(message, e);
  }

  public APIException(String message) {
    super(message);
  }

  public APIException(String message, String line, long lineNumber) {
    super(message);
    this.line = line;
    this.lineNumber = lineNumber;
  }
}
