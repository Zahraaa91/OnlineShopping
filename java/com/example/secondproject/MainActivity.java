package com.example.secondproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.secondproject.a.AActivityOne;
import com.google.android.material.navigation.NavigationView;
import com.rey.material.widget.FloatingActionButton;


public class MainActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    FloatingActionButton Floatb;
  //  TabItem firstItem,secondItem,thirdItem;
    //  PagerAdapter adapter;

    ListView listView;
    String mTitle[] = {"Apple Products", "SamSung Products ", "Cameras", "Accessories"};
    String mDescription[] = {"Click to View Products ", "Click to View Products ", "Click to View Products", "Click to View Products "};
    int images[] = {R.drawable.pp, R.drawable.s, R.drawable.c, R.drawable.a};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

     /*   pager = findViewById(R.id.viewpager);
        mTabLayout = findViewById(R.id.tablayout);*/
        Floatb= findViewById(R.id.fb);
      //  firstItem = findViewById(R.id.firstItem);
      //  secondItem = findViewById(R.id.secondItem);
      //  thirdItem = findViewById(R.id.thirdItem);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

      /*  adapter = new PagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,mTabLayout.getTabCount());
        pager.setAdapter(adapter);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

*/

        listView =  findViewById(R.id.listView);
        MyAdapter adapter = new MyAdapter(this, mTitle, mDescription, images);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == 0) {
                    Intent myIntent = new Intent(view.getContext(), AppleActivity.class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 1) {
                    Intent myIntent = new Intent(view.getContext(), SamSungActivity.class);
                    startActivityForResult(myIntent, 0);
                }
                if (position == 2) {
                    Intent myIntent = new Intent(view.getContext(), CameraActivity.class);
                    startActivityForResult(myIntent, 0);
                }
                if (position == 3) {
                    Intent myIntent = new Intent(view.getContext(), AccessoriesActivity.class);
                    startActivityForResult(myIntent, 0);
                }


            }
        });
/////////////////////
        Floatb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AActivityOne.class);
                startActivity(intent);
            }
        });
        /////////////////

    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String rTitle[];
        String rDescription[];
        int rImgs[];

        MyAdapter (Context c, String title[], String description[], int imgs[]) {
            super(c, R.layout.row, R.id.textView1, title);
            this.context = c;
            this.rTitle = title;
            this.rDescription = description;
            this.rImgs = imgs;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView images = row.findViewById(R.id.image);
            TextView myTitle = row.findViewById(R.id.textView1);
            TextView myDescription = row.findViewById(R.id.textView2);


            images.setImageResource(rImgs[position]);
            myTitle.setText(rTitle[position]);
            myDescription.setText(rDescription[position]);

            return row;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);

        if(item.getItemId() == R.id.shareTab){
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mma-for-smart-solutions.business.site/"));
            startActivity(intent);

        }
        else if (item.getItemId() == R.id.cartTab)
        {

          //  Intent intent = new Intent(MainActivity.this, AActivityOne.class);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/mma.team.smart/"));
            startActivity(intent);

        }
        else if (item.getItemId() == R.id.aboutTab)
        {

            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);

        }
        else if (item.getItemId() == R.id.settingsTab)
        {

            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);

        }
       // else if (item.getItemId() == R.id.faceTab)
      //  {


        //    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/mma.team.smart/"));

        //    startActivity(intent);

      //  }
        else if (item.getItemId() == R.id.logoutTab)
        {
//////
            {


                Intent intent = new Intent(MainActivity.this, AActivityOne.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
            //////////
            Intent intent = new Intent(MainActivity.this, AActivityOne.class);
            intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }
        return false;
    }
}