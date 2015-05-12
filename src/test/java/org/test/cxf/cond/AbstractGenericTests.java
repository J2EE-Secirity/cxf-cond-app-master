package org.test.cxf.cond;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response.Status;

import org.junit.Ignore;
import org.junit.Test;
import org.test.cxf.cond.rest.RestResource;

public abstract class AbstractGenericTests extends AbstractTestClient {

  @Test
  public void testPost() {

    System.out.println("| POST   |  |  |  |  |  |  |  |  |");

    test(HttpMethodEnum.POST, null, null, null, null, Status.CREATED);
  }

  @Test
  public void testPost_eTag() {
    RestResource.setLastModifiedDate(null);

    System.out.println("| POST + eTag   |  |  |  |  |  |  |  |  |");

    test(HttpMethodEnum.POST, HttpHeaders.IF_MATCH, eTagNoMatchString(), null, null, Status.PRECONDITION_FAILED);
    test(HttpMethodEnum.POST, HttpHeaders.IF_MATCH, eTagMatchString(), null, null, Status.CREATED);
    test(HttpMethodEnum.POST, HttpHeaders.IF_NONE_MATCH, eTagNoMatchString(), null, null, Status.CREATED);
    test(HttpMethodEnum.POST, HttpHeaders.IF_NONE_MATCH, eTagMatchString(), null, null, Status.PRECONDITION_FAILED);
  }

  @Test
  public void testPost_lastModifiedDate_eTag() {

    System.out.println("| POST + eTag + lastModifiedDate   |  |  |  |  |  |  |  |  |");

    test(HttpMethodEnum.POST, HttpHeaders.IF_MATCH, eTagNoMatchString(), HttpHeaders.IF_MODIFIED_SINCE, LAST_MODIFIED_DATE_NO_MATCH, Status.CREATED);
    test(HttpMethodEnum.POST, HttpHeaders.IF_MATCH, eTagNoMatchString(), HttpHeaders.IF_MODIFIED_SINCE, LAST_MODIFIED_DATE_MATCH, Status.NOT_MODIFIED);
    test(HttpMethodEnum.POST, HttpHeaders.IF_MATCH, eTagNoMatchString(), HttpHeaders.IF_UNMODIFIED_SINCE, LAST_MODIFIED_DATE_NO_MATCH, Status.PRECONDITION_FAILED);
    test(HttpMethodEnum.POST, HttpHeaders.IF_MATCH, eTagNoMatchString(), HttpHeaders.IF_UNMODIFIED_SINCE, LAST_MODIFIED_DATE_MATCH, Status.CREATED);
  }

  @Test
  public void testPost_lastModifiedDate() {
    RestResource.setETag(null);

    System.out.println("| POST + lastModifiedDate   |  |  |  |  |  |  |  |  |");

    test(HttpMethodEnum.POST, null, null, HttpHeaders.IF_MODIFIED_SINCE, LAST_MODIFIED_DATE_NO_MATCH, Status.CREATED);
    test(HttpMethodEnum.POST, null, null, HttpHeaders.IF_MODIFIED_SINCE, LAST_MODIFIED_DATE_MATCH, Status.NOT_MODIFIED);
    test(HttpMethodEnum.POST, null, null, HttpHeaders.IF_UNMODIFIED_SINCE, LAST_MODIFIED_DATE_NO_MATCH, Status.PRECONDITION_FAILED);
    test(HttpMethodEnum.POST, null, null, HttpHeaders.IF_UNMODIFIED_SINCE, LAST_MODIFIED_DATE_MATCH, Status.CREATED);
  }

