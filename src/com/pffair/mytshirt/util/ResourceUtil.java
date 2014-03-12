package com.pffair.mytshirt.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;

public class ResourceUtil {
  public static final int VIEWPAGER_TYPE_HOT = 0;//热销
  public static final int VIEWPAGER_TYPE_LIMIT = 1;//限量
  public static final int VIEWPAGER_TYPE_TJ = 2;//特价
  
  public static final String VIEWPAGER_TYPE_HOT_STR = "热销";
  public static final String VIEWPAGER_TYPE_LIMIT_STR = "限量";
  public static final String VIEWPAGER_TYPE_TJ_STR = "特价";

  
  private Resources resources;
  private String pkg;
  private static ResourceUtil resourceUtil;

  private ResourceUtil(Context context) {
      pkg = context.getPackageName();
      if (context instanceof Application) {
          resources = context.getResources();
      } else {
          resources = context.getApplicationContext().getResources();
      }
  }

  public static ResourceUtil getInstance(Context context) {
      if (resourceUtil == null) {
          resourceUtil = new ResourceUtil(context);
      }
      return resourceUtil;
  }

  protected int getResourcesId(Context context, String type, String name) {
      try {
          int id = resources.getIdentifier(name, type, pkg);
          return id;
      } catch (Exception e) {
          e.printStackTrace();
      }
      return 0;
  }

  public int getStringId(String name) {
      try {
          int id = resources.getIdentifier(name, "string", pkg);
          return id;
      } catch (Exception e) {
          e.printStackTrace();
      }
      return 0;
  }

  public String getString(String name) {
      String s = "";
      int id = getStringId(name);
      try {
          s = resources.getString(id);
      } catch (Exception e) {
          e.printStackTrace();
          s = "";
      }
      return s;
  }
  
  public String getString(int id) {
    String s = "";
    try {
        s = resources.getString(id);
    } catch (Exception e) {
        e.printStackTrace();
        s = "";
    }
    return s;
  }

  public int getColorId(String name) {
      try {
          int id = resources.getIdentifier(name, "color", pkg);
          return id;
      } catch (Exception e) {
          e.printStackTrace();
      }
      return 0;
  }

  public int getColor(String name) {
      int color = Color.TRANSPARENT;
      int id = getColorId(name);
      try {
          color = resources.getColor(id);
      } catch (Exception e) {
          color = 0;
      }
      return color;
  }

  public int getDimenId(String name) {
      try {
          int id = resources.getIdentifier(name, "dimen", pkg);
          return id;
      } catch (Exception e) {
          e.printStackTrace();
      }
      return 0;
  }

  public int getStyleId(String name) {
      try {
          int id = resources.getIdentifier(name, "style", pkg);
          return id;
      } catch (Exception e) {
          e.printStackTrace();
      }
      return 0;
  }

  public int getLayoutId(String name) {
      try {
          int id = resources.getIdentifier(name, "layout", pkg);
          return id;
      } catch (Exception e) {
          e.printStackTrace();
      }
      return 0;
  }

  public int getViewId(String name) {
      try {
          int id = resources.getIdentifier(name, "id", pkg);
          return id;
      } catch (Exception e) {
          e.printStackTrace();
      }
      return 0;
  }

  public int getAnimId(String name) {
      try {
          int id = resources.getIdentifier(name, "anim", pkg);
          return id;
      } catch (Exception e) {
          e.printStackTrace();
      }
      return 0;
  }

  public int getArrayId(String name) {
      try {
          int id = resources.getIdentifier(name, "array", pkg);
          return id;
      } catch (Exception e) {
          e.printStackTrace();
      }
      return 0;
  }

  public int getDrawableId(String name) {
      try {
          int id = resources.getIdentifier(name, "drawable", pkg);
          return id;
      } catch (Exception e) {
          e.printStackTrace();
      }
      return 0;
  }

  public int getStyleableId(String name) {
      try {
          int id = resources.getIdentifier(name, "styleable", pkg);
          return id;
      } catch (Exception e) {
          e.printStackTrace();
      }
      return 0;
  }

  public Drawable getDrawable(String name) {
      Drawable drawable = null;
      try {
          int id = getDrawableId(name);
          drawable = resources.getDrawable(id);
      } catch (Exception e) {
          drawable = null;
      }
      return drawable;
  }

  public int getRawId(String name) {
      try {
          int id = resources.getIdentifier(name, "raw", pkg);
          return id;
      } catch (Exception e) {
          e.printStackTrace();
      }
      return 0;
  }

  /**
   * 这是用来 获取raw下文件 String。
   * 
   * 比如json。
   * 
   * */
  public String readTextFileFromRawResourceId(Context context, int resourceId) {
      StringBuilder builder = new StringBuilder();

      BufferedReader reader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(
              resourceId)));

      try {
          for (String line = reader.readLine(); line != null; line = reader.readLine()) {
              builder.append(line).append("\n");
          }
      } catch (Exception e) {
          throw new RuntimeException(e);
      }

      return builder.toString();
  }

  /**
   * 这是用来 获取sdcard下文件 String。
   * 比如json。
   * */
  @SuppressWarnings("resource")
  public String readTextFileFromSDResourceId(Context context, String path, String Name) {
      StringBuilder builder = new StringBuilder();
      File file = new File(path + "/" + Name + ".json");
      if (file.exists()) {

          BufferedReader reader = null;
          try {
              reader = new BufferedReader(new FileReader(file));
              Log.e("TAG", "执行到这里!" + reader);
          } catch (FileNotFoundException e1) {
              e1.printStackTrace();
          }
          try {
              for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                  builder.append(line).append("\n");
              }
          } catch (Exception e) {
              throw new RuntimeException(e);
          }

          return builder.toString();
      } else {
          return null;
      }
  }
}
