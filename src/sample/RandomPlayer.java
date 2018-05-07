package sample;

import java.util.Random;

public class RandomPlayer implements Player {
	String playerID;

	private static RandomPlayer instance = new RandomPlayer("player 2");

	private RandomPlayer(String playerID){
		this.playerID = playerID;
	}
	public static RandomPlayer getInstance(){
		return instance;
	}
	@Override
	public boolean play() {
		// TODO Auto-generated method stub
		Random randomColumn = new Random();
		int Columnposition = randomColumn.nextInt(6);

		while (ConnectFour.dropChip(Columnposition, 'Y')) {
			Columnposition = randomColumn.nextInt(6);
		}
		ConnectFour.displayGrid();
		if (ConnectFour.gameStatus(Columnposition, 'Y')) {
			System.out.println("Player 2(Y)" + " won! Game Over!");
			return true;
		} else if (ConnectFour.checkdraw()) {
			System.out.println("it's a draw up");
			return true;
		}
		return false;
	}

    @Override
    public boolean play(String playerID, int column) {
        return false;
    }
}
