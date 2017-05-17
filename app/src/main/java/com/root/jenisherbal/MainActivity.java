package com.root.jenisherbal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> SubjectNames;
    RecyclerView recyclerview;
    RecyclerView.LayoutManager RecyclerViewLayoutManager;
    View ChildView ;
    int RecyclerViewItemPosition ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerview = (RecyclerView)findViewById(R.id.recyclerview1);

        RecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerview.setLayoutManager(RecyclerViewLayoutManager);

        //Adding items to RecyclerView
        SubjectNames = new ArrayList<>();
        SubjectNames.add("Akar Alang-Alang");
        SubjectNames.add("Daun Jambu Biji");
        SubjectNames.add("Lidah Buaya");
        SubjectNames.add("Daun Sirih");

        RecyclerView.Adapter adapter = new RecyclerViewAdapter(SubjectNames);

        recyclerview.setAdapter(adapter);

        recyclerview.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            GestureDetector gestureDetector = new GestureDetector(MainActivity.this, new GestureDetector.SimpleOnGestureListener() {

                @Override public boolean onSingleTapUp(MotionEvent motionEvent) {

                    return true;
                }

            });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

                ChildView = Recyclerview.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                if(ChildView != null && gestureDetector.onTouchEvent(motionEvent)) {

                    RecyclerViewItemPosition = Recyclerview.getChildAdapterPosition(ChildView);
                    switch(RecyclerViewItemPosition){

                        case 0:
                            Intent intent = new Intent(getApplicationContext(),akaralang.class);
                            startActivity(intent);
                            break;

                        case 1:
                            Intent jamb = new Intent(getApplicationContext(),daunjambu.class);
                            startActivity(jamb);
                            break;

                        case 2:
                            Intent lida = new Intent(getApplicationContext(),lidahbuaya.class);
                            startActivity(lida);
                            break;

                        case 3:
                            Intent sarah = new Intent(getApplicationContext(),daunsirih.class);
                            startActivity(sarah);
                            break;
                    }
                    Toast.makeText(MainActivity.this, SubjectNames.get(RecyclerViewItemPosition), Toast.LENGTH_LONG).show();
                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

    }

}