package com.derek.ucreate.ucreate.Catalogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.derek.ucreate.ucreate.R;

/**
 * Created by Derek on 5/27/2015.
 */
public class Catalog_3 extends Fragment {

    TextView tvDescription, tvComments;
    CheckBox cbRating, cbDescription, cbBuyButton;
    Button btnBuy;
    Boolean rating = false, description = false, buyButton = false;
    ImageView ivRating;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.catalog_3, container, false);

        cbRating = (CheckBox) view.findViewById(R.id.checkBoxRating);
        cbDescription = (CheckBox) view.findViewById(R.id.checkBoxDescription);
        cbBuyButton = (CheckBox) view.findViewById(R.id.checkBoxBuyButton);

        tvDescription = (TextView) view.findViewById(R.id.textViewDescription);
        btnBuy = (Button) view.findViewById(R.id.buttonBuy);
        ivRating = (ImageView) view.findViewById(R.id.imageViewRatingStars);

        ivRating.setImageDrawable(null);
        tvDescription.setText("");
        btnBuy.setVisibility(View.GONE);

        getActivity().getIntent().putExtra("Description",description);
        getActivity().getIntent().putExtra("buyButton",buyButton);
        getActivity().getIntent().putExtra("ratingStars", rating);

        cbRating.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    ivRating.setImageResource(R.drawable.rating_stars);
                    rating = true;
                }else{
                    ivRating.setImageDrawable(null);
                    rating = false;
                }
                getActivity().getIntent().putExtra("ratingStars",rating);
            }
        });

        cbDescription.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    tvDescription.setText("Descripci\u00F3n");
                    description = true;
                }else{
                    tvDescription.setText("");
                    description = false;
                }
                getActivity().getIntent().putExtra("Description",description);
            }
        });

        cbBuyButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    btnBuy.setVisibility(View.VISIBLE);
                    buyButton = true;
                }else{
                    btnBuy.setVisibility(View.GONE);
                    buyButton = false;
                }
                getActivity().getIntent().putExtra("buyButton",buyButton);
            }
        });
        return view;
    }
}
