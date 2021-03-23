package com.testit1st.core.sf.matchers;

import java.util.List;
import java.util.Optional;

import com.sforce.soap.partner.sobject.SObject;

import org.hamcrest.Matcher;

public interface IDataManager {
  Optional<SObject> ensureSObject(Matcher<SObject> sObjectShape);
  List<SObject> findObjects(Matcher<SObject> sObjectShape);
}
