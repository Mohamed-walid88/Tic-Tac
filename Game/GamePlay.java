public class GamePlay {
    private final char[][] board;
    private final StateChecker checker = new StateChecker();
    private final char player1;
    private char player2;
    private boolean singlePlayer;
    private DIFFICULTY difficulty;
    private GameState state = GameState.STILL;


    public GamePlay(char Player1, char[][] Board) {
        this.player1 = Player1;
        this.board = Board;
        if (this.player1 == 'X') this.player2 = 'O';
        else this.player2 = 'X';
    }

    public GamePlay(char Player1, DIFFICULTY Difficulty, char[][] Board) {
        this.player1 = Player1;
        this.difficulty = Difficulty;
        this.singlePlayer = true;
        this.board = Board;
    }

    public GameState GameLoop() {
        if (singlePlayer) {
            return SinglePlayerGame();
        }
        else {
            return TwoPlayerGame();
        }
    }

    private GameState SinglePlayerGame() {
        char computerSide;
        if (player1 == 'X') computerSide = 'O';
        else computerSide = 'X';

        ComputerPlayer computer = new ComputerPlayer(computerSide, difficulty);
        while (state == GameState.STILL) {
            int row = getRow();
            int col = getCol();

            board[row][col] = player1;
            computer.computerMove(board);
            state = checker.getGameState(board, player1);
        }
        return state;
    }

    private GameState TwoPlayerGame() {
        int playerToMove = 0;
        while (state == GameState.STILL) {
            int row = getRow();
            int col = getCol();

            board[row][col] = (playerToMove % 2 == 0) ? player1 : player2;
            playerToMove++;
            state = checker.getGameState(board, player1);
        }
        return state;
    }

    private int getRow() {
        return 0;
    }
    private int getCol() {
        return 0;
    }
}
