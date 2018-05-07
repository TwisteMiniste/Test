package com.example.user.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity {

    MyTask mt;
    TextView tvInfo;
    ProgressBar progressBar;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = (TextView) findViewById(R.id.tvInfo);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void onclick(View v) {
        mt = new MyTask();
        mt.execute();
    }

    class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvInfo.setText("Begin");
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... params) {
/*
            DefaultHttpClient hc = new DefaultHttpClient();
            ResponseHandler response = new BasicResponseHandler();
            HttpGet http = new HttpGet("http://api.devreadwrite.com/v1/getUserData?id=1");
            //получаем ответ от сервера
            String response = (String) hc.execute(http, response);

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost http = new HttpPost("http://api.devreadwrite.com/v1/getUserData");
            List nameValuePairs = new ArrayList(2);
            nameValuePairs.add(new BasicNameValuePair("id", "1"));
            nameValuePairs.add(new BasicNameValuePair("value2", "2"));
            http.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//получаем ответ от сервера
            String response = (String) httpclient.execute(http, new BasicResponseHandler());
            */
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            tvInfo.setText("End");
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}





/*
public class JSONTask extends AsyncTask<String,String, List<MovieModel> >{

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog.show();
    }

    @Override
    protected List<MovieModel> doInBackground(String... params) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line ="";
            while ((line = reader.readLine()) != null){
                buffer.append(line);
            }

            String finalJson = buffer.toString();

            JSONObject parentObject = new JSONObject(finalJson);
            JSONArray parentArray = parentObject.getJSONArray("movies");

            List<MovieModel> movieModelList = new ArrayList<>();

            Gson gson = new Gson();
            for(int i=0; i<parentArray.length(); i++) {
                JSONObject finalObject = parentArray.getJSONObject(i);
                /**
                 * below single line of code from Gson saves you from writing the json parsing yourself
                 * which is commented below
                 */
/*                MovieModel movieModel = gson.fromJson(finalObject.toString(), MovieModel.class); // a single line json parsing using Gson
//                    movieModel.setMovie(finalObject.getString("movie"));
//                    movieModel.setYear(finalObject.getInt("year"));
//                    movieModel.setRating((float) finalObject.getDouble("rating"));
//                    movieModel.setDirector(finalObject.getString("director"));
//
//                    movieModel.setDuration(finalObject.getString("duration"));
//                    movieModel.setTagline(finalObject.getString("tagline"));
//                    movieModel.setImage(finalObject.getString("image"));
//                    movieModel.setStory(finalObject.getString("story"));
//
//                    List<MovieModel.Cast> castList = new ArrayList<>();
//                    for(int j=0; j<finalObject.getJSONArray("cast").length(); j++){
//                        MovieModel.Cast cast = new MovieModel.Cast();
//                        cast.setName(finalObject.getJSONArray("cast").getJSONObject(j).getString("name"));
//                        castList.add(cast);
//                    }
//                    movieModel.setCastList(castList);
                // adding the final object in the list
                movieModelList.add(movieModel);
            }
            return movieModelList;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
            try {
                if(reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  null;
    }

    @Override
    protected void onPostExecute(final List<MovieModel> result) {
        super.onPostExecute(result);
        dialog.dismiss();
        if(result != null) {
            MovieAdapter adapter = new MovieAdapter(getApplicationContext(), R.layout.row, result);
            lvMovies.setAdapter(adapter);
            lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {  // list item click opens a new detailed activity
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    MovieModel movieModel = result.get(position); // getting the model
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra("movieModel", new Gson().toJson(movieModel)); // converting model json into string type and sending it via intent
                    startActivity(intent);
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Not able to fetch data from server, please check url.", Toast.LENGTH_SHORT).show();
        }
    }
}
*/