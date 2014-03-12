package com.pffair.mytshirt;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import com.pffair.mytshirt.adapter.SectionsPagerAdapter;

public class MainActivity extends BaseFragmentActivity implements ActionBar.TabListener{

  ViewPager mViewPager;
  SectionsPagerAdapter sectionsPagerAdapter;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

    final ActionBar actionBar = getActionBar();
    actionBar.setHomeButtonEnabled(false);
    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

    mViewPager = (ViewPager) findViewById(R.id.pager);
    mViewPager.setAdapter(sectionsPagerAdapter);
    mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            actionBar.setSelectedNavigationItem(position);
        }
    });
    for (int i = 0; i < sectionsPagerAdapter.getCount(); i++) {
        actionBar.addTab(
                actionBar.newTab()
                        .setText(sectionsPagerAdapter.getPageTitle(i))
                        .setTabListener(this));
    }
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }


  @Override
  public void onTabSelected(Tab tab, FragmentTransaction ft) {
    mViewPager.setCurrentItem(tab.getPosition());
  }


  @Override
  public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    
  }


  @Override
  public void onTabReselected(Tab tab, FragmentTransaction ft) {
    
  }

}
