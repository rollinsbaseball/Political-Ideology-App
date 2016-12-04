package com.example.christophercorbett.politicalquiz;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.icu.util.Calendar;
import android.content.Intent;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

public class MainActivity extends AppCompatActivity {

    private Button startQuiz;
    private Button pastQuiz;
    private DatabaseManager dbManager;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    /*public void onClick (View v ) {


        startQuiz = (Button) findViewById(R.id.start_quiz_button);
        startQuiz.setOnClickListener(this);

        pastQuiz = (Button) findViewById(R.id.past_quizzes_button);
        pastQuiz.setOnClickListener(this);
        if ( v == startQuiz ) {
                    startActivity( new Intent(getApplicationContext(), ))

                }
            }*/

    public void politicalGraph ( View v ) {
        setContentView(R.layout.graph);

        GraphView graph = (GraphView) findViewById(R.id.graph);
        graph.getGridLabelRenderer();
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(-3.0);
        graph.getViewport().setMaxX(3.0);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(-3.0);
        graph.getViewport().setMaxY(3.0);
        PointsGraphSeries<DataPoint> series = new PointsGraphSeries<DataPoint>
                (new DataPoint [] {
                        new DataPoint(0, 3),
                        new DataPoint(0,-3),
                        new DataPoint(3,0),
                        new DataPoint(-3,0) });
        graph.addSeries(series);
        series.setShape(PointsGraphSeries.Shape.POINT);
        series.setSize(10);

    }

    public void showQuestion (View v) {
        setContentView(R.layout.question_answer);
        //startActivity( new Intent(getApplicationContext(), Question.class));

    }

    public void showHistory ( View v ) {
        setContentView(R.layout.quiz_database);
        //startActivity( new Intent (getApplicationContext(), QuizHistory.class));
    }


    public boolean onCreateOptionsMenu( Menu menu ) {
        getMenuInflater( ).inflate(R.menu.main_menu, menu );
        return true;
    }


    public boolean onOptionsItemSelected( MenuItem item) {
        switch ( item.getItemId( )) {
            case R.id.homepage:
                AlertDialog.Builder builder;
                builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage(R.string.warning).setTitle(R.string.go_home);
                builder.setPositiveButton(R.string.back,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                setContentView(R.layout.activity_main);
                            }
                        });
                builder.setNegativeButton(R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                builder.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
