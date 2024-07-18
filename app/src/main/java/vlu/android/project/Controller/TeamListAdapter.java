package vlu.android.project.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vlu.android.project.Model.Team;
import vlu.android.project.R;

public class TeamListAdapter extends BaseAdapter {

    private Context context;
    private List<Team> teams;
    private LayoutInflater inflater;

    public TeamListAdapter(Context context, List<Team> teams) {
        this.context = context;
        this.teams = teams;
        this.inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return teams.size();
    }

    @Override
    public Object getItem(int position) {
        return teams.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.team_list_item, null);

        ImageView teamLogo = view.findViewById(R.id.teamLogo);
        TextView teamName = view.findViewById(R.id.teamName);
        TextView matches = view.findViewById(R.id.teamMatches);
        TextView wins = view.findViewById(R.id.teamWins);
        TextView draws = view.findViewById(R.id.teamDraws);
        TextView losses = view.findViewById(R.id.teamLosses);
        TextView goalsFor = view.findViewById(R.id.teamGoalsFor);
        TextView goalsAgainst = view.findViewById(R.id.teamGoalsAgainst);
        TextView points = view.findViewById(R.id.teamPoints);

        Team team = teams.get(position);

        int imgResId = context.getResources().getIdentifier(team.getImg(), "drawable", context.getPackageName());
        teamLogo.setImageResource(imgResId);
        teamName.setText(team.getName());
        matches.setText(String.valueOf(team.getMatches()));
        wins.setText(String.valueOf(team.getWins()));
        draws.setText(String.valueOf(team.getDraws()));
        losses.setText(String.valueOf(team.getLosses()));
        goalsFor.setText(String.valueOf(team.getGoalsFor()));
        goalsAgainst.setText(String.valueOf(team.getGoalsAgainst()));
        points.setText(String.valueOf(team.getPoints()));

        return view;
    }
}
