package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;

import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import checkstylePro.Checkstyle;

class CheckingUnit {

	// make instance of target class
	public Checkstyle unitMock = new Checkstyle();
	public int[] unitCorrect =  new int[] {TokenTypes.VARIABLE_DEF};
	
	// test acceptable tokens
	@Test
	public void testgetAcceptableTokens() {
		Assert.assertArrayEquals(unitCorrect,unitMock.getAcceptableTokens());
	}

	// test required tokens
	@Test
	public void testgetRequiredTokens() {
		int[] unitCorrect =  new int[0];
		Assert.assertArrayEquals(unitCorrect,unitMock.getRequiredTokens());
	}
	
	// test default tokens
	@Test
	public void testgetDefaultTokens() {
		Assert.assertArrayEquals(unitCorrect,unitMock.getDefaultTokens());
	}
	
	
}
