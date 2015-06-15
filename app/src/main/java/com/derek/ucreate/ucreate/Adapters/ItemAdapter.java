package com.derek.ucreate.ucreate.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.derek.ucreate.ucreate.Models.Item;
import com.derek.ucreate.ucreate.R;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {
    private List<Item> items;
    private Activity context;
    private int color;
    private int backgroundColor;
    private int rotation;
    private int cardBackgroundColor;

    public ItemAdapter(Context context, int resource, List<Item> items, int color, int backgroundColor, int cardBackgroundColor, int rotation) {
        super(context, resource, items);
        this.context = (Activity)context;
        this.items = items;
        this.color = color;
        this.backgroundColor = backgroundColor;
        this.rotation = rotation;
        this.cardBackgroundColor = cardBackgroundColor;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = context.getLayoutInflater().inflate(R.layout.catalog_2_list_items,parent,false);
        Item i = items.get(position);

        ImageView imageView = (ImageView) v.findViewById(R.id.imageViewItem);
        TextView textView = (TextView) v.findViewById(R.id.textViewItem);

        imageView.setImageBitmap(i.getImage());
        textView.setText(i.getName() + "\nQ." + i.getPrice());
        textView.setTextColor(color);

        CardView cardView = (CardView) v;
        cardView.setCardBackgroundColor(backgroundColor);
        cardView.findViewById(R.id.layoutCardView).setBackgroundColor(cardBackgroundColor);

        //http://stackoverflow.com/questions/5725745/horizontal-scrolling-grid-view
        textView.setRotation(rotation);
        imageView.setRotation(rotation);

        return v;
    }
}
