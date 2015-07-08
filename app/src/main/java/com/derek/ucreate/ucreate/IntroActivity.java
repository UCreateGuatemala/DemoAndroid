package com.derek.ucreate.ucreate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class IntroActivity extends ActionBarActivity {

    Button btnNext, btnLogo;
    ImageView logo;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        /*Thread introTimer = new Thread(){
            public void run(){
                try{
                    sleep(2000);
                    Intent intentStart;
                    intentStart = new Intent(IntroActivity.this, MainActivity.class);
                    startActivity(intentStart);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally{
                    finish();
                }
            }
        };
        introTimer.start();*/
        btnLogo = (Button) findViewById(R.id.buttonLogo);
        btnNext = (Button) findViewById(R.id.buttonNext);
        logo = (ImageView) findViewById(R.id.imageViewLogo);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_intro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    public void buttonIntro(View view) {
        switch (view.getId()){
            case R.id.buttonLogo:
                if (i==0){
                    logo.setImageResource(R.drawable.solologo2);
                }else{
                    logo.setImageResource(R.drawable.solologo);
                    i=-1;
                }
                i++;
                break;
            case R.id.buttonNext:
                Intent intentStart;
                intentStart = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(intentStart);
                finish();
                break;
        }
    }
}
