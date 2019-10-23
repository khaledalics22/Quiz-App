package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.security.acl.Group;

public class MainActivity extends AppCompatActivity {
    // define constants to refer to questions in Arrays
    private static final int Q1 = 0;
    private static final int Q2 = 1;
    private static final int Q3 = 2;
    private static final int Q4 = 3;
    private static final int Q5 = 4;
    private static final int Q6 = 5;

    RadioButton RB1_1;
    RadioButton RB1_2;
    RadioButton RB1_3;
    RadioButton RB1_4;
    RadioButton RB2_1;
    RadioButton RB2_2;
    RadioButton RB2_3;
    RadioButton RB2_4;

    CheckBox CHB1_1;
    CheckBox CHB1_2;
    CheckBox CHB1_3;
    CheckBox CHB1_4;
    CheckBox CHB2_1;
    CheckBox CHB2_2;
    CheckBox CHB2_3;
    CheckBox CHB2_4;

    EditText ET1;
    EditText ET2;

    TextView Q1_1;
    TextView Q1_2;
    TextView Q2_1;
    TextView Q2_2;
    TextView Q3_1;
    TextView Q3_2;

    RadioGroup radioGroup1;
    RadioGroup radioGroup2;

    Button btnSubmit;
    Button btnReset;

    Quiz Quiz1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Yout got (" + checkAnswers() + "/6)", Toast.LENGTH_LONG).show();
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((RadioButton) findViewById(radioGroup1.getCheckedRadioButtonId())).setChecked(false);
                ((RadioButton) findViewById(radioGroup2.getCheckedRadioButtonId())).setChecked(false);
                CHB1_1.setChecked(false);
                CHB1_2.setChecked(false);
                CHB1_3.setChecked(false);
                CHB1_4.setChecked(false);
                CHB2_1.setChecked(false);
                CHB2_2.setChecked(false);
                CHB2_3.setChecked(false);
                CHB2_4.setChecked(false);
                ET1.setText("");
                ET2.setText("");
            }
        });
        createQuiz1();
        attachQuestions();
        attachQuiz1Answers();
    }

    /* Name:checkAnswers
       parameters: No parameters
       this method checks if the user's inputs and evaluate the answer
    */
    private int checkAnswers() {
        /*get different model answers of the quiz*/
        String[] ans1 = Quiz1.getModelAns1();
        String[] ans2 = Quiz1.getModelAns2();
        String[] ans3 = Quiz1.getModelAns3();
        int score = 0;
        RadioButton radioButton = (RadioButton) findViewById(radioGroup1.getCheckedRadioButtonId());
        /* checks if there is a radioButton checked and the answer is correct if true add 1 to the score*/
        if (radioButton != null && ans1[Q1].matches(radioButton.getText().toString().trim()))
            score++;
        radioButton = (RadioButton) findViewById(radioGroup2.getCheckedRadioButtonId());
        if (radioButton != null && ans1[Q2].matches(radioButton.getText().toString().trim()))
            score++;
        /*check if answer of question six and seven (q3_1 &and q3_2) is correct,if so add one to score*/
        if (ans1[Q5].matches(ET1.getText().toString().trim()))
            score++;
        if (ans1[Q6].matches(ET2.getText().toString().trim()))
            score++;

        /*
        check multi answers
         */
        boolean ans = true;
        /*
        if value of checkBox equals any answer of all three model answers
        and the checkBox not checked, set answer to false (wrong answer)
        and if the value does not equal any answer then check if the checkedBox is checked.
        if checked,wrong answer
        and if neither of these happened then answer is correct
         */
        if (ans1[Q3].matches(CHB1_1.getText().toString().trim())
                || ans2[Q3].matches(CHB1_1.getText().toString().trim())
                || ans3[Q3].matches(CHB1_1.getText().toString().trim())
        ) {
            if (!CHB1_1.isChecked()) {
                ans = false;
            }
        } else if (CHB1_1.isChecked()) {
            ans = false;
        }
        if (ans1[Q3].matches(CHB1_2.getText().toString().trim())
                || ans2[Q3].matches(CHB1_2.getText().toString().trim())
                || ans3[Q3].matches(CHB1_2.getText().toString().trim())
        ) {
            if (!CHB1_2.isChecked()) {
                ans = false;
            }
        } else if (CHB1_2.isChecked()) {
            ans = false;
        }
        if (ans1[Q3].matches(CHB1_3.getText().toString().trim())
                || ans2[Q3].matches(CHB1_3.getText().toString().trim())
                || ans3[Q3].matches(CHB1_3.getText().toString().trim())
        ) {
            if (!CHB1_3.isChecked()) {
                ans = false;
            }

        } else if (CHB1_3.isChecked()) {
            ans = false;
        }
        if (ans1[Q3].matches(CHB1_4.getText().toString().trim())
                || ans2[Q3].matches(CHB1_4.getText().toString().trim())
                || ans3[Q3].matches(CHB1_4.getText().toString().trim())
        ) {
            if (!CHB1_4.isChecked()) {
                ans = false;
            }
        } else if (CHB1_4.isChecked()) {
            ans = false;
        }
        if (ans) {
            score++;
        }
        /*
        same for the question four
         */
        ans = true;
        if (ans1[Q4].matches(CHB2_1.getText().toString().trim())
                || ans2[Q4].matches(CHB2_1.getText().toString().trim())
                || ans3[Q4].matches(CHB2_1.getText().toString().trim())
        ) {
            if (!CHB2_1.isChecked()) {
                ans = false;
            }
        } else if (CHB2_1.isChecked()) {
            ans = false;
        }
        if (ans1[Q4].matches(CHB2_2.getText().toString().trim())
                || ans2[Q4].matches(CHB2_2.getText().toString().trim())
                || ans3[Q4].matches(CHB2_2.getText().toString().trim())
        ) {
            if (!CHB2_2.isChecked()) {
                ans = false;
            }
        } else if (CHB2_2.isChecked()) {
            ans = false;
        }
        if (ans1[Q4].matches(CHB2_3.getText().toString().trim())
                || ans2[Q4].matches(CHB2_3.getText().toString().trim())
                || ans3[Q4].matches(CHB2_3.getText().toString().trim())
        ) {
            if (!CHB2_3.isChecked()) {
                ans = false;
            }

        } else if (CHB2_3.isChecked()) {
            ans = false;
        }
        if (ans1[Q4].matches(CHB2_4.getText().toString().trim())
                || ans2[Q4].matches(CHB2_4.getText().toString().trim())
                || ans3[Q4].matches(CHB2_4.getText().toString().trim())
        ) {
            if (!CHB2_4.isChecked()) {
                ans = false;
            }

        } else if (CHB2_4.isChecked()) {
            ans = false;
        }
        if (ans) {
            score++;
        }
        return score;
    }
    /* Name:createQuiz1
          parameters: No parameters
          this method generates the questions and its answers
          and initialize the Quiz1 Object with them
       */
    private void createQuiz1() {
        /*
        set questions of the quiz
         */
        String[] ques = {
                "1+2*5=",
                "4*3/4=",
                "1-2*7=",
                "8*2/4=",
                "20+2*5=",
                "5-2*6=",

        };
        /*
        set the model answers of the quiz
         */
        String[] ModelAns1 = {
                "11",
                "3",
                "-13",
                "4",
                "30",
                "-7",
        };
        String[] ModelAns2 = {
                "11",
                "3",
                "2/1-15",
                "9-5/1",
                "30",
                "-7",
        };
        String[] ModelAns3 = {
                "11",
                "3",
                "5/5-14",
                "20/5",
                "30",
                "-7",
        };
        /*
        create a quiz object with questions and answers
         */
        Quiz1 = new Quiz(ques, ModelAns1, ModelAns2, ModelAns3);
    }

    /* Name:attachQuestions
      parameters: No parameters
      this method attaches the data to the views on the screen
      return: void
   */
    private void attachQuestions() {
        String ques = Q1_1.getText() + Quiz1.getQuestions()[Q1];
        Q1_1.setText(ques);
        ques = Q1_2.getText() + Quiz1.getQuestions()[Q2];
        Q1_2.setText(ques);
        ques = Q2_1.getText() + Quiz1.getQuestions()[Q3];
        Q2_1.setText(ques);
        ques = Q2_2.getText() + Quiz1.getQuestions()[Q4];
        Q2_2.setText(ques);
        ques = Q3_1.getText() + Quiz1.getQuestions()[Q5];
        Q3_1.setText(ques);
        ques = Q3_2.getText() + Quiz1.getQuestions()[Q6];
        Q3_2.setText(ques);
    }

    /* Name:attachQuiz1Answers
      parameters: No parameters
      this method displays the answers of the questions to the views on the screen
      return: void
   */
    private void attachQuiz1Answers() {
        RB1_1.setText(Quiz1.getModelAns1()[Q1]);
        RB1_2.setText(Quiz1.getModelAns3()[Q3]);
        RB1_3.setText(Quiz1.getModelAns2()[Q4]);
        RB1_4.setText(Quiz1.getModelAns1()[Q2]);
        RB2_1.setText(Quiz1.getModelAns1()[Q1]);
        RB2_2.setText(Quiz1.getModelAns3()[Q3]);
        RB2_3.setText(Quiz1.getModelAns2()[Q2]);
        RB2_4.setText(Quiz1.getModelAns1()[Q5]);

        CHB1_1.setText(Quiz1.getModelAns1()[Q1]);
        CHB1_2.setText(Quiz1.getModelAns3()[Q3]);
        CHB1_3.setText(Quiz1.getModelAns1()[Q5]);
        CHB1_4.setText(Quiz1.getModelAns2()[Q3]);
        CHB2_1.setText(Quiz1.getModelAns1()[Q4]);
        CHB2_2.setText(Quiz1.getModelAns3()[Q5]);
        CHB2_3.setText(Quiz1.getModelAns1()[Q1]);
        CHB2_4.setText(Quiz1.getModelAns2()[Q4]);

    }
    /* Name:findViews
          parameters: No parameters
          this method attaches the member data to the views on the screen
          return: void
    */
    private void findViews() {

        Q1_1 = (TextView) findViewById(R.id.q1_1);
        Q1_2 = (TextView) findViewById(R.id.q1_2);
        Q2_1 = (TextView) findViewById(R.id.q2_1);
        Q2_2 = (TextView) findViewById(R.id.q2_2);
        Q3_1 = (TextView) findViewById(R.id.q3_1);
        Q3_2 = (TextView) findViewById(R.id.q3_2);

        RB1_1 = (RadioButton) findViewById(R.id.radio1_1);
        RB1_2 = (RadioButton) findViewById(R.id.radio1_2);
        RB1_3 = (RadioButton) findViewById(R.id.radio1_3);
        RB1_4 = (RadioButton) findViewById(R.id.radio1_4);
        RB2_1 = (RadioButton) findViewById(R.id.radio2_1);
        RB2_2 = (RadioButton) findViewById(R.id.radio2_2);
        RB2_3 = (RadioButton) findViewById(R.id.radio2_3);
        RB2_4 = (RadioButton) findViewById(R.id.radio2_4);

        CHB1_1 = (CheckBox) findViewById(R.id.check1_1);
        CHB1_2 = (CheckBox) findViewById(R.id.check1_2);
        CHB1_3 = (CheckBox) findViewById(R.id.check1_3);
        CHB1_4 = (CheckBox) findViewById(R.id.check1_4);
        CHB2_1 = (CheckBox) findViewById(R.id.check2_1);
        CHB2_2 = (CheckBox) findViewById(R.id.check2_2);
        CHB2_3 = (CheckBox) findViewById(R.id.check2_3);
        CHB2_4 = (CheckBox) findViewById(R.id.check2_4);

        ET1 = (EditText) findViewById(R.id.edit1);
        ET2 = (EditText) findViewById(R.id.edit2);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        radioGroup1 = (RadioGroup) findViewById(R.id.radio_group1);
        radioGroup2 = (RadioGroup) findViewById(R.id.radio_group2);
        btnReset = (Button) findViewById(R.id.btn_reset);
    }
}