  @Test
  public void testPut_eTag() {
    RestResource.setLastModifiedDate(null);

    System.out.println("| PUT + eTag   |  |  |  |  |  |  |  |  |");

    test(HttpMethodEnum.PUT, HttpHeaders.IF_MATCH, eTagNoMatchString(), null, null, Status.PRECONDITION_FAILED);
    test(HttpMethodEnum.PUT, HttpHeaders.IF_MATCH, eTagMatchString(), null, null, Status.OK);
    test(HttpMethodEnum.PUT, HttpHeaders.IF_NONE_MATCH, eTagNoMatchString(), null, null, Status.OK);
    test(HttpMethodEnum.PUT, HttpHeaders.IF_NONE_MATCH, eTagMatchString(), null, null, Status.PRECONDITION_FAILED);
  }

  @Test
  public void testPut_eTag_Wildcard() {
    RestResource.setLastModifiedDate(null);

    System.out.println("| PUT + eTag '*'   |  |  |  |  |  |  |  |  |");

    test(HttpMethodEnum.PUT, HttpHeaders.IF_MATCH, "*", null, null, Status.OK);
    test(HttpMethodEnum.PUT, HttpHeaders.IF_NONE_MATCH, "*", null, null, Status.PRECONDITION_FAILED);
  }

  @Test
  public void testPut_lastModifiedDate() {
    RestResource.setETag(null);

    System.out.println("| PUT +  lastModifiedDate   |  |  |  |  |  |  |  |  |");

    test(HttpMethodEnum.PUT, null, null, HttpHeaders.IF_MODIFIED_SINCE, LAST_MODIFIED_DATE_NO_MATCH, Status.OK);
    test(HttpMethodEnum.PUT, null, null, HttpHeaders.IF_MODIFIED_SINCE, LAST_MODIFIED_DATE_MATCH, Status.NOT_MODIFIED);
    test(HttpMethodEnum.PUT, null, null, HttpHeaders.IF_UNMODIFIED_SINCE, LAST_MODIFIED_DATE_NO_MATCH, Status.PRECONDITION_FAILED);
    test(HttpMethodEnum.PUT, null, null, HttpHeaders.IF_UNMODIFIED_SINCE, LAST_MODIFIED_DATE_MATCH, Status.OK);
  }

