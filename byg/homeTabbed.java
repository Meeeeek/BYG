package michaelkim.byg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import java.lang.reflect.Array;
import java.util.ArrayList;

public class homeTabbed extends Fragment {

    TextView welcomeTextView;
    Button logoutButton;
    ListView announcementList;
    ArrayList<announcement> announcements = new ArrayList<>();
    ArrayList<String> announcementTitles = new ArrayList<>();
    ArrayAdapter<String> announcementAdapter;

    FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        firebaseAuth = firebaseAuth.getInstance();
        firebaseDatabase = firebaseDatabase.getInstance();

        welcomeTextView = (TextView) view.findViewById(R.id.welcomeText);
        logoutButton = (Button) view.findViewById(R.id.logoutButton);
        String welcomeText = "Welcome ";

        if (firebaseAuth.getCurrentUser() != null){
            welcomeText += firebaseAuth.getCurrentUser().getDisplayName();
        }
        else{
            logoutButton.setVisibility(View.INVISIBLE);
        }

        welcomeTextView.setText(welcomeText);


        logoutButton = (Button) view.findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                startActivity(new Intent(getActivity(), drawer.class));
            }
        });

        announcementList = (ListView) view.findViewById(R.id.announcementsList);
        announcementAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, announcementTitles);
        announcementList.setAdapter(announcementAdapter);

        announcementList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder prDialog = new AlertDialog.Builder(getContext());
                View prView = getLayoutInflater(savedInstanceState).inflate(R.layout.pr_layout_details, null);
                prDialog.setView(prView);

                AlertDialog dialog = prDialog.create();
                dialog.show();

                TextView announcementName, gradeText, announcementDetails, announcementPerson;

                announcementName = (TextView) prView.findViewById(R.id.prName);
                gradeText = (TextView) prView.findViewById(R.id.prGrade);
                announcementDetails = (TextView) prView.findViewById(R.id.prDetails);
                announcementPerson = (TextView) prView.findViewById(R.id.prTimeDate);

                // Set dialog's textviews to the selected announcement.
                announcementName.setText(announcements.get(i).announcement);
                announcementDetails.setText(announcements.get(i).details);

                // Hide unnecessary values.
                announcementPerson.setVisibility(View.INVISIBLE);
                gradeText.setVisibility(View.INVISIBLE);
            }
        });

        return view;

    }

    @Override
    public void onStart() {
        super.onStart();

        // Get the database reference for the currently logged in user's grade.
        DatabaseReference reference = firebaseDatabase.getReference("Announcements");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                announcements.clear();
                // Look through the list of announcements.
                for (DataSnapshot announcementSnapshot : dataSnapshot.getChildren()){
                    announcement currAnnouncement = announcementSnapshot.getValue(announcement.class);
                    if (firebaseAuth.getCurrentUser() != null && currAnnouncement.person.equals("Staff")){
                        announcements.add(currAnnouncement);
                        announcementTitles.add(currAnnouncement.announcement);
                    }
                    else if (currAnnouncement.person.equals("Student")){
                        announcements.add(currAnnouncement);
                        announcementTitles.add(currAnnouncement.announcement);
                    }
                    announcementAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, announcementTitles);
                    announcementList.setAdapter(announcementAdapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

}