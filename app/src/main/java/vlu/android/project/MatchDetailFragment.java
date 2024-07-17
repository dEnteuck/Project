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

public class MatchDetailFragment extends Fragment {

    private static final String ARG_TEAM_LOGO1 = "teamLogo1";
    private static final String ARG_TEAM_LOGO2 = "teamLogo2";
    private static final String ARG_TEAM_NAME1 = "teamName1";
    private static final String ARG_TEAM_NAME2 = "teamName2";
    private static final String ARG_TEAM_SCORE1 = "teamScore1";
    private static final String ARG_TEAM_SCORE2 = "teamScore2";
    private static final String ARG_MATCH_STATUS = "matchStatus";

    private int teamLogo1;
    private int teamLogo2;
    private String teamName1;
    private String teamName2;
    private String teamScore1;
    private String teamScore2;
    private String matchStatus;

    public static MatchDetailFragment newInstance(int teamLogo1, int teamLogo2, String teamName1, String teamName2, String teamScore1, String teamScore2, String matchStatus) {
        MatchDetailFragment fragment = new MatchDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TEAM_LOGO1, teamLogo1);
        args.putInt(ARG_TEAM_LOGO2, teamLogo2);
        args.putString(ARG_TEAM_NAME1, teamName1);
        args.putString(ARG_TEAM_NAME2, teamName2);
        args.putString(ARG_TEAM_SCORE1, teamScore1);
        args.putString(ARG_TEAM_SCORE2, teamScore2);
        args.putString(ARG_MATCH_STATUS, matchStatus);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            teamLogo1 = getArguments().getInt(ARG_TEAM_LOGO1);
            teamLogo2 = getArguments().getInt(ARG_TEAM_LOGO2);
            teamName1 = getArguments().getString(ARG_TEAM_NAME1);
            teamName2 = getArguments().getString(ARG_TEAM_NAME2);
            teamScore1 = getArguments().getString(ARG_TEAM_SCORE1);
            teamScore2 = getArguments().getString(ARG_TEAM_SCORE2);
            matchStatus = getArguments().getString(ARG_MATCH_STATUS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_match_detail, container, false);
        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        ViewPager2 viewPager2 = view.findViewById(R.id.viewPager);

        DetailViewPagerAdapter adapter = new DetailViewPagerAdapter(getActivity());
        viewPager2.setAdapter(adapter);

        ImageView logoView1 = view.findViewById(R.id.imgTeam1);
        ImageView logoView2 = view.findViewById(R.id.imgTeam2);
        TextView nameView1 = view.findViewById(R.id.txtTeam1);
        TextView nameView2 = view.findViewById(R.id.txtTeam2);
        TextView scoreView = view.findViewById(R.id.txtResult);

        logoView1.setImageResource(teamLogo1);
        logoView2.setImageResource(teamLogo2);
        nameView1.setText(teamName1);
        nameView2.setText(teamName2);
        scoreView.setText(teamScore1 + " - " + teamScore2);

        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            View tabView = LayoutInflater.from(getActivity()).inflate(R.layout.tab_item2, null);
            TextView tabTitle = tabView.findViewById(R.id.tab_title);
            switch (position) {
                case 0:
                    tabTitle.setText("Match Detail");
                    break;
                case 1:
                    tabTitle.setText("Line Up");
                    break;
            }
            tab.setCustomView(tabView);
        }).attach();

        return view;
    }
}
