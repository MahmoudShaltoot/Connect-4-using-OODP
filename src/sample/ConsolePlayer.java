package sample;

import java.util.Scanner;

public class ConsolePlayer implements Player {

	String playerID;

	public ConsolePlayer(String playerID) {
		this.playerID = playerID;
	}

	@Override
	public boolean play() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean play(String playerID ,int column) {
		if (ConnectFour.dropChip(column, playerID.equals("player 1") ? 'R' : 'Y')) {
			ConnectFour.setValidGame(false);
		} else {
			ConnectFour.setValidGame(true);
			ConnectFour.displayGrid();
			if (ConnectFour.gameStatus(column, playerID.equals("player 1") ? 'R' : 'Y')) {
				System.out
						.println(this.playerID.equals("player 1") ? "Player 1(R)" : "Player 2(Y)" + " won! Game Over!");
				return true;
			} else if (ConnectFour.checkdraw()) {
				System.out.println("it's a draw up");
				return true;
			}
		}
		return false;
	}
}