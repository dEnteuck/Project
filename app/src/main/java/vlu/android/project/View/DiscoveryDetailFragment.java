package vlu.android.project.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import vlu.android.project.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DiscoveryDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiscoveryDetailFragment extends Fragment {
    View view;
    ImageView imgDiscovery;
    TextView txtNameDiscovery, txtDayDiscovery, txtDetailDiscovery;
    String name, day, detail;
    int img;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DiscoveryDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param imgDiscovery
     * @param param1          Parameter 1.
     * @param param2          Parameter 2.
     * @param detailDiscovery
     * @return A new instance of fragment DiscoveryDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DiscoveryDetailFragment newInstance(int imgDiscovery, String param1, String param2, String detailDiscovery) {
        DiscoveryDetailFragment fragment = new DiscoveryDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_discovery_detail, container, false);
        imgDiscovery = (ImageView) view.findViewById(R.id.imgDiscovery);
        txtNameDiscovery = (TextView) view.findViewById(R.id.txtNameDiscovery);
        txtDayDiscovery = (TextView) view.findViewById(R.id.txtDayDiscovery);
        txtDetailDiscovery = (TextView) view.findViewById(R.id.txtDetailDiscovery);

        imgDiscovery.setImageResource(img);
        txtNameDiscovery.setText(name);
        txtDayDiscovery.setText(day);
        txtDetailDiscovery.setText(detail);
        return view;
    }
}