package com.orcchg.scrolltwolistviews;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

// From: http://stackoverflow.com/questions/12342419/android-scrolling-2-listviews-together
public class MainActivity extends Activity {
  ListView listView;
  ListView listView2;

  View clickSource;
  View touchSource;

  int offset = 0;

  Handler handler;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    listView = (ListView) findViewById(R.id.engNameList);
    listView.setOnTouchListener(new OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent event) {
        if (touchSource == null)
          touchSource = v;

        if (v == touchSource) {
          listView2.dispatchTouchEvent(event);
          if (event.getAction() == MotionEvent.ACTION_UP) {
            clickSource = v;
            touchSource = null;
          }
        }

        return false;
      }
    });

    listView.setOnItemClickListener(new OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent == clickSource) {
          Log.i("FIRST", (String) parent.getItemAtPosition(position));
        }
      }
    });

    listView.setOnScrollListener(new OnScrollListener() {
      @Override
      public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
          int totalItemCount) {
        if (view == clickSource)
          listView2.setSelectionFromTop(firstVisibleItem, view.getChildAt(0).getTop() + offset);
      }

      @Override
      public void onScrollStateChanged(AbsListView view, int scrollState) {}
    });

    SampleAdapter adapter1 = new SampleAdapter(this);
    adapter1.add("Alov Maxim");
    adapter1.add("Liana Gishkariani");
    adapter1.add("Nastya Petrovich");
    adapter1.add("Vladimir Putin");
    adapter1.add("Meshlab Connector");
    adapter1.add("Petr I Velikiy");
    adapter1.add("Sardanapal Chernomorov");
    adapter1.add("Victor Bucha");
    adapter1.add("Oleg Muratov");
    adapter1.add("Artem Shamsuarov");
    adapter1.add("Maria Lyu");
    adapter1.add("Yury Slynko");
    adapter1.add("Oleg Kurtsev");
    adapter1.add("Dr Jihwan Choi");
    adapter1.add("Dr Joshua Cho");
    listView.setAdapter(adapter1);

    // ---------------------------------------------------------------------------------------
    listView2 = (ListView) findViewById(R.id.list);
    listView2.setOnTouchListener(new OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent event) {
        if (touchSource == null)
          touchSource = v;

        if (v == touchSource) {
          listView.dispatchTouchEvent(event);
          if (event.getAction() == MotionEvent.ACTION_UP) {
            clickSource = v;
            touchSource = null;
          }
        }

        return false;
      }
    });

    listView2.setOnItemClickListener(new OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent == clickSource) {
          Log.e("SECOND", (String) parent.getItemAtPosition(position));
        }
      }
    });

    listView2.setOnScrollListener(new OnScrollListener() {
      @Override
      public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
          int totalItemCount) {
        if (view == clickSource)
          listView.setSelectionFromTop(firstVisibleItem, view.getChildAt(0).getTop() + offset);
      }

      @Override
      public void onScrollStateChanged(AbsListView view, int scrollState) {}
    });

    SampleAdapter adapter2 = new SampleAdapter(this);
    adapter2.add("Sujoy Saha");
    adapter2.add("Vitaly Chernov");
    adapter2.add("Selemene Command");
    adapter2.add("Goblin Puchkov");
    adapter2.add("Jazz musician");
    adapter2.add("OpenGL ES 2.0");
    adapter2.add("Support Library");
    adapter2.add("Natalia Polyanina");
    adapter2.add("Mambo Group");
    adapter2.add("Alexei Vekshtein");
    adapter2.add("Junio Krasavchik");
    adapter2.add("Mercedes Benz");
    adapter2.add("Ildar Haripoff");
    adapter2.add("Maxim Ryabko");
    adapter2.add("Lantsov Alexei");
    listView2.setAdapter(adapter2);

    handler = new Handler() {
      @Override
      public void handleMessage(Message msg) {
        // Set listView's x, y coordinates in loc[0], loc[1]
        int[] loc = new int[2];
        listView.getLocationInWindow(loc);

        // Save listView's y and get listView2's coordinates
        int firstY = loc[1];
        listView2.getLocationInWindow(loc);

        offset = firstY - loc[1];
        // Log.v("Example", "offset: " + offset + " = " + firstY + " + " + loc[1]);
      }
    };

  }

  @Override
  public void onResume() {
    super.onResume();
    handler.sendEmptyMessageDelayed(0, 500);
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
    if (id == R.id.action_settings) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
