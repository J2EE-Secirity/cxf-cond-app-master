package org.test.cxf.cond;

public enum HttpMethodEnum {
  GET, PUT, POST, DELETE, PATCH;

  @Override
  public String toString() {
   return super.toString().toLowerCase();
  }

  
}
