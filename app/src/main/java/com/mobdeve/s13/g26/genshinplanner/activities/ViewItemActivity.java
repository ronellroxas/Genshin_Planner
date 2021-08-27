package com.mobdeve.s13.g26.genshinplanner.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobdeve.s13.g26.genshinplanner.R;
import com.mobdeve.s13.g26.genshinplanner.adapters.ItemListAdapter;
import com.mobdeve.s13.g26.genshinplanner.keys.ItemKeys;

public class ViewItemActivity extends AppCompatActivity {

    private ImageView iv_Item_picture;
    private TextView tv_Item_name;
    private TextView tv_Item_type;
    private TextView tv_Item_obtain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);

        this.iv_Item_picture = findViewById(R.id.iv_view_item_picture);
        this.tv_Item_name = findViewById(R.id.tv_view_item_name);
        this.tv_Item_type = findViewById(R.id.tv_view_item_type);
        this.tv_Item_obtain = findViewById(R.id.tv_view_item_obtain);

        Intent intent = getIntent();
        this.iv_Item_picture.setImageResource(intent.getIntExtra(ItemKeys.KEY_PICTURE.name(), 0));
        this.tv_Item_name.setText(intent.getStringExtra(ItemKeys.KEY_NAME.name()));
        this.tv_Item_type.setText(intent.getStringExtra(ItemKeys.KEY_TYPE.name()));
        this.tv_Item_obtain.setText(intent.getStringExtra(ItemKeys.KEY_OBTAIN.name()));
    }
}