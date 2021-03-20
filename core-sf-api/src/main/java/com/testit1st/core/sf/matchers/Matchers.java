package com.testit1st.core.sf.matchers;

public class Matchers {
  public static HasField hasField(final String fieldName, final String fieldValue){
    return new HasField(fieldName, fieldValue);
  }
}
