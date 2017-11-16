package michaelkim.byg;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class drawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null){
            setContentView(R.layout.staff_drawer);
            drawer = (DrawerLayout) findViewById(R.id.staff_drawer_layout);
        }
        else {
            setContentView(R.layout.activity_drawer);
            drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        displaySelectedScreen(R.id.moveNewsfeed);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer;

        if (firebaseAuth.getCurrentUser() != null){
            drawer = (DrawerLayout) findViewById(R.id.staff_drawer_layout);
        }
        else {
            drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        }
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void displaySelectedScreen(int id){
        Fragment fragment = null;

        // When clicking a tab, move into another fragment.
        switch (id){
            case R.id.moveNewsfeed:
                fragment = new homeTabbed();
                break;
            /*
            case R.id.moveBulletin:
                fragment = new homeBulletin();
                break;
            case R.id.moveMiniGroups:
                fragment = new homeMiniGroups();
                break;
            */
            case R.id.moveConnect:
                fragment = new homeConnect();
                break;
            case R.id.moveLogs:
                fragment = new homeLogs();
                break;
            case R.id.movePrayerRequests:
                fragment = new homePR();
                break;
            case R.id.moveDirectory:
                fragment = new homeDirectory();
                break;
            /*
            case R.id.moveBDChat:
                fragment = new homeBDChat();
                break;
                */
            case R.id.moveRegister:
                startActivity(new Intent(getBaseContext(), registrationSelector.class));
                break;
            case R.id.moveLogin:
                startActivity(new Intent(getBaseContext(), loginfortheside.class));
                break;
            case R.id.giveFeedback:
                fragment = new homeFeedback();
                break;
        }

        if (fragment != null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_drawer, fragment);
            ft.commit();
        }

        DrawerLayout drawer;

        if (firebaseAuth.getCurrentUser() != null){
            drawer = (DrawerLayout) findViewById(R.id.staff_drawer_layout);
        }
        else {
            drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        }
        drawer.closeDrawer(GravityCompat.START);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        displaySelectedScreen(id);

        return true;
    }
}
