import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;

public class Game extends Application {
    //instance variables
    private boolean gameEnded = false;

    //players
    Player p1 = new Player(true);
    Player p2 = new Player(false);

    //JavaFX stuff
    private Button rollDiceBtn;
    private Button passTurnBtn;
    private Text diceTxt;
    private Text roundScoreTxt;
    private Text p1ScoreTxt;
    private Text p2ScoreTxt;
    private Text currentTurnTxt;
    private Text messageTxt;
    private Text titleTxt;
    @Override
    public void start(Stage stage) {
        //MORE JavaFX sthuff
        rollDiceBtn = new Button("Roll Dice");
        passTurnBtn = new Button("Pass Turn");
        diceTxt = new Text("Dice: 0 0");
        roundScoreTxt = new Text("Round score: 0");
        p1ScoreTxt = new Text("Player 1 score: 0");
        p2ScoreTxt = new Text("Player 2 score: 0");
        currentTurnTxt = new Text("Player 1's turn");
        messageTxt = new Text("");
        titleTxt = new Text("Pig Dice - Ian Brinkley, Zach Shannon");

        titleTxt.setFill(Color.rgb(140, 140, 140));

        HBox hBox1 = new HBox(40);
        hBox1.getChildren().addAll(currentTurnTxt, roundScoreTxt);
        hBox1.setPrefWidth(230);
        hBox1.setStyle("-fx-background-color: #e6e6e6");
        hBox1.setPadding(new Insets(10, 10, 10, 10));

        HBox hBox2 = new HBox(10);
        hBox2.getChildren().addAll(rollDiceBtn, diceTxt, passTurnBtn);
        hBox2.setStyle("-fx-background-color: #ffffff");
        hBox2.setPadding(new Insets(10, 10, 10, 10));

        HBox hBox3 = new HBox(20);
        hBox3.getChildren().addAll(p1ScoreTxt, p2ScoreTxt);
        hBox3.setStyle("-fx-background-color: #e6e6e6");
        hBox3.setPadding(new Insets(10, 10, 10, 10));

        VBox vBox1 = new VBox(10);
        vBox1.getChildren().addAll(titleTxt, hBox1, hBox2, hBox3, messageTxt);
        vBox1.setLayoutX(10);
        vBox1.setLayoutY(10);

        rollDiceBtn.setOnAction(this::rollDicePress);
        passTurnBtn.setOnAction(this::passTurnPress);

        Group root = new Group(vBox1);

        Scene scene = new Scene(root, 400.0, 400.0);

        stage.setTitle("PigDice");
        stage.setScene(scene);
        stage.show();
    }
    private void rollDicePress(ActionEvent event) {
        if (!gameEnded) {
            messageTxt.setText("");
            if (p1.getIsTurn()) {
                //Player 1 turn
                p1.rollDice();
                //if a 1 is rolled
                if (p1.scoreDice() == 0 || p1.scoreDice() == -1) {
                    p2.setIsTurn(true);
                    currentTurnTxt.setText("Player 2's turn");
                    roundScoreTxt.setText("Round score: " + p1.getRoundScore());
                    if(p1.scoreDice() == 0){
                        messageTxt.setText("Rolled a 1!");
                    }else{
                        messageTxt.setText("Rolled two 1s!");
                    }
                }
                //end turn stuff
                p1.updateScores();
                p1ScoreTxt.setText("Player 1 score: " + p1.getTotalScore());
                roundScoreTxt.setText("Round score: " + p1.getRoundScore());
                diceTxt.setText("Dice: " + p1.getD1Face() + " " + p1.getD2Face());
            } else {
                //Player 2 turn
                p2.rollDice();
                //if a 1 is rolled
                if (p2.scoreDice() == 0 || p2.scoreDice() == -1) {
                    p1.setIsTurn(true);
                    currentTurnTxt.setText("Player 1's turn");
                    roundScoreTxt.setText("Round score: " + p2.getRoundScore());
                    if(p2.scoreDice() == 0){
                        messageTxt.setText("Rolled a 1!");
                    }else{
                        messageTxt.setText("Rolled two 1s!");
                    }
                }
                //end turn stuff
                p2.updateScores();
                p2ScoreTxt.setText("Player 2 score: " + p2.getTotalScore());
                roundScoreTxt.setText("Round score: " + p2.getRoundScore());
                diceTxt.setText("Dice: " + p2.getD1Face() + " " + p2.getD2Face());
            }
        }
    }
    private void passTurnPress(ActionEvent event) {
        if (!gameEnded) {
            if (p1.getIsTurn()) {
                p1.passTurn();
                p1ScoreTxt.setText("Player 1 score: " + p1.getTotalScore());
                roundScoreTxt.setText("Round score: 0");
                if (p1.getTotalScore() < 100) {
                    p2.setIsTurn(true);
                    currentTurnTxt.setText("Player 2's turn");
                } else {
                    gameEnded = true;
                    messageTxt.setText("Player 1 wins!");
                }
            } else {
                p2.passTurn();
                p2ScoreTxt.setText("Player 2 score: " + p2.getTotalScore());
                roundScoreTxt.setText("Round score: 0");
                if (p2.getTotalScore() < 100) {
                    p1.setIsTurn(true);
                    currentTurnTxt.setText("Player 1's turn");
                } else {
                    gameEnded = true;
                    messageTxt.setText("Player 2 wins!");
                }
            }
        }
    }
}