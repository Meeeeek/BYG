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

public class homeBulletin extends Fragment {

    ListView pinnedPosts;
    ArrayList<String> sampleNames = new ArrayList<>();
    ArrayList<Integer> sampleImageViews = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        sampleNames.add("Pinned Post 1");
        sampleImageViews.add(R.drawable.sampleimageviewone);
        sampleNames.add("Pinned Post 2");
        sampleImageViews.add(R.drawable.sampleimageviewtwo);
        sampleNames.add("Pinned Post 3");
        sampleImageViews.add(R.drawable.sampleimageviewthree);

        View view = inflater.inflate(R.layout.fragment_bulletin, container, false);
        pinnedPosts = (ListView) view.findViewById(R.id.pinnedPosts);
        bulletinAdapter bulletinAdapter = new bulletinAdapter();
        pinnedPosts.setAdapter(bulletinAdapter);

        return view;

    }

    private class bulletinAdapter extends BaseAdapter {

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
            view = getActivity().getLayoutInflater().inflate(R.layout.listview_bulletin, null);

            ImageView imageView = (ImageView) view.findViewById(R.id.bulletinImage);
            imageView.setImageResource(sampleImageViews.get(i));
            TextView name = (TextView) view.findViewById(R.id.bulletinName);
            name.setText(sampleNames.get(i));

            return view;
        }
    }

}