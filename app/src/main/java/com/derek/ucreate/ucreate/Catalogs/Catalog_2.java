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
import android.graphics.drawable.GradientDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.derek.ucreate.ucreate.Models.Item;
import com.derek.ucreate.ucreate.R;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import org.lucasr.twowayview.TwoWayView;
import org.w3c.dom.Text;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Catalog_2 extends Fragment {

    private RelativeLayout  CatalogBackground, LogoLayout;
    private LinearLayout    LayoutItems;
    private boolean         globalChange;
    private ImageView       logoIcon;
    private TextView        logoText;
    private final int       PICK_IMAGE_REQUEST=1;
    private final int       PIC_CROP = 2;
    private int             backgroundColor=-1,textColor=-16777216,logoTextColor=-16777216, backgroundItemColor=-1;
    private TextView        tvItem1;
    private List<Item>      items;
    private GridView        gridView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.catalog_2,container,false);

        logoIcon = (ImageView) v.findViewById(R.id.imageViewCatalog1);
        logoText = (TextView) v.findViewById(R.id.textViewCatalog1);
        logoIcon.setOnClickListener(new ClickListenerLogo());
        logoText.setOnClickListener(new ClickListenerLogo());

        getActivity().getIntent().putExtra("BackgroundColor",backgroundColor);
        getActivity().getIntent().putExtra("TextColor",textColor);
        getActivity().getIntent().putExtra("LogoTextColor",logoTextColor);
        getActivity().getIntent().putExtra("Logo",R.drawable.solologo);
        getActivity().getIntent().putExtra("LogoName",R.string.app_name);
        getActivity().getIntent().putExtra("Orientation",0);

        CatalogBackground = (RelativeLayout) v.findViewById(R.id.CatalogBackground);
        CatalogBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectColors("BackgroundColor");
            }
        });
        LogoLayout = (RelativeLayout) v.findViewById(R.id.RelativeLayoutCatalog1Up);
        LayoutItems = (LinearLayout) v.findViewById(R.id.LinearLayoutItems);

        items = new ArrayList<>();
        for (int i=0; i<10; i++){
            items.add(new Item("asdfasdfasdfasd"+i,20.1, BitmapFactory.decodeResource(getResources(),R.drawable.solologo)));
        }

        gridView = (GridView) v.findViewById(R.id.ListItems);
        gridView.setRotation(270);
        gridView.setNumColumns(2);
        gridView.setAdapter(new ItemAdapter(getActivity(),R.layout.catalog_2_list_items,items,textColor,backgroundItemColor));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, final View view, int i, long l) {
                CharSequence options[] = new CharSequence[] {getResources().getString(R.string.change_color_item_option), getResources().getString(R.string.change_backgroundcolor_item_option)};
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(R.string.edit_item);
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0) {
                            selectColors("TextColor");
                        } else{
                            selectColors("BackgroundItemColor");
                        }
                    }
                }).show();
            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_CANCELED){
            logoIcon.setImageResource(R.drawable.solologo);
            getActivity().getIntent().putExtra("Logo",R.drawable.solologo);
            logoText.setText("UCreate");
        }
        else if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PIC_CROP){
                Bundle extras = data.getExtras();
                getActivity().getIntent().putExtra("Logo",extras.getParcelable("data"));
                logoIcon.setImageBitmap(extras.<Bitmap>getParcelable("data"));
                if (!globalChange) {
                    showPromtTitle(false);
                }
            }
        }
        if (requestCode==PICK_IMAGE_REQUEST && resultCode== Activity.RESULT_OK){
            Uri selectedImage = data.getData();
            String path = getRealPathFromURI(selectedImage);
            if (path != null) {
                try {
                    performCrop(selectedImage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void changeTemplate(Context context, int type){
        Toast.makeText(context,"TEMPLATE SELECTED "+type,Toast.LENGTH_SHORT).show();
        //http://stackoverflow.com/questions/5725745/horizontal-scrolling-grid-view
        if (type == 0){

        }
        else if(type == 1){

        }
        else if(type == 2){

        }
        else if(type == 3){

        }
        else if(type == 4){

        }
        else{

        }
    }

    class ItemAdapter extends ArrayAdapter<Item> {
        List<Item> items;
        Context context;
        int color;
        int backgroundColor;

        public ItemAdapter(Context context, int resource, List<Item> objects, int color, int backgroundColor) {
            super(context, resource, objects);
            this.context = context;
            items = objects;
            this.color = color;
            this.backgroundColor = backgroundColor;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = getActivity().getLayoutInflater().inflate(R.layout.catalog_2_list_items,parent,false);
            Item i = items.get(position);

            ImageView imageView = (ImageView) v.findViewById(R.id.imageViewItem);
            TextView textView = (TextView) v.findViewById(R.id.textViewItem);

            imageView.setImageBitmap(i.getImage());
            textView.setText(i.getName() + "\nQ." + i.getPrice());
            textView.setTextColor(color);
            v.setBackgroundColor(backgroundItemColor);

            //http://stackoverflow.com/questions/5725745/horizontal-scrolling-grid-view
            textView.setRotation(90);
            imageView.setRotation(90);

            return v;
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
    public String getRealPathFromURI(Uri contentUri){
        String [] proj      = {MediaStore.Images.Media.DATA};
        Cursor cursor       = getActivity().managedQuery( contentUri, proj, null, null,null);
        if (cursor == null) return null;
        int column_index    = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    private void performCrop(Uri mImageCaptureUri){
        try {
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setType("image/*");

            List<ResolveInfo> list = getActivity().getPackageManager().queryIntentActivities(intent, 0);
            int size = list.size();

            if (size >= 0) {
                intent.setData(mImageCaptureUri);
                intent.putExtra("crop", "true");
                intent.putExtra("aspectX", 1);
                intent.putExtra("aspectY", 1);
                intent.putExtra("outputX", 65);
                intent.putExtra("outputY", 65);
                intent.putExtra("scale", true);
                intent.putExtra("return-data", true);

                Intent i = new Intent(intent);
                ResolveInfo res = list.get(0);
                i.setComponent( new ComponentName(res.activityInfo.packageName, res.activityInfo.name));

                startActivityForResult(i, PIC_CROP);
            }
        }
        catch(ActivityNotFoundException anfe){
            String errorMessage = "Whoops - your device doesn't support the crop action!";
            Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    public void selectColors(final String type){
        ColorPickerDialogBuilder
                .with(getActivity())
                .showColorPreview(true)
                .setTitle(getResources().getString(R.string.select_color))
                .initialColor(getResources().getColor(R.color.green))
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(10)
                .setPositiveButton(getResources().getString(R.string.accept), new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                        Log.i("Color", selectedColor + "");
                        if (type.equals("TextColor")) {
                            textColor = selectedColor;
                            gridView.setAdapter(new ItemAdapter(getActivity(),R.layout.catalog_2_list_items,items,textColor,backgroundItemColor));
                            getActivity().getIntent().putExtra("TextColor",selectedColor);
                        } else if (type.equals("BackgroundColor")) {
                            CatalogBackground.setBackgroundColor(selectedColor);
                            getActivity().getIntent().putExtra("BackgroundColor",selectedColor);
                            backgroundColor = selectedColor;
                        } else if (type.equals("TitleColor")) {
                            logoText.setTextColor(selectedColor);
                            getActivity().getIntent().putExtra("LogoTextColor",selectedColor);
                            logoTextColor = selectedColor;
                        }
                        else if (type.equals("BackgroundItemColor")){
                            backgroundItemColor = selectedColor;
                            gridView.setAdapter(new ItemAdapter(getActivity(), R.layout.catalog_2_list_items, items, textColor, backgroundItemColor));
                            getActivity().getIntent().putExtra("BackgroundItemColor",backgroundItemColor);
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
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
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
