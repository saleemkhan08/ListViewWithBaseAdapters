package com.thnkin.listviewwithbaseadapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.pkmmte.view.CircularImageView;

import java.util.ArrayList;


public class BaseAdapterListActivity extends ActionBarActivity {

    ListView blogListView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_recall);
        blogListView = (ListView) findViewById(R.id.listView1);
        blogListView.setAdapter(new BlogViewAdapter(this));
    }

    class BlogRow
    {
        int image;
        String title, desc;
        BlogRow(int image,String title,String desc){
            this.image = image;
            this.title = title;
            this.desc = desc;
        }
    }
    class BlogViewAdapter extends BaseAdapter {

        ArrayList<BlogRow> blogList;
        Context mContext;
        Resources mResources;
        BlogViewAdapter(Context context)
        {
            mContext =context;
            mResources = context.getResources();
            blogList = new ArrayList<BlogRow>();
            String[] title = mResources.getStringArray(R.array.blog_titles),
            desc = mResources.getStringArray(R.array.blog_descriptions),
            image =  mResources.getStringArray(R.array.blog_dps);
            int len = title.length;
            for(int i=0;i<len; i++)
            {
                blogList.add(new BlogRow(mResources.getIdentifier(image[i],"drawable", getPackageName()),title[i],desc[i]));
            }
        }
        @Override
        public int getCount() {
            return blogList.size();
        }

        @Override
        public Object getItem(int i) {
            return blogList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup)
        {
            View recyclerView = view;
            BlogViewHolder holder = null;
            if(recyclerView == null)
            {
                LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                recyclerView = layoutInflater.inflate(R.layout.blog_row, viewGroup, false);
                holder = new BlogViewHolder(recyclerView);
                recyclerView.setTag(holder);
            }
            else
            {
                holder = (BlogViewHolder) recyclerView.getTag();
            }
            BlogRow temp = blogList.get(i);
            holder.desc.setText(temp.desc);
            holder.title.setText(temp.title);
            holder.dp.setImageResource(temp.image);
            return recyclerView;
        }
    }

    class BlogViewHolder{
        CircularImageView dp;
        TextView title, desc;
        BlogViewHolder(View v)
        {
            title = (TextView) v.findViewById(R.id.title);
            desc = (TextView) v.findViewById(R.id.desc);
            dp = (CircularImageView) v.findViewById(R.id.dp);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_activity_recall, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
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
