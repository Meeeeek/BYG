package michaelkim.byg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class homeConnect extends Fragment {

    TextView nameText;
    EditText nameField, prayerRequestField;
    CheckBox myMentorsCheckBox, staffCheckBox, pastorsCheckBox, anonCheckBox;
    Spinner gradeSpinner;
    Button submitRC;

    DatabaseReference databaseReference;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_connect, container, false);

        nameText = (TextView) view.findViewById(R.id.nameText);

        nameField = (EditText) view.findViewById(R.id.nameField);
        prayerRequestField = (EditText) view.findViewById(R.id.PRField);

        gradeSpinner = (Spinner) view.findViewById(R.id.gradeSpinner);

        myMentorsCheckBox = (CheckBox) view.findViewById(R.id.myMentorsCheckBox);
        staffCheckBox = (CheckBox) view.findViewById(R.id.staffCheckBox);
        pastorsCheckBox = (CheckBox) view.findViewById(R.id.pastorsCheckBox);
        anonCheckBox = (CheckBox) view.findViewById(R.id.anonCheckBox);

        submitRC = (Button) view.findViewById(R.id.uploadButton);

        databaseReference = FirebaseDatabase.getInstance().getReference("Prayer Requests");

        String[] grades = {"6B", "6G", "7B", "7G", "8B", "8G", "9B", "9G", "10B", "10G", "11B", "11G", "12B", "12G"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, grades);
        gradeSpinner.setAdapter(spinnerAdapter);

        anonCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (anonCheckBox.isChecked()){
                    nameText.setVisibility(View.INVISIBLE);
                    nameField.setVisibility(View.INVISIBLE);
                    gradeSpinner.setVisibility(View.INVISIBLE);
                    myMentorsCheckBox.setVisibility(View.INVISIBLE);
                    uncheckAll(anonCheckBox);
                }
                else{
                    nameText.setVisibility(View.VISIBLE);
                    nameField.setVisibility(View.VISIBLE);
                    gradeSpinner.setVisibility(View.VISIBLE);
                    myMentorsCheckBox.setVisibility(View.VISIBLE);
                    staffCheckBox.setVisibility(View.VISIBLE);
                    pastorsCheckBox.setVisibility(View.VISIBLE);
                }
            }
        });

        myMentorsCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myMentorsCheckBox.isChecked()){
                    staffCheckBox.setVisibility(View.INVISIBLE);
                    uncheckAll(myMentorsCheckBox);
                }
                else{
                    staffCheckBox.setVisibility(View.VISIBLE);
                }
            }
        });

        staffCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (staffCheckBox.isChecked()){
                    myMentorsCheckBox.setVisibility(View.INVISIBLE);
                    pastorsCheckBox.setVisibility(View.INVISIBLE);
                    uncheckAll(staffCheckBox);
                }
                else{
                    myMentorsCheckBox.setVisibility(View.VISIBLE);
                    pastorsCheckBox.setVisibility(View.VISIBLE);
                }
            }
        });

        submitRC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!pastorsCheckBox.isChecked() && !myMentorsCheckBox.isChecked() && !staffCheckBox.isChecked()){
                    Toast.makeText(getContext(), "Please Check Off Your Options", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (prayerRequestField.getText().toString().equals("") || (nameField.getText().toString().equals("") && !anonCheckBox.isChecked())) {
                        Toast.makeText(getContext(), "Please Make Sure All Information is Entered.", Toast.LENGTH_SHORT).show();
                    } else {
                        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy h:mm:ss a");
                        Date date = new Date();
                        String todaysDate = dateFormat.format(date);
                        String name = "", grade = "";

                        if (anonCheckBox.isChecked()) {
                            name = "Anonymous";
                        } else {
                            name = nameField.getText().toString();
                        }

                        if (staffCheckBox.isChecked()) {
                            grade = "A";
                        } else if (pastorsCheckBox.isChecked() && myMentorsCheckBox.isChecked()) {
                            grade = gradeSpinner.getSelectedItem().toString() + "P";
                        } else if (myMentorsCheckBox.isChecked()) {
                            grade = gradeSpinner.getSelectedItem().toString();
                        } else if (pastorsCheckBox.isChecked()) {
                            grade = "P";
                        }

                        prayerRequest pR = new prayerRequest(todaysDate.substring(10), todaysDate.substring(0, 10), name, grade, prayerRequestField.getText().toString());
                        databaseReference.child(todaysDate).setValue(pR);
                        Toast.makeText(getContext(), "Prayer Request Sent.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getContext(), homeTabbed.class));
                    }
                }
            }
        });

        return view;

    }

    private void uncheckAll (CheckBox current){

        if (staffCheckBox.getVisibility() == View.INVISIBLE)
            staffCheckBox.setChecked(false);
        if (pastorsCheckBox.getVisibility() == View.INVISIBLE)
            pastorsCheckBox.setChecked(false);
        if (myMentorsCheckBox.getVisibility() == View.INVISIBLE)
            myMentorsCheckBox.setChecked(false);

        current.setChecked(true);

    }

}