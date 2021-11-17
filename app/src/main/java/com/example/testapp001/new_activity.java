package com.example.testapp001;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
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
import android.widget.TextView;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class new_activity extends AppCompatActivity {

  //  private final String[] spinnerItems = {"1", "2", "3", "4", "5"};
    private TextView textView;
    private EditText editText;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        Context context = getApplicationContext();

        String fileName = "TestFile.txt";
        file = new File(context.getFilesDir(), fileName);

        textView = findViewById(R.id.text_view);
        editText = findViewById(R.id.edit_text);
        Button buttonSave = findViewById(R.id.button_save);

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
        });

        Button buttonRead = findViewById(R.id.button_read);
        // lambda
        buttonRead.setOnClickListener( v -> {
            String str = readFile();
            if (str != null) {
                textView.setText(str);
            } else {
                textView.setText(R.string.read_error);
            }
        });



        Button backButton = findViewById(R.id.new_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button backButton2 = findViewById(R.id.add_back);
        backButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish(); }
        });

        //textView = findViewById(R.id.text_view9);
        /*
        Spinner spinner = findViewById(R.id.spinner);

        // ArrayAdapter
        ArrayAdapter<String> adapter
                = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, spinnerItems);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // spinner に adapter をセット
        spinner.setAdapter(adapter);

        // リスナーを登録
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            //　アイテムが選択された時
            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int position, long id) {
                Spinner spinner = (Spinner)parent;
                String item = (String)spinner.getSelectedItem();
                textView.setText(item);
            }

            //　アイテムが選択されなかった
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });

         */
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