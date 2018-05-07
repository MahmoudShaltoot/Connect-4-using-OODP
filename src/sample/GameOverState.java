package sample;

public class GameOverState extends GameState {

    public  GameOverState() {
        this.gameState = "Game Over";
    }

    @Override
    public String getState() {
        return this.gameState;
    }
}
