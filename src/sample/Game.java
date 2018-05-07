package sample;

public abstract class Game {
	Player player1;
	Player player2;

	private static String gameMode;

	public static void setGameMode(String game_Mode){
		gameMode = game_Mode;
	}

	public static String getGameMode(){
		return gameMode;
	}
}
