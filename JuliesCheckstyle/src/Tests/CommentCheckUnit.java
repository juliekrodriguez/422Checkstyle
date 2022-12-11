package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;

import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import checkstylePro.CommentCheck;

class CommentCheckUnit {

	public CommentCheck mock = new CommentCheck();
	
	int[] TA =  new int[] { TokenTypes.BLOCK_COMMENT_BEGIN,
			TokenTypes.BLOCK_COMMENT_END, TokenTypes.COMMENT_CONTENT,
			TokenTypes.SINGLE_LINE_COMMENT};
	@Test
    public void testgetDefaultTokens() {
        Assert.assertArrayEquals(TA, mock.getDefaultTokens());
    }
    
    @Test
    public void testIfCommentNodesRequired() {
        assertTrue(mock.isCommentNodesRequired());
    }
	
	@Test
	public void testgetAcceptableTokens() {		
		Assert.assertArrayEquals(TA, mock.getAcceptableTokens());
	}

	@Test
	public void testgetRequiredTokens() {
		int[] EA = new int[0];
		Assert.assertArrayEquals(EA, mock.getRequiredTokens());
	}
	
}
