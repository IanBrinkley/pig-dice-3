public class Player {
    //instance variables
    private String playerName;
    private Die d1 = new Die();
    private Die d2 = new Die();
    private int totalScore = 0;
    private int roundScore = 0;
    private boolean isTurn = true;

    //constructor
    public Player(boolean isTurn) {
        this.isTurn = isTurn;
    }

    public void rollDice() {
        d1.roll();
        d2.roll();
    }

    public void passTurn() {
        isTurn = false;
        totalScore += roundScore;
        roundScore = 0;
    }
    // returns only the scoring of the dice
    public int scoreDice() {
        if(d1.getFace() == 1 || d2.getFace() == 1) {
            if (d1.getFace() == 1 && d2.getFace() == 1) {
                return -1;
            }
            return 0;
        }
        return d1.getFace() + d2.getFace();
    }
    // does things based on the score of the dice
    public void updateScores() {
        if(scoreDice() == -1) {
            totalScore = 0;
            roundScore = 0;
            isTurn = false;
        } else if (scoreDice() == 0) {
            roundScore = 0;
            isTurn = false;
        } else {
            roundScore += scoreDice();
        }
    }
    // many getters
    public int getTotalScore() {
        return totalScore;
    }
    public int getRoundScore() {
        return roundScore;
    }

    public boolean getIsTurn() {
        return isTurn;
    }
    public void setIsTurn(boolean isTurn) {
        this.isTurn = isTurn;
    }

    public int getD1Face() {
        return d1.getFace();
    }

    public int getD2Face() {
        return d2.getFace();
    }
}