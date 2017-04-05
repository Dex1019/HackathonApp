package com.example.prince.hackathon.util;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by prince on 3/26/17.
 */

public class APIhelper {
    private static final String baseUrl = "http://139.59.79.39";
    private static final String endpoint_ExamNameList = "/listexam/";
    private static final String endpoint_RollNo = "/";
    private static final String endpoint_getCenter = "/getcenter/";
    private static final String getEndpoint_Admidcard = "//";
    private static String examname;
    private static String rollno;


//    private static final String endpoint_Latitude = "/";
//    private static final String endpoint_Longitude = "/";


    public static String getEndpoint_ExamNameList() {
        return APIhelper.baseUrl + APIhelper.endpoint_ExamNameList;
    }

    public static String getEndpoint_Center() {
        return APIhelper.baseUrl + APIhelper.endpoint_getCenter + examname + "/" + rollno + "/";
    }

    public static String getGetEndpoint_Admidcard() {
        return APIhelper.baseUrl + APIhelper.endpoint_getCenter + examname + "/" + rollno + "/";
    }


    public static void getCenterLocation(String examname, String rollno, Context context, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        APIhelper.examname = examname;
        APIhelper.rollno = rollno;
        try {
            JsonObjectRequest getCenterRequest = new JsonObjectRequest(Request.Method.GET, APIhelper.getEndpoint_Center(), null, responseListener, errorListener);
            VolleySingleton.getInstance(context).addToRequestQueue(getCenterRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void getExamList(Context context, Response.Listener<JSONArray> responseListner, Response.ErrorListener errorListener) {
        try {

            JsonArrayRequest getExamRequest = new JsonArrayRequest(Request.Method.GET, APIhelper.getEndpoint_ExamNameList(), null, responseListner, errorListener);
//        JsonObjectRequest getExamRequest = new JsonObjectRequest(Request.Method.GET, APIhelper.getEndpoint_ExamNameList(), null, responseListner, errorListener);
            VolleySingleton.getInstance(context).addToRequestQueue(getExamRequest);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Some error occurred\nPlease try again after some time", Toast.LENGTH_SHORT).show();
        }
    }


}
