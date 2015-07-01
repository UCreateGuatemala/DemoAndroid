package com.derek.ucreate.ucreate.Main_Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.derek.ucreate.ucreate.R;

/**
 * Created by Derek on 5/12/2015.
 */
public class ThirdFragment  extends Fragment implements View.OnClickListener {

    TextView tvContactPhoneInfo;
    TextView tvContactEmailInfo;
    TextView tvContactFacebookInfo;

    int i =0;
    Button boton;
    ScrollView fondo;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_us, container, false);

        tvContactPhoneInfo = (TextView) view.findViewById(R.id.textViewContactPhoneInfo);
        tvContactEmailInfo = (TextView) view.findViewById(R.id.textViewContactEmailInfo);
        tvContactFacebookInfo = (TextView) view.findViewById(R.id.textViewContactFacebookInfo);

        tvContactPhoneInfo.setOnClickListener(this);
        tvContactEmailInfo.setOnClickListener(this);
        tvContactFacebookInfo.setOnClickListener(this);

        boton = (Button) view.findViewById(R.id.button);
        fondo = (ScrollView) view.findViewById(R.id.fondoContacto);
        boton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textViewContactPhoneInfo:
                String numberUCreate = tvContactPhoneInfo.getText().toString();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + numberUCreate));
                startActivity(callIntent);
                break;
            case R.id.textViewContactEmailInfo:
                String emailUCreate = tvContactEmailInfo.getText().toString();
                String[] TO = {emailUCreate};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                startActivity(emailIntent);
                break;
            case R.id.textViewContactFacebookInfo:
                Uri webPage = Uri.parse("https://www.facebook.com/ucreatesa?notif_t=fbpage_admin");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webPage);
                startActivity(webIntent);
                break;
            case R.id.button:
                if (i==0){
                    fondo.setBackgroundResource(R.drawable.fondo1);
                }
                if (i==1){
                    fondo.setBackgroundResource(R.drawable.fondo2);
                }
                if (i==2){
                    fondo.setBackgroundResource(R.drawable.fondo3);
                }
                if (i==3){
                    fondo.setBackgroundResource(R.drawable.fondo4);
                }
                if (i==4){
                    fondo.setBackgroundResource(R.drawable.fondo);
                    i = -1;
                }
                i++;
        }
    }
}
