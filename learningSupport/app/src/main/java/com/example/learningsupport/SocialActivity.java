package com.example.learningsupport;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class SocialActivity extends AppCompatActivity {

    LoginButton loginBtn;
    CallbackManager callbackManager;
    ProfilePictureView profilePictureView;
    Button btn_chucNang;
    TextView tv_name,tv_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        loginBtn = findViewById(R.id.loginBtn);
        profilePictureView = findViewById(R.id.profilePictureView);
        btn_chucNang = findViewById(R.id.btn_fb_chucNang);
        tv_email = findViewById(R.id.tv_fb_email);
        tv_name = findViewById(R.id.tv_fb_name);
        callbackManager = CallbackManager.Factory.create();
        FacebookSdk.sdkInitialize(getApplicationContext());

        btn_chucNang.setVisibility(View.INVISIBLE);
        tv_name.setVisibility(View.INVISIBLE);
        tv_email.setVisibility(View.INVISIBLE);
        loginBtn.setReadPermissions(Arrays.asList("public_profile","email"));

        loginBtn.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getApplicationContext(),"Đăng nhập thành công",Toast.LENGTH_LONG).show();

                btn_chucNang.setVisibility(View.VISIBLE);
                tv_name.setVisibility(View.VISIBLE);
                tv_email.setVisibility(View.VISIBLE);

                result();

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    private void result() {

        GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Log.d("JSON",response.getJSONObject().toString());
                try {
                    profilePictureView.setProfileId(Profile.getCurrentProfile().getId());
                    tv_name.setText(object.getString("name"));
                    tv_email.setText(object.getString("email"));
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
        Bundle bundle = new Bundle();
        bundle.putString("fields","name,email");
        graphRequest.setParameters(bundle);
        graphRequest.executeAsync();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    protected void onStart() {
        LoginManager.getInstance().logOut();
        super.onStart();
    }
}