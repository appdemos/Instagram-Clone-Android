package  com.example.joker.sistofoodtest.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;
import  com.example.joker.sistofoodtest.Models.User;

import java.util.ArrayList;
import java.util.List;

public class SharedPref {

    private static SharedPref sharedPref = null;
    private SharedPreferences sharedPreference;

    private static final String USER = "USER";

    private SharedPref(Context context) {
        sharedPreference = context.getSharedPreferences("instaclone", Context.MODE_PRIVATE);
    }

    public static SharedPref getInstance(Context context) {
        if (sharedPref == null) {
            sharedPref = new SharedPref(context);
        }
        return sharedPref;
    }


    public void saveUser(FirebaseUser firebaseUser) {

        SharedPreferences.Editor editor = sharedPreference.edit();

        List<String> names = new ArrayList<>();
        User user = new User(firebaseUser.getUid(), firebaseUser.getDisplayName(), firebaseUser.getEmail(), firebaseUser.getPhotoUrl().toString(), names);
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString(USER, json);

        editor.apply();

    }

    public User getUser() {

        Gson gson = new Gson();
        String json = sharedPreference.getString(USER, "");
        User user = gson.fromJson(json, User.class);

        return user;
    }
}
