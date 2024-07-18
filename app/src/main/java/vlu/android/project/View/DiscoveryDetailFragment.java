package vlu.android.project.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import vlu.android.project.R;

public class DiscoveryDetailFragment extends Fragment {

    private static final String ARG_IMG = "img";
    private static final String ARG_NAME = "name";
    private static final String ARG_DETAIL = "detail";
    private static final String ARG_DAY = "day";

    ImageView imgDiscovery;
    TextView txtNameDiscovery, txtDayDiscovery, txtDetailDiscovery;

    public DiscoveryDetailFragment() {
        // Required empty public constructor
    }

    public static DiscoveryDetailFragment newInstance(int img, String name, String detail, String day) {
        DiscoveryDetailFragment fragment = new DiscoveryDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_IMG, img);
        args.putString(ARG_NAME, name);
        args.putString(ARG_DETAIL, detail);
        args.putString(ARG_DAY, day);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discovery_detail, container, false);

        imgDiscovery = view.findViewById(R.id.imgDiscovery);
        txtNameDiscovery = view.findViewById(R.id.txtNameDiscovery);
        txtDayDiscovery = view.findViewById(R.id.txtDayDiscovery);
        txtDetailDiscovery = view.findViewById(R.id.txtDetailDiscovery);

        Bundle args = getArguments();
        if (args != null) {
            imgDiscovery.setImageResource(args.getInt(ARG_IMG));
            txtNameDiscovery.setText(args.getString(ARG_NAME));
            txtDayDiscovery.setText(args.getString(ARG_DAY));
            txtDetailDiscovery.setText(args.getString(ARG_DETAIL));
        }

        return view;
    }
}