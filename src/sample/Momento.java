package sample;

public class Momento {
    public static char[][] connectfourboard = new char[6][7];

    public static void saveStateToMomento(char[][] connectfourgrid){
        for (int i = 0; i < connectfourgrid.length; i++) {
            for (int j = 0; j < connectfourgrid[i].length; j++) {
                connectfourboard[i][j] = connectfourgrid[i][j];
            }
        }
    }

    public void getStateFromMomento() {
        for (int i = 0; i < connectfourboard.length; i++) {
            for (int j = 0; j < connectfourboard[i].length; j++) {
                ConnectFour.connectfourgrid[i][j] = connectfourboard[i][j];

            }
        }
    }
}
