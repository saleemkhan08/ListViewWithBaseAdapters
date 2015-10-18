package com.thnkin.listviewwithbaseadapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pkmmte.view.CircularImageView;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener{

    Resources mResources;
    ListView blogListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        blogListView = (ListView) findViewById(R.id.listView);
        mResources = getResources();
        blogListView.setAdapter(new BlogRowAdapter(this, new String[7]));
        blogListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, ((TextView)view.findViewById(R.id.title)).getText()+"",Toast.LENGTH_SHORT).show();
    }

    class BlogRow
    {
        String title,desc;
        int dp;
        BlogRow(int dp,String title,String desc )
        {
            this.dp = dp;
            this.title = title;
            this.desc = desc;
        }
    }

    class BlogViewHolder
    {
        CircularImageView dp;
        TextView title;
        TextView desc;
        BlogViewHolder(View v)
        {
            dp = (CircularImageView) v.findViewById(R.id.dp);
            title = (TextView) v.findViewById(R.id.title);
            desc = (TextView) v.findViewById(R.id.desc);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.base_list)
        {
            Intent intent = new Intent(this, BaseAdapterListActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.base_grid)
        {
            Intent intent = new Intent(this, BaseAdapterGridActivity.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.blog_temp)
        {
            Intent intent = new Intent(this, BlogTemplate.class);
            startActivity(intent);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    class BlogRowAdapter extends ArrayAdapter
    {
        Context mContext;
        String[] titles, descs,imageNames;
        BlogRowAdapter(Context context,String[] dummyArray)
        {
            super(context,R.layout.blog_row,R.id.title, dummyArray);

            titles = mResources.getStringArray(R.array.blog_titles);
            descs = mResources.getStringArray(R.array.blog_descriptions);
            imageNames = mResources.getStringArray(R.array.blog_dps);
            mContext = context;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup)
        {
            View recycledView = view;
            BlogViewHolder holder = null;
            if(recycledView == null)
            {
                LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                recycledView = layoutInflater.inflate(R.layout.blog_row,viewGroup,false);
                holder = new BlogViewHolder(recycledView);
                recycledView.setTag(holder);
            }
            else
            {
                holder = (BlogViewHolder) recycledView.getTag();
            }
            holder.title.setText(titles[i]);
            holder.desc.setText(descs[i]);
            holder.dp.setImageResource(mResources.getIdentifier(imageNames[i], "drawable", getPackageName()));
            recycledView.setTag(holder);
            return recycledView;
        }
    }
}