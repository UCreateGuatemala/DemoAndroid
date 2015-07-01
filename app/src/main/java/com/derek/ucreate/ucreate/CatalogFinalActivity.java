package com.derek.ucreate.ucreate;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.derek.ucreate.ucreate.Adapters.ItemAdapter;
import com.derek.ucreate.ucreate.Models.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class CatalogFinalActivity extends ActionBarActivity {

    private LinearLayout    layoutButtonsTop, layoutButtonsLeft;
    private RelativeLayout  layoutLogo, layoutItem;
    private int             templateType, backgroundColor, textColor, logoTextColor, orientation, backgroundItemColor, backgroundCardColor;
    private final int       numRows = 2,grayBackgroundColor=-1, cardBackgroundColor = -1;
    private String          logoName, imageUri;
    private Bitmap          logo;
    private List<Item>      items, items1, items2, items3, items4, items5;
    private GridView        gridView, gridView1, gridView2, gridView3, gridView4, gridView5;
    private Uri             logoUri;
    private Button          btnGroup1, btnGroup2, btnGroup3, btnGroup4, btnGroup5;
    private boolean         dialogButton, description, ratingStars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        Bundle b = getIntent().getExtras();
        getExtrasBundle(b);
        setTemplate();
        layoutLogo = (RelativeLayout) findViewById(R.id.RelativeLayoutCatalog1Up);
        layoutItem = (RelativeLayout) findViewById(R.id.RelativeLayoutItems);
        layoutButtonsTop = (LinearLayout) findViewById(R.id.LayoutButtonsTop);
        layoutButtonsLeft = (LinearLayout) findViewById(R.id.LayoutButtonsLeft);
        setOrientation();
        setBackgroundColor();
        setItemColors();
    }

    private void setItemColors() {
        if (templateType==5){
            gridView1.setAdapter(new ItemAdapter(this, R.layout.catalog_2_list_items, items1, textColor, backgroundItemColor, backgroundCardColor));
            gridView2.setAdapter(new ItemAdapter(this, R.layout.catalog_2_list_items, items2, textColor, backgroundItemColor, backgroundCardColor));
            gridView3.setAdapter(new ItemAdapter(this, R.layout.catalog_2_list_items, items3, textColor, backgroundItemColor, backgroundCardColor));
            gridView4.setAdapter(new ItemAdapter(this, R.layout.catalog_2_list_items, items4, textColor, backgroundItemColor, backgroundCardColor));
            gridView5.setAdapter(new ItemAdapter(this, R.layout.catalog_2_list_items, items5, textColor, backgroundItemColor, backgroundCardColor));
        }else{
            gridView.setAdapter(new ItemAdapter(this, R.layout.catalog_2_list_items, items, textColor, backgroundItemColor, backgroundCardColor));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_catalog_final, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intentContactUs;
            intentContactUs = new Intent(CatalogFinalActivity.this, ContactUsEndActivity.class);
            startActivity(intentContactUs);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getExtrasBundle(Bundle b){
        templateType = b.getInt("Template");
        backgroundColor = b.getInt("BackgroundColor");
        backgroundItemColor = b.getInt("BackgroundItemColor");
        backgroundCardColor = b.getInt("CardBackgroundColor");
        textColor = b.getInt("TextColor");
        logoTextColor = b.getInt("LogoTextColor");
        orientation = b.getInt("Orientation");
        logoName = b.getString("LogoName");
        byte[] bytes = b.getByteArray("Logo");
        logo = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        imageUri = b.getString("ImageUri");
        dialogButton = b.getBoolean("buyButton");
        description = b.getBoolean("Description");
        ratingStars = b.getBoolean("ratingStars");
    }

    public void setTemplate(){
        if(templateType == 1){
            setContentView(R.layout.template_1);
            gridView = (GridView) findViewById(R.id.ListItems);

            items = new ArrayList<>();
            Random r = new Random();
            for (int i=0; i<5; i++){
                items.add(new Item("Item "+(1+i),(i+r.nextInt(10))* r.nextInt(5), BitmapFactory.decodeResource(getResources(),R.drawable.item_1)));
                items.add(new Item("Item "+(6+i),(i+r.nextInt(10))* r.nextInt(5), BitmapFactory.decodeResource(getResources(),R.drawable.item_2)));
                items.add(new Item("Item "+(11+i),(i+r.nextInt(10))* r.nextInt(5), BitmapFactory.decodeResource(getResources(),R.drawable.item_3)));
                items.add(new Item("Item "+(16+i),(i+r.nextInt(10))* r.nextInt(5), BitmapFactory.decodeResource(getResources(),R.drawable.item_4)));
                items.add(new Item("Item "+(21+i),(i+r.nextInt(10))* r.nextInt(5), BitmapFactory.decodeResource(getResources(),R.drawable.item_5)));
            }

            gridView.setNumColumns(13);
            gridView.setAdapter(new ItemAdapter(this, R.layout.catalog_2_list_items, items, textColor, grayBackgroundColor, cardBackgroundColor));
            onClickItem();
        }
        else if (templateType == 2){
            setContentView(R.layout.template_2);
            gridView = (GridView) findViewById(R.id.ListItems);

            items = new ArrayList<>();
            Random r = new Random();
            for (int i=0; i<5; i++){
                items.add(new Item("Item "+(1+i),(i+r.nextInt(10))* r.nextInt(5), BitmapFactory.decodeResource(getResources(),R.drawable.item_1)));
                items.add(new Item("Item "+(6+i),(i+r.nextInt(10))* r.nextInt(5), BitmapFactory.decodeResource(getResources(),R.drawable.item_2)));
                items.add(new Item("Item "+(11+i),(i+r.nextInt(10))* r.nextInt(5), BitmapFactory.decodeResource(getResources(),R.drawable.item_3)));
                items.add(new Item("Item "+(16+i),(i+r.nextInt(10))* r.nextInt(5), BitmapFactory.decodeResource(getResources(),R.drawable.item_4)));
                items.add(new Item("Item "+(21+i),(i+r.nextInt(10))* r.nextInt(5), BitmapFactory.decodeResource(getResources(),R.drawable.item_5)));
            }

            gridView.setNumColumns(2);
            gridView.setAdapter(new ItemAdapter(this, R.layout.catalog_2_list_items, items, textColor, grayBackgroundColor, cardBackgroundColor));
            onClickItem();
        }
        else if (templateType==3){
            setContentView(R.layout.template_3);
            gridView = (GridView) findViewById(R.id.ListItems);
            items = new ArrayList<>();
            Random r = new Random();
            for (int i=0; i<5; i++){
                items.add(new Item("Item "+(1+i),(i+r.nextInt(10))* r.nextInt(5), BitmapFactory.decodeResource(getResources(),R.drawable.item_1)));
            }

            gridView.setNumColumns(2);
            gridView.setAdapter(new ItemAdapter(this, R.layout.catalog_2_list_items, items, textColor, grayBackgroundColor, cardBackgroundColor));
            onClickItem();

            btnGroup1 = (Button) findViewById(R.id.ButtonGroup1);
            btnGroup2 = (Button) findViewById(R.id.ButtonGroup2);
            btnGroup3 = (Button) findViewById(R.id.ButtonGroup3);
            btnGroup4 = (Button) findViewById(R.id.ButtonGroup4);
            btnGroup5 = (Button) findViewById(R.id.ButtonGroup5);
        }
        else if (templateType==4){
            setContentView(R.layout.template_4);
            gridView = (GridView) findViewById(R.id.ListItems);
            items = new ArrayList<>();
            Random r = new Random();
            for (int i=0; i<5; i++){
                items.add(new Item("Item "+(1+i),(i+r.nextInt(10))* r.nextInt(5), BitmapFactory.decodeResource(getResources(),R.drawable.item_1)));
            }

            gridView.setNumColumns(2);
            gridView.setAdapter(new ItemAdapter(this, R.layout.catalog_2_list_items, items, textColor, grayBackgroundColor, cardBackgroundColor));
            onClickItem();

            btnGroup1 = (Button) findViewById(R.id.ButtonGroup1);
            btnGroup2 = (Button) findViewById(R.id.ButtonGroup2);
            btnGroup3 = (Button) findViewById(R.id.ButtonGroup3);
            btnGroup4 = (Button) findViewById(R.id.ButtonGroup4);
            btnGroup5 = (Button) findViewById(R.id.ButtonGroup5);
        }
        else{
            setContentView(R.layout.template_5);
            gridView1 = (GridView) findViewById(R.id.ListItems1);
            items1 = new ArrayList<>();
            Random r = new Random();
            for (int i=0; i<5; i++){
                items1.add(new Item("Item "+(1+i),(i+r.nextInt(10))* r.nextInt(5), BitmapFactory.decodeResource(getResources(),R.drawable.item_1)));
            }
            gridView1.setNumColumns(5);
            gridView1.setAdapter(new ItemAdapter(this, R.layout.catalog_2_list_items, items1, textColor, grayBackgroundColor, cardBackgroundColor));
            onClickItem1();

            gridView2 = (GridView) findViewById(R.id.ListItems2);
            items2 = new ArrayList<>();
            Random r2 = new Random();
            for (int i=0; i<5; i++){
                items2.add(new Item("Item "+(1+i),(i+r2.nextInt(10))* r2.nextInt(5), BitmapFactory.decodeResource(getResources(),R.drawable.item_2)));
            }
            gridView2.setNumColumns(5);
            gridView2.setAdapter(new ItemAdapter(this, R.layout.catalog_2_list_items, items2, textColor, grayBackgroundColor, cardBackgroundColor));
            onClickItem2();

            gridView3 = (GridView) findViewById(R.id.ListItems3);
            items3 = new ArrayList<>();
            Random r3 = new Random();
            for (int i=0; i<5; i++){
                items3.add(new Item("Item "+(1+i),(i+r3.nextInt(10))* r3.nextInt(5), BitmapFactory.decodeResource(getResources(),R.drawable.item_3)));
            }
            gridView3.setNumColumns(5);
            gridView3.setAdapter(new ItemAdapter(this, R.layout.catalog_2_list_items, items3, textColor, grayBackgroundColor, cardBackgroundColor));
            onClickItem3();

            gridView4 = (GridView) findViewById(R.id.ListItems4);
            items4 = new ArrayList<>();
            Random r4 = new Random();
            for (int i=0; i<5; i++){
                items4.add(new Item("Item "+(1+i),(i+r4.nextInt(10))* r4.nextInt(5), BitmapFactory.decodeResource(getResources(),R.drawable.item_4)));
            }
            gridView4.setNumColumns(5);
            gridView4.setAdapter(new ItemAdapter(this, R.layout.catalog_2_list_items, items4, textColor, grayBackgroundColor, cardBackgroundColor));
            onClickItem4();

            gridView5 = (GridView) findViewById(R.id.ListItems5);
            items5 = new ArrayList<>();
            Random r5 = new Random();
            for (int i=0; i<5; i++){
                items5.add(new Item("Item "+(1+i),(i+r5.nextInt(10))* r5.nextInt(5), BitmapFactory.decodeResource(getResources(),R.drawable.item_5)));
            }
            gridView5.setNumColumns(5);
            gridView5.setAdapter(new ItemAdapter(this, R.layout.catalog_2_list_items, items5, textColor, grayBackgroundColor, cardBackgroundColor));
            onClickItem5();
        }
    }

    public void setOrientation(){
        TextView logoText = (TextView) layoutLogo.findViewById(R.id.textViewCatalog1);
        ImageView logoIcon = (ImageView) layoutLogo.findViewById(R.id.imageViewCatalog1);

        Resources r = getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, r.getDisplayMetrics());
        RelativeLayout.LayoutParams paramsIcon = new RelativeLayout.LayoutParams(px, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams paramsText = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, r.getDisplayMetrics());
        paramsIcon.setMargins(px, px, px, px);
        if (imageUri == null){
            logoIcon.setImageBitmap(logo);
        }else{
            logoUri = Uri.parse(imageUri);
            logoIcon.setImageURI(logoUri);
        }
        logoText.setText(logoName);
        logoText.setTextSize(18);
        logoText.setTextColor(logoTextColor);

        if (orientation == 0) {
            layoutLogo.removeAllViews();
            paramsIcon.addRule(RelativeLayout.CENTER_VERTICAL, logoIcon.getId());
            paramsText.addRule(RelativeLayout.CENTER_VERTICAL, logoText.getId());
            paramsText.addRule(RelativeLayout.RIGHT_OF, logoIcon.getId());
        } else if (orientation == 1) {
            layoutLogo.removeAllViews();
            px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, r.getDisplayMetrics());
            paramsIcon = new RelativeLayout.LayoutParams(px, px);
            paramsIcon.addRule(RelativeLayout.CENTER_HORIZONTAL, logoIcon.getId());
            paramsText.addRule(RelativeLayout.BELOW, logoIcon.getId());
            paramsText.addRule(RelativeLayout.CENTER_HORIZONTAL, logoText.getId());
            logoText.setTextSize(15);
        } else {
            layoutLogo.removeAllViews();
            paramsIcon.addRule(RelativeLayout.CENTER_VERTICAL, logoIcon.getId());
            paramsText.addRule(RelativeLayout.CENTER_VERTICAL, logoText.getId());
            paramsIcon.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, logoIcon.getId());
            paramsText.addRule(RelativeLayout.LEFT_OF, logoIcon.getId());
        }

        layoutLogo.addView(logoIcon, paramsIcon);
        layoutLogo.addView(logoText, paramsText);
    }
    public void setBackgroundColor(){
        layoutLogo.setBackgroundColor(backgroundColor);
        layoutItem.setBackgroundColor(backgroundColor);
    }

    public void onClickItem(){
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Create custom dialog object
                final Dialog dialog = new Dialog(CatalogFinalActivity.this);
                Item valor = items.get(position);
                // Include dialog.xml file
                dialog.setContentView(R.layout.popup_item);
                // Set dialog title
                dialog.setTitle("" + valor.getName());
                TextView text = (TextView) dialog.findViewById(R.id.textViewDialog);
                text.setText("Precio: \n Q. " + valor.getPrice());
                ImageView image = (ImageView) dialog.findViewById(R.id.imageViewDialog);
                image.setImageBitmap(valor.getImage());
                Button dialogBuyButton = (Button) dialog.findViewById(R.id.buttonBuy);
                TextView descriptionText = (TextView) dialog.findViewById(R.id.textViewDescription);
                RatingBar ratingBar = (RatingBar) dialog.findViewById(R.id.ratingBar);
                if (dialogButton){
                    dialogBuyButton.setVisibility(View.VISIBLE);
                }else{
                    dialogBuyButton.setVisibility(View.GONE);
                }
                if (description){
                    descriptionText.setText("Descripci\u00F3n: Este es un producto de demostraci\u00F3n");
                }else{
                    descriptionText.setText("");
                }
                if (ratingStars){
                    ratingBar.setVisibility(View.VISIBLE);
                }else{
                    ratingBar.setVisibility(View.GONE);
                }
                dialog.show();
            }
        });
    }

    public void onClickItem1(){
        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Create custom dialog object
                final Dialog dialog = new Dialog(CatalogFinalActivity.this);
                Item valor = items1.get(position);
                // Include dialog.xml file
                dialog.setContentView(R.layout.popup_item);
                // Set dialog title
                dialog.setTitle(""+valor.getName());
                TextView text = (TextView) dialog.findViewById(R.id.textViewDialog);
                text.setText("Precio: \n Q. "+valor.getPrice());
                ImageView image = (ImageView) dialog.findViewById(R.id.imageViewDialog);
                image.setImageBitmap(valor.getImage());
                Button dialogBuyButton = (Button) dialog.findViewById(R.id.buttonBuy);
                TextView descriptionText = (TextView) dialog.findViewById(R.id.textViewDescription);
                RatingBar ratingBar = (RatingBar) dialog.findViewById(R.id.ratingBar);
                if (dialogButton){
                    dialogBuyButton.setVisibility(View.VISIBLE);
                }else{
                    dialogBuyButton.setVisibility(View.GONE);
                }
                if (description){
                    descriptionText.setText("Descripci\u00F3n: Este es un producto de demostraci\u00F3n");
                }else{
                    descriptionText.setText("");
                }
                if (ratingStars){
                    ratingBar.setVisibility(View.VISIBLE);
                }else{
                    ratingBar.setVisibility(View.GONE);
                }
                dialog.show();
            }
        });
    }

    public void onClickItem2(){
        gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Create custom dialog object
                final Dialog dialog = new Dialog(CatalogFinalActivity.this);
                Item valor = items2.get(position);
                // Include dialog.xml file
                dialog.setContentView(R.layout.popup_item);
                // Set dialog title
                dialog.setTitle(""+valor.getName());
                TextView text = (TextView) dialog.findViewById(R.id.textViewDialog);
                text.setText("Precio: \n Q. "+valor.getPrice());
                ImageView image = (ImageView) dialog.findViewById(R.id.imageViewDialog);
                image.setImageBitmap(valor.getImage());
                Button dialogBuyButton = (Button) dialog.findViewById(R.id.buttonBuy);
                TextView descriptionText = (TextView) dialog.findViewById(R.id.textViewDescription);
                RatingBar ratingBar = (RatingBar) dialog.findViewById(R.id.ratingBar);
                if (dialogButton){
                    dialogBuyButton.setVisibility(View.VISIBLE);
                }else{
                    dialogBuyButton.setVisibility(View.GONE);
                }
                if (description){
                    descriptionText.setText("Descripci\u00F3n: Este es un producto de demostraci\u00F3n");
                }else{
                    descriptionText.setText("");
                }
                if (ratingStars){
                    ratingBar.setVisibility(View.VISIBLE);
                }else{
                    ratingBar.setVisibility(View.GONE);
                }
                dialog.show();
            }
        });
    }

    public void onClickItem3(){
        gridView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Create custom dialog object
                final Dialog dialog = new Dialog(CatalogFinalActivity.this);
                Item valor = items3.get(position);
                // Include dialog.xml file
                dialog.setContentView(R.layout.popup_item);
                // Set dialog title
                dialog.setTitle(""+valor.getName());
                TextView text = (TextView) dialog.findViewById(R.id.textViewDialog);
                text.setText("Precio: \n Q. "+valor.getPrice());
                ImageView image = (ImageView) dialog.findViewById(R.id.imageViewDialog);
                image.setImageBitmap(valor.getImage());
                Button dialogBuyButton = (Button) dialog.findViewById(R.id.buttonBuy);
                TextView descriptionText = (TextView) dialog.findViewById(R.id.textViewDescription);
                RatingBar ratingBar = (RatingBar) dialog.findViewById(R.id.ratingBar);
                if (dialogButton){
                    dialogBuyButton.setVisibility(View.VISIBLE);
                }else{
                    dialogBuyButton.setVisibility(View.GONE);
                }
                if (description){
                    descriptionText.setText("Descripci\u00F3n: Este es un producto de demostraci\u00F3n");
                }else{
                    descriptionText.setText("");
                }
                if (ratingStars){
                    ratingBar.setVisibility(View.VISIBLE);
                }else{
                    ratingBar.setVisibility(View.GONE);
                }
                dialog.show();
            }
        });
    }

    public void onClickItem4(){
        gridView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Create custom dialog object
                final Dialog dialog = new Dialog(CatalogFinalActivity.this);
                Item valor = items4.get(position);
                // Include dialog.xml file
                dialog.setContentView(R.layout.popup_item);
                // Set dialog title
                dialog.setTitle(""+valor.getName());
                TextView text = (TextView) dialog.findViewById(R.id.textViewDialog);
                text.setText("Precio: \n Q. "+valor.getPrice());
                ImageView image = (ImageView) dialog.findViewById(R.id.imageViewDialog);
                image.setImageBitmap(valor.getImage());
                Button dialogBuyButton = (Button) dialog.findViewById(R.id.buttonBuy);
                TextView descriptionText = (TextView) dialog.findViewById(R.id.textViewDescription);
                RatingBar ratingBar = (RatingBar) dialog.findViewById(R.id.ratingBar);
                if (dialogButton){
                    dialogBuyButton.setVisibility(View.VISIBLE);
                }else{
                    dialogBuyButton.setVisibility(View.GONE);
                }
                if (description){
                    descriptionText.setText("Descripci\u00F3n: Este es un producto de demostraci\u00F3n");
                }else{
                    descriptionText.setText("");
                }
                if (ratingStars){
                    ratingBar.setVisibility(View.VISIBLE);
                }else{
                    ratingBar.setVisibility(View.GONE);
                }
                dialog.show();
            }
        });
    }

    public void onClickItem5(){
        gridView5.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Create custom dialog object
                final Dialog dialog = new Dialog(CatalogFinalActivity.this);
                Item valor = items5.get(position);
                // Include dialog.xml file
                dialog.setContentView(R.layout.popup_item);
                // Set dialog title
                dialog.setTitle(""+valor.getName());
                TextView text = (TextView) dialog.findViewById(R.id.textViewDialog);
                text.setText("Precio: \n Q. "+valor.getPrice());
                ImageView image = (ImageView) dialog.findViewById(R.id.imageViewDialog);
                image.setImageBitmap(valor.getImage());
                Button dialogBuyButton = (Button) dialog.findViewById(R.id.buttonBuy);
                TextView descriptionText = (TextView) dialog.findViewById(R.id.textViewDescription);
                RatingBar ratingBar = (RatingBar) dialog.findViewById(R.id.ratingBar);
                if (dialogButton){
                    dialogBuyButton.setVisibility(View.VISIBLE);
                }else{
                    dialogBuyButton.setVisibility(View.GONE);
                }
                if (description){
                    descriptionText.setText("Descripci\u00F3n: Este es un producto de demostraci\u00F3n");
                }else{
                    descriptionText.setText("");
                }
                if (ratingStars){
                    ratingBar.setVisibility(View.VISIBLE);
                }else{
                    ratingBar.setVisibility(View.GONE);
                }
                dialog.show();
            }
        });
    }

    public void GroupButtonClicked(View view) {
        switch (view.getId()){
            case R.id.ButtonGroup1:
                items = new ArrayList<>();
                Random r1 = new Random();
                for (int i=0; i<5; i++){
                    items.add(new Item("Item "+(1+i),(i+r1.nextInt(10))* r1.nextInt(5), BitmapFactory.decodeResource(getResources(),R.drawable.item_1)));
                }

                gridView.setNumColumns(2);
                gridView.setAdapter(new ItemAdapter(this, R.layout.catalog_2_list_items, items, textColor, grayBackgroundColor, cardBackgroundColor));
                setItemColors();
                break;
            case R.id.ButtonGroup2:
                items = new ArrayList<>();
                Random r2 = new Random();
                for (int i=0; i<5; i++){
                    items.add(new Item("Item "+(1+i),(i+r2.nextInt(10))* r2.nextInt(5), BitmapFactory.decodeResource(getResources(),R.drawable.item_2)));
                }

                gridView.setNumColumns(2);
                gridView.setAdapter(new ItemAdapter(this, R.layout.catalog_2_list_items, items, textColor, grayBackgroundColor, cardBackgroundColor));
                setItemColors();
                break;
            case R.id.ButtonGroup3:
                items = new ArrayList<>();
                Random r3 = new Random();
                for (int i=0; i<5; i++){
                    items.add(new Item("Item "+(1+i),(i+r3.nextInt(10))* r3.nextInt(5), BitmapFactory.decodeResource(getResources(),R.drawable.item_3)));
                }

                gridView.setNumColumns(2);
                gridView.setAdapter(new ItemAdapter(this, R.layout.catalog_2_list_items, items, textColor, grayBackgroundColor, cardBackgroundColor));
                setItemColors();
                break;
            case R.id.ButtonGroup4:
                items = new ArrayList<>();
                Random r4 = new Random();
                for (int i=0; i<5; i++){
                    items.add(new Item("Item "+(1+i),(i+r4.nextInt(10))* r4.nextInt(5), BitmapFactory.decodeResource(getResources(),R.drawable.item_4)));
                }

                gridView.setNumColumns(2);
                gridView.setAdapter(new ItemAdapter(this, R.layout.catalog_2_list_items, items, textColor, grayBackgroundColor, cardBackgroundColor));
                setItemColors();
                break;
            case R.id.ButtonGroup5:
                items = new ArrayList<>();
                Random r5 = new Random();
                for (int i=0; i<5; i++){
                    items.add(new Item("Item "+(1+i),(i+r5.nextInt(10))* r5.nextInt(5), BitmapFactory.decodeResource(getResources(),R.drawable.item_5)));
                }

                gridView.setNumColumns(2);
                gridView.setAdapter(new ItemAdapter(this, R.layout.catalog_2_list_items, items, textColor, grayBackgroundColor, cardBackgroundColor));
                setItemColors();
                break;
        }
    }
}
