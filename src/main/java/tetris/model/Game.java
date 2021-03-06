package main.java.tetris.model;

public class Game {


    private final Board board;
    private Segment nextSegment;

    private boolean playing = false;
    private boolean paused = false;
    private boolean dropping = false;
    private boolean gameOver = false;

    private int freeFallIterations;
    private int totalScore;

    public Game() {
        board = new Board();
    }

    public BoardCell[][] getBoardCells() {
        return board.getBoardWithPiece();
    }

    public long getIterationDelay() {
        return (long) (0.5 * 1000);
    }

    public int getScore() {
        return (100*board.getFullLines());
    }

    public int getTotalScore() {
        return totalScore;
    }


    public void startGame() {
        paused = false;
        dropping = false;
        nextSegment = Segment.getRandomPiece();
        board.setCurrentSegment(Segment.getRandomPiece());
        playing = true;
    }

    public boolean isPlaying() {
        return playing;
    }


    public boolean isGameOver() {
        return gameOver;
    }


    public void moveLeft() {
        board.moveLeft();
    }

    public void moveRight() {
        board.moveRight();
    }

    public void moveDown() {
        if (!board.canCurrentPieceMoveDown()) {

            if (freeFallIterations == 0) {
                playing = false;
                gameOver = true;
            } else {
                dropping = false;
                board.setCurrentSegment(nextSegment);
                nextSegment = Segment.getRandomPiece();
                totalScore = getScore();
                freeFallIterations = 0;
            }
        } else {
            board.moveDown();
            freeFallIterations++;
        }
    }

    public void rotate() {
        board.rotate();
    }

    public void drop() {
        dropping = true;
    }

    public boolean isDropping() {
        return dropping;
    }

}