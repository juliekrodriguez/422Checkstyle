package julie.structuralMetrics.unitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import julie.structuralMetrics.HOperandCheck;

public class HOperandCheckTest {

  private int[] acceptableTokens = {  TokenTypes.LITERAL_VOID, TokenTypes.LITERAL_BOOLEAN, 
	      TokenTypes.LITERAL_BYTE, TokenTypes.LITERAL_CHAR, TokenTypes.LITERAL_SHORT, 
	      TokenTypes.LITERAL_INT, TokenTypes.LITERAL_FLOAT, TokenTypes.LITERAL_LONG, 
	      TokenTypes.LITERAL_DOUBLE, TokenTypes.IDENT, TokenTypes.NUM_DOUBLE,
	      TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG,
	      TokenTypes.CHAR_LITERAL, TokenTypes.STRING_LITERAL };
  private int[] defaultTokens = acceptableTokens;
  private int[] requiredTokens = new int[0];
  private HOperandCheck and = new HOperandCheck();
  
  @Test
  public void testGetDefaultTokens() {
    assertArrayEquals(defaultTokens, and.getDefaultTokens());
  }

  @Test
  public void testGetAcceptableTokens() {
    assertArrayEquals(acceptableTokens, and.getAcceptableTokens());
  }

  @Test
  public void testGetRequiredTokens() {
    assertArrayEquals(requiredTokens, and.getRequiredTokens());
  }

}
