package vlu.android.project;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

public class SoccerFragment extends Fragment {
    View view;
    ListView listView;
    ArrayList<Match> matchList;
    CustomAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_soccer, container, false);
        listView = view.findViewById(R.id.listView);

        // Sample data
        matchList = new ArrayList<>();
        matchList.add(new Match(R.drawable.logo_2, R.drawable.logo_1, "Real Madrid", "Barcelona", "1", "0", "HT"));
        matchList.add(new Match(R.drawable.logo_2, R.drawable.logo_1, "Manchester United", "Chelsea", "2", "1", "FT"));

        adapter = new CustomAdapter(getActivity(), matchList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Match selectedMatch = matchList.get(position);
                MatchDetailFragment matchDetailFragment = MatchDetailFragment.newInstance(
                        selectedMatch.getTeamLogo1(),
                        selectedMatch.getTeamLogo2(),
                        selectedMatch.getTeamName1(),
                        selectedMatch.getTeamName2(),
                        selectedMatch.getTeamScore1(),
                        selectedMatch.getTeamScore2(),
                        selectedMatch.getMatchStatus()
                );

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frmLayout, matchDetailFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }
}
