package michaelkim.byg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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

    FloatingActionButton addPost;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bulletin, container, false);
        pinnedPosts = (ListView) view.findViewById(R.id.pinnedPosts);
        bulletinAdapter bulletinAdapter = new bulletinAdapter();
        pinnedPosts.setAdapter(bulletinAdapter);

        addPost = (FloatingActionButton) view.findViewById(R.id.addPinned);
        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addPost = new Intent(getActivity(), addPost.class);
                startActivity(addPost);
            }
        });

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