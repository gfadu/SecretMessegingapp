package com.example.secretmessaging;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import static android.view.View.INVISIBLE;

public class MainActivity extends AppCompatActivity {

    private AdView mAdView;

    public void welcome(View view){

        Button welcome1 = (Button) findViewById(R.id.welcome);
        Button encode = (Button) findViewById(R.id.encode);
        Button decode = (Button) findViewById(R.id.decode);
        TextView description = (TextView) findViewById(R.id.description);
        TextView welcomeText = (TextView) findViewById(R.id.welcomeTextView);
        ImageView logoImageView = (ImageView) findViewById(R.id.logoImageView);
        description.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
        description.setGravity(Gravity.CENTER);
        welcome1.setVisibility(View.INVISIBLE);
        encode.setVisibility(View.VISIBLE);
        decode.setVisibility(View.VISIBLE);
        welcomeText.setVisibility(INVISIBLE);
        logoImageView.animate().alpha((float) 0.5).setDuration(500);
        description.animate().alpha(1).setDuration(500);
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

        MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713");
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
}
