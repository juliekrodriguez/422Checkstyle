package MyPackage;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.TokenTypes;

class RandJunit {

	RandCheck testClass = new RandCheck();
	
	int[] tokArr = {TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN, 
			                TokenTypes.VARIABLE_DEF, TokenTypes.TYPECAST, 
			                TokenTypes.METHOD_REF};
	int[] EA = new int[0];
	
	@Test
	void testGetTokens() {
		assertArrayEquals(tokArr, testClass.getDefaultTokens());
		assertArrayEquals(tokArr, testClass.getAcceptableTokens());
		assertArrayEquals(EA, testClass.getRequiredTokens());
	}
	
	@Test
    void testIsCommentNodeRequired() {
        assertTrue(testClass.isCommentNodesRequired());
    }

}
