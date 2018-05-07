package sample;

public class ConnectFour {
	public static char[][] connectfourgrid = new char[6][7];
	private static int row;
	private static int column;

	public static boolean gameOver = false;
	public static boolean validGame = true;

	private static Momento momento = new Momento();
	private static ContextState contextState = new ContextState();

	public static void setValidGame(boolean validGame2) {
		validGame = validGame2;
	}

	public static void displayGrid() {
		for (int i = 0; i < connectfourgrid.length; i++) {
			for (int j = 0; j < connectfourgrid[i].length; j++) {
				System.out.print("|" + (connectfourgrid[i][j]));
			}
			System.out.println("|");
		}
		for (int i = 0; i < connectfourgrid.length - 1; i++)
			System.out.print("---");
		System.out.println();
	}

	public static Boolean dropChip(int ColumnPoistion, char ChipColor) {
		for (int i = connectfourgrid.length - 1; i >= 0; i--)// start at the bottom of the grid
		{
			if (connectfourgrid[i][ColumnPoistion] == 0) {
				Momento.saveStateToMomento(connectfourgrid);
				connectfourgrid[i][ColumnPoistion] = ChipColor;
				row = i; 				column = ColumnPoistion;
				return false;
			}
		}
		System.out.println("Column is Full, " + ChipColor + " player ,try again!");
		return true;
	}

	public static void getMomentoState(){
		momento.getStateFromMomento();
		displayGrid();
	}

	public static int getRow(){
		return row;
	}

	public static int getColumn(){return column;}

	public static boolean gameStatus(int ColumnPoistion, char ChipColor) {
		int rowPosition = 0;
		for (int i = 0; i < connectfourgrid.length; i++) {
			if (connectfourgrid[i][ColumnPoistion] != 0) {
				rowPosition = i;
				break;
			}
		}
		gameOver = true;
		if (checkColumn(ColumnPoistion, ChipColor, rowPosition)) {
			contextState.setState(new GameOverState());
			return true;
		}
		if (CheckRow(ColumnPoistion, ChipColor, rowPosition)) {
			contextState.setState(new GameOverState());
			return true;
		}
		if (checkleftDigonal(ColumnPoistion, ChipColor, rowPosition)) {
			contextState.setState(new GameOverState());
			return true;
		}
		if (checkRightDigonal(ColumnPoistion, ChipColor, rowPosition)) {
			contextState.setState(new GameOverState());
			return true;
		}
		gameOver = false;
		return false;
	}

	public static boolean checkColumn(int ColumnPoistion, char ChipColor, int rowPoistion) {
		int chipCounter = 1;
		if ((rowPoistion + 4) <= 6) {
			for (int i = rowPoistion + 1; i <= (rowPoistion + 3); i++) {
				if (ChipColor == connectfourgrid[i][ColumnPoistion]) {
					chipCounter++;
				} else
					break;
			}
		}
		if (chipCounter >= 4) {
			return true;
		}

		return false;
	}

	public static boolean CheckRow(int ColumnPoistion, char ChipColor, int rowPoistion) {
		int chipCounter = 1;
		for (int i = ColumnPoistion - 1; i >= 0; i--) {
			if (ChipColor == connectfourgrid[rowPoistion][i])
				chipCounter++;
			else
				break;
			if (chipCounter >= 4)
				return true;
		}
		for (int i = ColumnPoistion + 1; i < connectfourgrid[0].length; i++) {
			if (ChipColor == connectfourgrid[rowPoistion][i])
				chipCounter++;
			else
				break;
			if (chipCounter >= 4)
				return true;
		}

		return false;
	}

	public static boolean checkleftDigonal(int ColumnPoistion, char ChipColor, int rowPoistion) {
		int chipCounter = 1;

		for (int i = rowPoistion - 1, j = ColumnPoistion - 1; i >= 0 && j >= 0; i--, j--)
			if (ChipColor == connectfourgrid[i][j])
				chipCounter++;
			else
				break;
		if (chipCounter >= 4)
			return true;

		for (int i = rowPoistion + 1, j = ColumnPoistion + 1; i < connectfourgrid.length
				&& j < connectfourgrid[0].length; i++, j++)
			if (ChipColor == connectfourgrid[i][j])
				chipCounter++;
			else
				break;
		if (chipCounter >= 4)
			return true;

		return false;
	}

	public static boolean checkRightDigonal(int ColumnPoistion, char ChipColor, int rowPoistion) {
		int chipcounter = 1;
		for (int i = rowPoistion + 1, j = ColumnPoistion - 1; i < connectfourgrid.length && j >= 0; i++, j--)
			if (ChipColor == connectfourgrid[i][j])
				chipcounter++;
			else
				break;
		if (chipcounter >= 4)
			return true;
		for (int i = rowPoistion - 1, j = ColumnPoistion + 1; i >= 0 && j < connectfourgrid[0].length; i--, j++)
			if (ChipColor == connectfourgrid[i][j])
				chipcounter++;
			else
				break;
		if (chipcounter >= 4)
			return true;

		return false;
	}

	public static boolean checkdraw() {
		for (int i = 0; i < connectfourgrid[0].length; i++) {
			if (connectfourgrid[0][i] == 0)
				return false;
		}
		return true;
	}
}
