package checkstylePro;
import com.puppycrawl.tools.checkstyle.api.*;


import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class FirstChecker extends AbstractCheck {
	int internalCalls = 0;
	int externalCalls = 0;
	int expressions;
	int lastline=0;
	int operators=0;
    int operands = 0;
    
	private int cyclomaticComp = 1;
	private boolean switcherBlock=false;
	public static final String MSG_KEY = "cyclomaticComplexity";
	
	private Map<String, Double> mappedRes = new HashMap<String, Double>();

  public void setswitcherBlock(boolean switcherBlock) {
    this.switcherBlock = switcherBlock;
  }

  int tokensToVisit[]= new int[] {
			TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN, 
			TokenTypes.BNOT, TokenTypes.BOR, TokenTypes.BOR_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN,
			TokenTypes.BXOR, TokenTypes.BXOR_ASSIGN,TokenTypes.DEC, TokenTypes.DIV, TokenTypes.DIV_ASSIGN,
			TokenTypes.EQUAL, TokenTypes.INC, TokenTypes.LAND, TokenTypes.LE, TokenTypes.LNOT, TokenTypes.LOR,
			TokenTypes.LT, TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD, TokenTypes.MOD_ASSIGN,
			TokenTypes.NOT_EQUAL, TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN, TokenTypes.POST_DEC,
			TokenTypes.POST_INC, TokenTypes.SL, TokenTypes.SL_ASSIGN, TokenTypes.SR, TokenTypes.SR_ASSIGN,
			TokenTypes.STAR, TokenTypes.STAR_ASSIGN, TokenTypes.UNARY_MINUS, TokenTypes.UNARY_PLUS,
			TokenTypes.EXPR, TokenTypes.METHOD_CALL, TokenTypes.RCURLY, TokenTypes.CTOR_DEF,
			TokenTypes.METHOD_DEF, TokenTypes.INSTANCE_INIT, TokenTypes.STATIC_INIT, TokenTypes.LITERAL_WHILE,
			TokenTypes.LITERAL_DO, TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_IF, TokenTypes.LITERAL_SWITCH,
			TokenTypes.LITERAL_CASE, TokenTypes.LITERAL_CATCH, TokenTypes.QUESTION
	};
	HashSet<Integer> isForComplexity = new HashSet<>(Arrays.asList(TokenTypes.CTOR_DEF, TokenTypes.METHOD_DEF,
			TokenTypes.INSTANCE_INIT, TokenTypes.STATIC_INIT, TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_DO,
			TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_IF, TokenTypes.LITERAL_SWITCH, TokenTypes.LITERAL_CASE,
			TokenTypes.LITERAL_CATCH, TokenTypes.QUESTION, TokenTypes.LAND, TokenTypes.LOR));
	HashSet<Integer> isOperator = new HashSet<> (Arrays.asList(TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN, 
			TokenTypes.BNOT, TokenTypes.BOR, TokenTypes.BOR_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN,
			TokenTypes.BXOR, TokenTypes.BXOR_ASSIGN,TokenTypes.DEC, TokenTypes.DIV, TokenTypes.DIV_ASSIGN,
			TokenTypes.EQUAL, TokenTypes.INC, TokenTypes.LAND, TokenTypes.LE, TokenTypes.LNOT, TokenTypes.LOR,
			TokenTypes.LT, TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD, TokenTypes.MOD_ASSIGN,
			TokenTypes.NOT_EQUAL, TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN, TokenTypes.POST_DEC,
			TokenTypes.POST_INC, TokenTypes.SL, TokenTypes.SL_ASSIGN, TokenTypes.SR, TokenTypes.SR_ASSIGN,
			TokenTypes.STAR, TokenTypes.STAR_ASSIGN, TokenTypes.UNARY_MINUS, TokenTypes.UNARY_PLUS));

	HashSet<Integer> unaryOperators = new HashSet<>(Arrays.asList(TokenTypes.BNOT, TokenTypes.DEC, TokenTypes.INC, TokenTypes.POST_DEC,
			TokenTypes.POST_INC, TokenTypes.UNARY_MINUS, TokenTypes.UNARY_PLUS));
	HashSet<String> uniqueOperands = new HashSet<String>();
	HashSet<Integer> uniqueOperators = new HashSet<Integer>();

	@Override
	public int[] getAcceptableTokens() {
		return tokensToVisit;
	}

	@Override
	public int[] getRequiredTokens() {
		return new int[]{TokenTypes.CTOR_DEF,
				TokenTypes.METHOD_DEF,
				TokenTypes.INSTANCE_INIT,
				TokenTypes.STATIC_INIT};
	}

	@Override
	public int[] getDefaultTokens() {
		return getAcceptableTokens();
	}

	public String rebuildExpression(DetailAST ast, boolean isTop) {
		if(ast.getType() == TokenTypes.LPAREN) {
			ast = ast.getNextSibling();
		}
		if(ast.getChildCount() == 0) {
			String self = ast.getText();
			uniqueOperands.add(self);
			return self;
		}else {
			String left = rebuildExpression(ast.getFirstChild(), false);
			String right = rebuildExpression(ast.getLastChild(), false);
			String expression = left + ast.getText() + right;
			if(!isTop) {
				uniqueOperands.add(expression);
			}
			return expression;
		}
	}
	
	private void visitTokH(DetailAST ast) {
		if (switcherBlock) {
			if (ast.getType() != TokenTypes.LITERAL_CASE) {
				cyclomaticComp++;
			}
		}
		else if (ast.getType() != TokenTypes.LITERAL_SWITCH) {
			cyclomaticComp++;
		}
	}


	@Override
	public void visitToken(DetailAST ast) {
		if(isForComplexity.contains(ast.getType())){
			switch (ast.getType()) {
			case TokenTypes.CTOR_DEF:
			case TokenTypes.METHOD_DEF:
			case TokenTypes.INSTANCE_INIT:
			case TokenTypes.STATIC_INIT:
				break;
			default:
				visitTokH(ast);
			}
		}

		if(ast.getType() == TokenTypes.EXPR) {
			boolean RT = false;
			boolean addExpression = true;
			DetailAST parent = ast.getParent();
			while(!RT) {
				if (parent.getType() == TokenTypes.OBJBLOCK) {
					RT = true;
				}
				if(parent.getType() == TokenTypes.EXPR) {
					addExpression = false;
					RT= true;
				}	
				parent = parent.getParent();
			}
			if(addExpression) {
				expressions++;
			}
			
		}else if (isOperator.contains(ast.getType())){
			operators++;
			operands += 2;
			if(unaryOperators.contains(ast.getType())){
				operands--;
			}
			
			uniqueOperators.add((Integer)ast.getType());
			String parent = "";
			parent = ast.getParent().getText();
			
			switch(ast.getText()) {
			case "=":
				if(parent.equals("VARIABLE_DEF")){
					parent = ast.getParent().findFirstToken(TokenTypes.IDENT).getText();
					uniqueOperands.add(parent);
				}else if(parent.equals("EXPR")) 
				{
					rebuildExpression(ast, true);
				}			
				break;
				
			default:
				rebuildExpression(ast, true);
				break;
			}
		}else if(ast.getType() == TokenTypes.METHOD_CALL) {
			if(ast.getChildCount(TokenTypes.DOT)>0) {
				externalCalls++;
			}else {
				internalCalls++;
			}
		}else if(ast.getType() == TokenTypes.RCURLY) {
			if(ast.getLineNo() > lastline) {
				lastline = ast.getLineNo();
			}
		}
	}
	
	@Override
    public void leaveToken(DetailAST ast) {
        if(isForComplexity.contains(ast.getType())) {
            switch (ast.getType()) {
            case TokenTypes.CTOR_DEF:
            case TokenTypes.METHOD_DEF:
            case TokenTypes.INSTANCE_INIT:
            case TokenTypes.STATIC_INIT:
                break;
            default:
                break;
            }
        }
    }

	@Override
	public void finishTree(DetailAST ast) {
		double percComm = 0.5; 
		int length = operators + operands;
		int vocab = 0;
		double vol = 0;
		
		mappedRes.put("length", (double)length);

		double maintain = 171 - 5.2*Math.log(vol)/Math.log(2)-0.23*cyclomaticComp-16.2*Math.log(lastline)/Math.log(2)+50*Math.sin(Math.sqrt(2.4*percComm));

		log(ast.getLineNo(), "operators"  , operators);
		log(0,"0",uniqueOperands.size());
		log(0,"1", uniqueOperators.size());
		log(0, "3", operands);
		log(0, "4", expressions);
		log(0, "length", length);
		if(uniqueOperators.size() != 0 && uniqueOperands.size() != 0) {
			vocab = uniqueOperators.size() +uniqueOperands.size();
			log(0, "vocab", vocab);
			vol = length * Math.log(vocab);
			log(0, "vol",vol);
			
			mappedRes.put("vocab", (double)vocab);
			mappedRes.put("vol", vol);
			
		}
		if(uniqueOperands.size() != 0) {
			double difficulty = uniqueOperators.size()*operands/(uniqueOperands.size()*2);
			double effort = difficulty * vol;
			log(0, "difficulty", difficulty);
			log(0, "effort", effort);
			
			mappedRes.put("difficulty", difficulty);
			mappedRes.put("effort", effort);
			
		}
		mappedRes.put("operands", (double)operands);
		mappedRes.put("operators", (double)operators);
		
		mappedRes.put("uOperators", (double)uniqueOperators.size());
		mappedRes.put("uOperands", (double)uniqueOperands.size());
		
		
		
		
		log(0, "complex", cyclomaticComp);
		log(0, "internal", internalCalls);
		log(0, "external", externalCalls);
		log(0, "lines", lastline);
		log(0, "maintain", maintain);
		mappedRes.put("maintain", (double)maintain);
		mappedRes.put("external", (double)externalCalls);
		
		operands = 0;
		operators = 0;
        expressions = 0;
        internalCalls = 0;
        externalCalls = 0;
        cyclomaticComp = 1;
		uniqueOperators = new HashSet<>();
		uniqueOperands = new HashSet<>();
		
	}
	
	
	public Map<String, Double> getResults() {
		return mappedRes;
	}
}
