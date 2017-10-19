package michaelkim.byg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class homeTabbed extends Fragment {

    private WebView webView ;
    TextView welcomeTextView;
    Button logoutButton;

    private FirebaseAuth firebaseAuth;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        firebaseAuth = FirebaseAuth.getInstance();

        welcomeTextView = (TextView) view.findViewById(R.id.welcomeText);
        String welcomeText = "Welcome ";

        if (firebaseAuth.getCurrentUser() != null){
            welcomeText += firebaseAuth.getCurrentUser().getDisplayName();
        }

        welcomeTextView.setText(welcomeText);

        //welcomeText.setText("Welcome " + firebaseAuth.getCurrentUser().getDisplayName());


        logoutButton = (Button) view.findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                startActivity(new Intent(getActivity(), drawer.class));
            }
        });


        return view;

    }

}