package org.test.cxf.cond;

import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response.Status;

import org.junit.Test;
import org.test.cxf.cond.rest.RestResource;

public class TestETagOnly extends AbstractTestClient {

  @Override
  protected String eTagMatchString() {
    return null;
  }

  @Override
  protected String eTagNoMatchString() {
    return null;
  }

  public static final EntityTag STRONG = new EntityTag("123abc");
  public static final EntityTag WEAK = new EntityTag("123abc", true);
  public static final EntityTag STRONG_NOMATCH = new EntityTag("456xyz");
  public static final EntityTag WEAK_NOMATCH = new EntityTag("456xyz", true);
  public static final EntityTag WILDCARD = new EntityTag("*");

  @Test
  public void ifMatch_put() {

    RestResource.setLastModifiedDate(null);

    System.out.println("| IfMatch PUT (" + STRONG + ") |  |  |  |  |  |  |  |  |");
    RestResource.setETag(STRONG);
    test(HttpMethodEnum.PUT, HttpHeaders.IF_MATCH, STRONG.toString(), null, null, Status.OK);
    test(HttpMethodEnum.PUT, HttpHeaders.IF_MATCH, STRONG_NOMATCH.toString(), null, null, Status.PRECONDITION_FAILED);
    test(HttpMethodEnum.PUT, HttpHeaders.IF_MATCH, WEAK_NOMATCH.toString(), null, null, Status.PRECONDITION_FAILED);
    test(HttpMethodEnum.PUT, HttpHeaders.IF_MATCH, WILDCARD.toString(), null, null, Status.OK);

    System.out.println("| IfMatch PUT (" + null + ") |  |  |  |  |  |  |  |  |");
    RestResource.setETag(null);
    test(HttpMethodEnum.PUT, HttpHeaders.IF_MATCH, WILDCARD.toString(), null, null, Status.PRECONDITION_FAILED);
 
    System.out.println("| IfMatch PUT (" + WEAK + ") |  |  |  |  |  |  |  |  |");
    RestResource.setETag(WEAK);
    test(HttpMethodEnum.PUT, HttpHeaders.IF_MATCH, WEAK.toString(), null, null, Status.PRECONDITION_FAILED);
    test(HttpMethodEnum.PUT, HttpHeaders.IF_MATCH, STRONG.toString(), null, null, Status.PRECONDITION_FAILED);
    test(HttpMethodEnum.PUT, HttpHeaders.IF_MATCH, WILDCARD.toString(), null, null, Status.PRECONDITION_FAILED);
 
  }

  @Test
  public void ifNoneMatch_get() {
    RestResource.setLastModifiedDate(null);

    System.out.println("| IfNoneMatch GET (" + STRONG + ") |  |  |  |  |  |  |  |  |");
    RestResource.setETag(STRONG);
    test(HttpMethodEnum.GET, HttpHeaders.IF_NONE_MATCH, STRONG.toString(), null, null, Status.NOT_MODIFIED);
    test(HttpMethodEnum.GET, HttpHeaders.IF_NONE_MATCH, WEAK.toString(), null, null, Status.NOT_MODIFIED);
    test(HttpMethodEnum.GET, HttpHeaders.IF_NONE_MATCH, WILDCARD.toString(), null, null, Status.NOT_MODIFIED);
    test(HttpMethodEnum.GET, HttpHeaders.IF_NONE_MATCH, STRONG_NOMATCH.toString(), null, null, Status.OK);

    System.out.println("| IfNoneMatch GET (" + WEAK + ") |  |  |  |  |  |  |  |  |");
    RestResource.setETag(WEAK);
    test(HttpMethodEnum.GET, HttpHeaders.IF_NONE_MATCH, WEAK.toString(), null, null, Status.NOT_MODIFIED);
    test(HttpMethodEnum.GET, HttpHeaders.IF_NONE_MATCH, STRONG.toString(), null, null, Status.NOT_MODIFIED);
    test(HttpMethodEnum.GET, HttpHeaders.IF_NONE_MATCH, WILDCARD.toString(), null, null, Status.NOT_MODIFIED);
}
  
}
