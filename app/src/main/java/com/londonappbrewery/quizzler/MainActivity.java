package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    // TODO: Declare member variables here:
    Button mTrueButton;
    Button mFalseButton;
    TextView mScoreTextView;
    TextView mQuestionTextView;
    ProgressBar mProgressBar;
    int mScore;
    int mIndex;
    int mQuestion;



    // TODO: Uncomment to create question bank
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
    };

    // TODO: Declare constants here
    final int PROGRESS_BAR_INCREMENT = (int) Math.ceil (100.0 / mQuestionBank.length);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mScoreTextView = (TextView) findViewById(R.id.score);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        mQuestion = mQuestionBank[mIndex].getQuestionID(); //using getter to get Question ID

        mQuestionTextView.setText(mQuestion);//set the index value (question) to the main textView



        mTrueButton.setOnClickListener(new View.OnClickListener() { //anonymous onClick
            @Override
            public void onClick(View view) {
                Log.d("Quizzler", "True Button Pressed");
                //Make a Toast Pop up anonymous
               // Toast.makeText(getApplicationContext(), "True Pressed", Toast.LENGTH_SHORT).show();
                checkAnswer(true);//display toast message
                updateQuestion();

            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() { //unnamed onclick (anonymous)
            @Override
            public void onClick(View view) {
                Log.d("Quizzler", "False Button Pressed");

                //make a toast variable that can be called upon again
               // Toast myToast = Toast.makeText(getApplicationContext(), "False Pressed", Toast.LENGTH_SHORT);
               // myToast.show();
                checkAnswer(false);//display toast message
                updateQuestion();//update the question after selection


            }
        });






    }

    private void updateQuestion(){
        mIndex = (mIndex + 1) % mQuestionBank.length;

        if(mIndex == 0){//if the array resets to 0, alert the user
            AlertDialog.Builder alert = new AlertDialog.Builder(this);//could use getApplicationContext Also.  this refers to mainActivity
            alert.setTitle("Game Over");
            alert.setCancelable(false);
            alert.setMessage("You Scored " + mScore + " Points");
            alert.setPositiveButton("Close Applicaton", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {//button for close alert
                    finish(); //closes app
                }
            });
            alert.show();
        }

        mQuestion = mQuestionBank[mIndex].getQuestionID();
        mQuestionTextView.setText(mQuestion);
        mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);

    }

    private void checkAnswer(boolean userSelection){//display user selection as true/false
        boolean correctAnswer = mQuestionBank[mIndex].isAnswer();
        if(userSelection == correctAnswer)
        {            //Toast message with true or false confirmation
            Toast.makeText(getApplicationContext(), R.string.correct_toast, Toast.LENGTH_SHORT).show();
            mScore = mScore + 1;
            mScoreTextView.setText("Score " + mScore + "/" + mQuestionBank.length);
        }else{
            //Toast message with true or false confirmation
            Toast.makeText(getApplicationContext(), R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
        }


    }


}
