package com.example.lenovo.myfinance;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.arch.lifecycle.ReportFragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.lenovo.myfinance.Fragments.Accounts_fragment;
import com.example.lenovo.myfinance.Fragments.Backup_fragment;
import com.example.lenovo.myfinance.Fragments.Categories_fragment;
import com.example.lenovo.myfinance.Fragments.Main_fragment;
import com.example.lenovo.myfinance.Fragments.Reports_fragment;
import com.example.lenovo.myfinance.Fragments.Transaction_fragment;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    android.support.v7.widget.Toolbar toolbar;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;

    private ResideMenu resideMenu;
    private ResideMenuItem itemAccount;
    private Context mContext;
    DBHelper myDb;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext = this;

//        setUpMenu();
        myDb = new DBHelper(this);

        mDrawerlayout= findViewById(R.id.drawerlayout);

        mToggle = new ActionBarDrawerToggle(this,mDrawerlayout,R.string.open,R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);

        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView =(NavigationView) findViewById(R.id.navigation_main);
        navigationView.setNavigationItemSelectedListener(this);

        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainactivity_fragment,new Main_fragment());
        transaction.commit();



    }


    private void setUpMenu(){

        resideMenu = new ResideMenu(this);

        resideMenu.setBackground(R.drawable.backgroundwhite);

        resideMenu.attachToActivity(this);
        resideMenu.setMenuListener(menuListener);
        resideMenu.setScaleValue(0.6f);

        //create menu itemscuroty
        String titles[] = { "Accounts", "Reminders", "Backup", "Security","settings" };
        int icon[] = { R.drawable.accounts, R.drawable.reminders, R.drawable.backup, R.drawable.security,R.drawable.settings };
        for (int i = 0; i < titles.length; i++){
            ResideMenuItem item = new ResideMenuItem(this, icon[i], titles[i]);
//            item.setOnClickListener(this);
            resideMenu.addMenuItem(item,  ResideMenu.DIRECTION_RIGHT); // or  ResideMenu.DIRECTION_RIGHT
        }



        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_LEFT);


    }
    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {
//            Toast.makeText(this, "Menu is opened!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void closeMenu() {
//            Toast.makeText(mContext, "Menu is closed!", Toast.LENGTH_SHORT).show();
        }
    };

    public ResideMenu getResideMenu(){
        return resideMenu;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.cleartransactions) {
            myDb.clearHistory();
        }
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
        //noinspection SimplifiableIfStatement

    }
private void setUpNavigationView(){

}


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_right);
        if(id == R.id.nav_account){
            Toast.makeText(this, " Accounts ", Toast.LENGTH_SHORT).show();
            transaction.replace(R.id.mainactivity_fragment,new Accounts_fragment());
            transaction.addToBackStack(null);
            transaction.commit();
            mDrawerlayout.closeDrawers();
            return true;

             }
        else if(id ==R.id.nav_backup){
            transaction.replace(R.id.mainactivity_fragment,new Backup_fragment());
            transaction.addToBackStack(null);
            transaction.commit();
            mDrawerlayout.closeDrawers();
            return true;
        }
        return false;

    }
}
