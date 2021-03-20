package com.proedoc;

public class URLParser {
  public static String parseUrl(String url){
    final String [] elements = url.split("/");
    return elements[elements.length - 2];
  }
}
