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

public class homeDirectory extends Fragment {

    ListView studentDirectory;
    ArrayList<String> sampleNames = new ArrayList<>();
    ArrayList<String> sampleGrades = new ArrayList<>();
    ArrayList<String> sampleBirthdays = new ArrayList<>();
    ArrayList<String> sampleAddresses = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_directory, container, false);

        sampleNames.add("Student 1");
        sampleGrades.add("6B");
        sampleBirthdays.add("01/10/2013");
        sampleAddresses.add("12331 Address St., Town, State, Zip");
        sampleNames.add("Student 2");
        sampleGrades.add("8G");
        sampleBirthdays.add("01/10/2011");
        sampleAddresses.add("543143 Address St., Town, State, Zip");
        sampleNames.add("Student 3");
        sampleGrades.add("9G");
        sampleBirthdays.add("01/10/2010");
        sampleAddresses.add("3421467 Address St., Town, State, Zip");
        sampleNames.add("Student 4");
        sampleGrades.add("12B");
        sampleBirthdays.add("01/10/2007");
        sampleAddresses.add("18520 Address St., Town, State, Zip");

        studentDirectory = (ListView) view.findViewById(R.id.directory);
        directoryAdapter directoryAdapter = new directoryAdapter();
        studentDirectory.setAdapter(directoryAdapter);

        return view;

    }

    private class directoryAdapter extends BaseAdapter {

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
            view = getActivity().getLayoutInflater().inflate(R.layout.listview_directory, null);

            TextView studentName = (TextView) view.findViewById(R.id.studentName);
            studentName.setText(sampleNames.get(i));
            TextView studentGrade = (TextView) view.findViewById(R.id.studentGrade);
            studentGrade.setText(sampleGrades.get(i));
            TextView studentBirthday = (TextView) view.findViewById(R.id.studentBirthday);
            studentBirthday.setText(sampleBirthdays.get(i));
            TextView studentAddress = (TextView) view.findViewById(R.id.studentAddress);
            studentAddress.setText(sampleAddresses.get(i));


            return view;
        }
    }

}