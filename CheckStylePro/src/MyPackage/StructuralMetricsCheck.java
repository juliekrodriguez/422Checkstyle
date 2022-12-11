package MyPackage;

import java.util.*;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class StructuralMetricsCheck extends AbstractCheck {

	private int l, or, and, exp;
	private Map<Integer, Integer> uOperators= new HashMap<Integer, Integer>();
	private Map<String, Integer> uOperands = new HashMap<String, Integer>();
	private Map<String, Float> res = new HashMap<String, Float>();
	
	@Override
    public void beginTree(DetailAST ast)
    {
        l = 0;
        and = 0;
        or = 0;
        exp = 0;
    }
  
	@Override
	public int[] getDefaultTokens() {
		return new int[] { 
				TokenTypes.COMMA, TokenTypes.DEC, TokenTypes.DIV, TokenTypes.DIV_ASSIGN, TokenTypes.DOT, TokenTypes.EQUAL, TokenTypes.GE,
				TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF, TokenTypes.METHOD_DEF, TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_DO, TokenTypes.DO_WHILE,
                TokenTypes.LITERAL_WHILE, TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN, TokenTypes.BNOT, TokenTypes.BOR,
                TokenTypes.BOR_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN, TokenTypes.BXOR, TokenTypes.BXOR_ASSIGN, TokenTypes.COLON,
				TokenTypes.GT, TokenTypes.INC, TokenTypes.INDEX_OP, TokenTypes.LAND, TokenTypes.LE, TokenTypes.LITERAL_INSTANCEOF,
				TokenTypes.LNOT, TokenTypes.LOR, TokenTypes.LT, TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD, 
				TokenTypes.MOD_ASSIGN, TokenTypes.NOT_EQUAL, TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN, TokenTypes.POST_DEC, TokenTypes.POST_INC,
				TokenTypes.QUESTION, TokenTypes.SL, TokenTypes.SL_ASSIGN, TokenTypes.SR, TokenTypes.SR_ASSIGN, TokenTypes.STAR,
				TokenTypes.STAR_ASSIGN, TokenTypes.UNARY_MINUS, TokenTypes.UNARY_PLUS, TokenTypes.EXPR,
				TokenTypes.IDENT, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG,
				TokenTypes.CHAR_LITERAL, TokenTypes.STRING_LITERAL };
	}

	@Override
	public int[] getAcceptableTokens() {
		// TODO Auto-generated method stub
		return getDefaultTokens();
	}
  

	@Override
	public int[] getRequiredTokens() {
		// TODO Auto-generated method stub
		return new int[0];
	}
	
	@Override
	public void finishTree(DetailAST ast)
	{
		int uAnd, uOr;
		if((uOperands.keySet() != null) && (uOperands.keySet().size() < 1))
			uAnd = 1;
		else
			uAnd = uOperands.keySet().size();
		if((uOperators.keySet() != null) && (uOperators.keySet().size() < 1))
			uOr = 1;
		else
			uOr = uOperators.keySet().size();
		
		float hv = (float) ((or + and) * (Math.log(uOr +
				uAnd) / Math.log(2)));
		log(0, ("Halstead Volume: " + hv + ", " + this.getClass().getSimpleName()));
		res.put("hv", hv);
		
		float hvocab = uOr + uAnd;
		log(0, ("Halstead Vocabulary: " + hvocab + ", " + this.getClass().getSimpleName()));
		res.put("hvocab", hvocab);
		
		float hd = ((float) (uOr / 2.0) * and) /
				uOr;
		log(0, ("Halstead Difficulty: " + hd + ", " + this.getClass().getSimpleName()));
		res.put("hd", hd);
		
		float he = hvocab * hd;
		log(0, ("Halstead Effort: " + he));
		res.put("he", he);
		
		log(0, ("Found " + l + " l" + ", " + this.getClass().getSimpleName()));
		res.put("l", (float)l);
		log(0, ("Found " + exp + " exp" + ", " + this.getClass().getSimpleName()));
		res.put("exp", (float)exp);
		
		log(0, ("Found " + or + " or" + ", " + this.getClass().getSimpleName()));
		res.put("or", (float)or);
		log(0, ("Found " + and + " and" + ", " + this.getClass().getSimpleName()));
		res.put("and", (float)and);
	}
	
	public Map<String, Float> getResults() { return res; }
	
	@Override
	public void visitToken(DetailAST ast) {
		int type;
		switch(type = ast.getType()) {
			case TokenTypes.LITERAL_FOR:
			case TokenTypes.LITERAL_WHILE:
			case TokenTypes.LITERAL_DO:
			case TokenTypes.DO_WHILE:
				l++;
				break; 
			case TokenTypes.EQUAL:
            case TokenTypes.GE:
            case TokenTypes.GT:
            case TokenTypes.INC:
            case TokenTypes.INDEX_OP:
            case TokenTypes.LAND:
            case TokenTypes.LE:
            case TokenTypes.LITERAL_INSTANCEOF:
            case TokenTypes.LNOT:
            case TokenTypes.LOR:
            case TokenTypes.LT:
            case TokenTypes.MINUS:
            case TokenTypes.MINUS_ASSIGN:
            case TokenTypes.MOD:
            case TokenTypes.MOD_ASSIGN:
            case TokenTypes.NOT_EQUAL:
            case TokenTypes.PLUS:
            case TokenTypes.PLUS_ASSIGN:
            case TokenTypes.POST_DEC:
            case TokenTypes.POST_INC:
            case TokenTypes.QUESTION:
            case TokenTypes.SL:
            case TokenTypes.SL_ASSIGN:
            case TokenTypes.SR:
            case TokenTypes.SR_ASSIGN:
            case TokenTypes.STAR:
            case TokenTypes.STAR_ASSIGN:
            case TokenTypes.UNARY_MINUS:
            case TokenTypes.UNARY_PLUS:
			case TokenTypes.ASSIGN:
			case TokenTypes.BAND:
			case TokenTypes.BAND_ASSIGN:
			case TokenTypes.BNOT:
			case TokenTypes.BOR:
			case TokenTypes.BOR_ASSIGN:
			case TokenTypes.BSR:
			case TokenTypes.BSR_ASSIGN:
			case TokenTypes.BXOR:
			case TokenTypes.BXOR_ASSIGN:
			case TokenTypes.COLON:
			case TokenTypes.COMMA:
			case TokenTypes.DEC:
			case TokenTypes.DIV:
			case TokenTypes.DIV_ASSIGN:
			case TokenTypes.DOT:
				or++;
				
				if(!uOperators.containsKey(type))
					uOperators.put(type, 0);
				else
					uOperators.put(type, (uOperators.get(type) + 1));
				break;
				
			case TokenTypes.EXPR:
				exp++;				
				break;
				
			case TokenTypes.IDENT:
			case TokenTypes.NUM_DOUBLE:
			case TokenTypes.NUM_FLOAT:
			case TokenTypes.NUM_INT:
			case TokenTypes.NUM_LONG:
			case TokenTypes.CHAR_LITERAL:
			case TokenTypes.STRING_LITERAL:
				
				if((ast.getParent().getType() == TokenTypes.TYPE) && (ast.getType() == TokenTypes.IDENT))
					return;
				
				and++;
				if(!uOperands.containsKey(ast.getText()))
					uOperands.put(ast.getText(), 1);
				else
					uOperands.put(ast.getText(), (uOperands.get(ast.getText()) + 1));
				break;
		}
	}
}