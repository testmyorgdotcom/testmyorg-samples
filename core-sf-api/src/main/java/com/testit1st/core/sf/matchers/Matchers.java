package com.testit1st.core.sf.matchers;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

import com.sforce.soap.partner.sobject.SObject;

import org.hamcrest.Matcher;

public class Matchers {
  public static Matcher<SObject> hasField(final String fieldName, final String fieldValue){
    return new HasField(fieldName, fieldValue);
  }
  public static Matcher<SObject> client(){
    return new HasField("type", "Account");
  }
  public static Matcher<SObject> hasId(){
    return new HasField("Id", not(emptyOrNullString()));
  }
  public static Matcher<SObject> hasName(final String name){
    return new HasField("Name", is(equalTo(name)));
  }
}
