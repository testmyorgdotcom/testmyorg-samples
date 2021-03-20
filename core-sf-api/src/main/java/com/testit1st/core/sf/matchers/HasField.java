package com.testit1st.core.sf.matchers;

import java.util.Objects;

import javax.annotation.Nonnull;

import com.sforce.soap.partner.sobject.SObject;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HasField extends TypeSafeMatcher<SObject> {
  @Nonnull
  final String fieldName;
  final Object fieldValue;

  @Override
  public void describeTo(final Description description) {
    description.appendText("hasField(")
      .appendValue(fieldName)
    .appendText(")").appendText(": ").appendText(Objects.toString(fieldValue, "null"));
  }
  
  @Override
  protected boolean matchesSafely(final SObject item) {
    final Object itemFieldValue = item.getField(fieldName);
    return Objects.equals(fieldValue, itemFieldValue);
  }
}
