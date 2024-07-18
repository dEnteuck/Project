package vlu.android.project.View;

import vlu.android.project.R;
import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import vlu.android.project.Model.Match;
import vlu.android.project.Controller.CustomAdapter;

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

        // Load data from JSON file
        matchList = loadMatchesFromAssets();

        if (matchList != null) {
            adapter = new CustomAdapter(getActivity(), matchList);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Match selectedMatch = matchList.get(position);
                    MatchDetailFragment matchDetailFragment = MatchDetailFragment.newInstance(
                            getLogoResourceId(selectedMatch.getTeamLogo1()),
                            getLogoResourceId(selectedMatch.getTeamLogo2()),
                            selectedMatch.getTeamName1(),
                            selectedMatch.getTeamName2(),
                            selectedMatch.getTeamScore1(),
                            selectedMatch.getTeamScore2(),
                            selectedMatch.getMatchStatus()
                    );

                    DetailStatisticsFragment detailStatisticsFragment = DetailStatisticsFragment.newInstance(position);

                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.frmLayout, detailStatisticsFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });
        } else {
            Log.e("SoccerFragment", "Failed to load matches from JSON");
        }

        return view;
    }

    private ArrayList<Match> loadMatchesFromAssets() {
        ArrayList<Match> matchList = new ArrayList<>();
        try {
            InputStream is = getActivity().getAssets().open("matches.json");
            Gson gson = new Gson();
            InputStreamReader reader = new InputStreamReader(is);

            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            JsonArray matchesArray = jsonObject.getAsJsonArray("matches");

            Type matchListType = new TypeToken<List<Match>>() {}.getType();
            matchList = gson.fromJson(matchesArray, matchListType);

            is.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("SoccerFragment", "Error reading matches.json", e);
        }
        return matchList;
    }

    private int getLogoResourceId(String resourceNameOrUrl) {
        return getResources().getIdentifier(resourceNameOrUrl, "drawable", getActivity().getPackageName());
    }
}
