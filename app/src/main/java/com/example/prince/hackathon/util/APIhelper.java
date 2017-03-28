package com.example.prince.hackathon.util;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

/**
 * Created by prince on 3/26/17.
 */

public class APIhelper {
    private static final String baseUrl = "http://139.59.79.39";
    private static final String endpoint_ExamName = "/listexam/";
    private static final String endpoint_AdmitNo = "/";
    private static final String endpoint_getCenter = "/";

//    private static final String endpoint_Latitude = "/";
//    private static final String endpoint_Longitude = "/";


    public static String getEndpoint_ExamName() {
        return APIhelper.baseUrl + APIhelper.endpoint_ExamName;
    }

    public static String getEndpoint_AdmitNo() {
        return APIhelper.baseUrl + APIhelper.endpoint_AdmitNo;
    }


    public static void getExamList(Context context, Response.Listener<JSONArray> responseListner, Response.ErrorListener errorListener) {
        try {

            JsonArrayRequest getExamRequest = new JsonArrayRequest(Request.Method.GET, APIhelper.getEndpoint_ExamName(), null, responseListner, errorListener);
//        JsonObjectRequest getExamRequest = new JsonObjectRequest(Request.Method.GET, APIhelper.getEndpoint_ExamName(), null, responseListner, errorListener);
            VolleySingleton.getInstance(context).addToRequestQueue(getExamRequest);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Some error occurred\nPlease try again after some time", Toast.LENGTH_SHORT).show();
        }
    }


}
