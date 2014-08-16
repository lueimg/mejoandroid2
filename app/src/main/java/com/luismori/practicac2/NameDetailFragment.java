package com.luismori.practicac2;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by luismori on 15/08/14.
 */
public class NameDetailFragment extends Fragment {

    private TextView txt_name;


    public void setName(String name){
        if(name != null){
            txt_name.setText(name);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_name_detail,null);
        txt_name = (TextView)view.findViewById(R.id.txt_name);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle argumets = getArguments();
        String name = (String)argumets.get(MyActivity.NAME_TAG);

        setName(name);

    }
}
