package com.example.testapp001;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import java.io.BufferedReader;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Button;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class new_activity extends AppCompatActivity {

    // the key constant
    public static final String EXTRA_MESSAGE
           = "com.example.testapp001.MESSAGE";

    private TextView textView;
    private EditText editText;
    private File file;
    static final int RESULT_SUBACTIVITY = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        Context context = getApplicationContext();

        String fileName = "TestFile.txt";
        file = new File(context.getFilesDir(), fileName);

        textView = findViewById(R.id.text_view);
        editText = findViewById(R.id.edit_text);
        Button buttonSave = findViewById(R.id.add_back);

        // lambda
        buttonSave.setOnClickListener( v -> {
            // エディットテキストのテキストを取得
            String text = editText.getText().toString();

            saveFile(text);
            if(text.length() == 0){
                textView.setText(R.string.no_text);
            }
            else{
                textView.setText(R.string.saved);
            }

            String str = readFile();
            if (str != null) {
                textView.setText(str);
            } else {
                textView.setText(R.string.read_error);
            }
        });

        //Button buttonRead = findViewById(R.id.button_read);
        // lambda
        final EditText editText= findViewById(R.id.edit_text);

        Button button = findViewById(R.id.add_back);
        button.setOnClickListener( v -> {
            Intent intent = new Intent(getApplication(), MainActivity.class);
            if(editText.getText() != null){
                String str = editText.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, str);
            }
            startActivityForResult( intent, RESULT_SUBACTIVITY );

            // in order to clear the edittext
            editText.setText("");
        });



        Button backButton = findViewById(R.id.new_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),MainActivity.class);
                startActivity(intent);
            }
        });

        /*Button backButton2 = findViewById(R.id.add_back);
        backButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),MainActivity.class);
                startActivity(intent);
            }
        });*/
    }


    // ファイルを保存
    public void saveFile(String str) {
        // try-with-resources
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(str);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ファイルを読み出し
    public String readFile() {
        String text = null;

        // try-with-resources
        try(
                BufferedReader br = new BufferedReader(new FileReader(file))
        ){
            text = br.readLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return text;
    }

}