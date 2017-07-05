package com.example.sidharthapriya.competition_prep1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sidharthapriya.competition_prep1.R;

import org.json.JSONArray;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static com.example.sidharthapriya.competition_prep1.R.id.questionbank_gotobutton;
import static com.example.sidharthapriya.competition_prep1.R.id.questionbank_gotodialog;
import static com.example.sidharthapriya.competition_prep1.R.id.questionbank_gotoedittext;
import static com.example.sidharthapriya.competition_prep1.R.id.questionbank_option1;
import static com.example.sidharthapriya.competition_prep1.R.id.questionbank_option2;
import static com.example.sidharthapriya.competition_prep1.R.id.questionbank_option3;
import static com.example.sidharthapriya.competition_prep1.R.id.questionbank_option4;

public class Questionbank_java extends AppCompatActivity {
    String myJSON;
    JSONArray problems = null;
    ArrayList<HashMap<String, String>> problem_bank;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionbank);


    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        final int id = 1;

        final String Subject = intent.getStringExtra("Subject");
        final RelativeLayout questionbank_gotodialog = (RelativeLayout) findViewById(R.id.questionbank_gotodialog);
        android.support.v7.app.ActionBar bar = getSupportActionBar();
        bar.setTitle(Subject+" Question Bank");
        Button questionbank_gotobutton = (Button) findViewById(R.id.questionbank_gotobutton);
        final EditText questionbank_gotoedittext = (EditText) findViewById(R.id.goto_edittext);
        TextView questionbank_gotook = (TextView) findViewById(R.id.goto_ok);
        TextView questionbank_gotocancel = (TextView) findViewById(R.id.goto_cancel);
        // FragmentManager manager;
        //manager = getSupportFragmentManager();

        // Goto button implimentation
        questionbank_gotobutton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                questionbank_gotodialog.setVisibility(View.VISIBLE);
                questionbank_gotoedittext.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(questionbank_gotoedittext, InputMethodManager.SHOW_IMPLICIT);
                //questionbank_gotoedittext.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

            }
        });
        //goto enter key implimentation
        questionbank_gotoedittext.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    //will do necessite
                    Toast.makeText(Questionbank_java.this, "calling function", Toast.LENGTH_LONG).show();
                    int id1 = Integer.parseInt(questionbank_gotoedittext.getText().toString());
                    questionbank_gotoedittext.setText("");
                    questionbank_gotodialog.setVisibility(View.INVISIBLE);


                    hideKeyboard(questionbank_gotoedittext.getWindowToken());
                    load_question(id1, Subject);
                }
                return false;
            }
        });


        //Goto ok button implimentation
        questionbank_gotook.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((questionbank_gotoedittext.getText().toString()).matches("")) {
                    Toast.makeText(Questionbank_java.this, "Please Enter Valid Number", Toast.LENGTH_SHORT).show();
                } else {
                    questionbank_gotodialog.setVisibility(View.INVISIBLE);
                    int id1 = Integer.parseInt(questionbank_gotoedittext.getText().toString());
                    questionbank_gotoedittext.setText("");
                    hideKeyboard(questionbank_gotoedittext.getWindowToken());
                    load_question(id1, Subject);
                }
            }
        });
        //Goto cancels button implimentation
        questionbank_gotocancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                questionbank_gotodialog.setVisibility(View.INVISIBLE);
                hideKeyboard(questionbank_gotoedittext.getWindowToken());
                questionbank_gotoedittext.setText("");

            }
        });

        switch (Subject) {
            case "ELECTRONICS":
                try {
                    load_question(id, "ELECTRONICS");

                } catch (Exception e) {
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    e.printStackTrace(pw);
                    Log.i("first try", sw.toString());
                    Toast.makeText(Questionbank_java.this, "error in first try", Toast.LENGTH_LONG).show();
                }
                break;
            case "GK":
                try {
                    load_question(id, "GK");

                } catch (Exception e) {
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    e.printStackTrace(pw);
                    Log.i("first try", sw.toString());
                    Toast.makeText(Questionbank_java.this, "error in first try", Toast.LENGTH_LONG).show();
                }
        }

    }

    //Method for hiding the keyboard when required
    private void hideKeyboard(IBinder token) {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(token,
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    protected void load_question(int id, final String subject) {
        /*Electronics Question Loading */
        Log.i("Reached inside ", subject);
        Toast.makeText(Questionbank_java.this, "Reached inside" + subject, Toast.LENGTH_LONG).show();
        if (subject.equals("ELECTRONICS")) {
            DBHelperElectronics dbhelper = new DBHelperElectronics(getApplicationContext());
            try {
                Cursor cur = dbhelper.getData(id);
                cur.moveToFirst();
                String qno = cur.getString(cur.getColumnIndex("id"));
                String question = cur.getString(cur.getColumnIndex("question"));
                String solution = cur.getString(cur.getColumnIndex("solution"));
                String option1 = cur.getString(cur.getColumnIndex("option1"));
                String option2 = cur.getString(cur.getColumnIndex("option2"));
                String option3 = cur.getString(cur.getColumnIndex("option3"));
                TextView questionbank_question_no = (TextView) findViewById(R.id.questionbank_question_no);
                TextView questionbank_question = (TextView) findViewById(R.id.questionbank_question);
                Button questionbank_next = (Button) findViewById(R.id.questionbank_next);
                Button questionbank_prev = (Button) findViewById(R.id.questionbank_prev);
                final TextView Answer_Status = (TextView) findViewById(R.id.questionbank_answer_status);
                final TextView Answer = (TextView) findViewById(R.id.questionbank_answer);
                final TextView Answeris = (TextView) findViewById(R.id.questionbank_answeris);
                final TextView questionbank_option1 = (TextView) findViewById(R.id.questionbank_option1);
                final TextView questionbank_option2 = (TextView) findViewById(R.id.questionbank_option2);
                final TextView questionbank_option3 = (TextView) findViewById(R.id.questionbank_option3);
                final TextView questionbank_option4 = (TextView) findViewById(R.id.questionbank_option4);
                Answer_Status.setVisibility(View.INVISIBLE);
                Answer.setVisibility(View.INVISIBLE);
                Answeris.setVisibility(View.INVISIBLE);
                Answer.setText(solution);
                questionbank_question_no.setText(qno);
                questionbank_question.setText(question);
                randomOptions(solution, option1, option2, option3);
                // listeners
                // option1 listener
                questionbank_option1.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        if (questionbank_option1.getText().equals(Answer.getText())) {
                            Answer_Status.setText("CORRECT ANSWER");
                            Answer_Status.setTextColor(Color.parseColor("#00FF00"));
                            Answer_Status.setVisibility(View.VISIBLE);
                        } else {
                            Answer_Status.setTextColor(Color.parseColor("#FF0000"));
                            Answer_Status.setText("INCORRECT ANSWER");
                            Answer.setVisibility(View.VISIBLE);
                            Answeris.setVisibility(View.VISIBLE);
                            Answer_Status.setVisibility(View.VISIBLE);
                        }
                    }
                });
                // option2 listener
                questionbank_option2.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        if (questionbank_option2.getText().equals(Answer.getText())) {
                            Answer_Status.setText("CORRECT ANSWER");
                            Answer_Status.setTextColor(Color.parseColor("#00FF00"));
                            Answer_Status.setVisibility(View.VISIBLE);
                        } else {
                            Answer_Status.setTextColor(Color.parseColor("#FF0000"));
                            Answer_Status.setText("INCORRECT ANSWER");
                            Answer.setVisibility(View.VISIBLE);
                            Answeris.setVisibility(View.VISIBLE);
                            Answer_Status.setVisibility(View.VISIBLE);
                        }
                    }
                });
                // option 3 listener
                questionbank_option3.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        if (questionbank_option3.getText().equals(Answer.getText())) {
                            Answer_Status.setText("CORRECT ANSWER");
                            Answer_Status.setTextColor(Color.parseColor("#00FF00"));
                            Answer_Status.setVisibility(View.VISIBLE);
                        } else {
                            Answer_Status.setTextColor(Color.parseColor("#FF0000"));
                            Answer_Status.setText("INCORRECT ANSWER");
                            Answer.setVisibility(View.VISIBLE);
                            Answeris.setVisibility(View.VISIBLE);
                            Answer_Status.setVisibility(View.VISIBLE);
                        }
                    }
                });
                // option4 listener
                questionbank_option4.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        if (questionbank_option4.getText().equals(Answer.getText())) {
                            Answer_Status.setText("CORRECT ANSWER");
                            Answer_Status.setTextColor(Color.parseColor("#00FF00"));
                            Answer_Status.setVisibility(View.VISIBLE);
                        } else {
                            Answer_Status.setTextColor(Color.parseColor("#FF0000"));
                            Answer_Status.setText("INCORRECT ANSWER");
                            Answer.setVisibility(View.VISIBLE);
                            Answeris.setVisibility(View.VISIBLE);
                            Answer_Status.setVisibility(View.VISIBLE);
                        }
                    }
                });
                final int id1 = id + 1;
                final int id2 = id - 1;
                if (id < 2) {
                    questionbank_prev.setVisibility(View.INVISIBLE);
                }
                if (id >= 2) {
                    questionbank_prev.setVisibility(View.VISIBLE);
                }
                questionbank_next.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        // TODO Auto-generated method stub
                        load_question(id1, subject);
                    }
                });
                questionbank_prev.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        // TODO Auto-generated method stub
                        load_question(id2, subject);
                    }
                });
            } catch (Exception e) {
                Toast.makeText(Questionbank_java.this, "This is the last question", Toast.LENGTH_LONG).show();
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);

                Log.i("Error", sw.toString());
            }
        }

		/*GK Question Loading*/

        if (subject.equals("GK")) {
            Toast.makeText(Questionbank_java.this, "got here", Toast.LENGTH_LONG).show();

            DBHelperGK dbhelper = new DBHelperGK(getApplicationContext());
            try {
                Cursor cur = dbhelper.getData(id);
                cur.moveToFirst();
                String qno = cur.getString(cur.getColumnIndex("id"));
                String question = cur.getString(cur.getColumnIndex("question"));
                String solution = cur.getString(cur.getColumnIndex("solution"));
                String option1 = cur.getString(cur.getColumnIndex("option1"));
                String option2 = cur.getString(cur.getColumnIndex("option2"));
                String option3 = cur.getString(cur.getColumnIndex("option3"));
                TextView questionbank_question_no = (TextView) findViewById(R.id.questionbank_question_no);
                TextView questionbank_question = (TextView) findViewById(R.id.questionbank_question);
                final TextView questionbank_option1 = (TextView) findViewById(R.id.questionbank_option1);
                final TextView questionbank_option2 = (TextView) findViewById(R.id.questionbank_option2);
                final TextView questionbank_option3 = (TextView) findViewById(R.id.questionbank_option3);
                final TextView questionbank_option4 = (TextView) findViewById(R.id.questionbank_option4);
                Button questionbank_next = (Button) findViewById(R.id.questionbank_next);
                Button questionbank_prev = (Button) findViewById(R.id.questionbank_prev);
                final TextView Answer_Status = (TextView) findViewById(R.id.questionbank_answer_status);
                final TextView Answer = (TextView) findViewById(R.id.questionbank_answer);
                final TextView Answeris = (TextView) findViewById(R.id.questionbank_answeris);
                Answer_Status.setVisibility(View.INVISIBLE);
                Answer.setVisibility(View.INVISIBLE);
                Answeris.setVisibility(View.INVISIBLE);

                Answer.setText(solution);
                questionbank_question_no.setText(qno);
                questionbank_question.setText(question);
                randomOptions(solution, option1, option2, option3);

                // listeners
                // option1 listener
                questionbank_option1.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        if (questionbank_option1.getText().equals(Answer.getText())) {
                            Answer_Status.setText("CORRECT ANSWER");
                            Answer_Status.setTextColor(Color.parseColor("#00FF00"));
                            Answer_Status.setVisibility(View.VISIBLE);
                        } else {
                            Answer_Status.setTextColor(Color.parseColor("#FF0000"));
                            Answer_Status.setText("INCORRECT ANSWER");
                            Answer.setVisibility(View.VISIBLE);
                            Answeris.setVisibility(View.VISIBLE);
                            Answer_Status.setVisibility(View.VISIBLE);
                        }
                    }
                });
                // option2 listener
                questionbank_option2.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        if (questionbank_option2.getText().equals(Answer.getText())) {
                            Answer_Status.setText("CORRECT ANSWER");
                            Answer_Status.setTextColor(Color.parseColor("#00FF00"));
                            Answer_Status.setVisibility(View.VISIBLE);
                        } else {
                            Answer_Status.setTextColor(Color.parseColor("#FF0000"));
                            Answer_Status.setText("INCORRECT ANSWER");
                            Answer.setVisibility(View.VISIBLE);
                            Answeris.setVisibility(View.VISIBLE);
                            Answer_Status.setVisibility(View.VISIBLE);
                        }
                    }
                });
                // option 3 listener
                questionbank_option3.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        if (questionbank_option3.getText().equals(Answer.getText())) {
                            Answer_Status.setText("CORRECT ANSWER");
                            Answer_Status.setTextColor(Color.parseColor("#00FF00"));
                            Answer_Status.setVisibility(View.VISIBLE);
                        } else {
                            Answer_Status.setTextColor(Color.parseColor("#FF0000"));
                            Answer_Status.setText("INCORRECT ANSWER");
                            Answer.setVisibility(View.VISIBLE);
                            Answeris.setVisibility(View.VISIBLE);
                            Answer_Status.setVisibility(View.VISIBLE);
                        }
                    }
                });
                // option4 listener
                questionbank_option4.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        if (questionbank_option4.getText().equals(Answer.getText())) {
                            Answer_Status.setText("CORRECT ANSWER");
                            Answer_Status.setTextColor(Color.parseColor("#00FF00"));
                            Answer_Status.setVisibility(View.VISIBLE);
                        } else {
                            Answer_Status.setTextColor(Color.parseColor("#FF0000"));
                            Answer_Status.setText("INCORRECT ANSWER");
                            Answer.setVisibility(View.VISIBLE);
                            Answeris.setVisibility(View.VISIBLE);
                            Answer_Status.setVisibility(View.VISIBLE);
                        }
                    }
                });
                final int id1 = id + 1;
                final int id2 = id - 1;
                if (id < 2) {
                    questionbank_prev.setVisibility(View.INVISIBLE);
                }
                if (id >= 2) {
                    questionbank_prev.setVisibility(View.VISIBLE);
                }
                questionbank_next.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        // TODO Auto-generated method stub
                        load_question(id1, subject);
                    }
                });
                questionbank_prev.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        // TODO Auto-generated method stub
                        load_question(id2, subject);
                    }
                });
            } catch (Exception e) {
                Toast.makeText(Questionbank_java.this, "This is the last question", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void randomOptions(String solution, String option1, String option2, String option3) {

        final TextView questionbank_option1 = (TextView) findViewById(R.id.questionbank_option1);
        final TextView questionbank_option2 = (TextView) findViewById(R.id.questionbank_option2);
        final TextView questionbank_option3 = (TextView) findViewById(R.id.questionbank_option3);
        final TextView questionbank_option4 = (TextView) findViewById(R.id.questionbank_option4);

        Random r = new Random();
        int i1 = r.nextInt(5 - 1) + 1;
        if (i1 == 1) {
            questionbank_option1.setText(solution);
            questionbank_option2.setText(option1);
            questionbank_option3.setText(option2);
            questionbank_option4.setText(option3);
        }
        if (i1 == 2) {
            questionbank_option1.setText(option1);
            questionbank_option2.setText(solution);
            questionbank_option3.setText(option2);
            questionbank_option4.setText(option3);
        }
        if (i1 == 3) {
            questionbank_option1.setText(option1);
            questionbank_option2.setText(option2);
            questionbank_option3.setText(solution);
            questionbank_option4.setText(option3);
        }
        if (i1 == 4) {
            questionbank_option1.setText(option1);
            questionbank_option2.setText(option2);
            questionbank_option3.setText(option3);
            questionbank_option4.setText(solution);
        }
    }
}

