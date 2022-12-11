package julie.structuralMetrics.checks;

import com.puppycrawl.tools.checkstyle.api.DetailAST;

public class HLength {
  
  public static final String MSG_KEY = "halsteadLength";
  
  private int HLen = 0;
  
  public int getHLen() {
    return HLen;
  }
  
  public static boolean isAcceptableToken(int type) {
    return Utils.contains(getAcceptableTokens(), type);
  }
  
  public static int[] getAcceptableTokens() {
    return Utils.concat(HTokens.getOperands(), HTokens.getOperators());
  }

  public void visitToken(DetailAST ast) {
    if (!isAcceptableToken(ast.getType())) {
      return;
    }
    HLen++;
  }
  
  public void beginTree(DetailAST root) {
    HLen = 0;
  }
}
