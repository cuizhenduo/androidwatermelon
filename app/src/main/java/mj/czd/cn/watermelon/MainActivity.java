package mj.czd.cn.watermelon;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        Button jishi_button = (Button) findViewById(R.id.jishi_data);
        Button his_button = (Button) findViewById(R.id.his_data);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerlayout);
        final NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.controll);
        }
        navigationView.setCheckedItem(R.id.nav_light);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_light:
                        //Toast.makeText(MainActivity.this,"light",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,GuangzhaoActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_tem:
                        //Toast.makeText(MainActivity.this,"temperature",Toast.LENGTH_SHORT).show();
                        Intent wintent = new Intent(MainActivity.this,WenduActivity.class);
                        startActivity(wintent);
                        break;
                    case R.id.nav_mois:
                        //Toast.makeText(MainActivity.this,"mois",Toast.LENGTH_SHORT).show();
                        Intent sintent = new Intent(MainActivity.this,ShiduActivity.class);
                        startActivity(sintent);
                        break;
                }
                return true;
            }
        });
        jishi_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jishiFragment fragment = new jishiFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager. beginTransaction();
                transaction.replace(R.id.right_layout, fragment);
                transaction.commit();
            }
        });
        his_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hisdataFragment fragment = new hisdataFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager. beginTransaction();
                transaction.replace(R.id.right_layout, fragment);
                transaction.commit();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }
}