  @Test
  public void testPut_lastModifiedDate_eTag() {

    System.out.println("| PUT + eTag + lastModifiedDate   |  |  |  |  |  |  |  |  |");

    test(HttpMethodEnum.PUT, HttpHeaders.IF_MATCH, eTagNoMatchString(), HttpHeaders.IF_MODIFIED_SINCE, LAST_MODIFIED_DATE_NO_MATCH, Status.OK);
    test(HttpMethodEnum.PUT, HttpHeaders.IF_MATCH, eTagNoMatchString(), HttpHeaders.IF_MODIFIED_SINCE, LAST_MODIFIED_DATE_MATCH, Status.NOT_MODIFIED);
    test(HttpMethodEnum.PUT, HttpHeaders.IF_MATCH, eTagNoMatchString(), HttpHeaders.IF_UNMODIFIED_SINCE, LAST_MODIFIED_DATE_NO_MATCH, Status.PRECONDITION_FAILED);
    test(HttpMethodEnum.PUT, HttpHeaders.IF_MATCH, eTagNoMatchString(), HttpHeaders.IF_UNMODIFIED_SINCE, LAST_MODIFIED_DATE_MATCH, Status.OK);

    test(HttpMethodEnum.PUT, HttpHeaders.IF_MATCH, eTagMatchString(), HttpHeaders.IF_MODIFIED_SINCE, LAST_MODIFIED_DATE_NO_MATCH, Status.OK);
    test(HttpMethodEnum.PUT, HttpHeaders.IF_MATCH, eTagMatchString(), HttpHeaders.IF_MODIFIED_SINCE, LAST_MODIFIED_DATE_MATCH, Status.OK);
    test(HttpMethodEnum.PUT, HttpHeaders.IF_MATCH, eTagMatchString(), HttpHeaders.IF_UNMODIFIED_SINCE, LAST_MODIFIED_DATE_NO_MATCH, Status.OK);
    test(HttpMethodEnum.PUT, HttpHeaders.IF_MATCH, eTagMatchString(), HttpHeaders.IF_UNMODIFIED_SINCE, LAST_MODIFIED_DATE_MATCH, Status.OK);

    test(HttpMethodEnum.PUT, HttpHeaders.IF_NONE_MATCH, eTagNoMatchString(), HttpHeaders.IF_MODIFIED_SINCE, LAST_MODIFIED_DATE_NO_MATCH, Status.OK);
    test(HttpMethodEnum.PUT, HttpHeaders.IF_NONE_MATCH, eTagNoMatchString(), HttpHeaders.IF_MODIFIED_SINCE, LAST_MODIFIED_DATE_MATCH, Status.OK);
    test(HttpMethodEnum.PUT, HttpHeaders.IF_NONE_MATCH, eTagNoMatchString(), HttpHeaders.IF_UNMODIFIED_SINCE, LAST_MODIFIED_DATE_NO_MATCH, Status.OK);
    test(HttpMethodEnum.PUT, HttpHeaders.IF_NONE_MATCH, eTagNoMatchString(), HttpHeaders.IF_UNMODIFIED_SINCE, LAST_MODIFIED_DATE_MATCH, Status.OK);

    test(HttpMethodEnum.PUT, HttpHeaders.IF_NONE_MATCH, eTagMatchString(), HttpHeaders.IF_MODIFIED_SINCE, LAST_MODIFIED_DATE_NO_MATCH, Status.OK);
    test(HttpMethodEnum.PUT, HttpHeaders.IF_NONE_MATCH, eTagMatchString(), HttpHeaders.IF_MODIFIED_SINCE, LAST_MODIFIED_DATE_MATCH, Status.NOT_MODIFIED);
    test(HttpMethodEnum.PUT, HttpHeaders.IF_NONE_MATCH, eTagMatchString(), HttpHeaders.IF_UNMODIFIED_SINCE, LAST_MODIFIED_DATE_NO_MATCH, Status.PRECONDITION_FAILED);
    test(HttpMethodEnum.PUT, HttpHeaders.IF_NONE_MATCH, eTagMatchString(), HttpHeaders.IF_UNMODIFIED_SINCE, LAST_MODIFIED_DATE_MATCH, Status.OK);
  }

  @Test
  public void test_428() {

    System.out.println("| 428   |  |  |  |  |  |  |  |  |");

    test(HttpMethodEnum.PATCH, null, null, null, null, 428);
    test(HttpMethodEnum.PUT, null, null, null, null, 428);
    test(HttpMethodEnum.DELETE, null, null, null, null, 428);
  }

  @Test
  @Ignore
  public void testPatch_eTag() {
    RestResource.setLastModifiedDate(null);

    test(HttpMethodEnum.PATCH, HttpHeaders.IF_MATCH, eTagNoMatchString(), null, null, Status.PRECONDITION_FAILED);
    test(HttpMethodEnum.PATCH, HttpHeaders.IF_MATCH, eTagMatchString(), null, null, Status.OK);
    test(HttpMethodEnum.PATCH, HttpHeaders.IF_NONE_MATCH, eTagNoMatchString(), null, null, Status.OK);
    test(HttpMethodEnum.PATCH, HttpHeaders.IF_NONE_MATCH, eTagMatchString(), null, null, Status.PRECONDITION_FAILED);
  }

  @Test
  @Ignore
  public void testPatch_lastModifiedDate() {
    RestResource.setETag(null);

    test(HttpMethodEnum.PATCH, null, null, HttpHeaders.IF_MODIFIED_SINCE, LAST_MODIFIED_DATE_NO_MATCH, Status.OK);
    test(HttpMethodEnum.PATCH, null, null, HttpHeaders.IF_MODIFIED_SINCE, LAST_MODIFIED_DATE_MATCH, Status.NOT_MODIFIED);
    test(HttpMethodEnum.PATCH, null, null, HttpHeaders.IF_UNMODIFIED_SINCE, LAST_MODIFIED_DATE_NO_MATCH, Status.PRECONDITION_FAILED);
    test(HttpMethodEnum.PATCH, null, null, HttpHeaders.IF_UNMODIFIED_SINCE, LAST_MODIFIED_DATE_MATCH, Status.OK);
  }

