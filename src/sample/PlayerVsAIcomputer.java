package sample;

public class PlayerVsAIcomputer extends Game{

    public PlayerVsAIcomputer(){
        player1 = new ConsolePlayer("player 1");
        player2 =  AIplayer.getInstance();
    }
}
