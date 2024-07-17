package vlu.android.project;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class LineUpViewPagerAdapter extends FragmentStateAdapter {
    public LineUpViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new FormationFragment();
            case 1:
                return new DetailLineUpFragment();
            default:
                return new SoccerFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
