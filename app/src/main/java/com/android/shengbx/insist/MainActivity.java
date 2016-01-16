package com.android.shengbx.insist;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.shengbx.insist.sql.DataBaseHelper;
import com.android.shengbx.insist.sql.InsistInfo;

public class MainActivity extends AppCompatActivity implements Loader.OnLoadCompleteListener<Cursor> {
    private GridView mGridView;
    private GridViewAdapter mAdapter;
    private DataBaseHelper db = new DataBaseHelper(MainActivity.this);
    private SQLiteDatabase mReadableDB;
    private SQLiteDatabase mWritableDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mGridView = (GridView) findViewById(R.id.grid_view);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        mReadableDB = db.getReadableDatabase();
        mWritableDB = db.getWritableDatabase();
        mAdapter = new GridViewAdapter(this);
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                InsistInfo item = mAdapter.getListItemByIndex(position);
                item.setDayNow(item.getDayNow()+1);
                ContentValues values = new ContentValues();
                values.put(DataBaseHelper.DAY_NOW,item.getDayNow());
                if(item.getDayNow()>item.getDayLongest()){
                    item.setDayLongest(item.getDayLongest()+1);
                    values.put(DataBaseHelper.DAY_LONGEST,item.getDayLongest());
                }
                mWritableDB.update(DataBaseHelper.TABLE_NAME,values,DataBaseHelper._ID+"=?",new String[]{String.valueOf(item.getId())});
                mAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), R.string.sign_successful,Toast.LENGTH_SHORT).show();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog = createDialog();
                dialog.show();
            }
        });
        new InfoLoadTask().execute();
    }

    private AlertDialog createDialog(){
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View v = inflater.inflate(R.layout.dialog,null);
        final EditText et_title = (EditText) v.findViewById(R.id.title_edit);
        Spinner sp_type = (Spinner) v.findViewById(R.id.type_spinner);
        sp_type.setAdapter(new TypeSpinnerAdapter());
        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setView(v)
                .setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        InsistInfo item = new InsistInfo();
                        item.setTitle(et_title.getText().toString());
                        ContentValues values = new ContentValues();
                        values.put(DataBaseHelper.TITLE, et_title.getText().toString());
                        long id = mWritableDB.insert(DataBaseHelper.TABLE_NAME, null, values);
                        item.setId(id);
                        mAdapter.add(item);
                        mAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        return dialog;
    }

    @Override
    public void onLoadComplete(Loader<Cursor> loader, Cursor data) {

    }

    private class TypeSpinnerAdapter extends BaseAdapter{



        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }

    public class InfoLoadTask extends AsyncTask<Void,InsistInfo,Integer> {


        @Override
        protected Integer doInBackground(Void... params) {
            Cursor cursor = mReadableDB.query(DataBaseHelper.TABLE_NAME,
                    new String[]{DataBaseHelper._ID,
                            DataBaseHelper.TITLE,
                            DataBaseHelper.TYPE,
                            DataBaseHelper.DAY_LONGEST,
                            DataBaseHelper.DAY_NOW},
                    null, null, null, null, "_id asc");
            InsistInfo item;
            int count = 0;
            while(cursor.moveToNext()){
                item = new InsistInfo();
                item.setId(cursor.getInt(cursor.getColumnIndex(DataBaseHelper._ID)));
                item.setTitle(cursor.getString(cursor.getColumnIndex(DataBaseHelper.TITLE)));
                item.setType(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.TYPE)));
                item.setDayLongest(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.DAY_LONGEST)));
                item.setDayNow(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.DAY_NOW)));
                mAdapter.add(item);
                mAdapter.notifyDataSetChanged();
                count++;
            }
            return count;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
