package michaelkim.byg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class registration extends AppCompatActivity {

    Button create;
    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_screen);

        create = (Button) findViewById(R.id.createAccount);
    }

    public void toHome (View v){
        fragment = new homeTabbed();
    }
}
