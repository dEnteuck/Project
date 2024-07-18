package vlu.android.project.View;


import vlu.android.project.R;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class DetailStatisticsFragment extends Fragment {

    private LinearLayout statsContainer;
    private static final String TAG = "DetailStatisticsFragment";
    private static final String ARG_MATCH_INDEX = "match_index";
    private int matchIndex;

    private ImageView teamLogo1;
    private ImageView teamLogo2;
    private TextView teamName1;
    private TextView teamName2;
    private TextView teamScore;

    public DetailStatisticsFragment() {
        // Required empty public constructor
    }

    public static DetailStatisticsFragment newInstance(int matchIndex) {
        DetailStatisticsFragment fragment = new DetailStatisticsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_MATCH_INDEX, matchIndex);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            matchIndex = getArguments().getInt(ARG_MATCH_INDEX);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_statistics, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        statsContainer = view.findViewById(R.id.stats_layout);

        teamLogo1 = view.findViewById(R.id.team_logo1);
        teamLogo2 = view.findViewById(R.id.team_logo2);
        teamName1 = view.findViewById(R.id.team_name1);
        teamName2 = view.findViewById(R.id.team_name2);
        teamScore = view.findViewById(R.id.team_score);

        // Load and display data from JSON
        displayMatchStatistics();
    }

    private void displayMatchStatistics() {
        try {
            // Load JSON data from file
            String json = loadJSONFromAsset("matches.json");
            if (json == null) {
                Log.e(TAG, "JSON file not loaded");
                return;
            }
            JSONArray jsonArray = new JSONObject(json).getJSONArray("matches");

            // Ensure matchIndex is within bounds
            if (matchIndex < 0 || matchIndex >= jsonArray.length()) {
                Log.e(TAG, "Invalid match index");
                return;
            }

            // Get the specific match data
            JSONObject matchObject = jsonArray.getJSONObject(matchIndex);

            // Set team logos using resource IDs
            teamLogo1.setImageResource(getLogoResourceId(matchObject.getString("teamLogo1")));
            teamLogo2.setImageResource(getLogoResourceId(matchObject.getString("teamLogo2")));

            // Set team names and score
            teamName1.setText(matchObject.getString("teamName1"));
            teamName2.setText(matchObject.getString("teamName2"));
            teamScore.setText(matchObject.getString("teamScore1") + " - " + matchObject.getString("teamScore2"));

            // Add stat rows for this match
            addStatRow("Shooting", matchObject.getString("shooting1"), matchObject.getString("shooting2"));
            addStatRow("Attacks", matchObject.getString("attacks1"), matchObject.getString("attacks2"));
            addStatRow("Possession", matchObject.getString("possession1"), matchObject.getString("possession2"));
            addStatRow("Cards", matchObject.getString("cards1"), matchObject.getString("cards2"));
            addStatRow("Corners", matchObject.getString("corners1"), matchObject.getString("corners2"));

        } catch (JSONException e) {
            Log.e(TAG, "JSON parsing error", e);
        }
    }

    private void addStatRow(String statLabel, String value1, String value2) {
        View statRowView = getLayoutInflater().inflate(R.layout.stat_row_layout, statsContainer, false);

        TextView statLabelTextView = statRowView.findViewById(R.id.stat_label);
        TextView value1TextView = statRowView.findViewById(R.id.stat_value1);
        TextView value2TextView = statRowView.findViewById(R.id.stat_value2);

        statLabelTextView.setText(statLabel);
        value1TextView.setText(value1);
        value2TextView.setText(value2);

        statsContainer.addView(statRowView);
    }

    private int getLogoResourceId(String logoName) {
        return getResources().getIdentifier(logoName, "drawable", requireActivity().getPackageName());
    }

    private String loadJSONFromAsset(String filename) {
        String json;
        try {
            InputStream is = requireActivity().getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
