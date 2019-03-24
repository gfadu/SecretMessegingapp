package com.example.secretmessaging;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Scanner;

public class Decode extends AppCompatActivity {

    String text;
    static String password;
    String bin;
    EditText displaymessage;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decode);

        mAdView=findViewById(R.id.adView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded() {
                Log.d("Ad Test","Add Finished Loading");
            }

            @Override
            public void onAdFailedToLoad(int i) {
                Log.d("Ad Test","Add Loading Failed");
            }

            @Override
            public void onAdClicked() {
                Log.d("Ad Test","Add clicked by the User");
            }

            @Override
            public void onAdOpened() {
                Log.d("Ad Test","Add is Visible Now");
            }

            @Override
            public void onAdLeftApplication() {
                Log.d("Ad Test","User Left the app");
            }

            @Override
            public void onAdClosed() {
                Log.d("Ad Test","User return back to the app after tapping on ad");
            }
        });

    }


    public void input()
    {
       // Scanner sc= new Scanner(System.in);
        //System.out.println("Enter the binary :");
        //text=sc.nextLine();
       // System.out.println("Enter the password:");
       // password=sc.nextLine();
        displaymessage = (EditText)findViewById(R.id.displayMessage);
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
        if(ops.equalsIgnoreCase(password) && password.length()!=0)
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
        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(displaymessage.getWindowToken(),0);
    }
}
