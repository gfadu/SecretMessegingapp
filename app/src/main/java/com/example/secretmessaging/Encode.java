package com.example.secretmessaging;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Encode extends AppCompatActivity {

    EditText message;
    EditText inputpassword;
    TextView print;
    String text, password, bin;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encode);
    }

    void encrypt() {
        byte[] bytes = this.text.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
            binary.append(' ');
        }
        //System.out.println("'" + text+ "' to binary: " + binary);
        bin = binary.toString();
        addpunc();
    }

    void input() {  
        //print = findViewById(R.id.print);
       // Button =findViewById(R.id.button);
        EditText message = (EditText)findViewById(R.id.inputMessage);
        EditText inputpassword = (EditText) findViewById(R.id.inputPassword);
        //button = (Button) findViewById(R.id.button);
        text = message.getText().toString();
        password = inputpassword.getText().toString();
        text = text + password;
        Log.i("text:",text);
        encrypt();
    }


    void addpunc() {
        char array[] = new char[bin.length()];
        for (int i = 0; i < bin.length(); i++) {
            array[i] = bin.charAt(i);
            //System.out.println(array[i]);
        }
        for (int i = 0; i < bin.length(); i++) {
            if (i % 2 == 0) {
                if (array[i] != ' ') {
                    if (array[i] == '1') {
                        array[i] = '0';
                    } else {
                        array[i] = '1';
                    }
                }
            }
        }
        String string=new String(array);
        Log.i("encoded",string);
        EditText textView=(EditText) findViewById(R.id.displayMessage);
        textView.setText(string);

    }
    public void save(View view){

        input();
    }
}


