package com.orcchg.scrolltwolistviews;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SampleAdapter extends BaseAdapter {
  private final Context mContext;
  private final List<String> mPersons;
  
  /* View holder */
  private static class ViewHolder {
    private TextView name;
    private TextView lessons_rest;
  }
  
  public SampleAdapter(Context context) {
    super();
    mContext = context;
    mPersons = new ArrayList<String>(100);
  }

  @Override
  public int getCount() {
    return mPersons.size();
  }

  @Override
  public Object getItem(int position) {
    return mPersons.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    ViewHolder holder = null;
    View rowView = convertView;
    LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    
    if (rowView == null) {
      rowView = inflater.inflate(R.layout.small_persons_adaptor_row, parent, false);
      holder = new ViewHolder();
      holder.name = (TextView) rowView.findViewById(R.id.small_persons_adaptor_name_textview);
      holder.lessons_rest = (TextView) rowView.findViewById(R.id.small_persons_adaptor_lessons_rest_textview);
      rowView.setTag(holder);
    } else {
      holder = (ViewHolder) rowView.getTag();
    }
    
    holder.name.setText(mPersons.get(position));
    holder.lessons_rest.setText(Integer.toString(25));
    return rowView;
  }
  
  public void add(final String person) {
    mPersons.add(person);
  }
  
  
  public void clear() {
    mPersons.clear();
  }
}

