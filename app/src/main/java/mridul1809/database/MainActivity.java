package mridul1809.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{



    //Variables
    EditText name,password,findName;
    Button add,getDetails,getName;
    MyHelper myHelper;
    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    String[] columns = {MyHelper.NAME,MyHelper.PASSWORD};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        generateIDS();
        myHelper = new MyHelper(this);
        sqLiteDatabase = myHelper.getWritableDatabase();
        contentValues = new ContentValues();
        myAdapter = new MyAdapter(this);
        myAdapter.cursor = sqLiteDatabase.query(MyHelper.TABLE_NAME,columns,null,null,null,null,null);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    public void generateIDS()
    {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);
        findName = (EditText) findViewById(R.id.findName);
        add = (Button) findViewById(R.id.add);
        getDetails = (Button) findViewById(R.id.getDetails);
        getName = (Button) findViewById(R.id.getName);
        add.setOnClickListener(this);
        getDetails.setOnClickListener(this);
        getName.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.add :
                if(!name.getText().toString().equals("") && !password.getText().toString().equals("")) {
                    contentValues.clear();
                    contentValues.put(MyHelper.NAME,name.getText().toString());
                    contentValues.put(MyHelper.PASSWORD,password.getText().toString());
                    long flag = sqLiteDatabase.insert(MyHelper.TABLE_NAME,null,contentValues);
                    if(flag != -1) {
                        MyDebugger.show(this,"Entry Added Successfully");
                        myAdapter.cursor = sqLiteDatabase.query(MyHelper.TABLE_NAME,columns,null,null,null,null,null);
                        myAdapter.notifyDataSetChanged();
                    }
                    else
                        MyDebugger.show(this,"Entry Failed to be Added");

                }
                break;
            case R.id.getDetails:
                String[] columns = {MyHelper.UID,MyHelper.NAME,MyHelper.PASSWORD};
                Cursor cursor = sqLiteDatabase.query(MyHelper.TABLE_NAME,columns,null,null,null,null,null);
                StringBuffer buffer = new StringBuffer();
                while(cursor.moveToNext())
                {
                    buffer.append(cursor.getInt(0) + " ");
                    buffer.append(cursor.getString(1) + " ");
                    buffer.append(cursor.getString(2) + "\n");
                }
                MyDebugger.show(this,buffer.toString());
                break;

            case R.id.getName :
                String[] columns1 = {MyHelper.NAME , MyHelper.PASSWORD};
                Cursor cursor1 = sqLiteDatabase.query(MyHelper.TABLE_NAME,columns1,MyHelper.NAME + " = '" + findName.getText().toString() + "'",null,null,null,null);
                StringBuffer buffer1 = new StringBuffer();
                while(cursor1.moveToNext())
                {
                    buffer1.append(cursor1.getString(cursor1.getColumnIndex(MyHelper.NAME)) + " ");
                    buffer1.append(cursor1.getString(cursor1.getColumnIndex(MyHelper.PASSWORD)) + "\n");
                }
                MyDebugger.show(this,buffer1.toString());
            break;
        }
    }
}