  @Test
  @Ignore
  public void testPatch_lastModifiedDate_eTag() {

    test(HttpMethodEnum.PATCH, HttpHeaders.IF_MATCH, eTagNoMatchString(), HttpHeaders.IF_MODIFIED_SINCE, LAST_MODIFIED_DATE_NO_MATCH, Status.OK);
    test(HttpMethodEnum.PATCH, HttpHeaders.IF_MATCH, eTagNoMatchString(), HttpHeaders.IF_MODIFIED_SINCE, LAST_MODIFIED_DATE_MATCH, Status.NOT_MODIFIED);
    test(HttpMethodEnum.PATCH, HttpHeaders.IF_MATCH, eTagNoMatchString(), HttpHeaders.IF_UNMODIFIED_SINCE, LAST_MODIFIED_DATE_NO_MATCH, Status.PRECONDITION_FAILED);
    test(HttpMethodEnum.PATCH, HttpHeaders.IF_MATCH, eTagNoMatchString(), HttpHeaders.IF_UNMODIFIED_SINCE, LAST_MODIFIED_DATE_MATCH, Status.OK);

    test(HttpMethodEnum.PATCH, HttpHeaders.IF_MATCH, eTagMatchString(), HttpHeaders.IF_MODIFIED_SINCE, LAST_MODIFIED_DATE_NO_MATCH, Status.OK);
    test(HttpMethodEnum.PATCH, HttpHeaders.IF_MATCH, eTagMatchString(), HttpHeaders.IF_MODIFIED_SINCE, LAST_MODIFIED_DATE_MATCH, Status.OK);
    test(HttpMethodEnum.PATCH, HttpHeaders.IF_MATCH, eTagMatchString(), HttpHeaders.IF_UNMODIFIED_SINCE, LAST_MODIFIED_DATE_NO_MATCH, Status.OK);
    test(HttpMethodEnum.PATCH, HttpHeaders.IF_MATCH, eTagMatchString(), HttpHeaders.IF_UNMODIFIED_SINCE, LAST_MODIFIED_DATE_MATCH, Status.OK);

    test(HttpMethodEnum.PATCH, HttpHeaders.IF_NONE_MATCH, eTagNoMatchString(), HttpHeaders.IF_MODIFIED_SINCE, LAST_MODIFIED_DATE_NO_MATCH, Status.OK);
    test(HttpMethodEnum.PATCH, HttpHeaders.IF_NONE_MATCH, eTagNoMatchString(), HttpHeaders.IF_MODIFIED_SINCE, LAST_MODIFIED_DATE_MATCH, Status.OK);
    test(HttpMethodEnum.PATCH, HttpHeaders.IF_NONE_MATCH, eTagNoMatchString(), HttpHeaders.IF_UNMODIFIED_SINCE, LAST_MODIFIED_DATE_NO_MATCH, Status.OK);
    test(HttpMethodEnum.PATCH, HttpHeaders.IF_NONE_MATCH, eTagNoMatchString(), HttpHeaders.IF_UNMODIFIED_SINCE, LAST_MODIFIED_DATE_MATCH, Status.OK);

    test(HttpMethodEnum.PATCH, HttpHeaders.IF_NONE_MATCH, eTagMatchString(), HttpHeaders.IF_MODIFIED_SINCE, LAST_MODIFIED_DATE_NO_MATCH, Status.OK);
    test(HttpMethodEnum.PATCH, HttpHeaders.IF_NONE_MATCH, eTagMatchString(), HttpHeaders.IF_MODIFIED_SINCE, LAST_MODIFIED_DATE_MATCH, Status.NOT_MODIFIED);
    test(HttpMethodEnum.PATCH, HttpHeaders.IF_NONE_MATCH, eTagMatchString(), HttpHeaders.IF_UNMODIFIED_SINCE, LAST_MODIFIED_DATE_NO_MATCH, Status.PRECONDITION_FAILED);
    test(HttpMethodEnum.PATCH, HttpHeaders.IF_NONE_MATCH, eTagMatchString(), HttpHeaders.IF_UNMODIFIED_SINCE, LAST_MODIFIED_DATE_MATCH, Status.OK);
  }

