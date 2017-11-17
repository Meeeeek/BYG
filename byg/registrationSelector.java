package michaelkim.byg;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
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

import org.w3c.dom.Text;

public class registrationSelector extends AppCompatActivity {

    TextView mentorSelect, staffSelect, loginSelect;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_selector);

        mentorSelect = (TextView) findViewById(R.id.rMentorSelect);
        staffSelect = (TextView) findViewById(R.id.rStaffSelect);
        loginSelect = (TextView) findViewById(R.id.rLogin);


        // When the user clicks to register as a mentor.
        mentorSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            registrationCode(12345678);
            }
        });

        mentorSelect.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Pressed
                    mentorSelect.setTextColor(Color.parseColor("#FFFFFF"));
                    mentorSelect.setBackgroundColor(Color.parseColor("#000000"));
                    return false;
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Released
                    mentorSelect.setTextColor(Color.parseColor("#000000"));
                    mentorSelect.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    return false;
                }
                return true;
            }
        });

        staffSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrationCode(87654321);
            }
        });

        staffSelect.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Pressed
                    staffSelect.setTextColor(Color.parseColor("#000000"));
                    staffSelect.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    return false;
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Released
                    staffSelect.setTextColor(Color.parseColor("#FFFFFF"));
                    staffSelect.setBackgroundColor(Color.parseColor("#000000"));
                    return false;
                }
                return true;
            }
        });

        loginSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        loginSelect.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Pressed
                    loginSelect.setTextColor(Color.parseColor("#FFFFFF"));
                    loginSelect.setBackgroundColor(Color.parseColor("#000000"));
                    return false;
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Released
                    loginSelect.setTextColor(Color.parseColor("#000000"));
                    loginSelect.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    return false;
                }
                return true;
            }
        });
    }

    private void registrationCode (final int PIN){
        AlertDialog.Builder mentorPIN = new AlertDialog.Builder(registrationSelector.this);
        View PINView = getLayoutInflater().inflate(R.layout.pin_dialog, null);
        mentorPIN.setView(PINView);
        mentorPIN.setTitle("Enter Registration Code");

        final AlertDialog dialog = mentorPIN.create();
        dialog.show();

        final EditText pin1 = (EditText) PINView.findViewById(R.id.pinNum1);
        final EditText pin2 = (EditText) PINView.findViewById(R.id.pinNum2);
        final EditText pin3 = (EditText) PINView.findViewById(R.id.pinNum3);
        final EditText pin4 = (EditText) PINView.findViewById(R.id.pinNum4);
        final EditText pin5 = (EditText) PINView.findViewById(R.id.pinNum5);
        final EditText pin6 = (EditText) PINView.findViewById(R.id.pinNum6);
        final EditText pin7 = (EditText) PINView.findViewById(R.id.pinNum7);
        final EditText pin8 = (EditText) PINView.findViewById(R.id.pinNum8);

        pin1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (pin1.getText().toString().length() == 1){
                            pin2.requestFocus();
                        }
                    }
                },100);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        pin2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (pin2.getText().toString().length() == 1){
                            pin3.requestFocus();
                        }
                    }
                },100);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        pin3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (pin3.getText().toString().length() == 1){
                            pin4.requestFocus();
                        }
                    }
                },100);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        pin4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (pin4.getText().toString().length() == 1){
                            pin5.requestFocus();
                        }
                    }
                },100);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        pin5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (pin5.getText().toString().length() == 1){
                            pin6.requestFocus();
                        }
                    }
                },100);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        pin6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (pin6.getText().toString().length() == 1){
                            pin7.requestFocus();
                        }
                    }
                },100);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        pin7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (pin7.getText().toString().length() == 1){
                            pin8.requestFocus();
                        }
                    }
                },100);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        pin8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (pin4.getText().toString().length() == 1){
                    int pinValue = Integer.parseInt(
                            pin1.getText().toString() +
                                    pin2.getText().toString() +
                                    pin3.getText().toString() +
                                    pin4.getText().toString() +
                                    pin5.getText().toString() +
                                    pin6.getText().toString() +
                                    pin7.getText().toString() +
                                    pin8.getText().toString());

                    if (pinValue == PIN){
                        if (PIN == 12345678){
                            dialog.dismiss();
                            registerPerson("Mentor");
                        }
                        else if (PIN == 87654321){
                            dialog.dismiss();
                            registerPerson("Staff");
                        }
                    }
                    else {
                        dialog.dismiss();
                        Toast.makeText(getBaseContext(), "INVALID PIN.", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void registerPerson (final String type){
        // Create dialog and rearrange it depending on which registration was selected.
        AlertDialog.Builder registerDialog = new AlertDialog.Builder(registrationSelector.this);
        View registrationView = getLayoutInflater().inflate(R.layout.registration_screen, null);
        registerDialog.setView(registrationView);
        if (type.equals("Mentor")){
            registerDialog.setTitle("Mentor Registration");
        }
        else{
            registerDialog.setTitle("Staff Registration");
        }
        final AlertDialog dialog = registerDialog.create();
        dialog.show();

        // Widget declarations and instantiations.
        final EditText rNameField, rEmailField, rPasswordField, rPhoneField;
        final Spinner rGradeSpinner;
        Button registerButton;

        rNameField = (EditText) registrationView.findViewById(R.id.mrNameField);
        rEmailField = (EditText) registrationView.findViewById(R.id.mrEmailField);
        rPasswordField = (EditText) registrationView.findViewById(R.id.mrPasswordField);
        rPhoneField = (EditText) registrationView.findViewById(R.id.mrPhoneField);
        rGradeSpinner = (Spinner) registrationView.findViewById(R.id.mrGradeSpinner);
        registerButton = (Button) registrationView.findViewById(R.id.regButton);

        String[] grades = {"-", "6B", "6G", "7B", "7G", "8B", "8G", "9B", "9G", "10B", "10G", "11B", "11G", "12B", "12G"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(registrationSelector.this, android.R.layout.simple_spinner_dropdown_item, grades);
        rGradeSpinner.setAdapter(spinnerAdapter);

        // Database references and progress dialog for successful registration.
        final ProgressDialog progressDialog;
        final FirebaseAuth firebaseAuth;

        Fragment fragment = null;

        final DatabaseReference databaseMentors;
        final DatabaseReference databaseStaff;

        // Rearrange layout depending on which registration was selected.
        if (type.equals("Staff")){
            TextView rGrade = (TextView) registrationView.findViewById(R.id.mrGrade);
            rGrade.setVisibility(View.INVISIBLE);
            rGradeSpinner.setVisibility(View.INVISIBLE);
        }

        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        databaseMentors = FirebaseDatabase.getInstance().getReference("Mentor");
        databaseStaff = FirebaseDatabase.getInstance().getReference("Staff");

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = rNameField.getText().toString().trim();
                String email = rEmailField.getText().toString().trim();
                String password = rPasswordField.getText().toString().trim();
                String phone = rPhoneField.getText().toString().trim();

                if (type.equals("Mentor")){
                    String grade = rGradeSpinner.getSelectedItem().toString().trim();
                    if (!name.equals("") && !email.equals("") && !password.equals("") && !phone.equals("") && !grade.equals("-")) {
                        final mentor newMentor = new mentor(name, email, grade, phone);
                        progressDialog.setMessage("Registering User");
                        progressDialog.show();
                        firebaseAuth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(registrationSelector.this, new OnCompleteListener<AuthResult>() {
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
                                            Intent backToHome = new Intent(getApplicationContext(), drawer.class);
                                            startActivity(backToHome);
                                        }
                                        else {
                                            try {
                                                throw task.getException();
                                            } catch (FirebaseAuthWeakPasswordException e) {
                                                Toast.makeText(getApplicationContext(), "Please make a stronger password", Toast.LENGTH_SHORT).show();
                                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                                Toast.makeText(getApplicationContext(), "Email Address has an invalid format", Toast.LENGTH_SHORT).show();
                                            } catch (FirebaseAuthUserCollisionException e) {
                                                Toast.makeText(getApplicationContext(), "Email Address already exists", Toast.LENGTH_SHORT).show();
                                            } catch (Exception e) {

                                            }
                                            progressDialog.dismiss();
                                        }
                                    }
                                });
                    }
                    else if (name.equals("") || email.equals("") || password.equals("") || phone.equals("") || grade.equals("-")){
                        Toast.makeText(getApplicationContext(), "Please enter all fields.", Toast.LENGTH_SHORT).show();
                    }
                }
                else if (type.equals("Staff")){
                    if (!name.equals("") && !email.equals("") && !password.equals("") && !phone.equals("")) {
                        final staff newStaff = new staff(name, email, phone);
                        progressDialog.setMessage("Registering User");
                        progressDialog.show();
                        firebaseAuth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(registrationSelector.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            databaseStaff.child(firebaseAuth.getCurrentUser().getUid()).setValue(newStaff);
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
                                            Intent backToHome = new Intent(getApplicationContext(), drawer.class);
                                            startActivity(backToHome);
                                        } else {
                                            try {
                                                throw task.getException();
                                            } catch (FirebaseAuthWeakPasswordException e) {
                                                Toast.makeText(getApplicationContext(), "Please make a stronger password", Toast.LENGTH_SHORT).show();
                                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                                Toast.makeText(getApplicationContext(), "Email Address has an invalid format", Toast.LENGTH_SHORT).show();
                                            } catch (FirebaseAuthUserCollisionException e) {
                                                Toast.makeText(getApplicationContext(), "Email Address already exists", Toast.LENGTH_SHORT).show();
                                            } catch (Exception e) {

                                            }
                                            progressDialog.dismiss();
                                        }
                                    }
                                });
                    }
                    else if (name.equals("") || email.equals("") || password.equals("") || phone.equals("")){
                        Toast.makeText(getApplicationContext(), "Please enter all fields.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void login(){
        AlertDialog.Builder loginDialog = new AlertDialog.Builder(registrationSelector.this);
        View loginView = getLayoutInflater().inflate(R.layout.activity_main, null);
        loginDialog.setView(loginView);
        loginDialog.setTitle("Enter Login Information");

        final AlertDialog dialog = loginDialog.create();
        dialog.show();

        TextView loginButton;

        final EditText username, password;
        final ProgressDialog progressDialog = new ProgressDialog(this);
        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        username = (EditText) loginView.findViewById(R.id.usernameField);
        password = (EditText) loginView.findViewById(R.id.passwordField);
        loginButton = (TextView) loginView.findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = username.getText().toString().trim();
                String pass = password.getText().toString().trim();

                progressDialog.setMessage("Logging in...");
                progressDialog.show();

                firebaseAuth.signInWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if (task.isSuccessful()){
                                    Intent home = new Intent(getApplicationContext(), drawer.class);
                                    startActivity(home);
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

}
