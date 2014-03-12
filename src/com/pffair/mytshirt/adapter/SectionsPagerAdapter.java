package com.pffair.mytshirt.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.pffair.mytshirt.fragment.ProductFragment;
import com.pffair.mytshirt.util.ResourceUtil;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

  public SectionsPagerAdapter(FragmentManager fm) {
      super(fm);
  }

  @Override
  public Fragment getItem(int position) {
    ProductFragment productFragment = new ProductFragment();
    productFragment.setArguments(ProductFragment.setParamters(position));
    return new ProductFragment();
  }

  @Override
  public int getCount() {
      return 3;
  }

  @Override
  public CharSequence getPageTitle(int position) {
      String title = "";
      switch(position){
        case ResourceUtil.VIEWPAGER_TYPE_HOT:
          title = ResourceUtil.VIEWPAGER_TYPE_HOT_STR;
          break;
        case ResourceUtil.VIEWPAGER_TYPE_LIMIT:
          title = ResourceUtil.VIEWPAGER_TYPE_LIMIT_STR;
          break;
        case ResourceUtil.VIEWPAGER_TYPE_TJ:
          title = ResourceUtil.VIEWPAGER_TYPE_TJ_STR;
          break;
      }
      return title;
  }
}
