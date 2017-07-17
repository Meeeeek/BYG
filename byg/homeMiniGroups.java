package michaelkim.byg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class homeMiniGroups extends Fragment {

    ListView mgList;
    ArrayList<Integer> sampleIndis = new ArrayList<>();
    ArrayList<String> sampleNames = new ArrayList<>();
    ArrayList<String> sampleDescs = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mini_groups, container, false);

        sampleNames.add("Ministry Group 1");
        sampleDescs.add("No new updates.");
        sampleIndis.add(R.drawable.none);
        sampleNames.add("Ministry Group 2");
        sampleDescs.add("2 new updates.");
        sampleIndis.add(R.drawable.exclamation);
        sampleNames.add("Ministry Group 3");
        sampleDescs.add("You are not a part of this ministry group.");
        sampleIndis.add(R.drawable.lock);

        mgList = (ListView) view.findViewById(R.id.mgList);
        mgAdapter mgAdapter = new mgAdapter();
        mgList.setAdapter(mgAdapter);

        return view;

    }

    private class mgAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return sampleNames.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            view = getActivity().getLayoutInflater().inflate(R.layout.listview_minigroups, null);

            ImageView imageView = (ImageView) view.findViewById(R.id.mgIndicator);
            imageView.setImageResource(sampleIndis.get(i));
            TextView name = (TextView) view.findViewById(R.id.minigroupName);
            name.setText(sampleNames.get(i));
            TextView desc = (TextView) view.findViewById(R.id.participationMessage);
            desc.setText(sampleDescs.get(i));

            return view;
        }
    }


}