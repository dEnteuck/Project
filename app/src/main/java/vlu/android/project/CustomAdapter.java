package vlu.android.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Match> {
    private final Context context;
    private final ArrayList<Match> matches;

    public CustomAdapter(Context context, ArrayList<Match> matches) {
        super(context, R.layout.custom_listview, matches);
        this.context = context;
        this.matches = matches;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_listview, parent, false);

        ImageView teamLogo1 = rowView.findViewById(R.id.team_logo1);
        ImageView teamLogo2 = rowView.findViewById(R.id.team_logo2);
        TextView teamName = rowView.findViewById(R.id.team_name);
        TextView teamScore = rowView.findViewById(R.id.team_score);
        TextView matchStatus = rowView.findViewById(R.id.ht);

        Match match = matches.get(position);

        // Set data to views
        teamLogo1.setImageResource(match.getTeamLogo1());
        teamLogo2.setImageResource(match.getTeamLogo2());
        teamName.setText(match.getTeamName1() + " vs " + match.getTeamName2());
        teamScore.setText(match.getTeamScore1() + " - " + match.getTeamScore2());
        matchStatus.setText(match.getMatchStatus());

        return rowView;
    }
}