  @Test
  public void testDelete_eTag() {
    RestResource.setLastModifiedDate(null);

    System.out.println("| DELETE + eTag   |  |  |  |  |  |  |  |  |");

    test(HttpMethodEnum.DELETE, HttpHeaders.IF_MATCH, eTagNoMatchString(), null, null, Status.PRECONDITION_FAILED);
    test(HttpMethodEnum.DELETE, HttpHeaders.IF_MATCH, eTagMatchString(), null, null, Status.NO_CONTENT);
    test(HttpMethodEnum.DELETE, HttpHeaders.IF_NONE_MATCH, eTagNoMatchString(), null, null, Status.NO_CONTENT);
    test(HttpMethodEnum.DELETE, HttpHeaders.IF_NONE_MATCH, eTagMatchString(), null, null, Status.PRECONDITION_FAILED);
  }

  @Test
  public void testDelete_lastModifiedDate() {
    RestResource.setETag(null);

    System.out.println("| DELETE + lastModifiedDate   |  |  |  |  |  |  |  |  |");

    test(HttpMethodEnum.DELETE, null, null, HttpHeaders.IF_MODIFIED_SINCE, LAST_MODIFIED_DATE_NO_MATCH, Status.NO_CONTENT);
    test(HttpMethodEnum.DELETE, null, null, HttpHeaders.IF_MODIFIED_SINCE, LAST_MODIFIED_DATE_MATCH, Status.NOT_MODIFIED);
    test(HttpMethodEnum.DELETE, null, null, HttpHeaders.IF_UNMODIFIED_SINCE, LAST_MODIFIED_DATE_NO_MATCH, Status.PRECONDITION_FAILED);
    test(HttpMethodEnum.DELETE, null, null, HttpHeaders.IF_UNMODIFIED_SINCE, LAST_MODIFIED_DATE_MATCH, Status.NO_CONTENT);
  }

