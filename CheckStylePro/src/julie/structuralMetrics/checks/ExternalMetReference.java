package julie.structuralMetrics.checks;

import com.puppycrawl.tools.checkstyle.api.DetailAST;

import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import java.util.Stack;

public class ExternalMetReference {
  
  public static final String MSG_KEY = "externalMethodReferences";
  
  private int count = 0;
  private Stack<String> stack = new Stack<String>();
  
  public void beginTree(DetailAST root) {
      count = 0;
    }
  
  public int getExtMetRef() {
    return count;
  }
  
  public static boolean isAcceptableToken(int type) {
    return Utils.contains(getAcceptableTokens(), type);
  }
  
  public static int[] getAcceptableTokens() {
    return new int[] { TokenTypes.CLASS_DEF, TokenTypes.METHOD_CALL, TokenTypes.METHOD_REF };
  }

  public void visitToken(DetailAST ast) {
    int type = ast.getType();
    if (!isAcceptableToken(type)) {
      return;
    }
    if (TokenTypes.CLASS_DEF == ast.getType()) {
      stack.push(findClassName(ast));
    }
    else if(TokenTypes.METHOD_CALL == ast.getType()) {
      if (isExternalMethodCall(ast)) {
        count++;
      }
    }
    else if(TokenTypes.METHOD_REF == ast.getType()) {
      if (isExternalMethodReference(ast)) {
        count++;
      }
    }
  }
  
  public void leaveToken(DetailAST ast) {
    int type = ast.getType();
    if (!isAcceptableToken(type)) {
      return;
    }
    if (TokenTypes.CLASS_DEF == ast.getType()) {
      stack.pop();
    }
  }
  
  private boolean isExternalMethodReference(DetailAST ast) {
      DetailAST child = ast.getFirstChild();
      if (TokenTypes.LITERAL_THIS == child.getType()) {
        return false;
      }
      else if (TokenTypes.IDENT == child.getType() 
          && stack.peek().equals(child.getText())) {
        return false;
      }
      return true;
    }
  
  private String findClassName(DetailAST ast) {
    if (ast.getType() != TokenTypes.CLASS_DEF) {
      return null;
    }
    DetailAST child = ast.getFirstChild();
    while (child != null) {
      if (child.getType() == TokenTypes.IDENT) {
        return child.getText();
      }
      child = child.getNextSibling();
    }
    return null;
  }
  
  private boolean isExternalMethodCall(DetailAST ast) {
  
    DetailAST child = ast.getFirstChild();
    if (TokenTypes.DOT != child.getType()) {
      return false;
    }
    else {
      DetailAST child2 = child.getFirstChild();
      
      if (TokenTypes.LITERAL_THIS == child2.getType()) {
        return false;
      }
      else if (TokenTypes.IDENT == child2.getType() 
          && stack.peek().equals(child2.getText())) {
        return false;
      }
    }
    return true;
  }
  public int getExternalMethodReferences() {
      return count;
    }

  
}
