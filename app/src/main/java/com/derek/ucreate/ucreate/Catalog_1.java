package com.derek.ucreate.ucreate;

import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;

/**
 * Created by Derek on 5/27/2015.
 */
public class Catalog_1 extends Fragment implements View.OnTouchListener, View.OnDragListener{

        RelativeLayout CatalogBackground;
        Button btnBackground, btnLogo, btnTitle, btnText;
        ImageView ivPosition1, ivPosition2, ivPosition3;
        TextView tvPosition1, tvPosition2, tvPosition3, tvItem1, tvItem2;
        int logoPosition = 0, titlePosition = 0;
        Boolean logoPosition1, logoPosition2, logoPosition3;
        Boolean titlePosition1, titlePosition2, titlePosition3;
        Boolean alreadyTitle = false, alreadyLogo = false;
        Boolean logo = false, title = false;
        String backgroundColor = "white", textColor = "black";
        private int PICK_IMAGE_REQUEST = 1;
    

        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.catalog_1, container, false);
            CatalogBackground = (RelativeLayout) view.findViewById(R.id.CatalogBackground);
            btnLogo = (Button) view.findViewById(R.id.buttonCatalogNewLogo);
            btnBackground = (Button) view.findViewById(R.id.buttonCatalogBackground);
            btnTitle = (Button) view.findViewById(R.id.buttonCatalogNewTitle);
            btnText = (Button) view.findViewById(R.id.buttonCatalogTextColor);

            /*ivPosition1 = (ImageView) view.findViewById(R.id.imageViewCatalog1Position1);
            ivPosition2 = (ImageView) view.findViewById(R.id.imageViewCatalog1Position2);
            ivPosition3 = (ImageView) view.findViewById(R.id.imageViewCatalog1Position3);

            tvPosition1 = (TextView) view.findViewById(R.id.textViewCatalog1Position1);
            tvPosition2 = (TextView) view.findViewById(R.id.textViewCatalog1Position2);
            tvPosition3 = (TextView) view.findViewById(R.id.textViewCatalog1Position3);

            tvItem1 = (TextView) view.findViewById(R.id.textViewItem1);
            tvItem2 = (TextView) view.findViewById(R.id.textViewItem2);

            view.findViewById(R.id.imageViewCatalog1Position1).setOnDragListener(this);
            view.findViewById(R.id.imageViewCatalog1Position2).setOnDragListener(this);
            view.findViewById(R.id.imageViewCatalog1Position3).setOnDragListener(this);

            view.findViewById(R.id.imageViewCatalog1Position1).setOnTouchListener(this);
            view.findViewById(R.id.imageViewCatalog1Position2).setOnTouchListener(this);
            view.findViewById(R.id.imageViewCatalog1Position3).setOnTouchListener(this);

            view.findViewById(R.id.textViewCatalog1Position1).setOnDragListener(this);
            view.findViewById(R.id.textViewCatalog1Position2).setOnDragListener(this);
            view.findViewById(R.id.textViewCatalog1Position3).setOnDragListener(this);

            view.findViewById(R.id.textViewCatalog1Position1).setOnTouchListener(this);
            view.findViewById(R.id.textViewCatalog1Position2).setOnTouchListener(this);
            view.findViewById(R.id.textViewCatalog1Position3).setOnTouchListener(this);*/


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
                                    alreadyLogo = true;
                                    logoPosition = 1;
                                    ivPosition1.setImageResource(R.mipmap.ic_launcher);
                                    return true;
                                case R.id.OtherLogo:
                                    alreadyLogo = true;
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

            btnTitle.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v){
                    Dialog dialog = new Dialog(getActivity());
                    dialog.setContentView(R.layout.title_dialog);
                    dialog.setTitle("Custom Dialog");
                    dialog.show();
                    alreadyTitle = true;
                    titlePosition = 1;
                    tvPosition1.setText("UCreate");
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
            return view;
        }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        final int action = event.getAction();
        switch(action){
            case DragEvent.ACTION_DRAG_STARTED:
                if (logo){
                    ivPosition1.setImageDrawable(null);
                    ivPosition2.setImageDrawable(null);
                    ivPosition3.setImageDrawable(null);
                    ivPosition1.setBackgroundColor(getResources().getColor(R.color.ripple_material_light));
                    ivPosition2.setBackgroundColor(getResources().getColor(R.color.ripple_material_light));
                    ivPosition3.setBackgroundColor(getResources().getColor(R.color.ripple_material_light));
                    logoPosition1 = false;
                    logoPosition2 = false;
                    logoPosition3 = false;
                }
                if (title){
                    tvPosition1.setText("");
                    tvPosition2.setText("");
                    tvPosition3.setText("");
                    tvPosition1.setBackgroundColor(getResources().getColor(R.color.ripple_material_light));
                    tvPosition2.setBackgroundColor(getResources().getColor(R.color.ripple_material_light));
                    tvPosition3.setBackgroundColor(getResources().getColor(R.color.ripple_material_light));
                    titlePosition1 = false;
                    titlePosition2 = false;
                    titlePosition3 = false;
                }
                return true;
            case DragEvent.ACTION_DRAG_ENTERED:
                if (logo){
                    if (v == ivPosition1){
                        logoPosition1 = true;
                        logoPosition2 = false;
                        logoPosition3 = false;
                    }else if (v == ivPosition2){
                        logoPosition1 = false;
                        logoPosition2 = true;
                        logoPosition3 = false;
                    }else if (v == ivPosition3){
                        logoPosition1 = false;
                        logoPosition2 = false;
                        logoPosition3 = true;
                    }
                }
                if (title){
                    if (v == tvPosition1){
                        titlePosition1 = true;
                        titlePosition2 = false;
                        titlePosition3 = false;
                    }else if (v == tvPosition2){
                        titlePosition1 = false;
                        titlePosition2 = true;
                        titlePosition3 = false;
                    }else if (v == tvPosition3){
                        titlePosition1 = false;
                        titlePosition2 = false;
                        titlePosition3 = true;
                    }
                }
                return true;
            case DragEvent.ACTION_DRAG_LOCATION:
                return true;
            case DragEvent.ACTION_DRAG_EXITED:
                if (logo) {
                    logoPosition1 = false;
                    logoPosition2 = false;
                    logoPosition3 = false;
                }
                return true;
            case DragEvent.ACTION_DROP:
                if (logo){
                    if (logoPosition1){
                        logoPosition = 1;
                    }else if (logoPosition2){
                        logoPosition = 2;
                    }else if (logoPosition3){
                        logoPosition = 3;
                    }
                }
                if (title){
                    if (titlePosition1){
                        tvPosition1.setText("UCreate");
                        tvPosition1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        //tvPosition2.setWidth(150);
                        titlePosition = 1;
                    }else if (titlePosition2){
                        tvPosition2.setText("UCreate");
                        tvPosition2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        //tvPosition1.setWidth(150);
                        titlePosition = 2;
                    }else if (titlePosition3){
                        tvPosition3.setText("UCreate");
                        tvPosition2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        titlePosition = 3;
                    }else{
                        if (titlePosition == 1){
                            tvPosition1.setText("UCreate");
                        }else if (titlePosition == 2){
                            tvPosition2.setText("UCreate");
                        }else if (titlePosition == 3){
                            tvPosition3.setText("UCreate");
                        }
                    }
                }
                return true;
            case DragEvent.ACTION_DRAG_ENDED:
                if (logo){
                    ivPosition1.setBackgroundColor(getResources().getColor(R.color.noColor));
                    ivPosition2.setBackgroundColor(getResources().getColor(R.color.noColor));
                    ivPosition3.setBackgroundColor(getResources().getColor(R.color.noColor));
                    if (logoPosition == 1){
                        ivPosition1.setImageResource(R.mipmap.ic_launcher);
                    }else if (logoPosition == 2){
                        ivPosition2.setImageResource(R.mipmap.ic_launcher);
                    }else if (logoPosition == 3){
                        ivPosition3.setImageResource(R.mipmap.ic_launcher);
                    }
                    logo = false;
                }
                if (title){
                    tvPosition1.setBackgroundColor(getResources().getColor(R.color.noColor));
                    tvPosition2.setBackgroundColor(getResources().getColor(R.color.noColor));
                    tvPosition3.setBackgroundColor(getResources().getColor(R.color.noColor));
                    title = false;
                }
                return true;
        }
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ClipData data = ClipData.newPlainText("", "");
        if (alreadyLogo){
            if (logoPosition == 1 && v == ivPosition1){
                logo = true;
                View.DragShadowBuilder shadow = new View.DragShadowBuilder(ivPosition1);
                v.startDrag(data, shadow, null, 0);
            }
            if (logoPosition == 2 && v == ivPosition2){
                logo = true;
                View.DragShadowBuilder shadow = new View.DragShadowBuilder(ivPosition2);
                v.startDrag(data, shadow, null, 0);
            }
            if (logoPosition == 3 && v == ivPosition3){
                logo = true;
                View.DragShadowBuilder shadow = new View.DragShadowBuilder(ivPosition3);
                v.startDrag(data, shadow, null, 0);
            }
        }
        if (alreadyTitle){
            if (titlePosition == 1 && v == tvPosition1){
                title =  true;
                View.DragShadowBuilder shadow = new View.DragShadowBuilder(tvPosition1);
                v.startDrag(data, shadow, null, 0);
            }
            if (titlePosition == 2 && v == tvPosition2){
                title =  true;
                View.DragShadowBuilder shadow = new View.DragShadowBuilder(tvPosition2);
                v.startDrag(data, shadow, null, 0);
            }
            if (titlePosition == 3 && v == tvPosition3){
                title =  true;
                View.DragShadowBuilder shadow = new View.DragShadowBuilder(tvPosition3);
                v.startDrag(data, shadow, null, 0);
            }
        }
        return false;
    }
}
