package com.proedoc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.sforce.soap.partner.sobject.SObject;

import org.junit.Test;

public class SfApiTest {
  @Test
  public void testQueryById(){
    final String sfId = "00609000002bHtAAAU";
    final SfApi sfApi = new SfApi();

    final SObject sfObject = sfApi.queryObject(sfId);

    assertNotNull(sfObject);
    assertEquals(sfId, sfObject.getId());
  }
}
