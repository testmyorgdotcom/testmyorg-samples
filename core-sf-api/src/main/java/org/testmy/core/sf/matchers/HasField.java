package org.testmy.core.sf.matchers;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.Objects;
import java.util.function.Consumer;

import com.sforce.soap.partner.sobject.SObject;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class HasField extends TypeSafeMatcher<SObject> implements ConstructingMatcher {
  private String fieldName;
  private Matcher<? extends Object> fieldValueMatcher;
  private Consumer<SObject> constructLogic;
  
  public HasField(final String fieldName, final String fieldValue){
    this(fieldName, is(equalTo(fieldValue)));
    constructLogic = sObj -> sObj.setField(fieldName, fieldValue);
  }
  public HasField(final String fieldName, final Matcher<? extends Object> fieldvalueMatcher){
    Objects.requireNonNull(fieldName);
    this.fieldName = fieldName;
    this.fieldValueMatcher = fieldvalueMatcher;
    constructLogic = sObj -> {
      throw new UnsupportedOperationException("cannot update SObject if based on constructor with matcher");
    };
  }

  @Override
  public void describeTo(final Description description) {
    description.appendText("hasField(")
      .appendValue(fieldName)
    .appendText(")").appendText(": ").appendText(fieldValueMatcher.toString());
  }
  
  @Override
  protected boolean matchesSafely(final SObject item) {
    final Object itemFieldValue = item.getField(fieldName);
    return fieldValueMatcher.matches(itemFieldValue);
  }
  @Override
  public void visitForUpdate(final SObject sObject) {
    constructLogic.accept(sObject);
  }
}
