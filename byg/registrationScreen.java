package michaelkim.byg;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class registrationScreen extends AppCompatActivity {

    EditText rNameField, rEmailField, rPasswordField, rPhoneField;
    Spinner rGradeSpinner;
    Button registerButton;
    TextView regiTextTitle;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    Fragment fragment = null;

    DatabaseReference databaseMentors;
    DatabaseReference databaseStaff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_screen);

        String selected = getIntent().getStringExtra("name");

        regiTextTitle = (TextView) findViewById(R.id.regiTextTitle);
        rNameField = (EditText) findViewById(R.id.mrNameField);
        rEmailField = (EditText) findViewById(R.id.mrEmailField);
        rPasswordField = (EditText) findViewById(R.id.mrPasswordField);
        rPhoneField = (EditText) findViewById(R.id.mrPhoneField);
        rGradeSpinner = (Spinner) findViewById(R.id.mrGradeSpinner);

        if (selected.equals("MENTOR")){
            regiTextTitle.setText("MENTOR REGISTRATION");
        }
        else{
            regiTextTitle.setText("STAFF REGISTRATION");
            TextView rGrade = (TextView) findViewById(R.id.mrGrade);
            rGrade.setVisibility(View.INVISIBLE);
            rGradeSpinner.setVisibility(View.INVISIBLE);
        }

        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        databaseMentors = FirebaseDatabase.getInstance().getReference("Mentor");
        databaseStaff = FirebaseDatabase.getInstance().getReference("Staff");
    }

    public void toHome (View v) {
        final String name = rNameField.getText().toString().trim();
        String email = rEmailField.getText().toString().trim();
        String password = rPasswordField.getText().toString().trim();
        String phone = rPhoneField.getText().toString().trim();

        if (regiTextTitle.getText().equals("MENTOR REGISTRATION")) {
            //String grade = rGradeSpinner.getSelectedItem().toString().trim();
            String grade = "";
            if (!name.equals("")) {
                final mentor newMentor = new mentor(name, email, grade, phone);
                progressDialog.setMessage("Registering User");
                progressDialog.show();
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    databaseMentors.child(firebaseAuth.getCurrentUser().getUid()).setValue(newMentor);
                                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                            .setDisplayName(name)
                                            .build();
                                    firebaseAuth.getCurrentUser().updateProfile(profileUpdates)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(getApplicationContext(), "Mentor Added.", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                    progressDialog.dismiss();
                                    Intent backToHome = new Intent(getApplicationContext(), loginfortheside.class);
                                    startActivity(backToHome);
                                }
                                else {
                                    try {
                                        throw task.getException();
                                    } catch (FirebaseAuthWeakPasswordException e) {
                                        Toast.makeText(getApplicationContext(), "Please make a stronger password", Toast.LENGTH_SHORT).show();
                                    } catch (FirebaseAuthInvalidCredentialsException e) {
                                        Toast.makeText(getApplicationContext(), "Email Address looks a little wrong there", Toast.LENGTH_SHORT).show();
                                    } catch (FirebaseAuthUserCollisionException e) {
                                        Toast.makeText(getApplicationContext(), "Email Address already exists", Toast.LENGTH_SHORT).show();
                                    } catch (Exception e) {

                                    }
                                    progressDialog.dismiss();
                                }
                            }
                        });
            }
            else {
                Toast.makeText(getApplicationContext(), "Please enter all fields.", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            if (!name.equals("")) {
                final staff newStaff = new staff(name, email, phone);
                progressDialog.setMessage("Registering User");
                progressDialog.show();
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    databaseMentors.child(firebaseAuth.getCurrentUser().getUid()).setValue(newStaff);
                                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                            .setDisplayName(name)
                                            .build();
                                    firebaseAuth.getCurrentUser().updateProfile(profileUpdates)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(getApplicationContext(), "Staff Added.", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                    progressDialog.dismiss();
                                    Intent backToHome = new Intent(getApplicationContext(), loginfortheside.class);
                                    startActivity(backToHome);
                                }
                                else {
                                    try {
                                        throw task.getException();
                                    } catch (FirebaseAuthWeakPasswordException e) {
                                        Toast.makeText(getApplicationContext(), "Please make a stronger password", Toast.LENGTH_SHORT).show();
                                    } catch (FirebaseAuthInvalidCredentialsException e) {
                                        Toast.makeText(getApplicationContext(), "Email Address looks a little wrong there", Toast.LENGTH_SHORT).show();
                                    } catch (FirebaseAuthUserCollisionException e) {
                                        Toast.makeText(getApplicationContext(), "Email Address already exists", Toast.LENGTH_SHORT).show();
                                    } catch (Exception e) {

                                    }
                                    progressDialog.dismiss();
                                }
                            }
                        });
            }
            else {
                Toast.makeText(getApplicationContext(), "Please enter all fields.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
