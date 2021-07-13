package com.example.android.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int i,a,b,score = 0,noOfQuestions=0;;
    Button button0,button1,button2,button3,playAgainButton, goButton;
    TextView resultTextView,scoreTextView,sumTextView,timerTextView;
    ArrayList<Integer> answers;
    ConstraintLayout gameLayout;

    public void chooseAnswer(View view)
    {
        Button button = (Button)view;
        int choosenAnswer = Integer.parseInt(button.getText().toString());
        if (choosenAnswer==(a + b)) {
            resultTextView.setText("Correct!!");
            score++;
            noOfQuestions++;
            newQuestion();}
        else{
            resultTextView.setText("Worng!!");
            noOfQuestions++;
            newQuestion();}

        scoreTextView.setText(score+" / "+noOfQuestions);
    }

    public void newQuestion()
    {
        answers = new ArrayList<Integer>();
        Random rand = new Random();
        a = rand.nextInt(21);
        b = rand.nextInt(21);
        answers.clear();
        sumTextView.setText(a+ " + "+ b);
        int locationOfCorrectAnswer = rand.nextInt(4);

        for ( i = 1; i<=4 ; i++)
        {
            if(i==locationOfCorrectAnswer){
                answers.add(a+b);
                Log.i("ans: ", String.valueOf(a+b));
            }
            else{
                int ans = rand.nextInt(40);
                answers.add(ans);
                Log.i("ans: ", String.valueOf(ans));

            }}

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    public void playAgain(View view)
    {
        score=0;
        noOfQuestions=0;
        timerTextView.setText("30s");
        newQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);

        new CountDownTimer(30100,1000)
        {

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done !!");
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();

    }
    public void hide(View view)
    {
        goButton.setVisibility(view.INVISIBLE);
        playAgain(sumTextView);
        gameLayout.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        playAgainButton = (Button)findViewById(R.id.playAgainButton);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        timerTextView = (TextView)findViewById(R.id.timerTextView);
        gameLayout = (ConstraintLayout)findViewById(R.id.gameLayout);
        goButton = (Button)findViewById(R.id.goButton);

        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

    }
}
