package com.example.julieanna.politicalideologyquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by julieanna on 11/20/16.
 */

public class QuizHistory extends AppCompatActivity {
    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_tip_history);

        dbManager = new DatabaseManager(this);
        showFileContents();

        ArrayAdapter dateAdapter = dbManager.fillAutoCompleteTextFields( this, DatabaseManager.DATE );
        if ( dateAdapter != null ) {
            AutoCompleteTextView dateEntry = (AutoCompleteTextView)findViewById(R.id.date_entry);
            dateEntry.setAdapter(dateAdapter);
        }

        public void showFileContents() {
        TextView historyDisplay = (TextView)findViewById(R.id.db_contents);
        String allHistory = "";

        ArrayList<String> allRecords = dbManager.selectAll();

        for (String s : allRecords) {
            allHistory += s + "\n";
        }

        historyDisplay.setText(allHistory);
    }

    public void showDataByColumn(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (imm.isAcceptingText()) {
            // Removes keyboard
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void displayData( ArrayList<String> data ) {
        TextView historyDisplay = (TextView)findViewById(R.id.db_contents);
        String historyData = "";
        for (String s : data) {
            historyData += s + "\n";
        }
        historyDisplay.setText(historyData);
    }

    ArrayList<String> results = new ArrayList<String>();
    results



    }
}
