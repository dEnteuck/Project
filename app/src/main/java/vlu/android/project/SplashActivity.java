package vlu.android.project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class SplashActivity extends AppCompatActivity {
    Button btnSignIn, btnSignUp;
    View viewBlur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }
    void addControls() {
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        viewBlur = (View) findViewById(R.id.viewBlur);
    }
    void addEvents() {
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoginFragment();
            }
        });
    }
    void showLoginFragment() {
        viewBlur.setVisibility(View.VISIBLE);
        LoginFragment login = new LoginFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(R.anim.slide_in_up, R.anim.stay, R.anim.stay, R.anim.slide_out_down);
        ft.replace(R.id.frmLayout, login);
        ft.addToBackStack(null);
        ft.commit();
        fm.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (fm.getBackStackEntryCount() == 0) {
                    viewBlur.setVisibility(View.GONE);
                }
            }
        });
    }
}
