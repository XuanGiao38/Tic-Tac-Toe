package Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
public class Controller {
    @FXML
    private Text title;
    @FXML
    private Button playerXButton;
    @FXML
    private Button playerOButton;
    @FXML
    private GridPane gameBoard;
    @FXML
    private Label playerXLabel;
    @FXML
    private Label playerOLabel;
    @FXML
    private Text message;
    @FXML
    private Button resetButton;

    private Button[][] board = new Button[3][3];
    private char currentPlayer = 'X';
    private int playerXScore = 0;
    private int playerOScore = 0;

    @FXML
    public void initialize() {
        title.setFont(Font.font("System", FontWeight.BOLD, 24));

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = new Button("");
                board[row][col].setPrefSize(75, 75);
                board[row][col].setFont(Font.font("System", FontWeight.BOLD, 36));
                gameBoard.add(board[row][col], col, row);
                int finalRow = row;
                int finalCol = col;
                board[row][col].setOnAction((ActionEvent event) -> handleButtonClicked(finalRow, finalCol));
            }
        }
        updateScoreboard();
        resetGame();
    }
    @FXML
    private void handlePlayerXButtonClicked(ActionEvent event) {
        currentPlayer = 'X';
        message.setFill(Color.BLACK);
        message.setText("Lượt của X");
    }

    @FXML
    private void handlePlayerOButtonClicked(ActionEvent event) {
        currentPlayer = 'O';
        message.setFill(Color.BLACK);
        message.setText("Lượt của O");
    }
    @FXML
    private void handleButtonClicked(int row, int col) {
        Button button = board[row][col];
        if (button.getText().equals("")) {
            button.setText(Character.toString(currentPlayer));
            if (checkForWin()) {
                message.setFill(Color.GREEN);
                message.setText("Người chơi " + currentPlayer + " thắng!");
                if (currentPlayer == 'X') {
                    playerXScore++;
                } else {
                    playerOScore++;
                }
                updateScoreboard();
                disableButtons();
            } else if (checkForTie()) {
                message.setFill(Color.BLUE);
                message.setText("HÒA!");
                disableButtons();
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                message.setFill(Color.BLACK);
                message.setText("Lượt của " + currentPlayer);
            }
            button.getStyleClass().removeAll("text-x", "text-o");
        }
    }
    private boolean checkForWin() {
        for (int row = 0; row < 3; row++) {
            if (board[row][0].getText().equals(board[row][1].getText()) && board[row][0].getText().equals(board[row][2].getText()) && !board[row][0].getText().equals("")) {
                return true;
            }
        }

        for (int col = 0; col < 3; col++) {
            if (board[0][col].getText().equals(board[1][col].getText()) && board[0][col].getText().equals(board[2][col].getText()) && !board[0][col].getText().equals("")) {
                return true;
            }
        }

        if (board[0][0].getText().equals(board[1][1].getText()) && board[0][0].getText().equals(board[2][2].getText()) && !board[0][0].getText().equals("")) {
            return true;
        }

        if (board[0][2].getText().equals(board[1][1].getText()) && board[0][2].getText().equals(board[2][0].getText()) && !board[0][2].getText().equals("")) {
            return true;
        }

        return false;
    }

    private boolean checkForTie() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void updateScoreboard() {
        playerXLabel.setText("Điểm của X: " + playerXScore);
        playerOLabel.setText("Điểm của O: " + playerOScore);
    }

    private void disableButtons() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col].setDisable(true);
            }
        }
    }

    private void enableButtons() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col].setDisable(false);
            }
        }
    }
    @FXML
    private void handleResetButtonClicked(ActionEvent event) {
        resetGame();
    }
    private void resetGame() {
        currentPlayer = 'X';
        message.setText("Lượt của X");
        enableButtons();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col].setText("");
            }
        }
    }
}
