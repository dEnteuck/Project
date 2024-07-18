package vlu.android.project.View;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import vlu.android.project.Controller.DiscoveryAdapter;
import vlu.android.project.Model.Discovery;
import vlu.android.project.R;

public class SoccerDiscoveryFragment extends Fragment {

    ListView listView;
    ArrayList<Discovery> discoveries = new ArrayList<>();
    DiscoveryAdapter adapter;
    String fileName = "discovery.json";

    public SoccerDiscoveryFragment() {
        // Required empty public constructor
    }

    public static SoccerDiscoveryFragment newInstance() {
        return new SoccerDiscoveryFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_soccer_discovery, container, false);

        listView = view.findViewById(R.id.lstViewDiscovery);

        try {
            discoveries = loadJSONData(fileName);
            if (discoveries.isEmpty()) {
                Log.e("SoccerDiscoveryFragment", "No data loaded from JSON");
            } else {
                Log.i("SoccerDiscoveryFragment", "Data loaded: " + discoveries.size() + " items");
            }
            adapter = new DiscoveryAdapter(getActivity(), discoveries);
            listView.setAdapter(adapter);
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            Discovery discovery = discoveries.get(position);
            DiscoveryDetailFragment detailFragment = DiscoveryDetailFragment.newInstance(
                    discovery.getImgDiscovery(),
                    discovery.getNameDiscovery(),
                    discovery.getDetailDiscovery(),
                    discovery.getDayDiscovery()
            );

            FragmentManager fm = getActivity().getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frmLayout, detailFragment);
            ft.addToBackStack("null");
            ft.commit();
        });

        return view;
    }

    ArrayList<Discovery> loadJSONData(String fileName) throws IOException, JSONException {
        ArrayList<Discovery> list = new ArrayList<>();
        InputStream inputStream = getActivity().getAssets().open(fileName);
        int size = inputStream.available();
        byte[] buffer = new byte[size];
        inputStream.read(buffer);
        inputStream.close();
        String result = new String(buffer, "UTF-8");

        JSONObject jsonObjectDiscovery = new JSONObject(result);
        JSONArray jsonArrayDiscovery = jsonObjectDiscovery.getJSONArray("discovery");
        for (int i = 0; i < jsonArrayDiscovery.length(); i++) {
            JSONObject discovery = jsonArrayDiscovery.getJSONObject(i);
            String img = discovery.getString("img");
            int resID = getResources().getIdentifier(img, "drawable", getActivity().getPackageName());
            String name = discovery.getString("name");
            String day = discovery.getString("day");
            String detail = discovery.getString("detail");
            Discovery dis = new Discovery(resID, name, day, detail);
            list.add(dis);
        }
        return list;
    }
}