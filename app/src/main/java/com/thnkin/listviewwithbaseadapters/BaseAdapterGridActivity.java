package com.thnkin.listviewwithbaseadapters;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class BaseAdapterGridActivity extends ActionBarActivity {

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_adapter_grid);
        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new GridViewBaseAdapter(this));
    }

    class GridBox {
        int image;
        String title;

        GridBox(int image, String title) {
            this.image = image;
            this.title = title;
        }
    }

    class GridViewBaseAdapter extends BaseAdapter {

        ArrayList<GridBox> gridList;
        GridBoxHolder holder = null;
        Context mContext;
        Resources mResources;

        GridViewBaseAdapter(Context context) {
            mContext = context;
            mResources = context.getResources();
            gridList = new ArrayList<GridBox>();
            String images[] = mResources.getStringArray(R.array.blog_dps);
            String titles[] = mResources.getStringArray(R.array.blog_titles);
            for (int i = 0; i < titles.length; i++) {
                gridList.add(new GridBox(mResources.getIdentifier(images[i], "drawable", getPackageName())
                        , titles[i]));
            }
        }

        @Override
        public int getCount() {
            return gridList.size();
        }

        @Override
        public Object getItem(int i) {
            return gridList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View recyclerView = view;
            holder = null;
            if (recyclerView == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                recyclerView = inflater.inflate(R.layout.grid_box, viewGroup, false);
                holder = new GridBoxHolder(recyclerView);
                recyclerView.setTag(holder);
            } else {
                holder = (GridBoxHolder) recyclerView.getTag();
            }
            GridBox temp = gridList.get(i);
            holder.title.setText(temp.title);
            holder.img.setImageResource(temp.image);
            return recyclerView;
        }
    }

    class GridBoxHolder {
        ImageView img;
        TextView title;

        GridBoxHolder(View parentView) {
            img = (ImageView) parentView.findViewById(R.id.gridImg);
            title = (TextView) parentView.findViewById(R.id.gridTitle);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_base_adapter_grid, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
