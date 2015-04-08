package com.example.s3ns3i.zadanie1;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.TextView;

/**
 * Created by s3ns3i on 2015-04-01.
 */
public class GetPathAsyncTask extends AsyncTask<Void, Void, Void> {

    private TextView filePath;
    private TextView intentResultCode;
    private Fragment fragment;
    private Context context;
    private Intent intent;
    int YOUR_RESULT_CODE;
    GetPathAsyncTask(TextView filePath, TextView intentResultCode, Fragment fragment){
        this.filePath = filePath;
        this.intentResultCode = intentResultCode;
        this.fragment = fragment;
    }

    @Override
    protected Void doInBackground(Void... params) {
        intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("file/*");
        YOUR_RESULT_CODE = 0;
        fragment.startActivityForResult(intent, YOUR_RESULT_CODE);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        filePath.setText(intent.getData().getPath());
        intentResultCode.setText(String.valueOf(YOUR_RESULT_CODE));
    }
}
