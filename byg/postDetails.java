package michaelkim.byg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class postDetails extends AppCompatActivity {

    TextView postName, postAuthor, postText, postTimeAndDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_detail_layout);

        postName = (TextView) findViewById(R.id.detailSubjectText);
        postAuthor = (TextView) findViewById(R.id.detailAuthor);
        postText = (TextView) findViewById(R.id.detailText);
        postTimeAndDate = (TextView) findViewById(R.id.detailTimeDate);

        Bundle strings = getIntent().getExtras();

        postName.setText(strings.getString("postName"));
        postAuthor.setText(strings.getString("postAuthor"));
        postText.setText(strings.getString("postText"));
        postTimeAndDate.setText(strings.getString("postDate") + " " + strings.getString("postTime"));

    }

}