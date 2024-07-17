package vlu.android.project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        ViewPager2 viewPager = view.findViewById(R.id.viewPager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity());
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            View tabView = LayoutInflater.from(getActivity()).inflate(R.layout.tab_item, null);
            TextView tabTitle = tabView.findViewById(R.id.tab_title);
            ImageView tabIcon = tabView.findViewById(R.id.tab_icon);
            switch (position) {
                case 0:
                    tabTitle.setText("Soccer");
                    tabIcon.setImageResource(R.drawable.img_soccer);
                    break;
                case 1:
                    tabTitle.setText("Basketball");
                    tabIcon.setImageResource(R.drawable.img_basketball);
                    break;
                case 2:
                    tabTitle.setText("Football");
                    tabIcon.setImageResource(R.drawable.img_football);
                    break;
            }
            tab.setCustomView(tabView);
        }).attach();

        return view;
    }
}
