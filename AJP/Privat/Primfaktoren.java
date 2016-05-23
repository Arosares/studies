import java.util.Scanner;
public class Primfaktoren {

	public static void main(String[] args) {
		Scanner horst = new Scanner(System.in);
		// System.in was initialized by the VM and will be closed by the VM
		System.out.println("Bitte gib eine gerade Zahl über 1 ein:");
		int x = horst.nextInt();
		if (x <= 1) {
			System.out
					.println("x muss groesser als 1 sein! Bitte neu eingeben:");
			x = horst.nextInt();
		}
		if (x % 2 == 0) {
			System.out.println(2);
			x = x / 2;
		}
		for (int n = 3; n <= x; n = n + 2) {
			if (x % n == 0) {
				System.out.println(n);
				x = x / n;
			}
		}
	}

}
