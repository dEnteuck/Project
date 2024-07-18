package vlu.android.project.Controller;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import vlu.android.project.View.BasketballStandingsFragment;
import vlu.android.project.View.FootballStandingsFragment;
import vlu.android.project.View.SoccerStandingFragment;

public class ViewPagerStandingAdapter extends FragmentStateAdapter {
    public ViewPagerStandingAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new SoccerStandingFragment();
            case 1:
                return new BasketballStandingsFragment();
            case 2:
                return new FootballStandingsFragment();
            default:
                return new SoccerStandingFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}