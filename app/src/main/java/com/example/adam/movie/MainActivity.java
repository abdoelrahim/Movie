package com.example.adam.movie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static String BASE_URL = "https://api.themoviedb.org";
    public static String API_KEY="bc4ba8224db9e1a15c6235ab9d52847b";
    public static String LANGUAGE="en-US";
    public static String CATEGORY="popular";
    public static int PAGE = 1;

    private TextView myTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTextView=(TextView) findViewById(R.id.myT_V);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface myinterface = retrofit.create(ApiInterface.class);

        Call<MovieResults> call =myinterface.ListOfMovies(CATEGORY,API_KEY,LANGUAGE,PAGE);

    call.enqueue(new Callback<MovieResults>() {
    @Override
    public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
         MovieResults results=response.body();
        List<MovieResults.Result> ListOfMovies =  results.getResults();
        MovieResults.Result firstmovie = ListOfMovies.get(0);
        myTextView.setText(firstmovie.getTitle());

    }

    @Override
    public void onFailure(Call<MovieResults> call, Throwable t) {
    t.printStackTrace();
    }
});


    }
}
