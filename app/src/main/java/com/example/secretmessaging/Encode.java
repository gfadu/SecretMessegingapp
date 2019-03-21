package com.example.secretmessaging;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        input();
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
    }

    void input() {  
        print = findViewById(R.id.print);
        Button button=findViewById(R.id.button);
        message = findViewById(R.id.message);
        inputpassword = findViewById(R.id.inputpassword);
        button = (Button) findViewById(R.id.button);
        text = message.getText().toString();
        password = inputpassword.getText().toString();
        text = text + password;
    }


    void addpunc() {
        encrypt();
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
        for (int i = 0; i < array.length; i++) {
            print.setText(array[i]);
        }

    }
    void save(View view){

        addpunc();
    }
}


