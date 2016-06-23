package com.example.user.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by USER on 22-06-2016.
 */
public class EmptyCrimeListFragment extends Fragment {
    private Button mButton;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
    {
        super.onCreateView(inflater,container,savedInstanceState);
        View v=inflater.inflate(R.layout.empty_list,container,false);
        mButton=(Button) v.findViewById(R.id.empty_list_new_crime);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Crime c=new Crime();
                CrimeLab.get(getActivity()).addCrime(c);
                Intent intent=CrimePagerActivity.newIntent(getActivity(),c.getId());
                startActivity(intent);
            }
        });
        return v;
    }
}
