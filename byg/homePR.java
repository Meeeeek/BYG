package michaelkim.byg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class homePR extends Fragment {

    ListView prList;
    ArrayList<String> sampleNames = new ArrayList<>();
    ArrayList<String> samplePRs = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pr, container, false);

        sampleNames.add("Person 1");
        samplePRs.add("Sample PR Here.");
        sampleNames.add("Person 2");
        samplePRs.add("Sample PR Here");

        prList = (ListView) view.findViewById(R.id.prList);
        prAdapter prAdapter = new prAdapter();
        prList.setAdapter(prAdapter);

        return view;

    }

    private class prAdapter extends BaseAdapter {

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
            view = getActivity().getLayoutInflater().inflate(R.layout.listview_pr, null);

            TextView name = (TextView) view.findViewById(R.id.stuName);
            name.setText(sampleNames.get(i));
            TextView pr = (TextView) view.findViewById(R.id.pReq);
            pr.setText(samplePRs.get(i));

            return view;
        }
    }

}