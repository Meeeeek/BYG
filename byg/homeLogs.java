package michaelkim.byg;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class homeLogs extends Fragment {

    // Widget declaration
    Button submitLog;

    // Widget fields that need to be addressed.
    Spinner gradeSpinner;
    EditText logPRField, logCommentsField, logDayField, logMissingField, logAttendanceField;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_logs, container, false);

        // Widget Instantiation.
        submitLog = (Button) view.findViewById(R.id.submitLog);
        gradeSpinner = (Spinner) view.findViewById(R.id.gradeSpinner);

        logPRField = (EditText) view.findViewById(R.id.logPRField);
        logCommentsField = (EditText) view.findViewById(R.id.logCommentsField);
        logDayField = (EditText) view.findViewById(R.id.logDayField);
        logMissingField = (EditText) view.findViewById(R.id.logMissingField);
        logAttendanceField = (EditText) view.findViewById(R.id.logAttendanceField);

        // Spinner Value Instantiation.
        String[] grades = {"6B", "6G", "7B", "7G", "8B", "8G", "9B", "9G", "10B", "10G", "11B", "11G", "12B", "12G"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, grades);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        gradeSpinner.setAdapter(spinnerAdapter);

        // Submit Button Usage.
        submitLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] emailAddress = {"michaelkim6@yahoo.com"};

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");

                emailIntent.putExtra(Intent.EXTRA_EMAIL, emailAddress);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "LOG for " + gradeSpinner.getSelectedItem() + " (" + getDate() + ")");
                emailIntent.putExtra(Intent.EXTRA_TEXT,
                        "Attendance : " + logAttendanceField.getText() + "\n\n" +
                        "Missing Students and Reasons : \n " + logMissingField.getText() + "\n\n" +
                        "What Did You Do Today? : \n " + logDayField.getText() + "\n\n" +
                        "Prayer Requests : \n " + logPRField.getText() + "\n\n" +
                        "Extra Comments : \n " + logCommentsField.getText());

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    Toast.makeText(getActivity(), "Preparing Email...", Toast.LENGTH_SHORT).show();
                }
                catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getActivity(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;

    }

    private String getDate(){
        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("MM/dd");
        String todaysDate = df.format(c.getTime());

        return todaysDate;
    }

}