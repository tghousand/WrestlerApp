package tghousand.finalproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class CreateFragment extends Fragment {

    public ArrayList<Map<String, ?>> wrestlerinfo = new ArrayList<Map<String, ?>>();
    public EditText nameInput;
    public RadioGroup genderButtons;
    public EditText heightFeetInput;
    public EditText heightInchesInput;
    public EditText weightInput;
    public EditText townInput;
    public EditText stateInput;
    public EditText countryInput;
    public RadioGroup pictureButtons;
    public Button createButton;
    public ScrollView scroller;

    public CreateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundle = getArguments();
        wrestlerinfo = (ArrayList<Map<String, ?>>)bundle.getSerializable("list");
        return inflater.inflate(R.layout.fragment_create, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        nameInput = (EditText)getView().findViewById(R.id.nameInput);
        genderButtons = (RadioGroup)getView().findViewById(R.id.genderButtons);
        heightFeetInput = (EditText)getView().findViewById(R.id.heightFeet);
        heightInchesInput = (EditText)getView().findViewById(R.id.heightInches);
        weightInput = (EditText)getView().findViewById(R.id.weightInput);
        townInput = (EditText)getView().findViewById(R.id.homeInput);
        stateInput = (EditText)getView().findViewById(R.id.stateInput);
        countryInput = (EditText)getView().findViewById(R.id.countryInput);
        pictureButtons = (RadioGroup)getView().findViewById(R.id.pictureButtons);
        createButton = (Button)getView().findViewById(R.id.createButton);
        scroller = (ScrollView)getView().findViewById(R.id.scollView);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkEmpty() == false){
                    if(validEntries() == true){
                        String name = nameInput.getText().toString().trim();
                        String heightFeet = heightFeetInput.getText().toString().trim();
                        String heightInches = heightInchesInput.getText().toString().trim();
                        String weight = weightInput.getText().toString().trim();
                        String town = townInput.getText().toString().trim();
                        String state = stateInput.getText().toString().trim();
                        String country = countryInput.getText().toString().trim();
                        int genderID = genderButtons.getCheckedRadioButtonId();
                        int pictureID = pictureButtons.getCheckedRadioButtonId();
                        String gender = "";
                        int picture = 0;
                        switch(genderID){
                            case R.id.maleButton:
                                gender = "male";
                                break;
                            case R.id.femaleButton:
                                gender = "female";
                                break;
                        }
                        switch(pictureID){
                            case R.id.alexaButton:
                                picture = R.drawable.alexabig;
                                break;
                            case R.id.aliciaButton:
                                picture = R.drawable.aliciabig;
                                break;
                            case R.id.beckyButton:
                                picture = R.drawable.beckybig;
                                break;
                            case R.id.bobbyButton:
                                picture = R.drawable.bobbybig;
                                break;
                            case R.id.caraButton:
                                picture = R.drawable.carabig;
                                break;
                            case R.id.danielButton:
                                picture = R.drawable.danielbig;
                                break;
                            case R.id.emberButton:
                                picture = R.drawable.emberbig;
                                break;
                            case R.id.finnButton:
                                picture = R.drawable.finnbig;
                                break;
                            case R.id.hideoButton:
                                picture = R.drawable.hideobig;
                                break;
                            case R.id.jasonButton:
                                picture = R.drawable.jasonbig;
                                break;
                            case R.id.metalikButton:
                                picture = R.drawable.metalikbig;
                                break;
                            case R.id.mikeButton:
                                picture = R.drawable.mikebig;
                                break;
                            case R.id.mustafaButton:
                                picture = R.drawable.mustafabig;
                                break;
                            case R.id.primoButton:
                                picture = R.drawable.primobig;
                                break;
                            case R.id.xavierButton:
                                picture = R.drawable.xavierbig;
                                break;
                        }
                        HashMap wrestler = new HashMap();
                        wrestler.put("name", name);
                        wrestler.put("gender", gender);
                        wrestler.put("feet", heightFeet);
                        wrestler.put("inches", heightInches);
                        wrestler.put("weight", weight);
                        wrestler.put("hometown", town);
                        wrestler.put("state", state);
                        wrestler.put("country", country);
                        wrestler.put("picture", picture);
                        wrestlerinfo.add(wrestler);
                        nameInput.getText().clear();
                        heightFeetInput.getText().clear();
                        heightInchesInput.getText().clear();
                        genderButtons.clearCheck();
                        weightInput.getText().clear();
                        townInput.getText().clear();
                        stateInput.getText().clear();
                        countryInput.getText().clear();
                        pictureButtons.clearCheck();
                        scroller.fullScroll(ScrollView.FOCUS_UP);
                        Toast.makeText(getActivity(), "Wrestler created!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(), "Make sure your height and weight is realistic.", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getActivity(), "All fields besides 'State' are required.", Toast.LENGTH_LONG).show();

                }
            }
        });


        super.onActivityCreated(savedInstanceState);
    }


    public boolean checkEmpty(){
        boolean empty = false;
        if(nameInput.getText().toString().trim().length() == 0){
            empty = true;
        }
        if(heightFeetInput.getText().toString().trim().length() == 0){
            empty = true;
        }
        if(heightFeetInput.getText().toString().trim().length() == 0){
            empty = true;
        }
        if(weightInput.getText().toString().trim().length() == 0){
            empty = true;
        }
        if(townInput.getText().toString().trim().length() == 0){
            empty = true;
        }
        if(countryInput.getText().toString().trim().length() == 0){
            empty = true;
        }
        if(genderButtons.getCheckedRadioButtonId() == -1){
            empty = true;
        }
        if(pictureButtons.getCheckedRadioButtonId() == -1){
            empty = true;
        }
        return empty;
    }

    public boolean validEntries(){
        boolean valid = true;
        if(Integer.parseInt(heightFeetInput.getText().toString()) > 8 || Integer.parseInt(heightFeetInput.getText().toString()) < 2){
            valid = false;
        }
        if(Integer.parseInt(heightInchesInput.getText().toString().trim()) > 11 || Integer.parseInt(heightInchesInput.getText().toString().trim()) < 0){
            valid = false;
        }
        if(Integer.parseInt(weightInput.getText().toString().trim()) > 500 || Integer.parseInt(weightInput.getText().toString().trim()) < 75){
            valid = false;
        }
        return valid;
    }




}