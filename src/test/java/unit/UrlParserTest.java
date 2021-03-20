package unit;

import static org.junit.Assert.assertEquals;

import com.proedoc.URLParser;

import org.junit.Test;

public class UrlParserTest {
  @Test
  public void testParse(){
    final String url = "https://brave-narwhal-qydbkb-dev-ed.lightning.force.com/lightning/r/Opportunity/00609000002bI9tAAE/view";
    final String id = URLParser.parseUrl(url);

    assertEquals("00609000002bI9tAAE", id);
  }

}
