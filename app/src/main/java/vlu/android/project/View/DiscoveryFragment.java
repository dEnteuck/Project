package vlu.android.project.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

import vlu.android.project.Controller.ViewPagerDiscoveryAdapter;
import vlu.android.project.R;

public class DiscoveryFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public DiscoveryFragment() {
        // Required empty public constructor
    }

    public static DiscoveryFragment newInstance(String param1, String param2) {
        DiscoveryFragment fragment = new DiscoveryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discovery, container, false);

        TabLayout tabLayout = view.findViewById(R.id.tabLayout2);
        ViewPager2 viewPager = view.findViewById(R.id.viewPager2);

        ViewPagerDiscoveryAdapter adapter = new ViewPagerDiscoveryAdapter(getActivity());
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            View tabView = LayoutInflater.from(getActivity()).inflate(R.layout.tab_item3, null);
            TextView tabTitle = tabView.findViewById(R.id.tab3_title);
            ImageView tabIcon = tabView.findViewById(R.id.tab3_icon);
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