package org.test.cxf.cond;

import javax.ws.rs.core.EntityTag;

import org.junit.Before;
import org.test.cxf.cond.rest.RestResource;

public class TestClientStrongETag extends AbstractGenericTests {

  private static final String STRONG_ETAG = "123abc";
  private static final String STRONG_NONMATCH_ETAG = "nomatch";
  private static final EntityTag strongETag = new EntityTag(STRONG_ETAG);
  
  @Before
  public void before() throws Exception {
    super.before();
    RestResource.setETag(strongETag);
  }

  @Override
  protected String eTagMatchString() {
     return STRONG_ETAG;
  }

  @Override
  protected String eTagNoMatchString() {
    return STRONG_NONMATCH_ETAG;
  }
  
}
