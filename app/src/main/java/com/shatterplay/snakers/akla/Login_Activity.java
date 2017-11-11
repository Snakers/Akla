package com.shatterplay.snakers.akla;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.DefaultAudience;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.loopj.android.image.SmartImageView;

import org.json.JSONObject;

import java.util.ArrayList;


public class Login_Activity extends AppCompatActivity {
    private CallbackManager callbackManager;
private LoginButton loginButton;
    private final App app= App.getInstance();

    private SharedPreferences sharedPreferences;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_login_);
        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton) findViewById(R.id.login_button);
        sharedPreferences = getSharedPreferences("applogin",MODE_PRIVATE);

            if (app.globalVariabl==0) {

                if(sharedPreferences.contains("name")){
                    Intent intent = new Intent(Login_Activity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                    app.globalVariabl=1;
                }

            }

loginButton.setReadPermissions("email");
loginButton.setToolTipMode(LoginButton.ToolTipMode.AUTOMATIC);
        loginButton.setDefaultAudience(DefaultAudience.EVERYONE);
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                getUserInfo(loginResult);

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });


    }
    private void getUserInfo(final LoginResult loginResult){

        GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {

                try {

                    String id = object.getString("id");
                     SharedPreferences.Editor editor = sharedPreferences.edit();

                    String imageUrl = "https://graph.facebook.com/" + id + "/picture?width=300&height=300";

                    String name = object.getString("name");
                    editor.putString("name",name);
                    editor.apply();
                    app.globalVariabl = 1;

                    String email;
                    if (object.has("email")) {
                        email = object.getString("email");
                        Log.v("lolzadasdsa",email);

                        Bundle b = new Bundle();
                        b.putString("name", name);
                        b.putString("email", email);
                        b.putString("image", imageUrl);
                        b.putString("id", id);

                        Intent intent = new Intent(Login_Activity.this, LoginPageTwo.class);
                        intent.putExtras(b);
                        startActivity(intent);


                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        Bundle bundle =new Bundle();
        bundle.putString("fields","name,id,email");
        graphRequest.setParameters(bundle);
        graphRequest.executeAsync();


    }
}
