package algorithm.year2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ya14A {

	private static final char[][] BOARD = new char[8][8];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String line = br.readLine();

		Piece wKing = new Piece(line.charAt(0) - 'a', line.charAt(1) - '1', 'K');
		Piece wRook = new Piece(line.charAt(3) - 'a', line.charAt(4) - '1', 'R');
		Piece bKing = new Piece(line.charAt(6) - 'a', line.charAt(7) - '1', '\0');

		setWhitePiece(wKing);
		setWhitePiece(wRook);

		boolean underAttack = false;
		boolean underKingAttack = false;
		boolean canMove = false;

		if (BOARD[bKing.v][bKing.h] != 0) {
			underAttack = true;
			underKingAttack = (BOARD[bKing.v][bKing.h] == 'k');
		}

		for (int i = -1; i < 2 && !canMove; i++)
			if (i + bKing.v >= 0 && i + bKing.v < 8)
				for (int j = -1; j < 2 && !canMove; j++)
					if (j + bKing.h >= 0 && j + bKing.h < 8 && (i != 0 || j != 0))
						if (BOARD[bKing.v + i][bKing.h + j] == 0
								|| BOARD[bKing.v + i][bKing.h + j] == 'R'
								|| BOARD[bKing.v + i][bKing.h + j] == 'K')
							canMove = true;

		if (underKingAttack)
			System.out.println("Strange");
		else if (!underAttack && canMove)
			System.out.println("Normal");
		else if (underAttack && canMove)
			System.out.println("Check");
		else if (underAttack)
			System.out.println("Checkmate");
		else
			System.out.println("Stalemate");
	}

	private static void setWhitePiece(Piece piece) {
		BOARD[piece.v][piece.h] = piece.name;
		if (piece.name == 'R') {
			for (int i = piece.v - 1; i >= 0; i--)
				if (setRookAttack(i, piece.h))
					break;
			for (int i = piece.v + 1; i < 8; i++)
				if (setRookAttack(i, piece.h))
					break;
			for (int i = piece.h - 1; i >= 0; i--)
				if (setRookAttack(piece.v, i))
					break;
			for (int i = piece.h + 1; i < 8; i++)
				if (setRookAttack(piece.v, i))
					break;
		} else if (piece.name == 'K') {
			for (int i = -1; i < 2; i++)
				if (i + piece.v >= 0 && i + piece.v < 8)
					for (int j = -1; j < 2; j++)
						if (j + piece.h >= 0 && j + piece.h < 8 && BOARD[piece.v + i][piece.h + j] == 0)
							BOARD[piece.v + i][piece.h + j] = 'k';
		}
	}

	private static boolean setRookAttack(int v, int h) {
		if (BOARD[v][h] == 'K') {
			BOARD[v][h] = 'F';
			return (true);
		}
		BOARD[v][h] = 'r';
		return (false);
	}

	static class Piece {
		int v;
		int h;
		char name;

		Piece(int v, int h, char name) {
			this.name = name;
			this.v = v;
			this.h = h;
		}
	}
}
