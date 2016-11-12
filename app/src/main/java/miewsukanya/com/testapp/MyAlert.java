package miewsukanya.com.testapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Sukanya Boonpun on 12/11/2559.
 */

public class MyAlert {

    //Explicit
    //ส่งบางอย่างให่ ส่งคลาสให้อีกคลาส
    private Context context;
    private int anInt;
    private String titleString, messageString;

//Setter MyAlert alrt+Ins
    public MyAlert(Context context, int anInt, String titleString, String messageString) {
        this.context = context;
        this.anInt = anInt;
        this.titleString = titleString;
        this.messageString = messageString;
    }

    public void MyDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setIcon(anInt);
        builder.setTitle(titleString);
        builder.setMessage(messageString);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });
        builder.show();
    }//myDialog
}//Main Class
