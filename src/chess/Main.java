package chess;

import java.util.Scanner;

public class Main {

	Scanner scan = new Scanner(System.in);
	String val = "";
	Board b = new Board();
	BoardPrinter p = new BoardPrinter(b);

	public Main() {
		do {
			p.print();
			try {
				b.inputCoor();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			scan.nextLine();

		} while (true);

	}

	public static void main(String[] args) {
		new Main();
	}

}
