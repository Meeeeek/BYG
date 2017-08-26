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
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class homeDirectory extends Fragment {

    ListView studentDirectory;
    ArrayList<student> studentList;
    directoryAdapter directoryAdapter;
    DatabaseReference databaseStudents;

    FloatingActionButton fab;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_directory, container, false);

        databaseStudents = FirebaseDatabase.getInstance().getReference("Students");
        studentList = new ArrayList<>();

        fab = (FloatingActionButton) view.findViewById(R.id.addStudent);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addStudent = new Intent(getContext(), addStudent.class);
                startActivity(addStudent);
            }
        });

        studentDirectory = (ListView) view.findViewById(R.id.directory);
        studentDirectory.setAdapter(directoryAdapter);

        return view;

    }

    @Override
    public void onStart() {
        super.onStart();

        databaseStudents.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                studentList.clear();

                for (DataSnapshot studentSnapshot : dataSnapshot.getChildren()){
                    student student = studentSnapshot.getValue(student.class);

                    studentList.add(student);
                }

                directoryAdapter = new directoryAdapter();
                studentDirectory.setAdapter(directoryAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private class directoryAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return studentList.size();
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

            TextView name = (TextView) view.findViewById(R.id.studentName);
            TextView grade = (TextView) view.findViewById(R.id.studentGrade);
            TextView birthday = (TextView) view.findViewById(R.id.studentBirthday);
            TextView address = (TextView) view.findViewById(R.id.studentAddress);

            student student = studentList.get(i);

            name.setText(student.getName());
            grade.setText(student.getGrade());
            birthday.setText(student.getBirthday());
            address.setText(student.getAddress() + ", " + student.getCity() + ", " + student.getState() + ", " + student.getZip());

            return view;
        }
    }

}