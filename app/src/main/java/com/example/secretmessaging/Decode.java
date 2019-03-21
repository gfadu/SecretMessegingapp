package com.example.secretmessaging;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Scanner;

public class Decode extends AppCompatActivity {

    String text;
    static String password;
    String bin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decode);
    }


    public void input()
    {
       // Scanner sc= new Scanner(System.in);
        //System.out.println("Enter the binary :");
        //text=sc.nextLine();
       // System.out.println("Enter the password:");
       // password=sc.nextLine();
        EditText messege=(EditText)findViewById(R.id.inputMessage);
        EditText Password=(EditText)findViewById(R.id.inputPassword);
        text=messege.getText().toString();
        password=Password.getText().toString();
        Log.i("entered",text);
        Log.i("pass",password);
        rempunc();
    }

    public void rempunc() {
        // TODO Auto-generated method stub
        char array[]=new char[text.length()];
        for(int i=0;i<text.length();i++)
        {
            array[i]=text.charAt(i);
            //System.out.println(array[i]);
        }
        for(int i=0;i<text.length();i++)
        {
            if(i%2==0)
            {
                if(array[i]!=' ')
                {
                    if(array[i]=='0')
                    {
                        array[i]='1';
                    }
                    else
                    {
                        array[i]='0';
                    }
                }
            }
        }
        bin=new String(array);
        //decrypt ob=new decrypt();
        init(bin);
        //ob.init(bin);
        //obj.decryptext();

    }

    public void init(String letters)
    {
        //java solution
        String s = " ";
        for(int index = 0; index < letters.length(); index+=9)
        {
            String temp = letters.substring(index, index+8);
            int num = Integer.parseInt(temp,2);
            char letter = (char) num;
            s = s+letter;
        }
        String ops=s.substring(s.length()-password.length());
        if(ops.equalsIgnoreCase(password))
        {
            int x=s.length()-password.length();
            s=s.substring(0,x);
            //System.out.print(s);
            Log.i("decoded:",s);
            EditText editText=(EditText)findViewById(R.id.displayMessage);
            editText.setAlpha(1);
            editText.setText(s);
        }
        else
        {
            Toast.makeText(this,"Access denied",Toast.LENGTH_LONG ).show();
            Log.i("access","denied");
        }
    }

    public void Decode(View view)
    {
        input();
    }
}
