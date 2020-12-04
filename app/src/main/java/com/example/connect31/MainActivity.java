package com.example.connect31;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // 0 = yellow, 1 = red

    private int activePlayer = 0;

    private LinearLayout mGameBoard;

    private LinearLayout mDialog;

    private TextView mWinnerMessage;

    private boolean gameIsActive = true;

    // 2 means unplayed

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8},{0,4,8},{2,4,6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tic_tac_toe);

        mGameBoard = findViewById(R.id.game_board);
        mDialog = findViewById(R.id.dialog);
        mWinnerMessage = findViewById(R.id.winner_message);
    }

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        int tag = Integer.parseInt(counter.getTag().toString());

        if (gameState[tag] == 2 && gameIsActive) {

            gameState[tag] = activePlayer;

            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).setDuration(300);

            for (int[] winningPosition: winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]]
                && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                && gameState[winningPosition[0]] != 2) {

                    // someone has won!

                    gameIsActive = false;

                    int winner = gameState[winningPosition[0]];

                    mDialog.setVisibility(View.VISIBLE);
                    mDialog.animate().alpha(1f).setDuration(1000);
                    mGameBoard.animate().alpha(0f).setDuration(500);

                    if (winner == 0) {
                        mWinnerMessage.setText("Player Yellow Wins!");
                    } else {
                        mWinnerMessage.setText("Player Red Wins");
                    }


                } else {

                    boolean gameIsOver = true;
                    for (int counterState : gameState) {
                        if (counterState == 2) gameIsOver = false;
                    }
                    if (gameIsOver) {
                        gameIsActive = false;
                        mDialog.setVisibility(View.VISIBLE);
                        mDialog.animate().alpha(1f).setDuration(1000);
                        mGameBoard.animate().alpha(0f).setDuration(500);
                        mWinnerMessage.setText("It's a draw.");
                    }

                }
            }

        }

    }

    public void playAgain(View view) {

        gameIsActive = true;

        activePlayer = 0;

        Arrays.fill(gameState, 2);

        mDialog.setVisibility(View.GONE);
        mDialog.setAlpha(0f);

        for (int i = 0; i < mGameBoard.getChildCount(); i++) {
            LinearLayout linearLayoutHorizontal = (LinearLayout) mGameBoard.getChildAt(i);
            for (int x = 0; x < linearLayoutHorizontal.getChildCount(); x++) {
                LinearLayout childOfHorizontal = (LinearLayout) linearLayoutHorizontal.getChildAt(x);
                ImageView imageView = (ImageView) childOfHorizontal.getChildAt(0);
                imageView.setImageResource(0);
            }
        }

        mGameBoard.animate().alpha(1f).setDuration(500);
    }
}















