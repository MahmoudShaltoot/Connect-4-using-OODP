package sample;

public interface Player {

	public boolean play() throws CloneNotSupportedException;

	public boolean play(String playerID, int column);
}
