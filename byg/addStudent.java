package michaelkim.byg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class addStudent extends AppCompatActivity {

    EditText sName, sBirth, sPhone, sAddress, sCity, sState, sZip;
    Button addStudent;

    DatabaseReference databaseStudents;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student);

        final Bundle editStudent = getIntent().getExtras();
        firebaseAuth = FirebaseAuth.getInstance();

        sName = (EditText) findViewById(R.id.studentName);
        sBirth = (EditText) findViewById(R.id.studentBirthday);
        sPhone = (EditText) findViewById(R.id.studentPhone);
        sAddress = (EditText) findViewById(R.id.studentAddress);
        sCity = (EditText) findViewById(R.id.studentCity);
        sState = (EditText) findViewById(R.id.studentState);
        sZip = (EditText) findViewById(R.id.studentZip);
        addStudent = (Button) findViewById(R.id.addStudentButton);

        if (editStudent != null){
            addStudent.setText("EDIT");
            sName.setText(editStudent.getString("studentName"));
            sBirth.setText(editStudent.getString("studentBirthday"));
            sPhone.setText(editStudent.getString("studentPhone"));
            sAddress.setText(editStudent.getString("studentAddress"));
            sCity.setText(editStudent.getString("studentCity"));
            sState.setText(editStudent.getString("studentState"));
            sZip.setText(editStudent.getString("studentZip"));
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
                            if (editStudent == null) {
                                Toast.makeText(getApplicationContext(), "Student Added.", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Student Updated.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Please enter a name.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }

}
