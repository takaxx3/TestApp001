package com.example.testapp001;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class new_activity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE
            = "com.example.testapp001.MESSAGE";
    static final int RESULT_SUBACTIVITY = 1000;
    private TextView textView;
    private EditText editTextName, editTextUpper,editTextLower,editTextAlarm;
    private TestOpenHelper helper;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_new);

        editTextName = findViewById(R.id.edit_text_name);
        editTextUpper = findViewById(R.id.edit_text_upper);
        editTextLower = findViewById(R.id.edit_text_lower);
        editTextAlarm = findViewById(R.id.edit_text_alarm);

        textView = findViewById(R.id.main_view);

        Button insertButton = findViewById(R.id.add_back);
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(helper == null){
                    helper = new TestOpenHelper(getApplicationContext());
                }

                if(db == null){
                    db = helper.getWritableDatabase();
                }

                String name = editTextName.getText().toString();
                String upper = editTextUpper.getText().toString();
                String lower = editTextLower.getText().toString();
                String alarm = editTextAlarm.getText().toString();

                // 価格は整数を想定
                insertData(db, name, Integer.parseInt(upper),Integer.parseInt(lower),Integer.parseInt(alarm));
            }
        });

       Button readButton = findViewById(R.id.add_back);
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readData();
                Intent intent = new Intent(getApplication(),MainActivity.class);
                startActivity(intent);
            }
        });

      //もともとのボタン
        Button backButton = findViewById(R.id.new_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),MainActivity.class);
                startActivity(intent);
            }
        });

        final EditText editText= findViewById(R.id.edit_text_name);

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

    }


    private void readData(){
        if(helper == null){
            helper = new TestOpenHelper(getApplicationContext());
        }

        if(db == null){
            db = helper.getReadableDatabase();
        }
        Log.d("debug","**********Cursor");

        Cursor cursor = db.query(
                "testdb",
                new String[] { "name", "upper","lower","alarm" },
                null,
                null,
                null,
                null,
                null
        );

        cursor.moveToFirst();

        StringBuilder sbuilder = new StringBuilder();

        for (int i = 0; i < cursor.getCount(); i++) {
            sbuilder.append(cursor.getString(0));
            sbuilder.append(": ");
            sbuilder.append(cursor.getInt(1));
            sbuilder.append("\n");
            sbuilder.append(cursor.getInt(2));
            sbuilder.append("\n");
            sbuilder.append(cursor.getInt(3));
            sbuilder.append("\n");
            cursor.moveToNext();
        }

        // 忘れずに！
        cursor.close();

        Log.d("debug","**********"+sbuilder.toString());
        textView.setText(sbuilder.toString());
    }

  private void insertData(SQLiteDatabase db, String data_name, int data_upper, int data_lower, int data_alarm){

        ContentValues values = new ContentValues();
        values.put("name", data_name);
        values.put("upper", data_upper);
        values.put("lower", data_lower);
        values.put("alarm", data_alarm);

        db.insert("testdb", null, values);
    }

}





  /*  // the key constant


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
    }*/
