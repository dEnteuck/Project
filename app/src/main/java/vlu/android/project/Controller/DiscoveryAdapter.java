package vlu.android.project.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import vlu.android.project.Model.Discovery;
import vlu.android.project.R;

public class DiscoveryAdapter extends ArrayAdapter<Discovery> {

    private Context context;
    private ArrayList<Discovery> discoveries;

    public DiscoveryAdapter(@NonNull Context context, ArrayList<Discovery> discoveries) {
        super(context, R.layout.custom_listview2, discoveries);
        this.context = context;
        this.discoveries = discoveries;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_listview2, parent, false);
        }

        ImageView imgDiscovery = view.findViewById(R.id.imgDiscovery);
        TextView nameDiscovery = view.findViewById(R.id.txtNameDiscovery);
        TextView dayDiscovery = view.findViewById(R.id.txtDayDiscovery);

        Discovery discovery = discoveries.get(position);

        int imgResource = getImageResourceByName(String.valueOf(discovery.getImgDiscovery()));
        if (imgResource != 0) {
            imgDiscovery.setImageResource(imgResource);
        } else {
            imgDiscovery.setImageResource(R.drawable.discovery1); // fallback to default image
        }

        nameDiscovery.setText(discovery.getNameDiscovery());
        dayDiscovery.setText(discovery.getDayDiscovery());

        return view;
    }

    int getImageResourceByName(String imageName) {
        return context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
    }
}
