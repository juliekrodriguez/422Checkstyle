package MyPackage;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TextBlock;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.utils.CommonUtil;


public class RandCheck extends AbstractCheck{
		
	private int cc      = 0;
	private int ncm   = 0;
	private int nvd   = 0;
	private int lmr = 0;
	private int nc          = 0;
	
	
	@Override
	public int[] getDefaultTokens() 
	{
		return new int[] {TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN,
						  TokenTypes.VARIABLE_DEF, TokenTypes.TYPECAST, TokenTypes.METHOD_REF};
	}

	@Override
	public int[] getAcceptableTokens() 
	{
		return getDefaultTokens();
	}

	@Override
	public int[] getRequiredTokens() 
	{
		return new int[0];
	}
	
	@Override
    public void visitToken(DetailAST ast) 
    {
        
        
        
        switch(ast.getType()) 
        {        
            case TokenTypes.SINGLE_LINE_COMMENT:
                cc++;                           
                ncm++;      
                break;
                
            case TokenTypes.BLOCK_COMMENT_BEGIN:
                cc++;   
                
                // Find number of lines in block comment
                DetailAST child = ast.getFirstChild();                  
                child = child.getNextSibling();                         
                ncm += (child.getLineNo() - ast.getLineNo() + 1); 
                
                break;
            case TokenTypes.VARIABLE_DEF:
                nvd++;
                break;
                
            case TokenTypes.TYPECAST:
                nc++;
                break;
            case TokenTypes.METHOD_REF:
                lmr++;
                break;      
        }
    }
	
	@Override
	public boolean isCommentNodesRequired() {
	    return true;
	}
	
	@Override
	public void finishTree(DetailAST ast) 
	{
	    log(ncm, "There are " + ncm + " lines of comments");
        log(nvd, "There are " + nvd + " variable declarations");
        log(nc, "There are " + nc+ " cast operations");
		log(cc, "There are " + cc + " total comments");
		log(lmr, "There are " + lmr + " local method references");
	}
	

}
