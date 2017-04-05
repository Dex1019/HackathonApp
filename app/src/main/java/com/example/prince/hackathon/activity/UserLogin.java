package com.example.prince.hackathon.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

import static android.support.design.widget.Snackbar.LENGTH_SHORT;


public class UserLogin extends AppCompatActivity implements View.OnClickListener, Response.Listener<JSONArray>, Response.ErrorListener {

    private ProgressDialog progressDialog;

    private AppCompatSpinner spinnerExamName;
    private AppCompatEditText editText_Roll_Number;
    private AppCompatButton button_submit;
    private User myUser;
    private Responsef[] name;

    //    private AppCompatTextView textViewAdmitCardId;
    //    private AppCompatButton buttongetLocation;
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
        this.button_submit = (AppCompatButton) this.findViewById(R.id.button_submit);
        APIhelper.getExamList(this, this, this);
        button_submit.setOnClickListener(this);


        //this.textViewAdmitCardId = (AppCompatTextView) this.findViewById(R.id.textView_AdmitCard);
        // this.buttonAdmitCard =(AppCompatButton) this.findViewById(R.id.button_Admit_Card);
        // this.buttongetLocation = (AppCompatButton) this.findViewById(R.id.button_getLocation);

    }

    @Override
    public void onClick(View view) {

//        int viewId = view.getId();
//        switch (viewId) {
//            case R.id.button_submit:
        this.submit_request();
//                break;
//        }
    }


    public void submit_request() {
        String exname = spinnerExamName.getSelectedItem().toString();
        String rollno = editText_Roll_Number.getText().toString();

        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(this.getCurrentFocus().getApplicationWindowToken(), 0);

        if (!rollno.isEmpty()) {
            UserDetails.launchActivity(this, exname, rollno);

        } else {
            Snackbar.make(this.getCurrentFocus(), "Please insert valid input", LENGTH_SHORT).show();
        }




    }


    @Override
    public void onResponse(JSONArray response) {
//        if (this.progressDialog != null && this.progressDialog.isShowing()) {
//            this.progressDialog.dismiss();
//        }


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














