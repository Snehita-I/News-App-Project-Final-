package com.example.fragments;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.BreakIterator;


public class moreNews extends AppCompatActivity {
    public static TextView t1 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_info);
        Bundle b ;
        Intent i = new Intent();
        Bundle extras = getIntent().getExtras();
        String infoUrl = extras.getString("info");
        WebView editWeb = findViewById(R.id.web);


        editWeb.loadUrl(infoUrl);



    }
}