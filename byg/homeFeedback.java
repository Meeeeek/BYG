package michaelkim.byg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class homeFeedback extends Fragment {

    DatabaseReference feedbackRef;
    FirebaseDatabase firebaseDatabase;

    EditText feedbackField;
    Button submitFeedback;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_feedback, container, false);

        firebaseDatabase = FirebaseDatabase.getInstance();
        feedbackRef = firebaseDatabase.getReference("Feedback");

        feedbackField = (EditText) view.findViewById(R.id.feedbackField);
        submitFeedback = (Button) view.findViewById(R.id.submitFeedback);

        submitFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (feedbackField.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Please Enter Your Feedback!", Toast.LENGTH_SHORT).show();
                }
                else{
                    DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy h:mm:ss a");
                    Date date = new Date();
                    String todaysDate = dateFormat.format(date);

                    feedback feedback = new feedback(feedbackField.getText().toString());

                    feedbackRef.child(todaysDate).setValue(feedback);

                    Toast.makeText(getContext(), "Feedback Sent!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getContext(), drawer.class));
                }
            }
        });

        return view;

    }

}