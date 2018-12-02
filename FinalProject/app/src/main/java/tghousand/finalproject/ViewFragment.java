package tghousand.finalproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ViewFragment extends Fragment {

    public ArrayList<Map<String, ?>> wrestlerinfo = new ArrayList<Map<String, ?>>();
    public TextView nameText;
    public TextView heightText;
    public TextView weightText;
    public TextView cityStateText;
    public TextView countryText;
    public ImageView picture;
    public Button nextButton;
    public Button previousButton;
    int wrestlerIndex;

    public ViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundle = getArguments();
        wrestlerinfo = (ArrayList<Map<String, ?>>)bundle.getSerializable("list");
        return inflater.inflate(R.layout.fragment_view, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        wrestlerIndex = 0;
        nameText = (TextView)getView().findViewById(R.id.nameText);
        heightText = (TextView)getView().findViewById(R.id.heightText);
        weightText = (TextView)getView().findViewById(R.id.weightText);
        cityStateText = (TextView)getView().findViewById(R.id.cityStateText);
        countryText = (TextView)getView().findViewById(R.id.countryText);
        picture = (ImageView) getView().findViewById(R.id.profilePicture);
        nextButton = (Button) getView().findViewById(R.id.nextButton);
        previousButton = (Button) getView().findViewById(R.id.previouseButton);


        if(wrestlerinfo.size() == 0){
            nameText.setText("No wrestlers created!");
            picture.setImageResource(R.drawable.vacant);
            heightText.setText(" ");
            weightText.setText(" ");
            cityStateText.setText(" ");
            countryText.setText( "");
            nextButton.setVisibility(View.GONE);
            previousButton.setVisibility(View.GONE);
        }else{
            nextButton.setVisibility(View.GONE);
            previousButton.setVisibility(View.GONE);
            if(wrestlerIndex < wrestlerinfo.size() - 1){
                nextButton.setVisibility(View.VISIBLE);
            }
            if(wrestlerIndex > 0){
                previousButton.setVisibility(View.VISIBLE);
            }
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(wrestlerIndex < wrestlerinfo.size() - 1){
                        wrestlerIndex++;
                        setWrestler(wrestlerIndex);
                        if(wrestlerIndex > 0){
                            previousButton.setVisibility(View.VISIBLE);
                        }
                        if(wrestlerIndex == wrestlerinfo.size() - 1){
                            nextButton.setVisibility(View.GONE);
                        }
                    }
                }
            });
            previousButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(wrestlerIndex > 0){
                        wrestlerIndex--;
                        setWrestler(wrestlerIndex);
                        if(wrestlerIndex < wrestlerinfo.size() - 1){
                            nextButton.setVisibility(View.VISIBLE);
                        }
                        if(wrestlerIndex == 0){
                            previousButton.setVisibility(View.GONE);
                        }
                    }
                }
            });
            setWrestler(wrestlerIndex);
        }

        super.onActivityCreated(savedInstanceState);
    }

    public void setWrestler(int wrestlerID){
        HashMap wrestler = (HashMap) wrestlerinfo.get(wrestlerID);
        nameText.setText(wrestler.get("name").toString());
        heightText.setText(wrestler.get("feet").toString() + "'" + wrestler.get("inches").toString());
        weightText.setText(wrestler.get("weight").toString() + " lbs.");
        String city = wrestler.get("hometown").toString();
        if(!city.isEmpty()){
            cityStateText.setText(city + ", " + wrestler.get("state").toString());
        }else cityStateText.setText(wrestler.get("state").toString());
        countryText.setText(wrestler.get("country").toString());
        picture.setImageResource(Integer.parseInt(wrestler.get("picture").toString()));
    }


}