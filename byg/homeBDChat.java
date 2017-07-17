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

public class homeBDChat extends Fragment {

    ListView bdChatList;
    ArrayList<String> sampleNames = new ArrayList<>();
    ArrayList<String> sampleGroupOfNames = new ArrayList<>();
    ArrayList<Integer> sampleImageViews = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bdchat, container, false);

        sampleNames.add("Retreat Staff");
        sampleGroupOfNames.add("Person 1, Person 2, Person 3");
        sampleImageViews.add(R.drawable.circle);
        sampleNames.add("Mentor Chat");
        sampleGroupOfNames.add("Person 1, Person 2, Person 3");
        sampleImageViews.add(R.drawable.circle);
        sampleNames.add("Pastoral Staff Chat");
        sampleGroupOfNames.add("Person 1, Person 2, Person 3");
        sampleImageViews.add(R.drawable.circle);

        bdChatList = (ListView) view.findViewById(R.id.bdChatList);
        bdChatAdapter bdChatAdapter = new bdChatAdapter();
        bdChatList.setAdapter(bdChatAdapter);

        return view;

    }

    private class bdChatAdapter extends BaseAdapter {

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
            view = getActivity().getLayoutInflater().inflate(R.layout.listview_bdchat, null);

            ImageView imageView = (ImageView) view.findViewById(R.id.chatImage);
            imageView.setImageResource(sampleImageViews.get(i));
            TextView name = (TextView) view.findViewById(R.id.groupName);
            name.setText(sampleNames.get(i));
            TextView listOfPeople = (TextView) view.findViewById(R.id.groupOfPeople);
            listOfPeople.setText(sampleGroupOfNames.get(i));

            return view;
        }
    }

}