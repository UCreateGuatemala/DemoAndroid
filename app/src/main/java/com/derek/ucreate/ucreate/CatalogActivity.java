package com.derek.ucreate.ucreate;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import com.derek.ucreate.ucreate.Adapters.PlaceholderFragment;
import com.derek.ucreate.ucreate.Catalogs.Catalog_1;
import com.derek.ucreate.ucreate.Catalogs.Catalog_2;
import com.derek.ucreate.ucreate.Catalogs.Catalog_3;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class CatalogActivity extends ActionBarActivity implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_catalog);
        Bundle b = getIntent().getExtras();
        getExtrasBundle(b);

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (position==1){
                    Catalog_2 c2 = (Catalog_2) PlaceholderFragment.getInstance(position);
                    int typeTemplate = getIntent().getIntExtra("Template",0);
                    c2.changeTemplate(CatalogActivity.this,typeTemplate);
                }
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }

    private void getExtrasBundle(Bundle b) {
        /*boolean newStart = b.getBoolean("StartFresh");
        Toast.makeText(CatalogActivity.this,""+newStart,
                Toast.LENGTH_SHORT).show();*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.reset) {
            return true;
        }
        else if(id == R.id.showCatalog){
            Bundle b = getIntent().getExtras();

            int templateType = b.getInt("Template");
            int backgroundColor = b.getInt("BackgroundColor");
            int backgroundItemColor = b.getInt("BackgroundItemColor");
            int backgroundCardColor = b.getInt("CardBackgroundColor");
            int textColor = b.getInt("TextColor");
            int logoTextColor = b.getInt("LogoTextColor");
            int orientation = b.getInt("Orientation");
            String logoName = b.getString("LogoName");
            byte[] logo = b.getByteArray("Logo");
            String imageUri = b.getString("ImageUri");
            Boolean buttonBuy = b.getBoolean("buyButton");
            Boolean description = b.getBoolean("Description");
            Boolean ratingStars = b.getBoolean("ratingStars");


            Intent i = new Intent(CatalogActivity.this,CatalogFinalActivity.class);

            i.putExtra("Template",templateType);
            i.putExtra("BackgroundColor",backgroundColor);
            i.putExtra("BackgroundItemColor",backgroundItemColor);
            i.putExtra("CardBackgroundColor",backgroundCardColor);
            i.putExtra("TextColor",textColor);
            i.putExtra("LogoTextColor",logoTextColor);
            i.putExtra("LogoName",logoName);
            i.putExtra("Orientation",orientation);
            i.putExtra("Logo",logo);
            i.putExtra("ImageUri", imageUri);
            i.putExtra("buyButton", buttonBuy);
            i.putExtra("Description",description);
            i.putExtra("ratingStars",ratingStars);

            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
            PlaceholderFragment.setInstances();
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.getInstance(position);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getResources().getString(R.string.step_1);
                case 1:
                    return getResources().getString(R.string.step_2);
                case 2:
                    return getResources().getString(R.string.step_3);
            }
            return null;
        }
    }
}
