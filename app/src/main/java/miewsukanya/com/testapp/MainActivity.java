package miewsukanya.com.testapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private Button signInButton, signUpButton;
    private EditText userEditText, passwordEditText;
    private String userString, passwordString;
    private MyConstant myConstant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myConstant = new MyConstant();

        //Bind Widget ผูกกับตัวแปร
        signInButton = (Button) findViewById(R.id.button);
        signUpButton = (Button) findViewById(R.id.button2); // ; Ctrl+Shift+Enter
        userEditText = (EditText) findViewById(R.id.editText5);
        passwordEditText = (EditText) findViewById(R.id.editText6);

        //SignIn Controller
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get Value from Edit Text
                userString = userEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();

                //ceck space
                if (userString.equals("")|| passwordString.equals("")) {
                    //HaveSpace
                    MyAlert myAlert = new MyAlert(MainActivity.this, R.drawable.bird48,
                            getResources().getString(R.string.title_HaveSpace),
                            getResources().getString(R.string.message_HaveSpace));
                    myAlert.MyDialog();
                } else {
                    //NoSpace
                    SynUser synUser = new SynUser(MainActivity.this);
                    synUser.execute(myConstant.getUrlGetJSON());
                }
            }//onClick
        });

        //Sign up Controller

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SignUpActivity.class));
            }
        });

//
    } //Main Method

    private class SynUser extends AsyncTask<String, Void, String> {
        //Explicit
        private Context context;
        private String[] nameStrings,phoneStrings, imageStrings;
        private String truePassword;
        private Boolean aBoolean = true;

        public SynUser(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(params[0]).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();

            } catch (Exception e) {
                Log.d("13novV2", "e doIn==>" + e.toString());
                return null;
            }
           //return null;
        }//doInBack

        @Override
        protected void onPostExecute(String s) {
            Log.d("13novV2", "Json ==>" + s);
            try {
                JSONArray jsonArray = new JSONArray(s);

                nameStrings = new String[jsonArray.length()];
                phoneStrings = new String[jsonArray.length()];
                imageStrings = new String[jsonArray.length()];
                for (int i=0;i<jsonArray.length();i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    nameStrings[i] = jsonObject.getString("Name");
                    phoneStrings[i] = jsonObject.getString("Phone");
                    imageStrings[i] = jsonObject.getString("Image");

                    Log.d("13novV3", "name(" + i + ") ==>" + nameStrings);
                    //chsck user
                    if (userString.equals(jsonObject.getString("User"))) {
                        aBoolean = false;
                        truePassword = jsonObject.getString("Password");

                    }

                }//for
                if (aBoolean) {
                    //user False
                    MyAlert myAlert = new MyAlert(context, R.drawable.kon48,
                            getResources().getString(R.string.title_UserFalse),
                            getResources().getString(R.string.message_UserFalse));
                    myAlert.MyDialog();

                } else if (phoneStrings.equals(truePassword)) {
                    //PW True
                    Toast.makeText(context,"Welcome",Toast.LENGTH_LONG).show();
                } else {
                    //PW False
                    MyAlert myAlert = new MyAlert(context, R.drawable.kon48,
                            getResources().getString(R.string.title_PassFalse),
                            getResources().getString(R.string.message_PassFalse));
                    myAlert.MyDialog();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            //super.onPostExecute(s);

        }//onPost

    }//SnyUser


} //Main class คลาสหลัก
