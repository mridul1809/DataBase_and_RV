package mridul1809.database;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Mridul on 29-12-2017.
 */

public class MyDebugger {
    public static void show(Context context , String message) {
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show();
    }
}
