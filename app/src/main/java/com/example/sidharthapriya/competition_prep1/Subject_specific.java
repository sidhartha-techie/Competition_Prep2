package com.example.sidharthapriya.competition_prep1;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sidharthapriya.competition_prep1.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;

public class Subject_specific extends AppCompatActivity {
	String myJSON;
	JSONArray problems = null;
	private static final String TAG_RESULTS = "result";
	private static final String TAG_ID = "Id";
	private static final String TAG_QUESTION = "Question";
	private static final String TAG_SOLUTION = "Solution";
	private static final String TAG_OPTION1 = "Option1";
	private static final String TAG_OPTION2 = "Option2";
	private static final String TAG_OPTION3 = "Option3";
	public static  String Subject1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subject_specific);
        setTheme(R.style.AppTheme);
		// Component Descriptions
		Button subject_specific_test = (Button) (findViewById(R.id.subject_specific_test));
		Button subject_specific_questionbank = (Button) (findViewById(R.id.subject_specific_questionbank));
		Button subject_specific_update = (Button) (findViewById(R.id.subject_specific_update));
		TextView subject_specific_totalquestions = (TextView) (findViewById(R.id.subject_specific_totalquestions));

		final TextView subject_specific_progresstext = (TextView) (findViewById(R.id.subject_specific_Progresstext));
		final ProgressBar subject_specific_progressbar1 = (ProgressBar) findViewById(R.id.progressBar1);

		Intent intent = getIntent();

		// Component Definitions
		final String Subject = intent.getStringExtra("Subject");
		Subject1=Subject;
		android.support.v7.app.ActionBar bar = getSupportActionBar();
		bar.setTitle(Subject);
		switch (Subject) {
		case "ELECTRONICS":
			subject_specific_test.setText("Electronics Test");
			subject_specific_questionbank.setText("Electronics Question Bank");
			DBHelperElectronics dbelectronics = new DBHelperElectronics(Subject_specific.this);
			int totalquestions = 0;
			try {
				totalquestions = dbelectronics.numberOfRows();
			} catch (Exception E) {
			}
			try {
				subject_specific_totalquestions.setText(String.valueOf(totalquestions));
			} catch (Exception E) {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				E.printStackTrace(pw);
				subject_specific_totalquestions.setText(sw.toString());
				Toast.makeText(Subject_specific.this, sw.toString(), Toast.LENGTH_LONG).show();
			}
			break;

		case "GK":
			subject_specific_test.setText("GK Test");
			subject_specific_questionbank.setText("GK Question Bank");
			DBHelperGK dbgk = new DBHelperGK(Subject_specific.this);
			int totalquestions1 = 0;
			try {
				totalquestions1 = dbgk.numberOfRows();
			} catch (Exception E) {
			}
			try {
				subject_specific_totalquestions.setText(String.valueOf(totalquestions1));
			} catch (Exception E) {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				E.printStackTrace(pw);
				subject_specific_totalquestions.setText(sw.toString());
				Toast.makeText(Subject_specific.this, sw.toString(), Toast.LENGTH_LONG).show();
			}
			break;

		}
		subject_specific_questionbank.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(getApplicationContext(), Questionbank_java.class);
				switch (Subject) {
				case "ELECTRONICS":
					intent.putExtra("Subject", "ELECTRONICS");
					startActivity(intent);
					break;
				case "GK":
					intent.putExtra("Subject", "GK");
					startActivity(intent);
				}
			}
		});
		
		// subject_specific_update onclick listeners
		
		subject_specific_update.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try{
				switch (Subject) {
				case "ELECTRONICS":
					DBHelperElectronics dbhelper = new DBHelperElectronics(getApplicationContext());
					int id = dbhelper.numberOfRows();

					subject_specific_progressbar1.setVisibility(View.VISIBLE);
					subject_specific_progresstext.setVisibility(View.VISIBLE);
					getData(id, "ELECTRONICS");
					break;
				case "GK":
						DBHelperGK dbhelper1 = new DBHelperGK(getApplicationContext());
						int id1 = dbhelper1.numberOfRows();

						subject_specific_progressbar1.setVisibility(View.VISIBLE);
						subject_specific_progresstext.setVisibility(View.VISIBLE);
						getData(id1, "GK");
						break;
				}
				
				}
				catch (Exception e){
					StringWriter sw = new StringWriter();
					PrintWriter pw = new PrintWriter(sw);
					e.printStackTrace(pw);
					Toast.makeText(Subject_specific.this, "something is wrong" + sw.toString(), Toast.LENGTH_LONG).show();
				}
			
				
			}
		});
		
		
	}

	protected void showList() {
		int i = 0;
		try {
			// JsonParser parser = new JsonParser();
			JSONObject jsonObj = new JSONObject(myJSON);
			problems = jsonObj.getJSONArray(TAG_RESULTS);

			for (i = 0; i < problems.length(); i++) {
				JSONObject c = problems.getJSONObject(i);
				String id = c.getString(TAG_ID);
				String question = c.getString(TAG_QUESTION);
				String solution = c.getString(TAG_SOLUTION);
				String option1 = c.getString(TAG_OPTION1);
				String option2 = c.getString(TAG_OPTION2);
				String option3 = c.getString(TAG_OPTION3);
				
				switch (Subject1){
				case "ELECTRONICS":
					DBHelperElectronics dbhelper = new DBHelperElectronics(getApplicationContext());
					dbhelper.insertProblem(question, solution, option1, option2, option3);
					break;
				case "GK":
					DBHelperGK dbhelper1 = new DBHelperGK(getApplicationContext());
					dbhelper1.insertProblem(question, solution, option1, option2, option3);
					break;
				} 
				
			}

			// ListAdapter adapter = new SimpleAdapter(Questionbank_java.this,
			// personList, R.layout.list_item,
			// new String[] { TAG_ID, TAG_NAME, TAG_ADD }, new int[] { R.id.id,
			// R.id.name, R.id.address });

			// list.setAdapter(adapter);
			// Toast.makeText(Questionbank_java.this, "inside try",
			// Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			// e.printStackTrace();

			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			// result = sw.toString();
			Toast.makeText(Subject_specific.this, "Result is 11 " + sw.toString(), Toast.LENGTH_LONG).show();
			Log.i("error is",sw.toString());

		}
		final TextView subject_specific_progresstext = (TextView) (findViewById(R.id.subject_specific_Progresstext));
		final ProgressBar subject_specific_progressbar1 = (ProgressBar) findViewById(R.id.progressBar1);
		subject_specific_progressbar1.setVisibility(View.INVISIBLE);
		subject_specific_progresstext.setVisibility(View.INVISIBLE);
		if (i == 0)
			Toast.makeText(Subject_specific.this, "No new Updates", Toast.LENGTH_LONG).show();
		else {
			try {

				Toast.makeText(Subject_specific.this, String.valueOf(i) + " Question(s) added", Toast.LENGTH_LONG)
						.show();
			} catch (Exception e) {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				Toast.makeText(Subject_specific.this, "i value is wrong" + sw.toString(), Toast.LENGTH_LONG).show();
			}
		}
	}

	public void getData(int last_id, String subject_1) {
		final int lastid = last_id;
		final String subject = subject_1;
		class GetDataJSON extends AsyncTask<String, Void, String> {
			// public GetDataJSON(int lastid) {
			// super();
			// do stuff
			// last_id[0]=lastid;
			// }
			@Override
			protected String doInBackground(String... params) {

				// Set this up in the UI thread.


				DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
				HttpPost httppost = new HttpPost("http://192.168.0.104/Competition_prep/question_retrieve.php?id="+lastid+"&subject="+Subject1);

				// Depends on your web service
				httppost.setHeader("Content-type", "application/json");

				InputStream inputStream = null;			
				String result = "null";
				StringBuilder sb = new StringBuilder();
				try {
					HttpResponse response = httpclient.execute(httppost);
					HttpEntity entity = response.getEntity();

					inputStream = entity.getContent();
					// json is UTF-8 by default
					BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 500);
					// StringBuilder sb = new StringBuilder();

					String line = null;
					while ((line = reader.readLine()) != null) {
						sb.append(line + "\n");
					}
					result = sb.toString();
				} catch (Exception e) {
					// Oops
					StringWriter sw = new StringWriter();
					PrintWriter pw = new PrintWriter(sw);
					e.printStackTrace(pw);
					result = sw.toString();
					boolean netinfo = isOnline();
					if (netinfo) {
						runOnUiThread(new Runnable()
						{
							public void run()
							{
								Toast.makeText(Subject_specific.this, "Server is down, please try after sometime",
										Toast.LENGTH_LONG).show();
							}
						});

					} else
						runOnUiThread(new Runnable()
						{
							public void run()
							{
								Toast.makeText(Subject_specific.this, "Please check your network connection", Toast.LENGTH_LONG)
										.show();
							}
						});

				} finally {
					try {
						if (inputStream != null)
							inputStream.close();
					} catch (Exception squish) {
					}
				}
				// Toast.makeText(Questionbank_java.this,"Result is 22 "+result
				// ,Toast.LENGTH_LONG).show();
				Log.i("result is",result);
				return result;
				//return new JSONObject(json.substring(json.indexOf("{"), json.lastIndexOf("}") + 1));
			}

			@Override
			protected void onPostExecute(String result) {
				myJSON = result;
				// Toast.makeText(Subject_specific.this, "Result is 33" +
				// result, Toast.LENGTH_LONG).show();
				showList();
			}

		}
		GetDataJSON g = new GetDataJSON();
		g.execute();
	}

	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		return netInfo != null && netInfo.isConnected();
	}
}