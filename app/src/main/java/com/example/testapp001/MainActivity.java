package com.example.testapp001;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity<val> extends AppCompatActivity {

    private EditText editText;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button nextButton = findViewById(R.id.output_send);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),output_activity.class);
                startActivity(intent);
            }
        });

        Button nextButton2 = findViewById(R.id.new_send);
        nextButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),new_activity.class);
                startActivity(intent);
            }
        });

        Button nextButton3 = findViewById(R.id.option_send);
        nextButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),option_activity.class);
                startActivity(intent);
            }
        });

        // to get message from new_activity
        Intent intentMain = getIntent();
        message = intentMain.getStringExtra(new_activity.EXTRA_MESSAGE);

        TextView textView = findViewById(R.id.main_view);
        textView.setText(message);

        //editText = findViewById(R.id.edit_text);
    }
}