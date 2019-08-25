package com.example.italika;

import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.italika.Adapters.PageAdapter;
import com.example.italika.Interfaces.CallbackRequest;
import com.example.italika.Request.Login;
import com.example.italika.Request.Request;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TabItem start,assitance,services,shopOnline,stadistics;
    private PagerAdapter pageAdapter;
    private TextView date,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager=findViewById(R.id.viewPager);
        tabLayout=findViewById(R.id.tablayout);
        start=findViewById(R.id.tabStart);
        assitance=findViewById(R.id.tabAssitance);
        services=findViewById(R.id.tabServices);
        shopOnline=findViewById(R.id.tabShopOnline);
        stadistics=findViewById(R.id.tabStadistics);
        date=findViewById(R.id.activityMainDate);
        name=findViewById(R.id.activityMainName);

        name.setText(Login.Instance().getName());
        Request.executeTimeout("date/?get", new CallbackRequest() {
            @Override
            public void onResult(JSONObject response) throws Exception {
                date.setText(response.optString("date"));
            }

            @Override
            public void onError() {

            }
        });


        pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


    }
}
