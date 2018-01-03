package mridul1809.database;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Mridul on 31-12-2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    LayoutInflater inflater;
    Cursor cursor;

    MyAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
        cursor.moveToPosition(position);
        holder.text.setText(cursor.getString(cursor.getColumnIndex(MyHelper.NAME)));
        holder.text1.setText(cursor.getString(cursor.getColumnIndex(MyHelper.PASSWORD)));
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView text,text1;
        public MyViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text);
            text1 = (TextView) itemView.findViewById(R.id.text1);
        }
    }
}
