package com.example.prince.hackathon.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.prince.hackathon.R;

import org.json.JSONObject;

/**
 * Created by prince on 3/28/17.
 */

public class UserDetails extends AppCompatActivity implements View.OnClickListener, Response.Listener<JSONObject>, Response.ErrorListener {

    private AppCompatTextView textViewExamName;
    private AppCompatTextView textViewRollNo;
    private AppCompatTextView textViewCenterName;
    private AppCompatButton buttonAdmitCard;
    private AppCompatButton buttongetLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();

    }


    private void initialize() {
        setContentView(R.layout.activity_userdetails);
        this.textViewExamName = (AppCompatTextView) findViewById(R.id.textView_exam_name);
        this.textViewExamName = (AppCompatTextView) findViewById(R.id.textView_admit_no);
        this.textViewCenterName = (AppCompatTextView) findViewById(R.id.textView_center_name);
        this.buttonAdmitCard = (AppCompatButton) findViewById(R.id.button_Admit_Card);
        this.buttongetLocation = (AppCompatButton) findViewById(R.id.button_getLocation);

    }


    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        switch (viewId) {
            case R.id.button_Admit_Card:
                this.print_admitCard();
                break;
            case R.id.button_getLocation:
                this.getLocation();
                break;

        }

    }

    //Method to print Admit Card
    private void print_admitCard() {

    }

    //Method to print GetLocation
    private void getLocation() {

    }


    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {

    }
}
