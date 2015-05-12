package org.test.cxf.cond.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.http.HttpEntity;

/**
 *  
 */
public class StringHelper {

  public static String dateToGmdString(Date date) {
    String DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss z";
    final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
    sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
    String dateTimeString = sdf.format(date);
    return dateTimeString;
  }

  public static String inputStreamToString(final InputStream in, final boolean preserveLineBreaks) throws IOException {
    final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));
    final StringBuilder stringBuilder = new StringBuilder();
    String line = null;

    while ((line = bufferedReader.readLine()) != null) {
      stringBuilder.append(line);
      if (preserveLineBreaks) {
        stringBuilder.append("\n");
      }
    }

    bufferedReader.close();

    final String result = stringBuilder.toString();

    return result;
  }

  public static String inputStreamToString(final InputStream in) throws IOException {
    return inputStreamToString(in, false);
  }

  public static String httpEntityToString(final HttpEntity entity) throws IOException, IllegalStateException {
    return inputStreamToString(entity.getContent());
  }

}
