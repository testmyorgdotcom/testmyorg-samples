package org.testmy.core.sf;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.testmy.core.sf.matchers.Matchers.hasField;

import java.util.Optional;
import java.util.stream.IntStream;

import com.sforce.soap.partner.sobject.SObject;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.testmy.core.sf.matchers.ConstructingMatcher;

public class TestDataManagerTest {
  final TestDataManager dataManagerUnderTest = new TestDataManager();
  @Test
  public void hasACache(){
    assertThat(dataManagerUnderTest.getData(), is(emptyCollectionOf(SObject.class)));
  }
  @Test
  public void canAddDataIntoCache(){
    final int elementsInCache = 3;
    IntStream.range(0, elementsInCache).forEach(i -> dataManagerUnderTest.addToCache(new SObject()));

    assertThat(dataManagerUnderTest.getData(), hasSize(elementsInCache));
  }
  @Test
  public void findObject_findsObjectByMathcer(){
    final String fieldName = "field", fieldValue = "value";
    final Matcher<SObject> objMathcer = hasField(fieldName, fieldValue);
    final SObject objInCache = new SObject();
    objInCache.setField(fieldName, fieldValue);
    dataManagerUnderTest.addToCache(objInCache);

    final Optional<SObject> foundObject = dataManagerUnderTest.findObject(objMathcer);
    
    assertThat(foundObject.isPresent(), is(true));
    assertThat(foundObject.get(), is(objMathcer));
  }
  @Test
  public void getOrCreate_createsObjectIfNotExists(){
    final String fieldName = "field", fieldValue = "value";
    final ConstructingMatcher sObjectShape = hasField(fieldName, fieldValue);

    final SObject sObject = dataManagerUnderTest.getOrCreate(sObjectShape);
    
    assertThat(sObject, is(sObjectShape));
  }
  @Test(expected = UnsupportedOperationException.class)
  public void getOrCreate_doesNotSupportIfConsutrctedThroughMatcher(){
    final String fieldName = "field", fieldValue = "value";
    final ConstructingMatcher sObjectShape = hasField(fieldName, is(equalTo(fieldValue)));

    dataManagerUnderTest.getOrCreate(sObjectShape);
  }
}
