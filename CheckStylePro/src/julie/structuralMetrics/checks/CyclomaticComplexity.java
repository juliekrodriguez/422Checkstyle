package julie.structuralMetrics.checks;

import java.util.ArrayDeque;

import java.util.Deque;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class CyclomaticComplexity {
  
  public static final String MSG_KEY = "CC";
  
  private boolean switcherBlock = true;
  private final Deque<Integer> stack = new ArrayDeque<>();
  private int currVal = 1;
  private int CC = 0;
  
  public void beginTree(DetailAST root) {
      stack.clear();
      CC = 0;
      currVal = 1;
    }
  
  private void visitMet() {
	    pushVal();
	  }
	  
	  private void leaveMet(DetailAST ast) {
	    popVal();
	  }
  
  public static int[] getAcceptableTokens() {
      return new int[] { TokenTypes.CTOR_DEF, TokenTypes.METHOD_DEF, TokenTypes.INSTANCE_INIT,
          TokenTypes.STATIC_INIT, TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_DO, 
          TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_IF, TokenTypes.LITERAL_SWITCH,
          TokenTypes.LITERAL_CASE, TokenTypes.LITERAL_CATCH, TokenTypes.QUESTION,
          TokenTypes.LAND, TokenTypes.LOR };
    }
    
    public static boolean isAcceptableToken(int type) {
      return Utils.contains(getAcceptableTokens(), type);
    }
    
    private void visitTokenH(DetailAST ast) {
        if (switcherBlock) {
          if (ast.getType() != TokenTypes.LITERAL_CASE) {
            currVal++;
          }
        } else if (ast.getType() != TokenTypes.LITERAL_SWITCH) {
          currVal++;;
        }
      }
  
  public void visitToken(DetailAST ast) {
      if (!isAcceptableToken(ast.getType())) {
        return;
      }
      switch (ast.getType()) {
        case TokenTypes.CTOR_DEF:
        case TokenTypes.METHOD_DEF:
        case TokenTypes.INSTANCE_INIT:
        case TokenTypes.STATIC_INIT:
          visitMet();
          break;
        default:
          visitTokenH(ast);
      }
    }
  
  public int getCylomaticComplexity() {
    return CC;
  }
  
  private void pushVal() {
      stack.push(currVal);
      currVal = 1;
    }
    
    private void popVal() {
      CC += currVal;
      currVal = stack.pop();
    }
  
  public void leaveToken(DetailAST ast) 
  {
    if (!isAcceptableToken(ast.getType())) 
    {
      return;
    }
    switch (ast.getType()) {
      case TokenTypes.CTOR_DEF:
      case TokenTypes.METHOD_DEF:
      case TokenTypes.INSTANCE_INIT:
      case TokenTypes.STATIC_INIT:
        leaveMet(ast);
        break;
      default:
        break;
    }
  }
  
}
