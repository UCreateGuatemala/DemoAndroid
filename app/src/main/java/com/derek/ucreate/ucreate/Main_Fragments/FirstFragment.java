package com.derek.ucreate.ucreate.Main_Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.derek.ucreate.ucreate.CatalogActivity;
import com.derek.ucreate.ucreate.R;

/**
 * Created by Derek on 5/12/2015.
 */
public class FirstFragment  extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.demo, container, false);

        Button btnCatalogTemplate = (Button) view.findViewById(R.id.buttonCatalogTemplate);
        btnCatalogTemplate.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonCatalogTemplate:
                Boolean newStart = true;
                Intent intentCatalogTemplateActivity = new Intent(getActivity(), CatalogActivity.class);
               // intentCatalogTemplateActivity.putExtra("StartFresh",newStart);
                getActivity().startActivity(intentCatalogTemplateActivity);
                break;
        }
    }
}
