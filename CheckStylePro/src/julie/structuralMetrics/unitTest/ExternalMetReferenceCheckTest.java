package julie.structuralMetrics.unitTest;

import static org.junit.Assert.*;


import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import julie.structuralMetrics.ExternalMetReferenceCheck;

public class ExternalMetReferenceCheckTest {

  private int[] acceptableTokens = { TokenTypes.CLASS_DEF, TokenTypes.METHOD_CALL, TokenTypes.METHOD_REF };
  private int[] defaultTokens = acceptableTokens;
  private int[] requiredTokens = new int[0];
  private ExternalMetReferenceCheck RC = new ExternalMetReferenceCheck();
    
  @Test
  public void testGetDefaultTokens() {
    assertArrayEquals(defaultTokens, RC.getDefaultTokens());
  }

  @Test
  public void testGetAcceptableTokens() {
    assertArrayEquals(acceptableTokens, RC.getAcceptableTokens());
  }

  @Test
  public void testGetRequiredTokens() {
    assertArrayEquals(requiredTokens, RC.getRequiredTokens());
  }

}
