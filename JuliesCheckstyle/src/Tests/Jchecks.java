package Tests;
import java.util.Random; 

public class Jchecks {

	public static void main(String[] args) {

		char chartoint = 6;
		int a = (int) chartoint;
		int a1 = (int) chartoint;
		int a2 = (int) chartoint;
		long a3 = (long) chartoint;
		double a4 = (double) chartoint;

		int co = 0;
		int size = 10;
		Random rand = new Random();
		int r = rand.nextInt(8);

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				r = rand.nextInt(8);
				if (r == 0) {
					System.out.print(" X ");
				}
				if (r == 1) {
					System.out.print(" A ");
				}
				if (r == 2) {
					System.out.print(" B ");
				}
				if (r == 3) {
					System.out.print(" C ");
				}
				if (r == 4) {
					System.out.print(" D ");
				}
				if (r == 5) {
					System.out.print(" E ");
				}
				if (r == 6) {
					System.out.print(" F ");
				}
				if (r == 7) {
					System.out.print(" G ");
				}
			}
			System.out.println("");
		}
		while (co < 10) {
			System.out.print(co);
			co++;
			for (int i = 0; i < 10; i++) {

			}
		}

	}
}
