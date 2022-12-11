package Tests;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.Assert;

import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import checkstylePro.CastingCheck;

class CastCheckUnit {

	public CastingCheck mock = new CastingCheck();
	
	int[] TA =  new int[] { TokenTypes.TYPECAST };
	
	@Test
	public void testgetAcceptableTokens() {		
		Assert.assertArrayEquals(TA, mock.getAcceptableTokens());
	}

	@Test
	public void testgetRequiredTokens() {
		int[] EA = new int[0];
		Assert.assertArrayEquals(EA, mock.getRequiredTokens());
	}
	
	@Test
	public void testgetDefaultTokens() {
		Assert.assertArrayEquals(TA, mock.getDefaultTokens());
	}
}
