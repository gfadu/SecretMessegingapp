package com.example.secretmessaging;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.view.View.INVISIBLE;

public class MainActivity extends AppCompatActivity {

    public void welcome(View view){

        Button welcome1 = (Button) findViewById(R.id.welcome);
        Button encode = (Button) findViewById(R.id.encode);
        Button decode = (Button) findViewById(R.id.decode);
        TextView description = (TextView) findViewById(R.id.description);
        description.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
        description.setGravity(Gravity.CENTER);
        welcome1.setVisibility(View.INVISIBLE);
        encode.setVisibility(View.VISIBLE);
        decode.setVisibility(View.VISIBLE);
        encode.animate().alpha(1).setDuration(500);
        decode.animate().alpha(1).setDuration(500);
        description.setVisibility(View.VISIBLE);
    }
    public void encode(View view) {
        Intent intent = new Intent(getApplicationContext(), Encode.class);
        startActivity(intent);
    }

    public void decode(View view){
        Intent intent1 = new Intent(getApplicationContext(), Decode.class);
        startActivity(intent1);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
