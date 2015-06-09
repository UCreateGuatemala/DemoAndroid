package com.derek.ucreate.ucreate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Boris on 09/06/2015.
 */
public class DisenoFragment_P1 extends Fragment {

    RelativeLayout  CatalogBackground;
    Button          btnBackground, btnLogo, btnTitle, btnText;
    ImageView       logoIcon;
    TextView        logoText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.catalog_1,container);
        logoIcon = (ImageView) v.findViewById(R.id.imageViewCatalog1);
        logoText = (TextView) v.findViewById(R.id.textViewCatalog1);
        logoIcon.setOnClickListener(new ClickListenerLogo());
        logoText.setOnClickListener(new ClickListenerLogo());

        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    class ClickListenerLogo implements View.OnClickListener{
        @Override
        public void onClick(View view) {

        }
    }
}
