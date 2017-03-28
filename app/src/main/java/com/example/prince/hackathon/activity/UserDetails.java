package com.example.prince.hackathon.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.prince.hackathon.R;
import com.example.prince.hackathon.model.User;
import com.example.prince.hackathon.util.APIhelper;
import com.google.gson.Gson;

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
    private String store_examname;
    private String store_rollno;
    private User myuser;


    public static void launchActivity(AppCompatActivity currentActivity, String exname, String rollno) {
        Intent intent = new Intent(currentActivity, UserDetails.class);
        intent.putExtra("ExamName", exname).putExtra("RollNo", rollno);
        currentActivity.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();

    }


    private void initialize() {
        setContentView(R.layout.activity_userdetails);
        this.textViewExamName = (AppCompatTextView) findViewById(R.id.textView_exam_name);
        this.textViewRollNo = (AppCompatTextView) findViewById(R.id.textView_roll_no);
        this.textViewCenterName = (AppCompatTextView) findViewById(R.id.textView_center_name);
        this.buttonAdmitCard = (AppCompatButton) findViewById(R.id.button_Admit_Card);
        this.buttongetLocation = (AppCompatButton) findViewById(R.id.button_getLocation);
        //this.myuser = new User();

        buttongetLocation.setOnClickListener(this);

        Intent intent = getIntent();
        store_examname = intent.getStringExtra("ExamName");
        store_rollno = intent.getStringExtra("RollNo");

        APIhelper.getCenterLocation(this.store_examname, this.store_rollno, this, this, this);


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

    //Method to print GetLocation
    private void getLocation() {
//        String latitude = "26.2096";
//        String longitude = "78.1664";
//        String myLabel = "You+are+here";

        //Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + latitude + "," + longitude + " (YOUR+EXAM+CENTER)");
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + this.myuser.getLatitude() + "," + this.myuser.getLongitude() + " (" + this.myuser.getCentername() + ")");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    //Method to print Admit Card
    private void print_admitCard() {

    }


    @Override
    public void onErrorResponse(VolleyError error) {
        error.printStackTrace();
    }

    @Override
    public void onResponse(JSONObject response) {

        try {
            System.out.print(response.toString());
            myuser = (new Gson()).fromJson(response.toString(), User.class);
            showData();

        } catch (Exception e) {
            e.printStackTrace();

        }


    }

    private void showData() {
        textViewExamName.setText(this.myuser.getExamname());
        textViewRollNo.setText(this.myuser.getAdmitno());
        textViewCenterName.setText(this.myuser.getCentername());


    }
}
