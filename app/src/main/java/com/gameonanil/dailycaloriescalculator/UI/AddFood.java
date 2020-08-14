package com.gameonanil.dailycaloriescalculator.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.gameonanil.dailycaloriescalculator.Adapters.ViewPagerAdapter;
import com.gameonanil.dailycaloriescalculator.R;
import com.google.android.material.tabs.TabLayout;


public class AddFood extends AppCompatActivity {
    private SearchFragment searchFragment;
    private CustomFoodFragment customFoodFragment;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        Toolbar toolbar = findViewById(R.id.toolbar_add_food);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewPager = findViewById(R.id.viewpager_addFood);
        tabLayout = findViewById(R.id.tabLayout_add_food);

        searchFragment = new SearchFragment();
        customFoodFragment = new CustomFoodFragment();

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),0);
        adapter.addFragment(searchFragment,"Search");
        adapter.addFragment(customFoodFragment,"Custom Food");
        viewPager.setAdapter(adapter);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }

    }
}