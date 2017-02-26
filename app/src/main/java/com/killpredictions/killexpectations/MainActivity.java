package com.killpredictions.killexpectations;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button gotoKillTroop=(Button)findViewById(R.id.troops);
        gotoKillTroop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent goTroops = new Intent(MainActivity.this, KillTroops.class);
                startActivity(goTroops);
            }
        });

        Button gotoKillVehicle=(Button)findViewById(R.id.vehi);
        gotoKillVehicle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent goVehi = new Intent(MainActivity.this, KillVehicle.class);
                startActivity(goVehi);
            }
        });

        deleteFile("gotoTro.txt");
        deleteFile("gotoVeh.txt");
    }

}

