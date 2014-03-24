package com.pffair.mytshirt.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.nhaarman.listviewanimations.ArrayAdapter;
import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;
import com.pffair.mytshirt.R;

public class ProductFragment extends BaseFragment {

  private static final String INTENTPARAM_TYPE = "type";
  View rootView;
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    rootView = inflater.inflate(R.layout.activity_gridview, container, false);
    Bundle args = getArguments();
    GridView gridView = (GridView) rootView.findViewById(R.id.activity_gridview_gv);
    SwingBottomInAnimationAdapter swingBottomInAnimationAdapter =
        new SwingBottomInAnimationAdapter(new MyAdapter(this.getActivity(), getItems()));
    swingBottomInAnimationAdapter.setAbsListView(gridView);
    swingBottomInAnimationAdapter.setInitialDelayMillis(300);
    gridView.setAdapter(swingBottomInAnimationAdapter);
    return rootView;
  }


  public static Bundle setParamters(int postion) {
    Bundle bundle = new Bundle();
    bundle.putInt(INTENTPARAM_TYPE, postion);
    return bundle;
  }

  private ArrayList<Integer> getItems() {
    ArrayList<Integer> items = new ArrayList<Integer>();
    for (int i = 0; i < 100; i++) {
      items.add(i);
    }
    return items;
  }

  private static class MyAdapter extends ArrayAdapter<Integer> {

    private final Context mContext;
    private final LruCache<Integer, Bitmap> mMemoryCache;

    public MyAdapter(final Context context, final List<Integer> list) {
      super(list);
      mContext = context;

      final int cacheSize = (int) (Runtime.getRuntime().maxMemory() / 1024);
      mMemoryCache = new LruCache<Integer, Bitmap>(cacheSize) {
        @Override
        protected int sizeOf(final Integer key, final Bitmap bitmap) {
          // The cache size will be measured in kilobytes rather than
          // number of items.
          return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
        }
      };
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup viewGroup) {
      ImageView imageView = (ImageView) convertView;

      if (imageView == null) {
        imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
      }

      int imageResId;
      switch (getItem(position) % 5) {
        case 0:
          imageResId = R.drawable.img_nature1;
          break;
        case 1:
          imageResId = R.drawable.img_nature2;
          break;
        case 2:
          imageResId = R.drawable.img_nature3;
          break;
        case 3:
          imageResId = R.drawable.img_nature4;
          break;
        default:
          imageResId = R.drawable.img_nature5;
      }

      Bitmap bitmap = getBitmapFromMemCache(imageResId);
      if (bitmap == null) {
        bitmap = BitmapFactory.decodeResource(mContext.getResources(), imageResId);
        addBitmapToMemoryCache(imageResId, bitmap);
      }
      imageView.setImageBitmap(bitmap);

      return imageView;
    }

    private void addBitmapToMemoryCache(final int key, final Bitmap bitmap) {
      if (getBitmapFromMemCache(key) == null) {
        mMemoryCache.put(key, bitmap);
      }
    }

    private Bitmap getBitmapFromMemCache(final int key) {
      return mMemoryCache.get(key);
    }
  }

}
