package vlu.android.project.Controller;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import vlu.android.project.View.BasketballDiscoveryFragment;
import vlu.android.project.View.FootballDiscoveryFragment;
import vlu.android.project.View.SoccerDiscoveryFragment;

public class ViewPagerDiscoveryAdapter extends FragmentStateAdapter {
    public ViewPagerDiscoveryAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new SoccerDiscoveryFragment();
            case 1:
                return new BasketballDiscoveryFragment();
            case 2:
                return new FootballDiscoveryFragment();
            default:
                return new SoccerDiscoveryFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}