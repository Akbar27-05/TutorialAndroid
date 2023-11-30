package com.akbar27.crud_laravel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity {
    private TextView tvResult;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tvResult);

        Gson gson = new GsonBuilder().serializeNulls().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.7:8000/api/v1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        // createSkill();
        // updateSkill();
    }

    public void getSkill(){

        Call<SkillResponse> call = jsonPlaceHolderApi.getSkills();

        call.enqueue(new Callback<SkillResponse>() {
            @Override
            public void onResponse(Call<SkillResponse> call, Response<SkillResponse> response) {
                if (!response.isSuccessful()){
                    tvResult.setText("Code: "+response.code());
                    return;
                }

                SkillResponse skillsResponse = response.body();

                for (Skill skill : skillsResponse.getSkills()){
                    String content = "";
                    content += "ID: " + skill.getId() + "\n";
                    content += "NAME: " + skill.getName() + "\n";
                    content += "SLUG: " + skill.getText() + "\n\n";

                    tvResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<SkillResponse> call, Throwable t) {
                tvResult.setText(t.getMessage());
            }
        });
    }

    private void createSkill(){
        Skill skill = new Skill("Telur", "telur Goreng");

        Call<Skill> call = jsonPlaceHolderApi.createSkills(skill);

        call.enqueue(new Callback<Skill>() {
            @Override
            public void onResponse(Call<Skill> call, Response<Skill> response) {
                if (!response.isSuccessful()){
                    tvResult.setText("Code: "+response.code());
                    return;
                }

                Skill skillResponse = response.body();

                String content = "";
                content += "CODE: " +response.code() + "\n";
                content += "ID: " + skillResponse.getId() + "\n";
                content += "NAME: " +skillResponse.getName() + "\n";
                content += "SLUG: " + skillResponse.getText() + "\n\n";

                tvResult.setText(content);
            }

            @Override
            public void onFailure(Call<Skill> call, Throwable t) {
                tvResult.setText(t.getMessage());
            }
        });
    }

    private void updateSkill(){
        Skill skill = new Skill("Wanto", "wanto");

        Call<Skill> call = jsonPlaceHolderApi.putSkill(5, skill);

        call.enqueue(new Callback<Skill>() {
            @Override
            public void onResponse(Call<Skill> call, Response<Skill> response) {
                if (!response.isSuccessful()){
                    tvResult.setText("Code: "+ response.code());
                    return;
                }

                Skill skillResponse = response.body();

                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "Id: " + skillResponse.getId() + "\n";
                content += "Name: " + skillResponse.getName() + "\n";
                content += "Slug: " + skillResponse.getText() + "\n\n";

                tvResult.setText(content);
            }

            @Override
            public void onFailure(Call<Skill> call, Throwable t) {

            }
        });
    }

    private void deleteSkill(){
        Call<Void> call = jsonPlaceHolderApi.deleteSkill(2);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                tvResult.setText("Code: " + response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                tvResult.setText(t.getMessage());
            }
        });
    }
}













