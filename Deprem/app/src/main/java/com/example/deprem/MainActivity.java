package com.example.deprem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import com.example.deprem.adapter.RecyclerViewAdapter;
import com.example.deprem.model.depremModel;
import com.example.deprem.service.depremService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ArrayList<depremModel> depremModels;
    RecyclerView recyclerView;
    Retrofit retrofit;
    RecyclerViewAdapter recyclerViewAdapter;
private String BASE_URL="https://deprem.odabas.xyz/api/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);
        //retrofit and gson
        Gson gson=new GsonBuilder().setLenient().create();
        retrofit=new  Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        loadData();
    }
    private  void loadData()
    {
        depremService depremS=retrofit.create(depremService.class);//servisi olusturdum.
        Call<List<depremModel>> call=depremS.getData();//veriyi alma islemi
        call.enqueue(new Callback<List<depremModel>>() {
            @Override
            public void onResponse(Call<List<depremModel>> call, Response<List<depremModel>> response) {
                if(response.isSuccessful())
                {
                    List<depremModel>responseList=response.body();
                   depremModels=new ArrayList<>(responseList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerViewAdapter=new RecyclerViewAdapter(depremModels);
                    recyclerView.setAdapter(recyclerViewAdapter);
                }

            }

            @Override
            public void onFailure(Call<List<depremModel>> call, Throwable t) {
                t.printStackTrace();

            }
        });

    }
}
