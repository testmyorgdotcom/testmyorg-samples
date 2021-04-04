package org.testmy.screenplay.task;

public class URLParser {
    public static String parseObjectId(String objectUrl) {
        final String [] elements = objectUrl.split("/");
        return elements[elements.length - 2];
    }

    public static String parseObjectType(String objectUrl) {
        final String [] elements = objectUrl.split("/");
        return elements[elements.length - 3];
    }
}
