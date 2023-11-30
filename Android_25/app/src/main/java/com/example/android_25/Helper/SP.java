package com.example.android_25.Helper;

import android.content.Context;
import android.content.SharedPreferences;

public class SP {
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    Context context;

    public SP(Context context) {
        this.sp = context.getSharedPreferences("apiku", context.MODE_PRIVATE);
        this.editor = sp.edit();
        this.context = context;
    }

    public String getValue(String key){
        return sp.getString(key,"");
    }

    public void setValue(String key, String value){
        editor.putString(key,value);
        editor.commit();
    }

    public String getValueInt(String key){
        return sp.getString(key,"");
    }

    public void setValueInt(String key, int value){
        editor.putInt(key,value);
        editor.commit();
    }

    public void setToken(String token){
        editor.putString("token",token);
        editor.commit();
    }
    public String getToken(){
        return getValueInt("token");
    }

    public void setName(String token){
        editor.putString("name",token);
        editor.commit();
    }
    public String getName(){
        return getValueInt("name");
    }

    public void setEmail(String token){
        editor.putString("email",token);
        editor.commit();
    }
    public String getEmail(){
        return getValueInt("email");
    }

    public void setPhone(String token){
        editor.putString("phone",token);
        editor.commit();
    }
    public String getPhone(){
        return getValueInt("phone");
    }


    public void setID(int token){
        editor.putInt("id",token);
        editor.commit();
    }

    public String getID(){
        return getValueInt("id");
    }



    public void clearData(){
        editor.clear();
        editor.commit();
    }
}
