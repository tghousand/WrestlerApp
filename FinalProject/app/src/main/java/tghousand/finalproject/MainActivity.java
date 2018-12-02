package tghousand.finalproject;

/*
    Final Project: "Create a Wrestler"
    Tyler Housand
    5/1/2018
    CSCI 343 Mobile Application Development, Prof. Fuchs
    The purpose of this project is to display the skills learned over the course of the semester. This project makes use of a custom toolbar, custom fragments, and data entry and retrieval.
    This is all under the guise of creating a fictional wrestler for user enjoyment.
 */

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public Toolbar toolbar;
    public ArrayList<Map<String, ?>> wrestlerinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.theToolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.colorAccent));

        if(savedInstanceState != null){
            wrestlerinfo = (ArrayList<Map<String, ?>>)savedInstanceState.getSerializable("arrayList");
            Toast.makeText(this, "Loaded Wrestlers", Toast.LENGTH_LONG).show();
        }else wrestlerinfo = new ArrayList<Map<String, ?>>();

        Bundle bundle = new Bundle();
        bundle.putSerializable("list", wrestlerinfo);
        setSupportActionBar(toolbar);

        Fragment frag = new Fragment();
        frag = new CreateFragment();
        frag.setArguments(bundle);
        FragmentManager fragManager = getSupportFragmentManager();
        FragmentTransaction fragTranaction = fragManager.beginTransaction();
        fragTranaction.replace(R.id.fragLayout, frag);

        fragTranaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.about:
                swapFragment(R.id.AboutFragment);
                break;
            case R.id.create:
                swapFragment(R.id.CreateFragment);
                break;
            case R.id.view:
                swapFragment(R.id.ViewFragment);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void swapFragment(int fragID){
        Fragment frag = null;
        if(fragID == R.id.AboutFragment){
            frag = new AboutFragment();
        }
        if(fragID == R.id.CreateFragment){
            frag = new CreateFragment();
        }
        if(fragID == R.id.ViewFragment){
            frag = new ViewFragment();
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable("list", wrestlerinfo);
        frag.setArguments(bundle);
        FragmentManager fragManager = getSupportFragmentManager();
        FragmentTransaction fragTranaction = fragManager.beginTransaction();
        fragTranaction.replace(R.id.fragLayout, frag);
        fragTranaction.addToBackStack(null);
        fragTranaction.commit();
    }

    @Override
    public void onBackPressed(){
        if(getFragmentManager().getBackStackEntryCount() > 0){
            getFragmentManager().popBackStack();
        }else super.onBackPressed();;
    }



    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        ArrayList<Map<String, ?>> savedList = wrestlerinfo;
        savedInstanceState.putSerializable("arrayList", savedList);
    }
}
