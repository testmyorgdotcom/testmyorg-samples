package org.testmy.core.sf.matchers;

import static org.junit.Assert.assertThat;
import static org.testmy.core.sf.matchers.Matchers.hasId;

import com.sforce.soap.partner.sobject.SObject;

import org.junit.Test;

public class HasIdTest {
  @Test
  public void matchIfWithId(){
    final String sfId = "005xyz...";
    final SObject sobject = new SObject();
    sobject.setId(sfId);

    assertThat(sobject, hasId());
  }
}
