package vlu.android.project.View;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.List;

import vlu.android.project.Model.Team;
import vlu.android.project.R;

public class LeagueDetailActivity extends AppCompatActivity {

    private static final String TAG = "LeagueDetailActivity";
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_detail);

        LinearLayout detailContainerLayout = findViewById(R.id.detailContainerLayout);

        // Retrieve the list of teams from the intent
        List<Team> leagueTeams = (List<Team>) getIntent().getSerializableExtra("leagueTeams");
        String leagueName = getIntent().getStringExtra("leagueName");
        int leagueLogoResId = getIntent().getIntExtra("leagueLogoResId", 0);

        // Debugging log to check teams data
        Log.d(TAG, "Teams data: " + new Gson().toJson(leagueTeams));

        // Create a table for the league
        createLeagueTable(detailContainerLayout, leagueName, leagueTeams, leagueLogoResId);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeagueDetailActivity.this, SoccerStandingFragment.class);
                startActivity(intent);
            }
        });
    }

    private void createLeagueTable(LinearLayout containerLayout, String leagueName, List<Team> leagueTeams, int leagueLogoResId) {
        // Create a new TableLayout for the league
        TableLayout tableLayout = new TableLayout(this);
        TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 32, 0, 32);
        tableLayout.setLayoutParams(layoutParams);
        tableLayout.setBackgroundColor(Color.BLACK);
        tableLayout.setStretchAllColumns(true);

        // Add league header with logo
        LinearLayout leagueHeaderLayout = new LinearLayout(this);
        leagueHeaderLayout.setOrientation(LinearLayout.HORIZONTAL);
        leagueHeaderLayout.setPadding(16, 16, 16, 16);
        leagueHeaderLayout.setBackgroundColor(Color.DKGRAY);

        ImageView leagueLogo = new ImageView(this);
        leagueLogo.setImageResource(leagueLogoResId);
        leagueLogo.setLayoutParams(new LinearLayout.LayoutParams(64, 64));
        leagueHeaderLayout.addView(leagueLogo);

        TextView leagueHeader = new TextView(this);
        leagueHeader.setText(leagueName);
        leagueHeader.setTextColor(Color.WHITE);
        leagueHeader.setTextSize(18);
        leagueHeader.setPadding(16, 0, 0, 0);
        leagueHeaderLayout.addView(leagueHeader);

        containerLayout.addView(leagueHeaderLayout);

        // Add table headers
        TableRow headers = new TableRow(this);
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
            TableRow row = new TableRow(this);
            ImageView teamLogo = new ImageView(this);
            int imgResId = getResources().getIdentifier(team.getImg(), "drawable", getPackageName());
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
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setTextColor(Color.WHITE);
        textView.setPadding(16, 16, 16, 16);
        textView.setBackgroundColor(Color.DKGRAY);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        row.addView(textView);
    }

    private void addCell(TableRow row, String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setTextColor(Color.WHITE);
        textView.setPadding(16, 16, 16, 16);
        row.addView(textView);
    }
}