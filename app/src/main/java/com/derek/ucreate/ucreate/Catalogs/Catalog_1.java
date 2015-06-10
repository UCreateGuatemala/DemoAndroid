package com.derek.ucreate.ucreate.Catalogs;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.derek.ucreate.ucreate.R;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import java.lang.reflect.Field;
import java.util.List;

public class Catalog_1 extends Fragment {

    private RelativeLayout  CatalogBackground;
    private RelativeLayout  LogoLayout;
    private Button          btnBackground, btnLogo, btnText;
    private boolean         globalChange;
    private ImageView       logoIcon;
    private TextView        logoText;
    private final int       PICK_IMAGE_REQUEST=1;
    private final int       PIC_CROP = 2;
    private String          backgroundColor="white",textColor="black",logoTextColor="black";
    private TextView        tvItem1, tvItem2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.catalog_1,container,false);

        //Se agregarn los valores para los siguientes fragments (especialmente el 3ero)
        getActivity().getIntent().putExtra("backgroundColor",backgroundColor);
        getActivity().getIntent().putExtra("textColor",textColor);
        getActivity().getIntent().putExtra("logoTextColor",logoTextColor);

        logoIcon = (ImageView) v.findViewById(R.id.imageViewCatalog1);
        logoText = (TextView) v.findViewById(R.id.textViewCatalog1);
        logoIcon.setOnClickListener(new ClickListenerLogo());
        logoText.setOnClickListener(new ClickListenerLogo());

        btnLogo = (Button) v.findViewById(R.id.buttonCatalogNewLogo);
        btnBackground = (Button) v.findViewById(R.id.buttonCatalogBackground);
        btnText = (Button) v.findViewById(R.id.buttonCatalogTextColor);

        CatalogBackground = (RelativeLayout) v.findViewById(R.id.CatalogBackground);
        LogoLayout = (RelativeLayout) v.findViewById(R.id.RelativeLayoutCatalog1Up);

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
                                showPromtTitle(false);
                                return true;
                            case R.id.OtherLogo:
                                selectImage(false);
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popup.show();
            }
        });

        btnBackground.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectColors("BackgroundColor");
            }
        });
        btnText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectColors("TextColor");
            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_CANCELED){
            logoIcon.setImageResource(R.mipmap.ic_launcher);
            logoText.setText("UCreate");
            btnLogo.setEnabled(false);
        }
        else if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PIC_CROP){
                Bundle extras = data.getExtras();
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
                        if (type.equals("TextColor")) {
                            tvItem1.setTextColor(selectedColor);
                            tvItem2.setTextColor(selectedColor);
                            textColor = "" + selectedColor;
                        } else if (type.equals("BackgroundColor")) {
                            CatalogBackground.setBackgroundColor(selectedColor);
                            backgroundColor = selectedColor + "";
                        } else if (type.equals("TitleColor")) {
                            logoText.setTextColor(selectedColor);
                            logoTextColor = ""+selectedColor;
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
                RelativeLayout.LayoutParams paramsIcon = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                RelativeLayout.LayoutParams paramsText = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

                Resources r = getActivity().getResources();
                int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, r.getDisplayMetrics());
                paramsIcon.setMargins(px, px, px, px);
                logoText.setTextSize(18);

                if (i == 0) {
                    LogoLayout.removeAllViews();
                    paramsIcon.addRule(RelativeLayout.CENTER_VERTICAL, logoIcon.getId());
                    paramsText.addRule(RelativeLayout.CENTER_VERTICAL, logoText.getId());
                    paramsText.addRule(RelativeLayout.RIGHT_OF, logoIcon.getId());
                } else if (i == 1) {
                    LogoLayout.removeAllViews();
                    paramsIcon.addRule(RelativeLayout.CENTER_HORIZONTAL, logoIcon.getId());
                    paramsText.addRule(RelativeLayout.BELOW, logoIcon.getId());
                    paramsText.addRule(RelativeLayout.CENTER_HORIZONTAL, logoText.getId());
                    logoText.setTextSize(15);
                    int newpx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, r.getDisplayMetrics());
                    paramsIcon.setMargins(px, newpx, px, px);
                } else {
                    LogoLayout.removeAllViews();
                    paramsIcon.addRule(RelativeLayout.CENTER_VERTICAL, logoIcon.getId());
                    paramsText.addRule(RelativeLayout.CENTER_VERTICAL, logoText.getId());
                    paramsIcon.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, logoIcon.getId());
                    paramsText.addRule(RelativeLayout.LEFT_OF, logoIcon.getId());
                }

                LogoLayout.addView(logoIcon, paramsIcon);
                LogoLayout.addView(logoText, paramsText);
                btnLogo.setText(R.string.edit_logo);
                btnLogo.setOnClickListener(new ClickListenerLogo());
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
                if (!change) {
                    selectOrientation();
                }
            }
        });
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                logoText.setText(R.string.app_name);
                btnLogo.setText(R.string.edit_logo);
                btnLogo.setOnClickListener(new ClickListenerLogo());
            }
        });
        dialog.show();
    }
}
