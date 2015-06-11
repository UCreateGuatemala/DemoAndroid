package com.derek.ucreate.ucreate.Catalogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.derek.ucreate.ucreate.R;

/**
 * Created by Derek on 5/27/2015.
 */
public class Catalog_1 extends Fragment {

    ImageButton ibTemplate1, ibTemplate2, ibTemplate3, ibTemplate4, ibTemplate5;
    ImageView ivTemplate;
    int template = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.catalog_2, container, false);

        ibTemplate1 = (ImageButton) view.findViewById(R.id.imageButtonTemplate1);
        ibTemplate2 = (ImageButton) view.findViewById(R.id.imageButtonTemplate2);
        ibTemplate3 = (ImageButton) view.findViewById(R.id.imageButtonTemplate3);
        ibTemplate4 = (ImageButton) view.findViewById(R.id.imageButtonTemplate4);
        ibTemplate5 = (ImageButton) view.findViewById(R.id.imageButtonTemplate5);
        ivTemplate = (ImageView) view.findViewById(R.id.imageViewTemplate);

        ibTemplate1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                ivTemplate.setImageResource(R.drawable.template_1);
                template = 1;
            }
        });

        ibTemplate2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                ivTemplate.setImageResource(R.drawable.template_2);
                template = 2;
            }
        });

        ibTemplate3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                ivTemplate.setImageResource(R.drawable.template_3);
                template = 3;
            }
        });

        ibTemplate4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                ivTemplate.setImageResource(R.drawable.template_4);
                template = 4;
            }
        });

        ibTemplate5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                ivTemplate.setImageResource(R.drawable.template_5);
                template = 5;
            }
        });
        return view;
    }
}
