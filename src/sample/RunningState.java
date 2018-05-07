package sample;

public class RunningState extends GameState {

    public RunningState() {
        this.gameState = "running";
    }

    @Override
    public String getState() {
        return this.gameState;
    }
}
