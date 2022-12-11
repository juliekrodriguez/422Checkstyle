package blackbox;							
public class BBtest { 								
	
	private static String str = "test";	
	private int a, b;								
	private BBtest x;							
	private boolean y;								
	private String[] strArr; 
	private void init()								
	{
		a = 100;							
		b = 20;								
		strArr = new String[5];				//gittest
		y = false;								
	}
	
	public BBtest()									
	{
		init();										
	}
	
	public BBtest(BBtest var)				
	{
		this.init();									
		x = var;									
	}
	
	public void met()										
	{
		int i = 0;											
		do													
		{
			i++;										
		}while(i < 10);										
		
		for(int j = 0; j < 100; j++)						
		{
			if((0 < 10 + 3) && (2 + (j - j) > 0))			
			{
				j += 2;		
			}
		}
	}
	
	protected void res(int par)				
	{
		a -= par;							
	}
	
}
