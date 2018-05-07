package sample;

public class PlayerVSRandomComputer extends Game {

	public  PlayerVSRandomComputer() {
		// TODO Auto-generated method stub
		player1 = new ConsolePlayer("player 1");
		player2 =  RandomPlayer.getInstance();

	}
}
