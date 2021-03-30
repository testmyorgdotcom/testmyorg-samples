package org.testmy.core.sf;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.sforce.soap.partner.sobject.SObject;

import org.hamcrest.Matcher;
import org.testmy.core.sf.matchers.ConstructingMatcher;

public class TestDataManager implements ITestDataManager {
  private List<SObject> sObjects = new LinkedList<>();

  public List<SObject> getData() {
    return Collections.unmodifiableList(sObjects);
  }
  void addToCache(SObject object) {
    sObjects.add(object);
  }
  @Override
  public SObject getOrCreate(ConstructingMatcher sObjectShape) {
    return findObject(sObjectShape).orElseGet(() -> {
      final SObject result = new SObject("Type has to be initialized 1st, but can be changed later");
      sObjectShape.visitForUpdate(result);
      return result;
    });
  }
  @Override
  public Optional<SObject> findObject(Matcher<SObject> sObjectShape) {
    return sObjects.stream().filter(sObjectShape::matches).findFirst();
  }
  @Override
  public List<SObject> findObjects(Matcher<SObject> sObjectShape) {
    return sObjects.stream().filter(sObjectShape::matches).collect(Collectors.toList());
  }
}
