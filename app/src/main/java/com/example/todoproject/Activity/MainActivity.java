package com.example.todoproject.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoproject.Addapter;
import com.example.todoproject.Model.Task;
import com.example.todoproject.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Task> taskList;
    Addapter addapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        setUpViews();

        taskList = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // passing data
        onLoad();

        addapter = new Addapter(taskList,this);
        recyclerView.setAdapter(addapter);
    }

    private void setUpViews() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    public void onLoad() {
//        edtBook = findViewById(R.id.edtBook);
        String url = "http://192.168.1.113/rest/infoTask.php";
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET},
                    123);

        } else {
            DownloadTextTask runner = new DownloadTextTask();
            runner.execute(url);
        }
    }

    private InputStream OpenHttpConnection(String urlString) throws IOException {
        InputStream in = null;
        int response = -1;

        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();

        if (!(conn instanceof HttpURLConnection))
            throw new IOException("Not an HTTP connection");
        try {
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");

            httpConn.connect();
            Log.d("Networking:", "Ameen");

            response = httpConn.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }
        } catch (Exception ex) {
            Log.d("Networking", ex.getLocalizedMessage());
            throw new IOException("Error connecting");
        }
        return in;
    }

    private String DownloadText(String URL) {
        int BUFFER_SIZE = 2000;
        InputStream in = null;
        try {
            in = OpenHttpConnection(URL);
        } catch (IOException e) {
            Log.d("Networking", e.getLocalizedMessage());
            return "";
        }

        InputStreamReader isr = new InputStreamReader(in);
        int charRead;
        String str = "";
        char[] inputBuffer = new char[BUFFER_SIZE];
        try {
            while ((charRead = isr.read(inputBuffer)) > 0) {
                //---convert the chars to a String---
                String readString =
                        String.copyValueOf(inputBuffer, 0, charRead);
                str += readString;
                inputBuffer = new char[BUFFER_SIZE];
            }
            in.close();
        } catch (IOException e) {
            Log.d("Networking", e.getLocalizedMessage());
            return "";
        }
        return str;
    }

    public void btnAddNewTask(View view) {
        Intent intent = new Intent(this, AddNewTask.class);
        startActivity(intent);
    }

    public void seach_onClick(View view) {
    }

    private class DownloadTextTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            return DownloadText(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
//            Toast.makeText(getBaseContext(), result, Toast.LENGTH_LONG).show();
            String[] taskListString = result.split("#");
            for (int index = 0; index < taskListString.length; index++) {
                String[] tasks = taskListString[index].split(",");
                Task task = new Task(Integer.parseInt(tasks[0]), Integer.parseInt(tasks[2]), tasks[1], tasks[3]);
                taskList.add(task);
            }
//            EditText txtBooks = findViewById(R.id.txtBooks);
//            txtBooks.setText(result);
        }
    }

}