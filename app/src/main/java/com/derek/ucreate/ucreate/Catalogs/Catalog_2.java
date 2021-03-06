package com.derek.ucreate.ucreate.Catalogs;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.derek.ucreate.ucreate.Models.Item;
import com.derek.ucreate.ucreate.Adapters.*;
import com.derek.ucreate.ucreate.R;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Catalog_2 extends Fragment {

    private RelativeLayout  CatalogBackground, LogoLayout, RelativeLayoutItems;
    private LinearLayout    LayoutButtonsLeft, LayoutButtonsTop;
    private boolean         globalChange;
    private ImageView       logoIcon;
    private TextView        logoText;
    private final int       PICK_IMAGE_REQUEST=1;
    private final int       PIC_CROP = 2;
    private final int       numRows = 2,startUpGrayBackgroundColor=-1, startUpBackgroundColor=-1,startUpTextColor=-16777216,startUpLogoTextColor=-16777216, startUpBackgroundItemColor=-1, startUpCardBackgroundColor = -1;;
    private int             grayBackgroundColor=-1,backgroundColor=-1,textColor=-16777216,logoTextColor=-16777216, backgroundItemColor=-1, cardBackgroundColor = -1;
    private List<Item>      items;
    private GridView        gridView;
    private Bitmap          logo;
    private Uri             logoImage = null;
    private byte[]          bytes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.catalog_2,container,false);

        gridView = (GridView) v.findViewById(R.id.ListItems);
        logoIcon = (ImageView) v.findViewById(R.id.imageViewCatalog1);
        logoText = (TextView) v.findViewById(R.id.textViewCatalog1);
        logoIcon.setOnClickListener(new ClickListenerLogo());
        logoText.setOnClickListener(new ClickListenerLogo());

        startup();

        getActivity().getIntent().putExtra("BackgroundColor", startUpBackgroundColor);
        getActivity().getIntent().putExtra("TextColor", startUpTextColor);
        getActivity().getIntent().putExtra("LogoTextColor",startUpLogoTextColor);
        getActivity().getIntent().putExtra("LogoName",getResources().getString(R.string.app_name));
        getActivity().getIntent().putExtra("Orientation",0);
        getActivity().getIntent().putExtra("BackgroundItemColor", startUpBackgroundItemColor);
        getActivity().getIntent().putExtra("CardBackgroundColor", startUpGrayBackgroundColor);
        getActivity().getIntent().putExtra("Logo",bytes);

        CatalogBackground = (RelativeLayout) v.findViewById(R.id.CatalogBackground);
        CatalogBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectColors("BackgroundColor");
            }
        });
        LogoLayout = (RelativeLayout) v.findViewById(R.id.RelativeLayoutCatalog1Up);
        RelativeLayoutItems = (RelativeLayout) v.findViewById(R.id.RelativeLayoutItems);
        RelativeLayoutItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectColors("BackgroundColor");
            }
        });
        LayoutButtonsLeft = (LinearLayout) RelativeLayoutItems.findViewById(R.id.LayoutButtonsLeft);
        LayoutButtonsTop = (LinearLayout) RelativeLayoutItems.findViewById(R.id.LayoutButtonsTop);

        items = new ArrayList<>();
        Random r = new Random();
        for (int i=0; i<4; i++){
            items.add(new Item("Item "+i,(i+r.nextInt(10))* r.nextInt(5), BitmapFactory.decodeResource(getResources(),R.drawable.item_1)));
        }

        gridView.setNumColumns(numRows);
        gridView.setAdapter(new ItemAdapter(getActivity(),R.layout.catalog_2_list_items,items,startUpTextColor,startUpGrayBackgroundColor,startUpCardBackgroundColor));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, final View view, int i, long l) {
                CharSequence options[] = new CharSequence[] {getResources().getString(R.string.change_color_item_option), getResources().getString(R.string.change_backgroundcolor_item_option), getResources().getString(R.string.change_card_color_title_option)};
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(R.string.edit_item);
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0) {
                            selectColors("TextColor");
                        } else if (i==1){
                            selectColors("BackgroundItemColor");
                        } else{
                            selectColors("CardBackgroundColor");
                        }
                    }
                }).show();
            }
        });
        return v;
    }

    private void startup() {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        logo = BitmapFactory.decodeResource(getResources(),R.drawable.logo);
        logo.compress(Bitmap.CompressFormat.PNG, 100, stream);
        bytes = stream.toByteArray();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_CANCELED){
            logoIcon.setImageResource(R.drawable.logo);
            getActivity().getIntent().putExtra("Logo",R.drawable.logo);
            logoText.setText("UCreate");
        }
        else if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PIC_CROP){
                Bundle extras = data.getExtras();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                logo = extras.getParcelable("data");
                logo.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] bytes = stream.toByteArray();
                getActivity().getIntent().putExtra("Logo",bytes);

                logoIcon.setImageBitmap(extras.<Bitmap>getParcelable("data"));
                if (!globalChange) {
                    showPromtTitle(false);
                }
            }
        }
        if (requestCode==PICK_IMAGE_REQUEST && resultCode== Activity.RESULT_OK){
            logoIcon.setImageURI(data.getData());
            logoImage = data.getData();
            getActivity().getIntent().putExtra("ImageUri",logoImage.toString());
        }
    }

    public void changeTemplate(Context context, int type){
        if (type==3){
            RelativeLayoutItems.removeView(LayoutButtonsTop);
            RelativeLayoutItems.addView(LayoutButtonsTop);
            RelativeLayoutItems.removeView(LayoutButtonsLeft);
        }
        else if (type==4){
            RelativeLayoutItems.removeView(LayoutButtonsLeft);
            RelativeLayoutItems.addView(LayoutButtonsLeft);
            RelativeLayoutItems.removeView(LayoutButtonsTop);
        }
        else{
            RelativeLayoutItems.removeView(LayoutButtonsTop);
            RelativeLayoutItems.removeView(LayoutButtonsLeft);
        }
    }

    class ClickListenerLogo implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            CharSequence options[] = new CharSequence[] {getResources().getString(R.string.change_logo_option),getResources().getString(R.string.change_title_option),getResources().getString(R.string.change_orientation_option),getResources().getString(R.string.change_color_title_option)};
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.edit_logo);
            builder.setItems(options, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (i == 0) {
                        selectImage(true);
                    } else if (i == 1) {
                        showPromtTitle(true);
                    } else if (i==2) {
                        selectOrientation();
                    } else{
                        selectColors("TitleColor");
                    }
                }
            }).show();
        }
    }

    public void selectColors(final String type){
        ColorPickerDialogBuilder
                .with(getActivity())
                .setTitle(getResources().getString(R.string.select_color))
                .initialColor(grayBackgroundColor)
                .wheelType(ColorPickerView.WHEEL_TYPE.CIRCLE)
                .density(8)
                .setPositiveButton(getResources().getString(R.string.accept), new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                        Log.i("Color", selectedColor + "");
                        if (type.equals("TextColor")) {
                            textColor = selectedColor;
                            gridView.setAdapter(new ItemAdapter(getActivity(),R.layout.catalog_2_list_items,items,textColor,backgroundItemColor,cardBackgroundColor));
                            getActivity().getIntent().putExtra("TextColor",selectedColor);
                        } else if (type.equals("BackgroundColor")) {
                            CatalogBackground.setBackgroundColor(selectedColor);
                            RelativeLayoutItems.setBackgroundColor(selectedColor);
                            getActivity().getIntent().putExtra("BackgroundColor",selectedColor);
                            backgroundColor = selectedColor;
                        } else if (type.equals("TitleColor")) {
                            logoText.setTextColor(selectedColor);
                            getActivity().getIntent().putExtra("LogoTextColor",selectedColor);
                            logoTextColor = selectedColor;
                        }
                        else if (type.equals("BackgroundItemColor")){
                            backgroundItemColor = selectedColor;
                            gridView.setAdapter(new ItemAdapter(getActivity(),R.layout.catalog_2_list_items,items,textColor,backgroundItemColor,cardBackgroundColor));
                            getActivity().getIntent().putExtra("BackgroundItemColor",selectedColor);
                        }
                        else if(type.equals("CardBackgroundColor")){
                            cardBackgroundColor = selectedColor;
                            gridView.setAdapter(new ItemAdapter(getActivity(),R.layout.catalog_2_list_items,items,textColor,backgroundItemColor,cardBackgroundColor));
                            getActivity().getIntent().putExtra("CardBackgroundColor",selectedColor);
                        }
                    }
                })
                .setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .build()
                .show();
    }

    public void selectOrientation(){
        CharSequence orientation[] = new CharSequence[] {getResources().getString(R.string.left), getResources().getString(R.string.center), getResources().getString(R.string.right)};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.orientation);
        builder.setItems(orientation, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Resources r = getActivity().getResources();
                int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, r.getDisplayMetrics());
                RelativeLayout.LayoutParams paramsIcon = new RelativeLayout.LayoutParams(px, RelativeLayout.LayoutParams.WRAP_CONTENT);
                RelativeLayout.LayoutParams paramsText = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

                px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, r.getDisplayMetrics());
                paramsIcon.setMargins(px, px, px, px);
                logoText.setTextSize(18);

                if (i == 0) {
                    LogoLayout.removeAllViews();
                    paramsIcon.addRule(RelativeLayout.CENTER_VERTICAL, logoIcon.getId());
                    paramsText.addRule(RelativeLayout.CENTER_VERTICAL, logoText.getId());
                    paramsText.addRule(RelativeLayout.RIGHT_OF, logoIcon.getId());
                } else if (i == 1) {
                    LogoLayout.removeAllViews();
                    px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, r.getDisplayMetrics());
                    paramsIcon = new RelativeLayout.LayoutParams(px, px);
                    paramsIcon.addRule(RelativeLayout.CENTER_HORIZONTAL, logoIcon.getId());
                    paramsText.addRule(RelativeLayout.BELOW, logoIcon.getId());
                    paramsText.addRule(RelativeLayout.CENTER_HORIZONTAL, logoText.getId());
                    logoText.setTextSize(15);
                } else {
                    LogoLayout.removeAllViews();
                    paramsIcon.addRule(RelativeLayout.CENTER_VERTICAL, logoIcon.getId());
                    paramsText.addRule(RelativeLayout.CENTER_VERTICAL, logoText.getId());
                    paramsIcon.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, logoIcon.getId());
                    paramsText.addRule(RelativeLayout.LEFT_OF, logoIcon.getId());
                }

                getActivity().getIntent().putExtra("Orientation",i);

                LogoLayout.addView(logoIcon, paramsIcon);
                LogoLayout.addView(logoText, paramsText);
            }
        }).show();
    }

    public void selectImage(boolean change){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Logo"), PICK_IMAGE_REQUEST);
        globalChange = change;
    }

    public void showPromtTitle(final boolean change){
        View promptTitleView = View.inflate(getActivity(),R.layout.title_dialog,null);
        final EditText logoTextPrompt = (EditText) promptTitleView.findViewById(R.id.etTextLogo);
        logoTextPrompt.setText(R.string.app_name);
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setView(promptTitleView);
        dialog.setTitle(R.string.logo_title);
        dialog.setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                logoText.setText(logoTextPrompt.getText().toString());
                getActivity().getIntent().putExtra("LogoName",logoText.getText().toString());
                if (!change) {
                    selectOrientation();
                }
            }
        });
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                logoText.setText(R.string.app_name);
                getActivity().getIntent().putExtra("LogoName",R.string.app_name);
            }
        });
        dialog.show();
    }
}
