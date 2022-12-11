package julie.structuralMetrics.checks;

import java.util.ArrayList;

import com.puppycrawl.tools.checkstyle.api.DetailAST;

public class HVolume {
  
  public static final String MSG_KEY = "halsteadVolume";

  public ArrayList<String> specialTok = new ArrayList<String>();
  public int totalTokens = 0;
  
  public double getHalsteadVolume() {
    return totalTokens * Utils.log(specialTok.size(), 2);
  }
  
  public static boolean isAcceptableToken(int type) {
    return Utils.contains(getAcceptableTokens(), type);
  }
  
  public static int[] getAcceptableTokens() {
    return Utils.concat(HTokens.getOperands(), HTokens.getOperators());
  }

  public void visitToken(DetailAST ast) {
    int type = ast.getType();
    if (!isAcceptableToken(type)) {
      return;
    }
    totalTokens++;
    String text = ast.getText();
    if (!specialTok.contains(text)) {
      specialTok.add(text);
    }
  }
  
  public void beginTree(DetailAST ast) {
    totalTokens = 0;
    specialTok.clear();
  }
}
