package vlu.android.project.View;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

import vlu.android.project.Model.Team;
import vlu.android.project.R;

public class SoccerStandingFragment extends Fragment {

    private static final String TAG = "SoccerStandingFragment";

    public SoccerStandingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_soccer_standing, container, false);

        LinearLayout containerLayout = view.findViewById(R.id.containerLayout);
        List<Team> teams = readJsonFile();
        if (teams != null) {
            // Debugging log to check teams data
            Log.d(TAG, "Teams data: " + new Gson().toJson(teams));

            // Create tables for each league and show only the top 4 teams
            createLeagueTable(containerLayout, "Premier League", getTopTeams(filterTeamsByLeague(teams, "Premier League"), 4), R.drawable.premierleaguelogo);
            createLeagueTable(containerLayout, "LaLiga", getTopTeams(filterTeamsByLeague(teams, "LaLiga"), 4), R.drawable.laligalogo);
        } else {
            Log.e(TAG, "Failed to load teams data");
        }

        return view;
    }

    private List<Team> readJsonFile() {
        String json = null;
        try {
            InputStream is = getContext().getAssets().open("teams.json"); // Place your JSON file in the assets folder
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        Gson gson = new Gson();
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        Type teamListType = new TypeToken<List<Team>>() {}.getType();
        return gson.fromJson(jsonObject.get("teams"), teamListType);
    }

    private List<Team> filterTeamsByLeague(List<Team> teams, String league) {
        // Filter teams by league
        List<Team> filteredTeams = teams.stream().filter(team -> league.equalsIgnoreCase(team.getLeague())).collect(Collectors.toList());
        Log.d(TAG, "Filtered teams for " + league + ": " + new Gson().toJson(filteredTeams));
        return filteredTeams;
    }

    private List<Team> getTopTeams(List<Team> teams, int limit) {
        // Get top teams by points, limited to 'limit' number of teams
        List<Team> topTeams = teams.stream()
                .sorted((team1, team2) -> Integer.compare(team2.getPoints(), team1.getPoints()))
                .limit(limit)
                .collect(Collectors.toList());
        Log.d(TAG, "Top teams: " + new Gson().toJson(topTeams));
        return topTeams;
    }

    private void createLeagueTable(LinearLayout containerLayout, String leagueName, List<Team> leagueTeams, int leagueLogoResId) {
        // Create a new TableLayout for the league
        TableLayout tableLayout = new TableLayout(getContext());
        TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 32, 0, 32);
        tableLayout.setLayoutParams(layoutParams);
        tableLayout.setBackgroundColor(Color.BLACK);
        tableLayout.setStretchAllColumns(true);

        // Add league header with logo
        LinearLayout leagueHeaderLayout = new LinearLayout(getContext());
        leagueHeaderLayout.setOrientation(LinearLayout.HORIZONTAL);
        leagueHeaderLayout.setPadding(16, 16, 16, 16);
        leagueHeaderLayout.setBackgroundColor(Color.DKGRAY);

        ImageView leagueLogo = new ImageView(getContext());
        leagueLogo.setImageResource(leagueLogoResId);
        leagueLogo.setLayoutParams(new LinearLayout.LayoutParams(64, 64));
        leagueHeaderLayout.addView(leagueLogo);

        TextView leagueHeader = new TextView(getContext());
        leagueHeader.setText(leagueName);
        leagueHeader.setTextColor(Color.WHITE);
        leagueHeader.setTextSize(18);
        leagueHeader.setPadding(16, 0, 0, 0);
        leagueHeaderLayout.addView(leagueHeader);

        containerLayout.addView(leagueHeaderLayout);

        // Add table headers
        TableRow headers = new TableRow(getContext());
        addHeaderCell(headers, "");
        addHeaderCell(headers, "Team");
        addHeaderCell(headers, "M");
        addHeaderCell(headers, "W");
        addHeaderCell(headers, "D");
        addHeaderCell(headers, "L");
        addHeaderCell(headers, "GF");
        addHeaderCell(headers, "GA");
        addHeaderCell(headers, "Pts");
        tableLayout.addView(headers);

        // Populate table rows
        for (Team team : leagueTeams) {
            TableRow row = new TableRow(getContext());

            ImageView teamLogo = new ImageView(getContext());
            int imgResId = getResources().getIdentifier(team.getImg(), "drawable", getContext().getPackageName());
            teamLogo.setImageResource(imgResId);
            teamLogo.setLayoutParams(new TableRow.LayoutParams(64, 64));
            row.addView(teamLogo);

            addCell(row, team.getName());
            addCell(row, String.valueOf(team.getMatches()));
            addCell(row, String.valueOf(team.getWins()));
            addCell(row, String.valueOf(team.getDraws()));
            addCell(row, String.valueOf(team.getLosses()));
            addCell(row, String.valueOf(team.getGoalsFor()));
            addCell(row, String.valueOf(team.getGoalsAgainst()));
            addCell(row, String.valueOf(team.getPoints()));

            tableLayout.addView(row);
        }

        containerLayout.addView(tableLayout);
    }

    private void addHeaderCell(TableRow row, String text) {
        TextView textView = new TextView(getContext());
        textView.setText(text);
        textView.setTextColor(Color.WHITE);
        textView.setPadding(16, 16, 16, 16);
        textView.setBackgroundColor(Color.DKGRAY);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        row.addView(textView);
    }

    private void addCell(TableRow row, String text) {
        TextView textView = new TextView(getContext());
        textView.setText(text);
        textView.setTextColor(Color.WHITE);
        textView.setPadding(16, 16, 16, 16);
        row.addView(textView);
    }
}
