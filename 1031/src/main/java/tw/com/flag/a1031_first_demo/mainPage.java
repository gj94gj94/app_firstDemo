package tw.com.flag.a1031_first_demo;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class mainPage extends AppCompatActivity implements Tab1_left.OnFragmentInteractionListener,Tab2_mid.OnFragmentInteractionListener,Tab3_right.OnFragmentInteractionListener {
    // For the database
    public static final String db_name="testDB";
    public static final String tb_name="test";
    public static SQLiteDatabase db;
    public static Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);


        // Swift left and right
        final ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
        final PagerAdapter adapter = new PageAdapter(getSupportFragmentManager(),3);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);//設定首頁在中間頁
        Intent intent = new Intent(this,loginPage.class);
        if(!loginPage.ActivityA.isFinishing()){loginPage.ActivityA.finish();}

        // For the database
        db = openOrCreateDatabase(db_name, Context.MODE_PRIVATE,null);
        String createTable="CREATE TABLE IF NOT EXISTS "+tb_name+"(_id INTEGER PRIMARY KEY AUTOINCREMENT, "+"item VARCHAR(32), "
                + "itemName VARCHAR(32), " + "state VARCHAR(32))";
        db.execSQL(createTable);

        c=db.rawQuery("SELECT * FROM "+tb_name,null);
        if(c.getCount()==0){
            addData("item1","炫炮小東西","invisible");
            addData("item2","志剛coding小王子","invisible");
            addData("item3","老大幫忙做介面","invisible");
            addData("item4","Berry幹話王","invisible");
        }
    }


    // For swift left and right
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void readEdit(View v){

    }

    // For database
    public static void addData(String item,String itemName,String state){
        ContentValues cv = new ContentValues(3);
        cv.put("item",item);
        cv.put("itemName",itemName);
        cv.put("state",state);

        db.insert(tb_name,null,cv);
    }
}
