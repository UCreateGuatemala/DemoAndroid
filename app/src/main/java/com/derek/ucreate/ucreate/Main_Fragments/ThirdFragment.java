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

    Button btnContactPhoneInfo;
    Button btnContactEmailInfo;
    Button btnContactFacebookInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_us, container, false);

        btnContactPhoneInfo = (Button) view.findViewById(R.id.buttonContactPhoneInfo);
        btnContactEmailInfo = (Button) view.findViewById(R.id.buttonContactEmailInfo);
        btnContactFacebookInfo = (Button) view.findViewById(R.id.buttonContactFacebookInfo);

        btnContactPhoneInfo.setOnClickListener(this);
        btnContactEmailInfo.setOnClickListener(this);
        btnContactFacebookInfo.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonContactPhoneInfo:
                String numberUCreate = btnContactPhoneInfo.getText().toString();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + numberUCreate));
                startActivity(callIntent);
                break;
            case R.id.buttonContactEmailInfo:
                String emailUCreate = btnContactEmailInfo.getText().toString();
                String[] TO = {emailUCreate};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                startActivity(emailIntent);
                break;
            case R.id.buttonContactFacebookInfo:
                Uri webPage = Uri.parse("https://www.facebook.com/ucreatesa?notif_t=fbpage_admin");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webPage);
                startActivity(webIntent);
                break;
        }
    }
}
