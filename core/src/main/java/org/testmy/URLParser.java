package org.testmy;

import java.net.MalformedURLException;
import java.net.URL;

import org.testmy.error.TestRuntimeException;

public class URLParser {
    public static String parseObjectId(String objectUrl) {
        final String[] elements = objectUrl.split("/");
        return elements[elements.length - 2];
    }

    public static String parseObjectType(String objectUrl) {
        final String[] elements = objectUrl.split("/");
        return elements[elements.length - 3];
    }

    public static String extractMainUrl(final String completeUrl) {

        try {
            final URL url = new URL(completeUrl);
            return String.format("%s://%s", url.getProtocol(), url.getHost());
        }
        catch (final MalformedURLException e) {
            throw new TestRuntimeException(e);
        }
    }

    public static String extractDomain(String completeUrl) {

        try {
            final URL url = new URL(completeUrl);
            return url.getHost();
        }
        catch (final MalformedURLException e) {
            throw new TestRuntimeException(e);
        }
    }
}
