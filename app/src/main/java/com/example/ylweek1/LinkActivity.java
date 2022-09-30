package com.example.ylweek1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class LinkActivity extends AppCompatActivity {
    private FloatingActionButton fab;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog alertDialog;
    private Button add,close;
    private EditText name,url;
    private List<Link> linkList = new ArrayList<>();
    private String inputName, inputUrl;
    private RecyclerView mRecyclerView;
    private LinkAdapter mAdapter;
    //private ConstraintLayout constraintLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);
        //constraintLayout=findViewById(R.id.constraintLinkLayout);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDialog();
            }
        });
        mRecyclerView = findViewById(R.id.linkRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        LinkAdapter.RecyclerViewClickListener mListener = new LinkAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, String id) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + id));
                startActivity(intent);
            }
        };

        mAdapter = new LinkAdapter(linkList,mListener);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void addDialog() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View addPopupView = getLayoutInflater().inflate(R.layout.popup_linkfill, null);
        add = (Button) addPopupView.findViewById(R.id.bttAddLink);
        close = (Button)addPopupView.findViewById(R.id.bttClose);
        name = (EditText)addPopupView.findViewById(R.id.inputName);
        url = (EditText)addPopupView.findViewById(R.id.inputlink);
        dialogBuilder.setView(addPopupView);
        alertDialog = dialogBuilder.create();
        alertDialog.show();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputName=name.getText().toString();
                inputUrl=url.getText().toString();
                Link newLink = new Link(inputName,inputUrl);
                System.out.println(newLink.getName());
                linkList.add(newLink);
                Snackbar.make(addPopupView, "You had successfully add the link!", Snackbar.LENGTH_LONG)
                        .setAction("CLOSE", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                alertDialog.dismiss();
                            }
                        })
                        .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
                        .show();
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }
}