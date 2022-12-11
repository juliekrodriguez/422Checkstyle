package checkstylePro;

import com.puppycrawl.tools.checkstyle.api.*;


public class CommentCheck extends AbstractCheck{
    
    private static final int[] COMMENT_TOKENS = { TokenTypes.BLOCK_COMMENT_BEGIN,
            TokenTypes.BLOCK_COMMENT_END, TokenTypes.COMMENT_CONTENT,
            TokenTypes.SINGLE_LINE_COMMENT};
	private static final String CATCH_MSH = "number of comments: ";
 
	int TTC = 0;
	int CL = 0;
	

	@Override
	public int[] getAcceptableTokens() {
		return COMMENT_TOKENS;
	}

	@Override
	public int[] getRequiredTokens() {
		return new int[0];
	}

	@Override
	public int[] getDefaultTokens() {
		return COMMENT_TOKENS;
	}

	@Override
	public void visitToken(DetailAST ast) {
		
		visitTok(ast);

	}
	
	@Override
    public boolean isCommentNodesRequired()
    {
        return true;
    }
	
	public void visitTok(DetailAST ast)
	{
		switch(ast.getType())
		{
		case TokenTypes.BLOCK_COMMENT_BEGIN:
			totalCommLineComm(ast, true);
			break;
		case TokenTypes.SINGLE_LINE_COMMENT:
			totalCommLineComm(ast, false);
			break;
		}
	}
	
	public void addSC()
    {
        TTC += 1;
        CL += 1;
    }
	
	public void totalCommLineComm(DetailAST ast, boolean isBlock)
	{
		if(isBlock)
		{
			TTC += 1;
			DetailAST end = ast.findFirstToken(TokenTypes.BLOCK_COMMENT_END);
			CL += (end.getLineNo() - ast.getLineNo()) + 1;
		}
		else
		{	
			addSC();
		}
	}
	
	
	@Override
	public void finishTree(DetailAST ast)
	{
		if(TTC >= 1)
			log(0, "total comments", TTC);
		log(0, "comment Lines", CL);
		TTC = 0;
		CL = 0;
	}
}

