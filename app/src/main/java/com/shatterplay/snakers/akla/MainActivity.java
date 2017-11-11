package com.shatterplay.snakers.akla;

import android.app.Dialog;
import android.content.Intent;
import android.database.DatabaseUtils;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


import com.loopj.android.image.SmartImageView;

import java.util.ArrayList;

import static com.facebook.internal.CallbackManagerImpl.RequestCodeOffset.Login;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TabLayout tab = (TabLayout) findViewById(R.id.tablay);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpagerid);
        ViewPagerAdap viewPagerAdap = new ViewPagerAdap(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdap);
        tab.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public void onBackPressed() {
            super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        startActivity(intent);
        finish();
        System.exit(0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.icon){
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog);

   DatabaseForAkla forAkla = new DatabaseForAkla(getApplicationContext());

    SmartImageView smartImageView = (SmartImageView) dialog.findViewById(R.id.dialogimage);
            TextView name = (TextView) dialog.findViewById(R.id.namey);
            TextView email = (TextView) dialog.findViewById(R.id.emaily);

            Long i=     DatabaseForAkla.rawid;
            int id = i.intValue();
DatabaseForAkla databaseForAkla = new DatabaseForAkla(MainActivity.this);
            ArrayList<Users>users=databaseForAkla.insertUsers();
            databaseForAkla.getReadableDatabase();
            int d = databaseForAkla.idl;

            // name.setText("My Name : " +DatabaseForAkla.databaseForAkla().insertUsers().get(d).getId() );
databaseForAkla.insertUsers().size();
            Log.v("idididid",String.valueOf(id));
                email.setText("My Email : " + databaseForAkla.users.getEmail());
               smartImageView.setImageUrl(databaseForAkla.users.getImageurl());
               name.setText("Your Name is " + databaseForAkla.users.getName());
//smartImageView.setImageUrl(   DatabaseForAkla.databaseForAkla().insertUsers().get(d).getImageurl());

    Log.v("IMAGEEE","lol");
            dialog.show();
        }
        if(item.getItemId()==R.id.log){

Intent intent =new Intent(this,Login_Activity.class);
App app = App.getInstance();
            app.globalVariabl=1;
        startActivity(intent);
        }
        return super.onOptionsItemSelected(item);

    }

}
