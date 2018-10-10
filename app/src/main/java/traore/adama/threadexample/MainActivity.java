package traore.adama.threadexample;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView lblChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblChange = findViewById(R.id.lblChange);
    }

    public void longCalculClicked(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 100000;
                while(i > 0) {
                    System.out.println("From long thread : "+i);
                    i--;
                }
                lblChange.post(new Runnable() {
                    @Override
                    public void run() {
                        lblChange.setText("Long");
                    }
                });
            }
        }).start();
    }

    public void otherLongCalculClicked(View view){
        new VeryLongAsyncTask().execute();
        lblChange.setText("En cours");
    }

    private class VeryLongAsyncTask extends AsyncTask<Void, Integer, Void>{


        @Override
        protected Void doInBackground(Void... voids) {
            int i = 1000000;
            while(i > 0) {
                System.out.println("From very long thread : "+i);
                i--;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            lblChange.setText("Very Long");
        }

    }

}
