package com.example.prince.hackathon.activity;

import android.Manifest;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.Toast;

import com.example.prince.hackathon.R;

import static com.example.prince.hackathon.R.id.button_Admit_Card;


/**
 * Created by prince on 4/2/17.
 */

public class AdmitcardActivity extends AppCompatActivity {
    private static final int REQUEST_STORAGE = 1;
    private final static String DOWNLOAD_URL = "http://i.imgur.com/wsibrEw.gif";
    private long enqueue;
    private AppCompatButton Button_Admitcard;
    private DownloadManager downloadManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdetails);
        Button_Admitcard = (AppCompatButton) findViewById(button_Admit_Card);

        BroadcastReceiver receiver = new BroadcastReceiver() {


            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
                    intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0);
                    DownloadManager.Query query = new DownloadManager.Query();
                    query.setFilterById(enqueue);
                    Cursor cursor = downloadManager.query(query);
                    if (cursor.moveToFirst()) {
                        int columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
                        if (DownloadManager.STATUS_SUCCESSFUL == cursor.getInt(columnIndex)) {

                            String uriString = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
//                            button_Admit_Card.Uri (Uri.parse(uriString));
//                            Snackbar.make(this.getCurrentFocus(),"Download complete", LENGTH_SHORT).show();
                        }
                    }
                }
            }
        };

        registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    public void onClick(View view) {
        int currentApiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentApiVersion >= Build.VERSION_CODES.M) {
            requestStoragePermission();
        } else {
            downloadFile();
        }
    }

    private void requestStoragePermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_STORAGE);
        } else {
            Log.i("Main", "Storage permissions have already been granted. Download the file");
            downloadFile();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_STORAGE) {
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                downloadFile();
            } else {
                Toast.makeText(this, "You now can not download the file!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void downloadFile() {
        downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(DOWNLOAD_URL));

        //Get download file name
        String fileExtenstion = MimeTypeMap.getFileExtensionFromUrl(DOWNLOAD_URL);
        String name = URLUtil.guessFileName(DOWNLOAD_URL, null, fileExtenstion);

        //Save file to destination folder
        request.setDestinationInExternalPublicDir("/Downloads", name);
        enqueue = downloadManager.enqueue(request);
    }

}


