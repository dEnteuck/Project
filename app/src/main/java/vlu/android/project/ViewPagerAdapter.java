package vlu.android.project;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new SoccerFragment();
            case 1:
                return new BasketballFragment();
            case 2:
                return new FootballFragment();
            default:
                return new SoccerFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
