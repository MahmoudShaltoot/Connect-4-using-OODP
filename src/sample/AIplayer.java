package sample;

public class AIplayer implements Player {

    String playerID;

    minimax minimaxObj ;

    private static AIplayer instance = new AIplayer("player 2");

    public static AIplayer getInstance(){
        return instance;
    }

    private AIplayer(String playerID) {
        this.playerID = playerID;
        minimaxObj = new minimax(1);
    }

    @Override
    public boolean play() throws CloneNotSupportedException {
        Board board = new Board(6,7);
        board.setBoard(ConnectFour.connectfourgrid);

        int column = minimaxObj.getAction(board);

        ConnectFour.dropChip(column, 'Y');

        ConnectFour.displayGrid();
        if (ConnectFour.gameStatus(column, 'Y')) {
            System.out.println("AI Algorithm Destroyed you (Y) / " + " won! Game Over!");
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
