package miewsukanya.com.testapp;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class SignUpActivity extends AppCompatActivity {

    //Explicit
    //เติม Type Ctrl+ Space
    private EditText nameEditText,phoneEditText,usernameEditText, passwordEditText;
    private ImageView imageView;
    private Button button;
    private String nameString,phoneString,userString,passwordString,imagePathString,imageNameString;
    private Uri uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Bind widget
        //key == alt+enter = cath to,alt+ctrl+enter = ; ปิด ;
        nameEditText = (EditText) findViewById(R.id.editText);
        phoneEditText = (EditText) findViewById(R.id.editText2);
        usernameEditText = (EditText) findViewById(R.id.editText3);
        passwordEditText = (EditText) findViewById(R.id.editText4);
        imageView = (ImageView) findViewById(R.id.imageView);
        button = (Button) findViewById(R.id.button3);


        //SignUp Controller
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get Value From Edit Text
                nameString = nameEditText.getText().toString().trim(); //ถ้ากด SignUp แล้วมีช่องว่างจะตัดออก
                phoneString = phoneEditText.getText().toString().trim();
                userString = usernameEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();

                //check Space
                if (nameString.equals("") || phoneString.equals("")||
                        userString.equals("")|| passwordString.equals("")) {
                    //Have space check log
                    Log.d("12novV1", "Have Space");
                    MyAlert myAlert = new MyAlert(SignUpActivity.this, R.drawable.nobita48, "มีช่องว่าง", "กรุณากรอกให้ครบทุกช่องครับ");
                    myAlert.MyDialog();
                }

            }//on click
        });


     //Image Controller
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT); //intent program
                intent.setType("image/*"); // select photo เปิดโปรแกรมไหนที่มีภาพ
                startActivityForResult(Intent.createChooser(intent,"โปรดเลือกแอปดูภาพ"),0); //ใส่ int อะไรก็ได้

            }//on click
        });
    }//Main Method

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == 0)&&(resultCode == RESULT_OK)) {

            Log.d("12novV1", "Result OK");
            //show Image
            uri = data.getData();
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver()
                        .openInputStream(uri));
                imageView.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //Find path of image
            imagePathString = myFindPath(uri);
            Log.d("12novV1", "imagePath ==>" + imagePathString);
            //find name of Image
            imageNameString = imagePathString.substring(imagePathString.lastIndexOf("/"));
            Log.d("12novV1", "imageName ==>" + imageNameString);

        }//if


    }//onActivityResult

    private String myFindPath(Uri uri) {

        String result = null;
        String[] strings = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, strings, null, null,null);

        if (cursor != null) {
            cursor.moveToFirst();
            int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            result = cursor.getString(index);

        } else {
            result = uri.getPath();
        }
        return result;
    }
}//Main Class
