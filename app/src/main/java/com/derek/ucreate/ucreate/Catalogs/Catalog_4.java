package com.derek.ucreate.ucreate.Catalogs;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.derek.ucreate.ucreate.R;

/**
 * Created by Derek on 6/8/2015.
 */
public class Catalog_4 extends Fragment {

    int finalPosition = 1;
    String image;
    String titleText = "UCreate";
    int titleColor = 1;
    int backgroundColor = 1;
    int textColor = 1;

    int template = 1;

    int categoriesQuantity = 2;
    int productsQuantity = 16;
    Boolean rating = false, btnBuy = false, description = false, reviews = false;

    RelativeLayout catalogBackground, logoLayout, mainLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.catalog_4, container, false);

        catalogBackground = (RelativeLayout) view.findViewById(R.id.CatalogBackground);
        logoLayout = (RelativeLayout) view.findViewById(R.id.RelativeLayoutCatalogTitle);
        mainLayout = (RelativeLayout) view.findViewById(R.id.RelativeLayoutCatalogMain);

        logoPlusTitle();
        templateType(template);
        return view;
    }

    private void logoPlusTitle() {
        ImageView logoIcon = new ImageView(getActivity().getApplicationContext());
        TextView logoText = new TextView(getActivity().getApplicationContext());
        logoIcon.setId(0b0);
        logoText.setId();
        logoIcon.setImageResource(R.drawable.item_1);
        logoText.setText("UCreate");
        RelativeLayout.LayoutParams paramsIcon = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams paramsText = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        Resources r = getActivity().getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, r.getDisplayMetrics());
        paramsIcon.setMargins(px, px, px, px);
        logoText.setTextSize(18);
        if (finalPosition==1){
            Toast.makeText(getActivity().getApplicationContext(), "1", Toast.LENGTH_SHORT).show();
            logoLayout.removeAllViews();
            paramsIcon.addRule(RelativeLayout.CENTER_VERTICAL, logoIcon.getId());
            paramsText.addRule(RelativeLayout.CENTER_VERTICAL, logoText.getId());
            paramsText.addRule(RelativeLayout.RIGHT_OF, logoIcon.getId());
        }else if (finalPosition==2){
            Toast.makeText(getActivity().getApplicationContext(), "2", Toast.LENGTH_SHORT).show();
            logoLayout.removeAllViews();
            paramsIcon.addRule(RelativeLayout.CENTER_HORIZONTAL, logoIcon.getId());
            paramsText.addRule(RelativeLayout.BELOW, logoIcon.getId());
            paramsText.addRule(RelativeLayout.CENTER_HORIZONTAL, logoText.getId());
            logoText.setTextSize(15);
            int newpx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, r.getDisplayMetrics());
            paramsIcon.setMargins(px, newpx, px, px);
        }else{
            Toast.makeText(getActivity().getApplicationContext(), "3", Toast.LENGTH_SHORT).show();
            logoLayout.removeAllViews();
            paramsIcon.addRule(RelativeLayout.CENTER_VERTICAL, logoIcon.getId());
            paramsText.addRule(RelativeLayout.CENTER_VERTICAL, logoText.getId());
            paramsIcon.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, logoIcon.getId());
            paramsText.addRule(RelativeLayout.LEFT_OF, logoIcon.getId());
        }
        logoLayout.addView(logoIcon, paramsIcon);
        logoLayout.addView(logoText, paramsText);
    }

    private void templateType(int template) {
        if (template==1){

        }else if (template==2){

        }else if (template==3){

        }else if (template==4){

        }else if (template==5){

        }else{

        }
    }
}
