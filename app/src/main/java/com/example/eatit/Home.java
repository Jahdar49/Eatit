package com.example.eatit;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eatit.Common.Common;
import com.example.eatit.Interface.ItemClickListener;
import com.example.eatit.MainActivity;
import com.example.eatit.Model.Category;
import com.example.eatit.R;
import com.example.eatit.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import io.paperdb.Paper;

    public class Home extends AppCompatActivity
            implements NavigationView.OnNavigationItemSelectedListener {

        FirebaseDatabase database;
        DatabaseReference category;
        TextView txtFullName;
        RecyclerView recycler_menu;
        RecyclerView.LayoutManager linearLayoutManager;

        FirebaseRecyclerAdapter<Category, MenuViewHolder> adapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home);
            Toolbar toolbar = findViewById(R.id.toolbar);
            toolbar.setTitle("Menu");


            // Init FireBase
            database = FirebaseDatabase.getInstance();
            category = database.getReference("Category");

            FloatingActionButton fab = findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent cartIntent = new Intent(Home.this,Cart.class);
                    startActivity(cartIntent);
                }
            });





            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            NavigationView navigationView = findViewById(R.id.nav_view);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationview =  findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);

            // set name for user
            View headerView = navigationView.getHeaderView(0);
            txtFullName = headerView.findViewById(R.id.txtFullName);
            txtFullName.setText(Common.currentUser.getName());


            //Load menu
            recycler_menu = findViewById(R.id.recycler_menu);
            recycler_menu.setHasFixedSize(true);
            linearLayoutManager = new LinearLayoutManager(this);
            recycler_menu.setLayoutManager(linearLayoutManager);
            recycler_menu.setAdapter(adapter);

            loadMenu();
        }

        private void loadMenu() {

            adapter = new FirebaseRecyclerAdapter<Category, MenuViewHolder>(Category.class, R.layout.menu_item, MenuViewHolder.class, category) {
                @Override
                protected void populateViewHolder(MenuViewHolder viewHolder, Category model, int position) {
                    viewHolder.txtMenuName.setText(model.getName());
                    Picasso.with(getBaseContext()).load(model.getImage())
                            .into(viewHolder.imageView);
                    final Category clickItem = model;
                    viewHolder.setItemClickListener(new ItemClickListener() {
                        @Override
                        public void onClick(View view, int position, boolean isLongClick) {
                            //Get CategoryID and send to new Activity
                            Intent foodList = new Intent(Home.this,FoodList.class);
                            foodList.putExtra("CategoryId",adapter.getRef(position).getKey());
                            startActivity(foodList);
                        }
                    });
                }
            };
            recycler_menu.setAdapter(adapter);

        }

        ///
        @Override
        public void onBackPressed() {
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            if(drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            }else {
                super.onBackPressed();
            }

        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present
            getMenuInflater().inflate(R.menu.home, menu);
            return true;
        }
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {


            return super.onOptionsItemSelected(item);
        }

        @SuppressWarnings("StatementWithEmptyBody")
        public boolean onNavaigationItemSelected(MenuItem item) {
            // Handle navigation view item clicks here,
            int id = item.getItemId();

            if(id == R.id.nav_menu) {
                // Handle the camera action
            }else if (id == R.id.nav_cart) {

            }else if (id == R.id.nav_orders) {

            }else if (id == R.id.nav_log_out) {


            }

            // delete remember user and password
            Paper.book().destroy();

            Intent mainActivity = new Intent(Home.this, MainActivity.class);
            mainActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(mainActivity);

            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }

    }



