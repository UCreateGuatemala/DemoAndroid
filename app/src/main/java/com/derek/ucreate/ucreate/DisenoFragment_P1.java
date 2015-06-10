package com.derek.ucreate.ucreate;

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

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by Boris on 09/06/2015.
 */
public class DisenoFragment_P1 extends Fragment {

    private RelativeLayout  CatalogBackground;
    private RelativeLayout  LogoLayout;
    private Button          btnBackground, btnLogo, btnText;
    private boolean         globalChange;
    private ImageView       logoIcon;
    private TextView        logoText;
    private final int       PICK_IMAGE_REQUEST=1;
    private final int       PIC_CROP = 2;
    private String          backgroundColor="white";
    private String          textColor="black";
    private TextView        tvItem1, tvItem2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.catalog_1,container,false);

        //Se agregarn los valores para los siguientes fragments (especialmente el 3ero)
        getActivity().getIntent().putExtra("backgroundColor",backgroundColor);
        getActivity().getIntent().putExtra("textColor",textColor);

        logoIcon = (ImageView) v.findViewById(R.id.imageViewCatalog1);
        logoText = (TextView) v.findViewById(R.id.textViewCatalog1);
        logoIcon.setOnClickListener(new ClickListenerLogo());
        logoText.setOnClickListener(new ClickListenerLogo());

        btnLogo = (Button) v.findViewById(R.id.buttonCatalogNewLogo);
        btnBackground = (Button) v.findViewById(R.id.buttonCatalogBackground);
        btnText = (Button) v.findViewById(R.id.buttonCatalogTextColor);

        CatalogBackground = (RelativeLayout) v.findViewById(R.id.layoutCatalog_1);
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
                                logoText.setTextColor(getResources().getColor(R.color.orange));
                                textColor = "orange";
                                return true;
                            case R.id.red:
                                tvItem1.setTextColor(getResources().getColor(R.color.red));
                                tvItem2.setTextColor(getResources().getColor(R.color.red));
                                logoText.setTextColor(getResources().getColor(R.color.red));
                                textColor = "red";
                                return true;
                            case R.id.yellow:
                                tvItem1.setTextColor(getResources().getColor(R.color.yellow));
                                tvItem2.setTextColor(getResources().getColor(R.color.yellow));
                                logoText.setTextColor(getResources().getColor(R.color.yellow));
                                textColor = "yellow";
                                return true;
                            case R.id.green:
                                tvItem1.setTextColor(getResources().getColor(R.color.green));
                                tvItem2.setTextColor(getResources().getColor(R.color.green));
                                logoText.setTextColor(getResources().getColor(R.color.green));
                                textColor = "green";
                                return true;
                            case R.id.blue:
                                tvItem1.setTextColor(getResources().getColor(R.color.blue));
                                tvItem2.setTextColor(getResources().getColor(R.color.blue));
                                logoText.setTextColor(getResources().getColor(R.color.blue));
                                textColor = "blue";
                                return true;
                            case R.id.purple:
                                tvItem1.setTextColor(getResources().getColor(R.color.purple));
                                tvItem2.setTextColor(getResources().getColor(R.color.purple));
                                logoText.setTextColor(getResources().getColor(R.color.purple));
                                textColor = "purple";
                                return true;
                            case R.id.white:
                                tvItem1.setTextColor(getResources().getColor(R.color.white));
                                tvItem2.setTextColor(getResources().getColor(R.color.white));
                                logoText.setTextColor(getResources().getColor(R.color.white));
                                textColor = "white";
                                return true;
                            case R.id.black:
                                tvItem1.setTextColor(getResources().getColor(R.color.black));
                                tvItem2.setTextColor(getResources().getColor(R.color.black));
                                logoText.setTextColor(getResources().getColor(R.color.black));
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
            CharSequence options[] = new CharSequence[] {getResources().getString(R.string.change_logo_option),getResources().getString(R.string.change_title_option),getResources().getString(R.string.change_orientation_option)};
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.change_options);
            builder.setItems(options, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (i == 0) {
                        selectImage(true);
                    } else if (i == 1) {
                        showPromtTitle(true);
                    } else {
                        selectOrientation();
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

    public void selectOrientation(){
        CharSequence orientation[] = new CharSequence[] {getResources().getString(R.string.left), getResources().getString(R.string.center), getResources().getString(R.string.right)};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.orientation);
        builder.setItems(orientation, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                RelativeLayout.LayoutParams paramsIcon = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                RelativeLayout.LayoutParams paramsText = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                if (i == 0) {
                    LogoLayout.removeAllViews();
                    paramsIcon.addRule(RelativeLayout.CENTER_VERTICAL, logoIcon.getId());
                    paramsText.addRule(RelativeLayout.CENTER_VERTICAL, logoText.getId());
                    paramsText.addRule(RelativeLayout.RIGHT_OF, logoIcon.getId());
                } else if (i == 1) {
                    LogoLayout.removeAllViews();
                    paramsIcon.addRule(RelativeLayout.CENTER_IN_PARENT, logoIcon.getId());
                    paramsText.addRule(RelativeLayout.BELOW, logoIcon.getId());
                    paramsText.addRule(RelativeLayout.CENTER_IN_PARENT, logoText.getId());
                } else {
                    LogoLayout.removeAllViews();
                    paramsIcon.addRule(RelativeLayout.CENTER_VERTICAL, logoIcon.getId());
                    paramsText.addRule(RelativeLayout.CENTER_VERTICAL, logoText.getId());
                    paramsIcon.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, logoIcon.getId());
                    paramsText.addRule(RelativeLayout.LEFT_OF, logoIcon.getId());
                }

                Resources r = getActivity().getResources();
                int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, r.getDisplayMetrics());
                paramsIcon.setMargins(px, px, px, px);

                LogoLayout.addView(logoIcon, paramsIcon);
                LogoLayout.addView(logoText, paramsText);
                btnLogo.setEnabled(false);
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
        logoTextPrompt.setHint(R.string.enter_logo_title);
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setView(promptTitleView);
        dialog.setTitle(R.string.logo_title);
        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                logoText.setText(logoTextPrompt.getText().toString());
                if (!change) {
                    selectOrientation();
                }
            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                logoText.setText("UCreate");
                btnLogo.setEnabled(false);
            }
        });
        dialog.show();
    }
}
