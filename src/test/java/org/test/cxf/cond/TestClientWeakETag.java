package org.test.cxf.cond;

import javax.ws.rs.core.EntityTag;

import org.junit.Before;
import org.test.cxf.cond.rest.RestResource;

public class TestClientWeakETag extends AbstractGenericTests {
 
  private static final String WEAK_ETAG = "W/\"123abc\"";
  private static final String WEAK_NONEMATCH_ETAG = "W/\"nomatch\"";
  private static EntityTag weakETag = new EntityTag(WEAK_ETAG);

  @Before
  public void before() throws Exception {
    super.before();
    RestResource.setETag(weakETag);
  }

  @Override
  protected String eTagMatchString() {
    return WEAK_ETAG;
  }

  @Override
  protected String eTagNoMatchString() {
    return WEAK_NONEMATCH_ETAG;
  }
  
}
