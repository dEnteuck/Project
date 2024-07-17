package vlu.android.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    Toolbar toolbar;
    SearchView searchView;
    BottomNavigationView bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        addControls();
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false); // Disable default title
        }

        // Load HomeFragment into the FrameLayout
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frmLayout, new HomeFragment())
                    .commit();
        }

        addEvents();
    }

    void addControls() {
        toolbar = findViewById(R.id.toolbar);
        searchView = findViewById(R.id.search_view);
        bottom = findViewById(R.id.navBottom);
    }

    void addEvents() {
        bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.itmHome:
                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.itmDiscovery:
                        //selectedFragment = new DiscoveryFragment();
                        break;
                    case R.id.itmChart:
                        //selectedFragment = new ChartFragment();
                        break;
                    case R.id.itmProfile:
                        selectedFragment = new ProfileFragment();
                        break;
                }
                if (selectedFragment != null) {
                    loadFragment(selectedFragment);
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        MenuItem itmSearch = menu.findItem(R.id.itmSearch);
        SearchView viewSearch = (SearchView) itmSearch.getActionView();
        viewSearch.setQueryHint("Search...");
        viewSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itmSearch:
                searchView.setVisibility(View.VISIBLE);
                return true;
            case R.id.itmNotifi:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frmLayout, fragment);
        ft.commit();
    }
}