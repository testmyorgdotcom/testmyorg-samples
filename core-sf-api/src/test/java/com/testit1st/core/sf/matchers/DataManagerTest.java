package com.testit1st.core.sf.matchers;

import static com.testit1st.core.sf.matchers.Matchers.hasField;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import com.sforce.soap.partner.sobject.SObject;

import org.hamcrest.Matcher;
import org.junit.Test;

public class DataManagerTest {
  final DataManager dataManager = new DataManager();
  @Test
  public void haveACacheToStoreDataInMemory(){
    assertThat(dataManager.getData(), is(emptyCollectionOf(SObject.class)));
  }
  @Test
  public void addObjectsToCache(){
    final int elementsInCache = 3;
    IntStream.range(0, elementsInCache).forEach(i -> dataManager.addToCache(new SObject()));

    assertThat(dataManager.getData(), hasSize(elementsInCache));
  }
  @Test
  public void returnOptionalByMatcher(){
    final Matcher<SObject> byField = hasField("MissingField", "AnyValeue");
    final Optional<SObject> foundObject = dataManager.ensureSObject(byField);

    assertThat(foundObject, is(equalTo(Optional.empty())));
  }
  @Test
  public void findObjectByMatcher(){
    final String field = "field", value = "value";
    final SObject objectInCache = new SObject();
    objectInCache.setField(field, value);
    dataManager.addToCache(objectInCache);

    final Optional<SObject> foundObject = dataManager.ensureSObject(hasField(field, value));

    assertThat(foundObject, not(equalTo(Optional.empty())));
    assertThat(foundObject.get(), is(equalTo(objectInCache)));
  }
  @Test
  public void findObjectByAnyHamcrestMatcher(){
    final String field1 = "field1", value1 = "value1", field2 = "field2", value2 = "value2";
    final SObject objectInCache = new SObject();
    objectInCache.setField(field1, value1);
    objectInCache.setField(field2, value2);
    dataManager.addToCache(objectInCache);

    final Optional<SObject> foundObjectAllMatchers = dataManager.ensureSObject(
      allOf(
        hasField(field1, value1),
        hasField(field2, value2)
    ));

    assertThat(foundObjectAllMatchers.get(), is(equalTo(objectInCache)));

    final Optional<SObject> foundObjectAnyMatchers = dataManager.ensureSObject(
      anyOf(
        hasField(field1, value1),
        hasField(field2, value2)
    ));

    assertThat(foundObjectAnyMatchers.get(), is(equalTo(objectInCache)));
  }
  @Test
  public void findObjectsByMatcher(){
    final int elementsInCache = 3;
    final String field = "field", value = "value";
    IntStream.range(0, elementsInCache).forEach(i -> {
      final SObject objectInCache = new SObject();
      objectInCache.setField(field, value);
      dataManager.addToCache(objectInCache);
    });

    final List<SObject> foundObjects = dataManager.findObjects(hasField(field, value));

    assertThat(foundObjects, hasSize(elementsInCache));
  }
}
