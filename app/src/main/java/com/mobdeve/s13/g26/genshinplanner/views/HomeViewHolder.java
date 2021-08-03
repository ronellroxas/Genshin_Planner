package com.mobdeve.s13.g26.genshinplanner.views;

import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.s13.g26.genshinplanner.R;

import org.jetbrains.annotations.NotNull;

public class HomeViewHolder extends RecyclerView.ViewHolder {

    private Button btnGoogle;

    public HomeViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        this.btnGoogle = itemView.findViewById(R.id.bnt_home_google);
    }

    public void setBtnGoogleListener(View.OnClickListener e) {
        this.btnGoogle.setOnClickListener(e);
    }
}
