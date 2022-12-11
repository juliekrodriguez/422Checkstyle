package Tests;

/**						1
 * @author Andrew		2
 *	There are 2 block	3
 *	comments and 14		4
 * 	total lines of		5
 *  comments			6 */
public class CommentCheckTest {

	//Junk comments		7
	private static int myVar = 0;

	/**					8
	 * Pointless main 	9
	 * header block		10
	 * comment.			11
	 * @param args		12 */
	public static void main(String[] args) {

		//Loop			13
		for(int i = 0; i < 10; i++)
		{
			myVar += i;
		}

		//End			14
		return;
	}
}
