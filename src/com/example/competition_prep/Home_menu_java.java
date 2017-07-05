package com.example.competition_prep;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Home_menu_java extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_menu_xml);
		//Creating Tables for future use
		int x=0;
		DBHelperCreateTables CreatingTables=new DBHelperCreateTables(getApplicationContext());
		x=CreatingTables.getData(1);
		if (x==1)
		Toast.makeText(this, "Tables Created", Toast.LENGTH_LONG).show();
		else
			Toast.makeText(this, "Tables Not Created", Toast.LENGTH_LONG).show();
		// Component Descriptions
		Button Home_menu_Electronics = (Button) (findViewById(R.id.Home_menu_electronics));
		Button Home_menu_Gk = (Button) (findViewById(R.id.Home_menu_gk));
		// Component Definitions
		Home_menu_Electronics.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent open_sub_menu = new Intent(Home_menu_java.this, Subject_specific.class);
				open_sub_menu.putExtra("Subject", "Electronics");
				startActivity(open_sub_menu);
			}
		});
		Home_menu_Gk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent open_sub_menu = new Intent(Home_menu_java.this, Subject_specific.class);
				open_sub_menu.putExtra("Subject", "gk");
				startActivity(open_sub_menu);
			}
		});

	}

}
