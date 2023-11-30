package com.akbar27.crud_laravel;

import com.google.gson.annotations.SerializedName;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {
    @GET("skills")
    Call<SkillResponse> getSkills();

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("skills")
    Call<Skill> createSkills(@Body Skill skill);

    @PUT("skills/{id}")
    Call<Skill> putSkill(@Path("id") int id, @Body Skill skill);

    @PATCH("skills/{id}")
    Call<Skill> patchSkill(@Path("id") int id, @Body Skill skill);

    @DELETE("skills/{id}")
    Call<Void> deleteSkill(@Path("id") int id);
}
