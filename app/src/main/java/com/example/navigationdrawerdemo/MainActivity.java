package com.example.navigationdrawerdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Context c;
    String[] heading = {
            "Gujarati",
            "Punjabi",
            "Chiness",
            "Italian"
    };
    String[][] items = {
            {"Dalbhat","Khichadi","Sev Tameta","Bhakhri"},
            {"Paneer Kadai","Paneer Tufani","Paneer Angara","Paneer Handi"},
            {"Manchuriyan Dry","Meggi"},
            {"Veg Pizza","Veg Spicy","Magerita","American Thin Crux"}
    };
    ExpandableListView exlv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        loadlayout();
        loadList();
    }

    @Override
    public void onBackPressed() {

        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id==R.id.nav_page1){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            Bundle bundle = new Bundle();
            bundle.putString("pagename","This is page1");

            Page1 p1 = new Page1();
            p1.setArguments(bundle);
            ft.replace(R.id.frame1,p1,"p1");
            ft.commit();
        }else if(id==R.id.nav_page2){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            Bundle bundle = new Bundle();
            bundle.putString("pagename","This is page2");

            Page2 p2 = new Page2();
            p2.setArguments(bundle);
            ft.replace(R.id.frame1,p2);
            ft.commit();
        }else if(id==R.id.nav_page3){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            Bundle bundle = new Bundle();
            bundle.putString("pagename","This is page3");

            Page3 p3 = new Page3();
            p3.setArguments(bundle);
            ft.replace(R.id.frame1,p3);
            ft.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    void loadlayout(){
        Page1 p1 = new Page1();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putString("pagename","This is page1");

        p1.setArguments(bundle);
        ft.replace(R.id.frame1,p1);
        ft.commit();
    }

    void loadList(){


        c = MainActivity.this;
        exlv = findViewById(R.id.exlv);

        Custom adapter = new Custom();
        exlv.setAdapter(adapter);
    }

    class Custom extends BaseExpandableListAdapter {

        @Override
        public int getGroupCount() {
            return heading.length;
        }

        @Override
        public int getChildrenCount(int i) {
            return items[i].length;
        }

        @Override
        public Object getGroup(int i) {
            return null;
        }

        @Override
        public Object getChild(int i, int i1) {
            return null;
        }

        @Override
        public long getGroupId(int i) {
            return 0;
        }

        @Override
        public long getChildId(int i, int i1) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int i, boolean b, View v, ViewGroup viewGroup) {

            v = LayoutInflater.from(c).inflate(R.layout.ad_headinglayout,viewGroup,false);

            TextView tvheading = v.findViewById(R.id.tvheading);

            tvheading.setText(heading[i]);

            return v;
        }

        @Override
        public View getChildView(int row, int column, boolean b, View v, ViewGroup viewGroup) {
            v = LayoutInflater.from(c).inflate(R.layout.ad_childlayout,viewGroup,false);

            TextView tvitem = v.findViewById(R.id.tvitem);

            tvitem.setText(items[row][column]);

            return v;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return false;
        }
    }

}
