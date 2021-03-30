package org.testmy.core.sf.matchers;

import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;

public class Matchers {
  public static HasField hasField(final String fieldName, final String fieldValue){
    return new HasField(fieldName, fieldValue);
  }
  public static HasField client(){
    return new HasField("type", "Account");
  }
  public static HasField hasId(){
    return new HasField("Id", not(emptyOrNullString())); //TODO: support anyfield approach
  }
  public static HasField hasName(final String name){
    return new HasField("Name", name);
  }
}
