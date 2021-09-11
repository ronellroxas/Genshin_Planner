package com.mobdeve.s13.g26.genshinplanner.views;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.s13.g26.genshinplanner.R;

import org.jetbrains.annotations.NotNull;

public class CreatePlanViewHolder {

    private EditText etName;
    private EditText etDescription;
    private EditText etResin;
    private Button btnSubmit;
    private Button btnCancel;


    public CreatePlanViewHolder(Context context) {
        this.etName = (EditText) ((Activity) context).findViewById(R.id.et_create_plan_name);
        this.etDescription = (EditText) ((Activity) context).findViewById(R.id.etml_create_plan_description);
        this.etResin = (EditText) ((Activity) context).findViewById(R.id.et_create_plan_resin);
        this.btnSubmit = (Button) ((Activity) context).findViewById(R.id.btn_create_plan_save);
        this.btnCancel = (Button) ((Activity) context).findViewById(R.id.btn_create_plan_cancel);

    }

    public String getName() {
        return etName.getText().toString();
    }

    public String getDescription() {
        return etDescription.getText().toString();
    }

    public String getResin() {
        return etResin.getText().toString();
    }

    public void setSubmitListener(View.OnClickListener listener) {
        this.btnSubmit.setOnClickListener(listener);
    }

    public void setCancelListener(View.OnClickListener listener) {
        this.btnCancel.setOnClickListener(listener);
    }

    public void setName(String name){
        this.etName.setText(name);
    }

    public void setDescription(String description){
        this.etDescription.setText(description);
    }

    public void setResin(String text){
        this.etResin.setText(text);
    }
}
