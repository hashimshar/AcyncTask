package com.hashimshar.acynctasklab;

//freshdevelopers.tk

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String TEXT_STATE = "currentText";
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize mTextView
        mTextView = (TextView) findViewById(R.id.textView1);
        // Restore TextView if there is a savedInstanceState
        if(savedInstanceState!=null){
            mTextView.setText(savedInstanceState.getString(TEXT_STATE));
        }

    }

    public void startTask(View view) {
// Put a message in the text view
        mTextView.setText(R.string.napping);
        // Start the AsyncTask.
        // The AsyncTask has a callback that will update the text view.
        new MyTask(mTextView).execute();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the state of the TextView
        outState.putString(TEXT_STATE, mTextView.getText().toString());
    }
    public class MyTask extends AsyncTask<Void, Void, String> {
        private WeakReference<TextView> mTextView;
        public MyTask(TextView tv) {
            mTextView = new WeakReference<>(tv);
        }



        @Override
        protected String doInBackground(Void... voids) {
            // Generate a random number between 0 and 10
            Random r = new Random();
            int n = r.nextInt(9);
            int s = n * 2000;
            // Make the task take long enough that we have
            // time to rotate t
            // Sleep for the random amount of time
            try {
                Thread.sleep(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Return a String result
            return "AcyncTask worked in background for " + s + " milliseconds!";

        }

        protected void onPostExecute(String result) {
            mTextView.get().setText(result);
        }

    }

}
