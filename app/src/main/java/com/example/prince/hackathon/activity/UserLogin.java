package com.example.prince.hackathon.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.ArrayAdapter;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.prince.hackathon.R;
import com.example.prince.hackathon.model.Responsef;
import com.example.prince.hackathon.model.User;
import com.example.prince.hackathon.util.APIhelper;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;

public class UserLogin extends AppCompatActivity implements View.OnClickListener, Response.Listener<JSONArray>, Response.ErrorListener {

    private ProgressDialog progressDialog;

    private AppCompatSpinner spinnerExamName;
    private AppCompatEditText editText_Roll_Number;
    //    private AppCompatTextView textViewAdmitCardId;
    //    private AppCompatButton   buttonAdmitCard;
    //    private AppCompatButton buttongetLocation;
    private User myUser;

    private Responsef[] name;
//    private String[] name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initialize();

    }


    private void initialize() {
        setContentView(R.layout.activity_user_login);
        this.spinnerExamName = (AppCompatSpinner) this.findViewById(R.id.spinner_ExamName);
        this.editText_Roll_Number = (AppCompatEditText) this.findViewById(R.id.editText_Roll_Number);

        //this.textViewAdmitCardId = (AppCompatTextView) this.findViewById(R.id.textView_AdmitCard);
        // this.buttonAdmitCard =(AppCompatButton) this.findViewById(R.id.button_Admit_Card);
        // this.buttongetLocation = (AppCompatButton) this.findViewById(R.id.button_getLocation);
        APIhelper.getExamList(this, this, this);
    }

    @Override
    public void onClick(View view) {

        int viewId = view.getId();
        switch (viewId) {
            case R.id.button_submit:
                // TODO call submit_request() here
                break;
        }
    }


    private void submit_request() {


    }


    @Override
    public void onResponse(JSONArray response) {
        if (this.progressDialog != null && this.progressDialog.isShowing()) {
            this.progressDialog.dismiss();
        }
        try {
            if (response != null) {
                ArrayList<String> Arr = convertJsonToArr((new Gson()).fromJson(response.toString(), Responsef[].class));

                fillSpinner(Arr);
            }

        } catch (Exception e) {
            e.printStackTrace();
            this.onErrorResponse(new VolleyError("Corrupt network response"));
        }

    }

    private ArrayList<String> convertJsonToArr(Responsef[] responsefs) {
        ArrayList<String> theArr = new ArrayList<>();
        for (Responsef i : responsefs) {
            theArr.add(i.getName());
        }
        return theArr;
    }

    private void fillSpinner(ArrayList<String> name) {
        spinnerExamName.setAdapter(new ArrayAdapter<String>(UserLogin.this, android.R.layout.simple_spinner_dropdown_item, name));

    }

    @Override
    public void onErrorResponse(VolleyError error) {
//        Log.e("Get-Exam", (new String(error.networkResponse.data, Charset.defaultCharset())));
        error.printStackTrace();
    }


}














