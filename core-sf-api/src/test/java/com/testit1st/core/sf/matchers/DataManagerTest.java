package com.testit1st.core.sf.matchers;

import static com.testit1st.core.sf.matchers.Matchers.hasField;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

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
    final Optional<SObject> foundObject = dataManager.findObject(byField);

    assertThat(foundObject, is(equalTo(Optional.empty())));
  }
  @Test
  public void findObjectByMatcher(){
    final String field = "field", value = "value";
    final SObject objectInCache = new SObject();
    objectInCache.setField(field, value);
    dataManager.addToCache(objectInCache);

    final Optional<SObject> foundObject = dataManager.findObject(hasField(field, value));

    assertThat(foundObject, not(equalTo(Optional.empty())));
    assertThat(foundObject.get(), is(equalTo(objectInCache)));
  }
}
