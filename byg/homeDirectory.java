package michaelkim.byg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class homeDirectory extends Fragment {

    Bundle sIS;

    ListView studentDirectory;
    ArrayList<student> studentList;
    directoryAdapter directoryAdapter;

    DatabaseReference databaseStudents;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;

    Button editStudent, deleteStudent;
    FloatingActionButton fab;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {

        sIS = savedInstanceState;

        View view = inflater.inflate(R.layout.fragment_directory, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseStudents = firebaseDatabase.getReference("Students");
        studentList = new ArrayList<>();

        fab = (FloatingActionButton) view.findViewById(R.id.addStudent);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEditStudent("Add", sIS, -1);
            }
        });

        studentDirectory = (ListView) view.findViewById(R.id.directory);
        studentDirectory.setAdapter(directoryAdapter);

        return view;

    }

    private void addEditStudent(final String action, Bundle savedInstanceState, int position){
        AlertDialog.Builder addStudentDialog = new AlertDialog.Builder(getContext());
        View addStudentDialogView = getLayoutInflater(savedInstanceState).inflate(R.layout.add_student, null);
        addStudentDialog.setView(addStudentDialogView);

        AlertDialog dialog = addStudentDialog.create();
        dialog.show();

        // Widget declaration for the add student dialog.
        final EditText sName, sBirth, sPhone, sAddress, sCity, sState, sZip;
        Button addStudent;

        // Widget instantiation.
        sName = (EditText) addStudentDialogView.findViewById(R.id.studentName);
        sBirth = (EditText) addStudentDialogView.findViewById(R.id.studentBirthday);
        sPhone = (EditText) addStudentDialogView.findViewById(R.id.studentPhone);
        sAddress = (EditText) addStudentDialogView.findViewById(R.id.studentAddress);
        sCity = (EditText) addStudentDialogView.findViewById(R.id.studentCity);
        sState = (EditText) addStudentDialogView.findViewById(R.id.studentState);
        sZip = (EditText) addStudentDialogView.findViewById(R.id.studentZip);
        addStudent = (Button) addStudentDialogView.findViewById(R.id.addStudentButton);

        if (action.equals("Edit")){
            addStudent.setText("EDIT");

            student currStudent = studentList.get(position);

            sName.setText(currStudent.name);
            sBirth.setText(currStudent.birthday);
            sPhone.setText(currStudent.phone);
            sAddress.setText(currStudent.address);
            sCity.setText(currStudent.city);
            sState.setText(currStudent.state);
            sZip.setText(currStudent.zip);
        }

        databaseStudents = FirebaseDatabase.getInstance().getReference("Students");

        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                String name = currentUser.getUid();
                DatabaseReference firebaseDatabase = FirebaseDatabase.getInstance().getReference("Mentor/" + name + "/grade");
                firebaseDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        final String grade = (dataSnapshot.getValue(String.class));
                        String name = sName.getText().toString().trim();
                        String birthday = sBirth.getText().toString().trim();
                        String phone = sPhone.getText().toString().trim();
                        String address = sAddress.getText().toString().trim();
                        String city = sCity.getText().toString().trim();
                        String state = sState.getText().toString().trim();
                        String zip = sZip.getText().toString().trim();

                        if (!TextUtils.isEmpty(name)){
                            student newStudent = new student(name, grade, birthday, phone, address, city, state, zip);
                            databaseStudents.child(name).setValue(newStudent);
                            Toast.makeText(getContext(), "Student " + action + "ed.", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getContext(), "Please enter a name.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        String name = currentUser.getUid();

        DatabaseReference reference = firebaseDatabase.getReference("Mentor/" + name + "/grade");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final String grade = dataSnapshot.getValue(String.class);
                if (grade == null){
                    Toast.makeText(getContext(), "ALL", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(), grade, Toast.LENGTH_SHORT).show();
                }
                databaseStudents.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        studentList.clear();

                        for (DataSnapshot studentSnapshot : dataSnapshot.getChildren()){
                            student student = studentSnapshot.getValue(student.class);
                            if (grade == null){
                                studentList.add(student);
                            }
                            else {
                                if (student.getGrade().equals(grade)) {
                                    studentList.add(student);
                                }
                            }
                        }

                        directoryAdapter = new directoryAdapter();
                        studentDirectory.setAdapter(directoryAdapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
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
            TextView phone = (TextView) view.findViewById(R.id.studentPhone);

            final student student = studentList.get(i);

            name.setText(student.getName());
            grade.setText(student.getGrade());
            birthday.setText(student.getBirthday());
            address.setText(student.getAddress() + ", " + student.getCity() + ", " + student.getState() + ", " + student.getZip());
            phone.setText(student.getPhone());

            editStudent = (Button) view.findViewById(R.id.editStudent);
            deleteStudent = (Button) view.findViewById(R.id.deleteStudent);

            editStudent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addEditStudent("Edit", sIS, i);
                }
            });

            deleteStudent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = studentList.get(i).name;
                    databaseStudents.child(name).removeValue();
                    Toast.makeText(getContext(), "Student Removed.", Toast.LENGTH_SHORT).show();
                }
            });

            return view;
        }
    }

}