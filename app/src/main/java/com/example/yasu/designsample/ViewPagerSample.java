package com.example.yasu.designsample;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewPagerSample extends ActionBarActivity {

    private final String LOG_TAG = this.getClass().getSimpleName();

    public class ScreenPagerAdapter extends PagerAdapter{
        private final String LOG_TAG = this.getClass().getSimpleName();
        private LayoutInflater mLayoutInflater = null;
        private final int[] PAGE_RES = new int[]{
                R.layout.single_page,
                R.layout.single_page,
                R.layout.single_page,
        };

        @Override
        public int getCount() {
            return PAGE_RES.length;
        }

        public ScreenPagerAdapter(Context context){
            super();
            mLayoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }



        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Log.i(LOG_TAG,new Throwable().getStackTrace()[0].getMethodName());

            //ページレイアウトの展開
            LinearLayout page = (LinearLayout)mLayoutInflater.inflate(PAGE_RES[position], null);

            //新しいレイアウトからテキスト要素を取る
            TextView tv = (TextView)page.findViewById(R.id.page_text);
            tv.setText("This is page " + position);

            //ViewGroupへ追加
            container.addView(page);
            return page;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            Log.i(LOG_TAG,new Throwable().getStackTrace()[0].getMethodName());
            container.removeView((View)object);
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            //ページ送りの時に必ず呼ばれる
            Log.i(LOG_TAG,new Throwable().getStackTrace()[0].getMethodName());
            return view.equals(o);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_sample);

        ViewPager viewPager = (ViewPager)findViewById(R.id.view_pager);
        PagerAdapter pagerAdapter = new ScreenPagerAdapter(getApplicationContext());

        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_pager_sample, menu);
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
