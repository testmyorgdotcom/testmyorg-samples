package org.testmy.core.sf;

import java.util.List;
import java.util.Optional;

import com.sforce.soap.partner.sobject.SObject;

import org.hamcrest.Matcher;
import org.testmy.core.sf.matchers.ConstructingMatcher;

public interface ITestDataManager {
  SObject getOrCreate(ConstructingMatcher sObjectShape);
  Optional<SObject> findObject(Matcher<SObject> sObjectShape);
  List<SObject> findObjects(Matcher<SObject> sObjectShape);
}
