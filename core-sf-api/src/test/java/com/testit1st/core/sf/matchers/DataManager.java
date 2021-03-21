package com.testit1st.core.sf.matchers;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.sforce.soap.partner.sobject.SObject;

import org.hamcrest.Matcher;

public class DataManager {
  private List<SObject> sObjects = new LinkedList<>();

  public Collection<SObject> getData() {
    return sObjects;
  }

  public Optional<SObject> findObject(Matcher<SObject> byField) {
    return sObjects.stream().filter(byField::matches).findFirst();
  }

  public void addToCache(SObject object) {
    sObjects.add(object);
  }
}
