package michaelkim.byg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = (TextView) findViewById(R.id.loginButton);
    }

    public void toHome (View v){
        Intent drawer = new Intent(this, michaelkim.byg.drawer.class);
        startActivity(drawer);
    }

    public void toRegi (View v){
        Intent regi = new Intent(this, registration.class);
        startActivity(regi);
    }
}
