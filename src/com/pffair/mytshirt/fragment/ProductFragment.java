package com.pffair.mytshirt.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pffair.mytshirt.R;

public class ProductFragment extends Fragment{
  
  private static final String INTENTPARAM_TYPE = "type";
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
          Bundle savedInstanceState) {
      View rootView = inflater.inflate(R.layout.fragment_section, container, false);
      Bundle args = getArguments();
      return rootView;
  }
  
  
  public static Bundle setParamters(int postion){
    Bundle bundle = new Bundle();
    bundle.putInt(INTENTPARAM_TYPE, postion);
    return bundle;
  }
}
