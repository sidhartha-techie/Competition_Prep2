package com.example.competition_prep;

import android.view.View.OnClickListener;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Questionbank_java extends Activity {

	String myJSON;

	JSONArray problems = null;

	ArrayList<HashMap<String, String>> problem_bank;

	ListView list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.questionbank);
		// problem_bank = new ArrayList<HashMap<String, String>>();

		// list = (ListView) findViewById(R.id.listView);

		Intent intent = getIntent();
		int id = 1;

		final String Subject = intent.getStringExtra("Subject");
		final RelativeLayout questionbank_gotodialog = (RelativeLayout) findViewById(R.id.questionbank_gotodialog);
		Button questionbank_go = (Button) findViewById(R.id.questionbank_go);
		Button questionbank_gotobutton = (Button) findViewById(R.id.questionbank_gotobutton);
		final EditText questionbank_gotoedittext = (EditText) findViewById(R.id.questionbank_gotoedittext);
		LinearLayout goto_dialog_close=(LinearLayout) findViewById(R.id.Button_goto_dialog_close);

		questionbank_gotobutton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				questionbank_gotodialog.setVisibility(View.VISIBLE);
			}
		});
		goto_dialog_close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				questionbank_gotodialog.setVisibility(View.INVISIBLE);
			}
		});

		switch (Subject) {
		case "Electronics":
			try {
				load_question(id, "electronics");
				questionbank_go.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						String edittextvalue = questionbank_gotoedittext.getText().toString();
						questionbank_gotodialog.setVisibility(View.INVISIBLE);
						load_question(Integer.parseInt(edittextvalue), "electronics");
					}
				});
			} catch (Exception E) {
				Toast.makeText(Questionbank_java.this, "error in first try", Toast.LENGTH_LONG).show();
			}
			break;
		case "gk":
			try {
				load_question(id, "gk");
				questionbank_go.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						String edittextvalue = questionbank_gotoedittext.getText().toString();
						questionbank_gotodialog.setVisibility(View.INVISIBLE);
						load_question(Integer.parseInt(edittextvalue), "gk");
					}
				});
			} catch (Exception E) {
				Toast.makeText(Questionbank_java.this, "error in first try", Toast.LENGTH_LONG).show();
			}
		}

	}

	protected void load_question(int id, final String subject) {
		/*Electronics Question Loading */
		if (subject == "electronics") {
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
		
		/*GK Question Loading*/
		
		if (subject == "gk") {
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
}
