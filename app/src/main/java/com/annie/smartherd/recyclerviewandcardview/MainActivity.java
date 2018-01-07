package com.annie.smartherd.recyclerviewandcardview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
		MyAdapter adapter = new MyAdapter(this, NatureModel.getObjectList());             //declaring the instance of MyAdapter Class, constructor will be called
		recyclerView.setAdapter(adapter);                                                 //linking adapter with the recyclerView

		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);                       
		recyclerView.setLayoutManager(layoutManager);                                     //linking recyclerView with the layoutManager.

		recyclerView.setItemAnimator(new DefaultItemAnimator());                          //Setting DefaultItemAnimator for the recyclerView
	}
}
