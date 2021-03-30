package org.testmy.core.sf.matchers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.testmy.core.sf.matchers.Matchers.hasField;

import com.sforce.soap.partner.sobject.SObject;

import org.junit.Test;

public class HasFieldTest {
  @Test
  public void matchByField(){
    final String fieldName  = "FieldName";
    final String fieldValue = "FieldValue";
    final SObject sobject = new SObject();
    sobject.setField(fieldName, fieldValue);
    
    assertThat(sobject, hasField(fieldName, fieldValue));
  }
  @Test
  public void matchWithNullFieldValue(){
    final String fieldName  = "FieldName";
    final String fieldValue = null;
    final SObject sobject = new SObject();
    sobject.setField(fieldName, fieldValue);
  
    assertThat(sobject, hasField(fieldName, fieldValue));
  }
  @Test
  public void noMatchIfFieldValuesDifferent(){
    final String fieldName  = "FieldName";
    final String fieldValue = "FieldValue";
    final String fieldValueDifferent = "DifferentValue";
    final SObject sobject = new SObject();
    sobject.setField(fieldName, fieldValueDifferent);

    assertThat(sobject, not(hasField(fieldName, fieldValue)));
  }
  @Test
  public void noMatchIfFieldsDifferent(){
    final String fieldName  = "FieldName";
    final String fieldNameDifferent = "DifferentFieldName";
    final String fieldValue = "FieldValue";
    final SObject sobject = new SObject();
    sobject.setField(fieldNameDifferent, fieldValue);

    assertThat(sobject, not(hasField(fieldName, fieldValue)));
  }
  @Test(expected = NullPointerException.class)
  public void exceptionWithNullFieldName(){
    final String fieldValue = "FieldValue";

    hasField(null, fieldValue);
  }
}
