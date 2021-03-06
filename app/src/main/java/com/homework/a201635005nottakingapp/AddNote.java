package com.homework.a201635005nottakingapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class AddNote extends AppCompatActivity {
    Toolbar toolbar;
    EditText noteTitle,noteDetails;
    Calendar c;
    String todaysDate;
    String currentTime;
    private String pad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources(),getColor(R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("New Note");
        getSupportActionBar() .setDisplayHomeAsUpEnabled(true);

        noteTitle = findViewById(R.id.noteTitle);
        noteDetails = findViewById(R.id.noteDetails);

        noteTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0){
                    getSupportActionBar().setTitle(s);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        // get current date and time
        c = Calendar.getInstance();
        todaysDate = c.get(Calendar.YEAR)+"/"+c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.DAY_OF_MONTH);
        currentTime = pad c.get(Calendar.HOUR)+":"pad+c.get(Calendar.MINUTE);
        String msg;
        Log.d("calendar", msg:"Date and Time: "+ todaysDate +"and" + currentTime);
    }

    private String pad(int i) {
        if (i<10)
            return "0"+i;
        return String.valueOf(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delete){
            Toast.makeText(this,"Note Not Saved.",Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
        if (item.getItemId() == R.id.save){
            Note note = new Note(noteTitle.getText() .toString(),noteDetails.getText().toString(),todaysDate,currentTime);
            NoteDatabase db = new NoteDatabase(this);
            db.addNote(note);
            Toast.makeText(this,"Save btn",Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}