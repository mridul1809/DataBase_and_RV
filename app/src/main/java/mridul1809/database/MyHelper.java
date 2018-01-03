package mridul1809.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mridul on 29-12-2017.
 */

public class MyHelper extends SQLiteOpenHelper {

    //Variables
    Context context;
    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "MyDataBase";
    public static final String TABLE_NAME = "MyTable";
    public static final String NAME = "name";
    public static final String PASSWORD = "password";
    public static final String UID = "_id";

    //Constructor
    public MyHelper(Context context) {

        super(context , DATABASE_NAME , null , VERSION);
        this.context = context;
    }





    //Methods
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " , " + PASSWORD + " );";
        try {
            sqLiteDatabase.execSQL(CREATE_TABLE);
            MyDebugger.show(context,"Table Created");
        }
        catch (SQLException e) {
            MyDebugger.show(context,"Error in Table Creation");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
