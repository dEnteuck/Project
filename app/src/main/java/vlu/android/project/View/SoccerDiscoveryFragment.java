package vlu.android.project.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import vlu.android.project.Controller.DiscoveryAdapter;
import vlu.android.project.Model.Discovery;
import vlu.android.project.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SoccerDiscoveryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SoccerDiscoveryFragment extends Fragment {
    View view;
    ListView listView;
    ArrayList<Discovery> dataLV = new ArrayList<>();
    DiscoveryAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SoccerDiscoveryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SoccerDiscoveryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SoccerDiscoveryFragment newInstance(String param1, String param2) {
        SoccerDiscoveryFragment fragment = new SoccerDiscoveryFragment();
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
        view = inflater.inflate(R.layout.fragment_soccer_discovery, container, false);
        listView = (ListView) view.findViewById(R.id.lstViewDiscovery);
        dataLV.add(new Discovery(R.drawable.img_soccer, "Lampard's position under threat", "04 JAN 2021", "03 JAN 2021"));
        adapter = new DiscoveryAdapter(getActivity(), dataLV);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Discovery discovery = dataLV.get(position);
                DiscoveryDetailFragment detail = DiscoveryDetailFragment.newInstance(
                        discovery.getImgDiscovery(),
                        discovery.getNameDiscovery(),
                        discovery.getDayDiscovery(),
                        discovery.getDetailDiscovery()
                );
                if (getActivity() != null) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, detail)
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
        return view;
    }
}