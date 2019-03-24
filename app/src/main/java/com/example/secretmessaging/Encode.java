package com.example.secretmessaging;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class Encode extends AppCompatActivity {

    EditText message;
    EditText inputpassword;
    TextView print;
    String text, password, bin;
    Button button;
    private ClipboardManager myClipboard;
    private ClipData myClip;
    EditText textView;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encode);


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

    public void encrypt() {
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

    public void input() {
        message = (EditText) findViewById(R.id.inputMessage);
        inputpassword = (EditText) findViewById(R.id.inputPassword);
        text = message.getText().toString();
        password = inputpassword.getText().toString();
        Log.i("Entered message",message.getText().toString());
        Log.i("Entered message",inputpassword.getText().toString());
        text = text + password;
        Log.i("text:", text);
        encrypt();
    }


    public void addpunc() {
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
        String string = new String(array);
        Log.i("encoded", string);
        textView = (EditText) findViewById(R.id.displayMessage);
        textView.setAlpha(1);
        textView.setText(string);
    }

    public void save(View view) {

        message = (EditText) findViewById(R.id.inputMessage);
        inputpassword = (EditText) findViewById(R.id.inputPassword);
        Button copy=(Button) findViewById(R.id.copy);
        copy.setVisibility(View.VISIBLE);
        if (message.toString().length()==0)
        {
            Toast.makeText(this,"Enter a message",Toast.LENGTH_SHORT).show();
        }
        else if (inputpassword.toString().length()==0)
        {
            Toast.makeText(this,"Enter a password",Toast.LENGTH_SHORT).show();
        }
        else {

            input();
        }
        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(textView.getWindowToken(),0);
    }


    void copy(View view)
    {
        button = (Button) findViewById(R.id.copy);
        myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
        Toast.makeText(this,"Double tap to copy",Toast.LENGTH_SHORT).show();
        button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                try {
                    String text = textView.getText().toString();
                    myClip = ClipData.newPlainText("text", text);
                    myClipboard.setPrimaryClip(myClip);
                    Toast.makeText(getApplicationContext(), "Text Copied",
                            Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Enter a valid message",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}


