package com.testit1st.core.sf.matchers;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.sforce.soap.partner.sobject.SObject;

import org.hamcrest.Matcher;

public class DataManager implements IDataManager {
  private List<SObject> sObjects = new LinkedList<>();

  public List<SObject> getData() {
    return Collections.unmodifiableList(sObjects);
  }
  void addToCache(SObject object) {
    sObjects.add(object);
  }
  @Override
  public Optional<SObject> ensureSObject(Matcher<SObject> sObjectShape) {
    return sObjects.stream().filter(sObjectShape::matches).findFirst();
  }
  @Override
  public List<SObject> findObjects(Matcher<SObject> sObjectShape) {
    return sObjects.stream().filter(sObjectShape::matches).collect(Collectors.toList());
  }
}
