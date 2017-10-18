package michaelkim.byg;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
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

public class registration extends AppCompatActivity {

    EditText rNameField, rEmailField, rPasswordField, rAddressField, rPhoneField, rCityField, rZipField;
    Spinner rGradeSpinner, rStateSpinner;
    Button registerButton, rBirthdayField;
    TextView regiTextTitle;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    Fragment fragment = null;

    DatabaseReference databaseMentors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_screen);

        regiTextTitle = (TextView) findViewById(R.id.regiTextTitle);
        rNameField = (EditText) findViewById(R.id.rNameField);
        rEmailField = (EditText) findViewById(R.id.rEmailField);
        rPasswordField = (EditText) findViewById(R.id.rPasswordField);
        rAddressField = (EditText) findViewById(R.id.rAddressField);
        rPhoneField = (EditText) findViewById(R.id.rPhoneField);
        rCityField = (EditText) findViewById(R.id.rCityField);
        rZipField = (EditText) findViewById(R.id.rZipField);
        rGradeSpinner = (Spinner) findViewById(R.id.rGradeSpinner);
        rStateSpinner = (Spinner) findViewById(R.id.rStateSpinner);
        registerButton = (Button) findViewById(R.id.regButton);
        rBirthdayField = (Button) findViewById(R.id.rBirthdayField);

        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        databaseMentors = FirebaseDatabase.getInstance().getReference("Mentor");
    }

    public void toHome (View v){
        final String name = rNameField.getText().toString().trim();
        String email = rEmailField.getText().toString().trim();
        String password = rPasswordField.getText().toString().trim();
        String grade = rGradeSpinner.getSelectedItem().toString().trim();
        String birthday = rBirthdayField.getText().toString().trim();
        String phone = rPhoneField.getText().toString().trim();
        String address = rAddressField.getText().toString().trim();
        String city = rCityField.getText().toString().trim();
        String state = rStateSpinner.getSelectedItem().toString().trim();
        String zip = rZipField.getText().toString().trim();
        if (regiTextTitle.getText().equals("STUDENT REGISTRATION")){
            Toast.makeText(getApplicationContext(), "Student Added.", Toast.LENGTH_SHORT).show();
        }
        else if (regiTextTitle.getText().equals("MENTOR REGISTRATION")){
            if (!TextUtils.isEmpty(name) || !TextUtils.isEmpty(email) || !TextUtils.isEmpty(password) || !TextUtils.isEmpty(grade) || !TextUtils.isEmpty(birthday) || !TextUtils.isEmpty(phone) ||
                    !TextUtils.isEmpty(address) || !TextUtils.isEmpty(city) || !TextUtils.isEmpty(state) || !TextUtils.isEmpty(zip)){
                final mentor newMentor = new mentor(name, email, grade, birthday, phone, address, city, state, zip);
                progressDialog.setMessage("Registering User");
                progressDialog.show();
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    databaseMentors.child(firebaseAuth.getCurrentUser().getUid()).setValue(newMentor);
                                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                            .setDisplayName(name)
                                            .build();
                                    firebaseAuth.getCurrentUser().updateProfile(profileUpdates)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()){
                                                        Toast.makeText(getApplicationContext(), "Mentor Added.", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                    progressDialog.dismiss();
                                    Intent backToHome = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(backToHome);
                                }
                                else{
                                    try {
                                        throw task.getException();
                                    } catch(FirebaseAuthWeakPasswordException e) {
                                        Toast.makeText(getApplicationContext(), "Please make a stronger password", Toast.LENGTH_SHORT).show();
                                    } catch(FirebaseAuthInvalidCredentialsException e) {
                                        Toast.makeText(getApplicationContext(), "Email Address looks a little wrong there", Toast.LENGTH_SHORT).show();
                                    } catch(FirebaseAuthUserCollisionException e) {
                                        Toast.makeText(getApplicationContext(), "Email Address already exists", Toast.LENGTH_SHORT).show();
                                    } catch(Exception e) {

                                    }
                                    progressDialog.dismiss();
                                }
                            }
                        });
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Please enter all fields.", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Staff Added.", Toast.LENGTH_SHORT).show();
                }
    }

    @Override
    public void onBackPressed() {
            Intent backToHome = new Intent(this, MainActivity.class);
            startActivity(backToHome);
    }
}
