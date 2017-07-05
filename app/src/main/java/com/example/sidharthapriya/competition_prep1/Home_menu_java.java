package com.example.sidharthapriya.competition_prep1;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Home_menu_java extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_menu_xml);
        setTheme(R.style.AppTheme);
		android.support.v7.app.ActionBar bar = getSupportActionBar();
		bar.setTitle("Competition Prepare");
		//Creating Tables for future use
		DBHelperCreateTables CreatingTables=new DBHelperCreateTables(getApplicationContext());
		// Component Descriptions
		Button Home_menu_Electronics = (Button) (findViewById(R.id.Home_menu_electronics));
		Button Home_menu_Gk = (Button) (findViewById(R.id.Home_menu_gk));
		// Component Definitions
		Home_menu_Electronics.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent open_sub_menu = new Intent(Home_menu_java.this, Subject_specific.class);
				open_sub_menu.putExtra("Subject", "ELECTRONICS");
				startActivity(open_sub_menu);
			}
		});
		Home_menu_Gk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent open_sub_menu = new Intent(Home_menu_java.this, Subject_specific.class);
				open_sub_menu.putExtra("Subject", "GK");
				startActivity(open_sub_menu);
			}
		});

	}

}
