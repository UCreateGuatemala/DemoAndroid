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

import com.derek.ucreate.ucreate.R;

/**
 * Created by Derek on 6/8/2015.
 */
public class Catalog_4 extends Fragment {

    int position = 2;
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

        logoPlusTitle(position,image,titleText,titleColor);
        templateType(template);
        return view;
    }

    private void logoPlusTitle(int position, String image, String titleText, int titleColor) {
        ImageView logoIcon = new ImageView(getActivity().getApplicationContext());
        logoIcon.setImageResource(R.drawable.solologo);
        RelativeLayout.LayoutParams paramsIcon = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        //RelativeLayout.LayoutParams paramsText = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        /*Resources r = getActivity().getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, r.getDisplayMetrics());
        paramsIcon.setMargins(px, px, px, px);
        logoText.setTextSize(18);*/
        if (position==1){

        }else if (position==2){
            logoLayout.removeAllViews();
            paramsIcon.addRule(RelativeLayout.CENTER_VERTICAL, logoIcon.getId());
            // paramsText.addRule(RelativeLayout.CENTER_VERTICAL, logoText.getId());
            // paramsText.addRule(RelativeLayout.RIGHT_OF, logoIcon.getId());
        }else if (position==3){

        }else{

        }
        logoLayout.addView(logoIcon, paramsIcon);
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
