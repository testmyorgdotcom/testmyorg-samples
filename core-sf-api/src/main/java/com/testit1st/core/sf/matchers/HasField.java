package com.testit1st.core.sf.matchers;

import static org.hamcrest.Matchers.equalTo;

import java.util.Objects;

import com.sforce.soap.partner.sobject.SObject;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class HasField extends TypeSafeMatcher<SObject> {
  final String fieldName;
  final Matcher<? extends Object> fieldValueMatcher;

  public HasField(final String fieldName, final String fieldValue){
    this(fieldName, equalTo(fieldValue));
  }
  public HasField(final String fieldName, final Matcher<? extends Object> fieldValueMatcher){
    Objects.requireNonNull(fieldName);
    this.fieldName = fieldName;
    this.fieldValueMatcher = fieldValueMatcher;
  }

  @Override
  public void describeTo(final Description description) {
    description.appendText("hasField(")
      .appendValue(fieldName)
    .appendText(")").appendText(": ").appendText(Objects.toString(fieldValueMatcher, "null"));
  }
  
  @Override
  protected boolean matchesSafely(final SObject item) {
    final Object itemFieldValue = item.getField(fieldName);
    return fieldValueMatcher.matches(itemFieldValue);
  }
}
