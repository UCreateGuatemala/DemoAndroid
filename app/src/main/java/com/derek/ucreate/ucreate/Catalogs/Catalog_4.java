package com.derek.ucreate.ucreate.Catalogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.derek.ucreate.ucreate.R;

/**
 * Created by Derek on 6/8/2015.
 */
public class Catalog_4 extends Fragment {

    int position = 2;
    //String image;
    String titleText = "UCreate";
    int titleColor = 1;
    int backgroundColor = 1;
    int textColor = 1;

    int template = 1;

    int categoriesQuantity = 2;
    int productsQuantity = 16;
    Boolean rating = false, btnBuy = false, description = false, reviews = false;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.catalog_2, container, false);
        return view;
    }
}
