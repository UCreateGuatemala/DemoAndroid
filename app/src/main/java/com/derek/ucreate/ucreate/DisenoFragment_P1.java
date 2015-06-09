package com.derek.ucreate.ucreate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;

/**
 * Created by Boris on 09/06/2015.
 */
public class DisenoFragment_P1 extends Fragment {

    private RelativeLayout  CatalogBackground;
    private Button          btnBackground, btnLogo, btnTitle, btnText;
    private ImageView       logoIcon;
    private TextView        logoText;
    private final int       PICK_IMAGE_REQUEST=1;
    private String          backgroundColor="white";
    private String          textColor="black";
    private ImageView       ivPosition1, ivPosition2, ivPosition3;
    private TextView        tvPosition1, tvPosition2, tvPosition3, tvItem1, tvItem2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.catalog_1,container);
        logoIcon = (ImageView) v.findViewById(R.id.imageViewCatalog1);
        logoText = (TextView) v.findViewById(R.id.textViewCatalog1);
        logoIcon.setOnClickListener(new ClickListenerLogo());
        logoText.setOnClickListener(new ClickListenerLogo());

        btnLogo = (Button) v.findViewById(R.id.buttonCatalogNewLogo);
        btnBackground = (Button) v.findViewById(R.id.buttonCatalogBackground);
        btnTitle = (Button) v.findViewById(R.id.buttonCatalogNewTitle);
        btnText = (Button) v.findViewById(R.id.buttonCatalogTextColor);

        tvItem1 = (TextView) v.findViewById(R.id.textViewItem1);
        tvItem2 = (TextView) v.findViewById(R.id.textViewItem2);

        btnLogo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getActivity().getApplicationContext(), v);
                popup.inflate(R.menu.menu_add_logo);
                Object menuHelper;
                Class[] argTypes;
                try {
                    Field fMenuHelper = PopupMenu.class.getDeclaredField("mPopup");
                    fMenuHelper.setAccessible(true);
                    menuHelper = fMenuHelper.get(popup);
                    argTypes = new Class[] { boolean.class };
                    menuHelper.getClass().getDeclaredMethod("setForceShowIcon", argTypes).invoke(menuHelper, true);
                } catch (Exception e) {
                    popup.show();
                    return;
                }
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.OurLogo:
                                logoIcon.setImageResource(R.mipmap.ic_launcher);
                                return true;
                            case R.id.OtherLogo:
                                Context context2 = getActivity().getApplicationContext();
                                CharSequence text2 = "Choose your image";
                                int duration2 = Toast.LENGTH_SHORT;
                                Toast toast2 = Toast.makeText(context2, text2, duration2);
                                toast2.show();

                                Intent intent = new Intent();
                                intent.setType("image/*");
                                intent.setAction(Intent.ACTION_GET_CONTENT);
                                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
                                return true;
                            default:
                                return false;
                        }
                    };
                });
                popup.show();
            }
        });

        btnBackground.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getActivity().getApplicationContext(), v);
                popup.inflate(R.menu.menu_change_colors);
                Object menuHelper;
                Class[] argTypes;
                try {
                    Field fMenuHelper = PopupMenu.class.getDeclaredField("mPopup");
                    fMenuHelper.setAccessible(true);
                    menuHelper = fMenuHelper.get(popup);
                    argTypes = new Class[] { boolean.class };
                    menuHelper.getClass().getDeclaredMethod("setForceShowIcon", argTypes).invoke(menuHelper, true);
                } catch (Exception e) {
                    popup.show();
                    return;
                }
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.orange:
                                CatalogBackground.setBackgroundColor(getResources().getColor(R.color.orange));
                                backgroundColor = "orange";
                                return true;
                            case R.id.red:
                                CatalogBackground.setBackgroundColor(getResources().getColor(R.color.red));
                                backgroundColor = "red";
                                return true;
                            case R.id.yellow:
                                CatalogBackground.setBackgroundColor(getResources().getColor(R.color.yellow));;
                                backgroundColor = "yellow";
                                return true;
                            case R.id.green:
                                CatalogBackground.setBackgroundColor(getResources().getColor(R.color.green));
                                backgroundColor = "green";
                                return true;
                            case R.id.blue:
                                CatalogBackground.setBackgroundColor(getResources().getColor(R.color.blue));
                                backgroundColor = "blue";
                                return true;
                            case R.id.purple:
                                CatalogBackground.setBackgroundColor(getResources().getColor(R.color.purple));
                                backgroundColor = "purple";
                                return true;
                            case R.id.white:
                                CatalogBackground.setBackgroundColor(getResources().getColor(R.color.white));
                                backgroundColor = "white";
                                return true;
                            default:
                                return false;
                        }
                    };
                });
                popup.show();
            }
        });
        btnText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getActivity().getApplicationContext(), v);
                popup.inflate(R.menu.menu_change_text_color);
                Object menuHelper;
                Class[] argTypes;
                try {
                    Field fMenuHelper = PopupMenu.class.getDeclaredField("mPopup");
                    fMenuHelper.setAccessible(true);
                    menuHelper = fMenuHelper.get(popup);
                    argTypes = new Class[] { boolean.class };
                    menuHelper.getClass().getDeclaredMethod("setForceShowIcon", argTypes).invoke(menuHelper, true);
                } catch (Exception e) {
                    popup.show();
                    return;
                }
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.orange:
                                tvItem1.setTextColor(getResources().getColor(R.color.orange));
                                tvItem2.setTextColor(getResources().getColor(R.color.orange));
                                textColor = "orange";
                                return true;
                            case R.id.red:
                                tvItem1.setTextColor(getResources().getColor(R.color.red));
                                tvItem2.setTextColor(getResources().getColor(R.color.red));
                                textColor = "red";
                                return true;
                            case R.id.yellow:
                                tvItem1.setTextColor(getResources().getColor(R.color.yellow));
                                tvItem2.setTextColor(getResources().getColor(R.color.yellow));
                                textColor = "yellow";
                                return true;
                            case R.id.green:
                                tvItem1.setTextColor(getResources().getColor(R.color.green));
                                tvItem2.setTextColor(getResources().getColor(R.color.green));
                                textColor = "green";
                                return true;
                            case R.id.blue:
                                tvItem1.setTextColor(getResources().getColor(R.color.blue));
                                tvItem2.setTextColor(getResources().getColor(R.color.blue));
                                textColor = "blue";
                                return true;
                            case R.id.purple:
                                tvItem1.setTextColor(getResources().getColor(R.color.purple));
                                tvItem2.setTextColor(getResources().getColor(R.color.purple));
                                textColor = "purple";
                                return true;
                            case R.id.white:
                                tvItem1.setTextColor(getResources().getColor(R.color.white));
                                tvItem2.setTextColor(getResources().getColor(R.color.white));
                                textColor = "white";
                                return true;
                            case R.id.black:
                                tvItem1.setTextColor(getResources().getColor(R.color.black));
                                tvItem2.setTextColor(getResources().getColor(R.color.black));
                                textColor = "white";
                                return true;
                            default:
                                return false;
                        }
                    };
                });
                popup.show();
            }
        });
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
