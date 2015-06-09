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
