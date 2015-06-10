package com.derek.ucreate.ucreate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Derek on 5/27/2015.
 */
public class Catalog_3 extends Fragment implements SeekBar.OnSeekBarChangeListener {

    SeekBar sbCategory, sbProducts;
    TextView tvCategory, tvProducts;
    CheckBox cbRating, cbDescription, cbReviews, cbBuyButton;
    int category = 4, products = 20;
    int progressCategory = 1, progressProducts = 1;
    Boolean rating = false, description = false, reviews =  false, buyButton = false;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.catalog_3, container, false);

        sbCategory = (SeekBar) view.findViewById(R.id.seekBarCategories);
        sbProducts = (SeekBar) view.findViewById(R.id.seekBarProducts);

        tvCategory = (TextView) view.findViewById(R.id.textViewCategoryNumber);
        tvProducts = (TextView) view.findViewById(R.id.textViewProductsNumber);

        cbRating = (CheckBox) view.findViewById(R.id.checkBoxRating);
        cbDescription = (CheckBox) view.findViewById(R.id.checkBoxDescription);
        cbReviews = (CheckBox) view.findViewById(R.id.checkBoxReviews);
        cbBuyButton = (CheckBox) view.findViewById(R.id.checkBoxBuyButton);

        sbProducts.setOnSeekBarChangeListener(this);
        sbCategory.setOnSeekBarChangeListener(this);

        cbRating.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    rating = true;
                }else{
                    rating = false;
                }
            }
        });

        cbDescription.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    description = true;
                }else{
                    description = false;
                }

            }
        });

        cbBuyButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    buyButton = true;
                }else{
                    buyButton = false;
                }
            }
        });

        cbReviews.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    reviews = true;
                }else{
                    reviews = false;
                }
            }
        });
        return view;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {

            case R.id.seekBarCategories:
                progressCategory = progress;
                category = 1+progressCategory;
                tvCategory.setText(""+category);
                break;

            case R.id.seekBarProducts:
                progressProducts = progress;
                products = 15+progressProducts;
                tvProducts.setText(""+products);
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
