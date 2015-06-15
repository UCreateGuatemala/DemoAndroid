package com.derek.ucreate.ucreate;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.derek.ucreate.ucreate.Adapters.ItemAdapter;
import com.derek.ucreate.ucreate.Models.Item;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Boris on 6/11/2015.
 */
public class ShowCatalogActivity extends Activity{

    private LinearLayout    layoutButtonsTop, layoutButtonsLeft;
    private RelativeLayout  layoutLogo, layoutItem;
    private int             templateType, backgroundColor, textColor, logoTextColor, orientation, backgroundItemColor, backgroundCardColor;
    private String          logoName;
    private Bitmap          logo;
    private List<Item>      items;
    private int             rotationItem, rotation;
    private GridView        grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_catalog);

        layoutLogo = (RelativeLayout) findViewById(R.id.RelativeLayoutCatalog1Up);
        layoutItem = (RelativeLayout) findViewById(R.id.RelativeLayoutItems);
        layoutButtonsTop = (LinearLayout) findViewById(R.id.LayoutButtonsTop);
        layoutButtonsLeft = (LinearLayout) findViewById(R.id.LayoutButtonsLeft);
        grid = (GridView) findViewById(R.id.ListItems);

        Bundle b = getIntent().getExtras();
        getExtrasBundle(b);
        setTemplate();
        setOrientation();
        setBackgroundColor();

        items = new ArrayList<>();
        Random r = new Random();
        for (int i=0; i<8; i++){
            items.add(new Item("Item "+i,(i+r.nextInt(10))* r.nextInt(5), BitmapFactory.decodeResource(getResources(), R.drawable.solologo)));
        }
        grid.setRotation(rotation);
        grid.setNumColumns(2);
        grid.setAdapter(new ItemAdapter(this, R.layout.catalog_2_list_items, items, textColor, backgroundItemColor, backgroundCardColor, rotationItem));
    }

    public void setTemplate(){
        if(templateType == 1){
            layoutItem.removeView(layoutButtonsTop);
            layoutItem.removeView(layoutButtonsLeft);
            rotationItem = 90;
            rotation = 270;
        }
        else if (templateType == 2){
            layoutItem.removeView(layoutButtonsTop);
            layoutItem.removeView(layoutButtonsLeft);
            rotationItem = 0;
            rotation = 0;
        }
        else if (templateType==3){
            layoutItem.removeView(layoutButtonsTop);
            layoutItem.addView(layoutButtonsTop);
            layoutItem.removeView(layoutButtonsLeft);
        }
        else if (templateType==4){
            layoutItem.removeView(layoutButtonsLeft);
            layoutItem.addView(layoutButtonsLeft);
            layoutItem.removeView(layoutButtonsTop);
        }
        else{
            layoutItem.removeAllViews();
            layoutItem.addView(grid);
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
        logoIcon.setImageBitmap(logo);
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
    }
}