  @Test
  public void testDelete_lastModifiedDate_eTag() {

    System.out.println("| DELETE + eTag + lastModifiedDate   |  |  |  |  |  |  |  |  |");

    test(HttpMethodEnum.DELETE, HttpHeaders.IF_MATCH, eTagNoMatchString(), HttpHeaders.IF_MODIFIED_SINCE, LAST_MODIFIED_DATE_NO_MATCH, Status.NO_CONTENT);
    test(HttpMethodEnum.DELETE, HttpHeaders.IF_MATCH, eTagNoMatchString(), HttpHeaders.IF_MODIFIED_SINCE, LAST_MODIFIED_DATE_MATCH, Status.NOT_MODIFIED);
    test(HttpMethodEnum.DELETE, HttpHeaders.IF_MATCH, eTagNoMatchString(), HttpHeaders.IF_UNMODIFIED_SINCE, LAST_MODIFIED_DATE_NO_MATCH, Status.PRECONDITION_FAILED);
    test(HttpMethodEnum.DELETE, HttpHeaders.IF_MATCH, eTagNoMatchString(), HttpHeaders.IF_UNMODIFIED_SINCE, LAST_MODIFIED_DATE_MATCH, Status.NO_CONTENT);

    test(HttpMethodEnum.DELETE, HttpHeaders.IF_MATCH, eTagMatchString(), HttpHeaders.IF_MODIFIED_SINCE, LAST_MODIFIED_DATE_NO_MATCH, Status.NO_CONTENT);
    test(HttpMethodEnum.DELETE, HttpHeaders.IF_MATCH, eTagMatchString(), HttpHeaders.IF_MODIFIED_SINCE, LAST_MODIFIED_DATE_MATCH, Status.NO_CONTENT);
    test(HttpMethodEnum.DELETE, HttpHeaders.IF_MATCH, eTagMatchString(), HttpHeaders.IF_UNMODIFIED_SINCE, LAST_MODIFIED_DATE_NO_MATCH, Status.NO_CONTENT);
    test(HttpMethodEnum.DELETE, HttpHeaders.IF_MATCH, eTagMatchString(), HttpHeaders.IF_UNMODIFIED_SINCE, LAST_MODIFIED_DATE_MATCH, Status.NO_CONTENT);

    test(HttpMethodEnum.DELETE, HttpHeaders.IF_NONE_MATCH, eTagNoMatchString(), HttpHeaders.IF_MODIFIED_SINCE, LAST_MODIFIED_DATE_NO_MATCH, Status.NO_CONTENT);
    test(HttpMethodEnum.DELETE, HttpHeaders.IF_NONE_MATCH, eTagNoMatchString(), HttpHeaders.IF_MODIFIED_SINCE, LAST_MODIFIED_DATE_MATCH, Status.NO_CONTENT);
    test(HttpMethodEnum.DELETE, HttpHeaders.IF_NONE_MATCH, eTagNoMatchString(), HttpHeaders.IF_UNMODIFIED_SINCE, LAST_MODIFIED_DATE_NO_MATCH, Status.NO_CONTENT);
    test(HttpMethodEnum.DELETE, HttpHeaders.IF_NONE_MATCH, eTagNoMatchString(), HttpHeaders.IF_UNMODIFIED_SINCE, LAST_MODIFIED_DATE_MATCH, Status.NO_CONTENT);

    test(HttpMethodEnum.DELETE, HttpHeaders.IF_NONE_MATCH, eTagMatchString(), HttpHeaders.IF_MODIFIED_SINCE, LAST_MODIFIED_DATE_NO_MATCH, Status.NO_CONTENT);
    test(HttpMethodEnum.DELETE, HttpHeaders.IF_NONE_MATCH, eTagMatchString(), HttpHeaders.IF_MODIFIED_SINCE, LAST_MODIFIED_DATE_MATCH, Status.NOT_MODIFIED);
    test(HttpMethodEnum.DELETE, HttpHeaders.IF_NONE_MATCH, eTagMatchString(), HttpHeaders.IF_UNMODIFIED_SINCE, LAST_MODIFIED_DATE_NO_MATCH, Status.PRECONDITION_FAILED);
    test(HttpMethodEnum.DELETE, HttpHeaders.IF_NONE_MATCH, eTagMatchString(), HttpHeaders.IF_UNMODIFIED_SINCE, LAST_MODIFIED_DATE_MATCH, Status.NO_CONTENT);
  }

  @Test
  public void testGet() {
    System.out.println("| GET   |  |  |  |  |  |  |  |  |");

    test(HttpMethodEnum.GET, null, null, null, null, Status.OK);
  }

  @Test
  public void testGet_eTag() {
    RestResource.setLastModifiedDate(null);

    System.out.println("| GET + eTag   |  |  |  |  |  |  |  |  |");

    test(HttpMethodEnum.GET, HttpHeaders.IF_MATCH, eTagNoMatchString(), null, null, Status.PRECONDITION_FAILED);
    test(HttpMethodEnum.GET, HttpHeaders.IF_MATCH, eTagMatchString(), null, null, Status.OK);
    test(HttpMethodEnum.GET, HttpHeaders.IF_NONE_MATCH, eTagNoMatchString(), null, null, Status.OK);
    test(HttpMethodEnum.GET, HttpHeaders.IF_NONE_MATCH, eTagMatchString(), null, null, Status.NOT_MODIFIED);
  }
  
}
