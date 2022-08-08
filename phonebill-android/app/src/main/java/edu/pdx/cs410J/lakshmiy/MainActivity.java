package edu.pdx.cs410J.lakshmiy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavController nvc = Navigation.findNavController(this, R.id.navigation_fragment);
//        AppBarConfiguration confg = new AppBarConfiguration.Builder(nvc.getGraph()).build();
        AppBarConfiguration confg = new AppBarConfiguration.Builder(R.id.home_lyt, R.id.add_phone_call_lyt, R.id.search_lyt, R.id.readme_lyt).build();
        BottomNavigationView navview = findViewById(R.id.bottom_navigation_view);
//        NavigationView navview = findViewById(R.id.bottom_navigation_view);
        //check for label change
        NavigationUI.setupActionBarWithNavController(this, nvc, confg);
        NavigationUI.setupWithNavController(navview, nvc);

    }
}


