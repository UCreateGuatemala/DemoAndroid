package com.derek.ucreate.ucreate;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Boris on 6/11/2015.
 */
public class ShowCatalogActivity extends Activity{

    private int templateType, backgroundColor, textColor, logoTextColor, orientation;
    private String logoName;
    private Bitmap logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_catalog);

        Bundle b = getIntent().getExtras();
        getExtrasBundle(b);
    }

    public void getExtrasBundle(Bundle b){
        templateType = b.getInt("Template");
        backgroundColor = b.getInt("BackgroundColor");
        textColor = b.getInt("TextColor");
        logoTextColor = b.getInt("LogoTextColor");
        orientation = b.getInt("Orientation");
        logoName = b.getString("LogoName");
        logo = b.getParcelable("Logo");
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}
