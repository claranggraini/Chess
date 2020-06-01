package chess;

import java.util.Scanner;

public class Main {
	Scanner scan = new Scanner(System.in);
	String val = "";
	Board b = new Board();

	public Main() {
		do {
			b.print();
			try {
				b.inputCoor();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while (!b.isEnded);
	}

	public static void main(String[] args) {
		new Main();
	}
}
