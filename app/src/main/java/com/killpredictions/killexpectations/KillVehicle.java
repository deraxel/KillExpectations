package com.killpredictions.killexpectations;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class KillVehicle extends AppCompatActivity {
    Spinner BS, str, ap, armV, is;
    EditText rof;
    TextView expectedHull, expectedExplode;
    ArrayAdapter<CharSequence> ztt, zts, avD;
    private CheckBox ordinance, melta, Pref_Enemy, rending, twin_linked;
    static final int READ_BLOCK_SIZE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kill_vehicle);

        Button gotoKillVehicle = (Button) findViewById(R.id.gotoTro);
        gotoKillVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goTro = new Intent(getApplicationContext(), KillTroops.class);
                goTro.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                try{
                    FileOutputStream fileout;
                    fileout = openFileOutput("gotoVeh.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
                    outputWriter.write(BS.getSelectedItem().toString()+" "+str.getSelectedItem().toString()+" "+ap.getSelectedItem().toString()+" "+
                            rof.getText().toString()+" "+rending.isChecked()+" "+melta.isChecked()+" "+twin_linked.isChecked()+" "+
                            Pref_Enemy.isChecked()+" "+ordinance.isChecked()+" "+armV.getSelectedItem().toString()+" "+
                            is.getSelectedItem().toString());
                    outputWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                startActivity(goTro);
            }
        });

        ztt = ArrayAdapter.createFromResource(this, R.array.balSkil, android.R.layout.simple_spinner_item);
        zts = ArrayAdapter.createFromResource(this, R.array.zs, android.R.layout.simple_spinner_item);
        avD = ArrayAdapter.createFromResource(this, R.array.av, android.R.layout.simple_spinner_item);
        ztt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        zts.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        avD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        BS = (Spinner) findViewById(R.id.BS);
        BS.setAdapter(ztt);
        str = (Spinner) findViewById(R.id.str);
        str.setAdapter(ztt);
        ap = (Spinner) findViewById(R.id.ap);
        ap.setAdapter(zts);
        armV = (Spinner) findViewById(R.id.aV);
        armV.setAdapter(avD);
        is = (Spinner) findViewById(R.id.isV);
        is.setAdapter(zts);
        expectedHull = (TextView) findViewById(R.id.expectedkill);
        expectedExplode = (TextView) findViewById(R.id.explodeexpoutp);
        ordinance = (CheckBox) findViewById(R.id.ordinance);
        melta = (CheckBox) findViewById(R.id.melta);
        Pref_Enemy = (CheckBox) findViewById(R.id.Pref_Enemy);
        rending = (CheckBox) findViewById(R.id.rending);
        twin_linked = (CheckBox) findViewById(R.id.twin_linked);
        rof = (EditText) findViewById(R.id.rof);

        try{
            FileInputStream fileIn=openFileInput("gotoVeh.txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn);
            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            String s="";
            int charRead;
            while ((charRead=InputRead.read(inputBuffer))>0) {
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();
            setItems(s);
            doEverything();
            deleteFile("gotoVeh.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Button LREQ = (Button) findViewById(R.id.LREQ);
        LREQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                armV.setSelection(4);
                is.setSelection(0);
                doEverything();
                Toast.makeText(getBaseContext(), "Leman Russ Equivalent", Toast.LENGTH_SHORT).show();
            }
        });

        Button PEQ = (Button) findViewById(R.id.PEQ);
        PEQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                armV.setSelection(3);
                is.setSelection(0);
                doEverything();
                Toast.makeText(getBaseContext(), "Predator Equivalent", Toast.LENGTH_SHORT).show();
            }
        });

        Button ResetEQ = (Button) findViewById(R.id.ResetEQ);
        ResetEQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BS.setSelection(0);
                str.setSelection(0);
                ap.setSelection(0);
                armV.setSelection(0);
                is.setSelection(0);
                rof.setText("1");
                ordinance.setChecked(false);
                melta.setChecked(false);
                twin_linked.setChecked(false);
                rending.setChecked(false);
                Pref_Enemy.setChecked(false);
                doEverything();
                Toast.makeText(getBaseContext(), "Reset", Toast.LENGTH_SHORT).show();
            }
        });

        Button BEQ = (Button) findViewById(R.id.BEQ);
        BEQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BS.setSelection(3);
                ap.setSelection(5);
                str.setSelection(3);
                doEverything();
                Toast.makeText(getBaseContext(), "Bolter Equivalent", Toast.LENGTH_SHORT).show();
            }
        });

        Button LEQ = (Button) findViewById(R.id.LEQ);
        LEQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BS.setSelection(3);
                ap.setSelection(2);
                str.setSelection(8);
                doEverything();
                Toast.makeText(getBaseContext(), "Lascannon Equivalent", Toast.LENGTH_SHORT).show();
            }
        });

        Button rofUP = (Button) findViewById(R.id.rofUP);
        rofUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    rof.setText(String.valueOf(Integer.parseInt(rof.getText().toString()) + 1));
                    doEverything();
                } catch (Exception e){
                    e.printStackTrace();
                    rof.setText("1");
                }
            }
        });

        Button rofDOWN = (Button) findViewById(R.id.rofDOWN);
        rofDOWN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (Integer.parseInt(rof.getText().toString()) > 0) {
                        rof.setText(String.valueOf(Integer.parseInt(rof.getText().toString()) - 1));
                    }
                    doEverything();
                } catch (Exception e){
                    e.printStackTrace();
                    rof.setText("0");
                }
            }
        });

        str.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                doEverything();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        BS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                doEverything();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ap.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                doEverything();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        is.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                doEverything();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        armV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                doEverything();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Pref_Enemy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doEverything();
            }
        });

        rending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doEverything();
            }
        });

        ordinance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doEverything();
            }
        });

        twin_linked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doEverything();
            }
        });

        melta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doEverything();
            }
        });

        rof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doEverything();
            }

        });

        try{
            openFileInput("vehicleOPreset1.txt");
            Button setsav1O = (Button) findViewById(R.id.setsav1O);
            setsav1O.setVisibility(View.INVISIBLE);
            Button cus1O = (Button) findViewById(R.id.cus1O);
            cus1O.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Button cus1O = (Button) findViewById(R.id.cus1O);
        cus1O.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Button setsav1O = (Button) findViewById(R.id.setsav1O);
                setsav1O.setVisibility(View.VISIBLE);
                Button cus1O = (Button) findViewById(R.id.cus1O);
                cus1O.setVisibility(View.INVISIBLE);
                deleteFile("vehicleOPreset1.txt");
                doEverything();
                return true;
            }
        });

        try{
            openFileInput("vehicleOPreset2.txt");
            Button setsav2O = (Button) findViewById(R.id.setsav2O);
            setsav2O.setVisibility(View.INVISIBLE);
            Button cus2O = (Button) findViewById(R.id.cus2O);
            cus2O.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Button cus2O = (Button) findViewById(R.id.cus2O);
        cus2O.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Button setsav2O = (Button) findViewById(R.id.setsav2O);
                setsav2O.setVisibility(View.VISIBLE);
                Button cus2O = (Button) findViewById(R.id.cus2O);
                cus2O.setVisibility(View.INVISIBLE);
                deleteFile("vehicleOPreset2.txt");
                doEverything();
                return true;
            }
        });

        try{
            openFileInput("vehicleOPreset3.txt");
            Button setsav3O = (Button) findViewById(R.id.setsav3O);
            setsav3O.setVisibility(View.INVISIBLE);
            Button cus3O = (Button) findViewById(R.id.cus3O);
            cus3O.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Button cus3O = (Button) findViewById(R.id.cus3O);
        cus3O.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Button setsav3O = (Button) findViewById(R.id.setsav3O);
                setsav3O.setVisibility(View.VISIBLE);
                Button cus3O = (Button) findViewById(R.id.cus3O);
                cus3O.setVisibility(View.INVISIBLE);
                deleteFile("vehicleOPreset3.txt");
                doEverything();
                return true;
            }
        });

        try{
            openFileInput("vehicleOPreset4.txt");
            Button setsav4O = (Button) findViewById(R.id.setsav4O);
            setsav4O.setVisibility(View.INVISIBLE);
            Button cus4O = (Button) findViewById(R.id.cus4O);
            cus4O.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Button cus4O = (Button) findViewById(R.id.cus4O);
        cus4O.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Button setsav4O = (Button) findViewById(R.id.setsav4O);
                setsav4O.setVisibility(View.VISIBLE);
                Button cus4O = (Button) findViewById(R.id.cus4O);
                cus4O.setVisibility(View.INVISIBLE);
                deleteFile("vehicleOPreset4.txt");
                doEverything();
                return true;
            }
        });

        try{
            openFileInput("vehicleDPreset1.txt");
            Button setsav1D = (Button) findViewById(R.id.setsav1D);
            setsav1D.setVisibility(View.INVISIBLE);
            Button cus1D = (Button) findViewById(R.id.cus1D);
            cus1D.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Button cus1D = (Button) findViewById(R.id.cus1D);
        cus1D.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Button setsav1D = (Button) findViewById(R.id.setsav1D);
                setsav1D.setVisibility(View.VISIBLE);
                Button cus1D = (Button) findViewById(R.id.cus1D);
                cus1D.setVisibility(View.INVISIBLE);
                deleteFile("vehicleDPreset1.txt");
                doEverything();
                return true;
            }
        });

        try{
            openFileInput("vehicleDPreset2.txt");
            Button setsav2D = (Button) findViewById(R.id.setsav2D);
            setsav2D.setVisibility(View.INVISIBLE);
            Button cus2D = (Button) findViewById(R.id.cus2D);
            cus2D.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Button cus2D = (Button) findViewById(R.id.cus2D);
        cus2D.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Button setsav2D = (Button) findViewById(R.id.setsav2D);
                setsav2D.setVisibility(View.VISIBLE);
                Button cus2D = (Button) findViewById(R.id.cus2D);
                cus2D.setVisibility(View.INVISIBLE);
                deleteFile("vehicleDPreset2.txt");
                doEverything();
                return true;
            }
        });

        try{
            openFileInput("vehicleDPreset3.txt");
            Button setsav3D = (Button) findViewById(R.id.setsav3D);
            setsav3D.setVisibility(View.INVISIBLE);
            Button cus3D = (Button) findViewById(R.id.cus3D);
            cus3D.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Button cus3D = (Button) findViewById(R.id.cus3D);
        cus3D.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Button setsav3D = (Button) findViewById(R.id.setsav3D);
                setsav3D.setVisibility(View.VISIBLE);
                Button cus3D = (Button) findViewById(R.id.cus3D);
                cus3D.setVisibility(View.INVISIBLE);
                deleteFile("vehicleDPreset3.txt");
                doEverything();
                return true;
            }
        });

        try{
            openFileInput("vehicleDPreset4.txt");
            Button setsav4D = (Button) findViewById(R.id.setsav4D);
            setsav4D.setVisibility(View.INVISIBLE);
            Button cus4D = (Button) findViewById(R.id.cus4D);
            cus4D.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Button cus4D = (Button) findViewById(R.id.cus4D);
        cus4D.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Button setsav4D = (Button) findViewById(R.id.setsav4D);
                setsav4D.setVisibility(View.VISIBLE);
                Button cus4D = (Button) findViewById(R.id.cus4D);
                cus4D.setVisibility(View.INVISIBLE);
                deleteFile("vehicleDPreset4.txt");
                doEverything();
                return true;
            }
        });
    }

    public void setsav1O(View view){
        try{
            FileOutputStream fileout = openFileOutput("vehicleOPreset1.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(BS.getSelectedItem().toString()+" "+str.getSelectedItem().toString()+" "+ap.getSelectedItem().toString()+" "+
                    rof.getText().toString()+" "+rending.isChecked()+" "+melta.isChecked()+" "+twin_linked.isChecked()+" "+
                    Pref_Enemy.isChecked()+" "+ordinance.isChecked());
            outputWriter.close();
            Button setsav1O = (Button) findViewById(R.id.setsav1O);
            setsav1O.setVisibility(View.INVISIBLE);
            Button cus1O = (Button) findViewById(R.id.cus1O);
            cus1O.setVisibility(View.VISIBLE);
            Toast.makeText(getBaseContext(), "Hold to delete preset", Toast.LENGTH_SHORT).show();
            doEverything();
        } catch (Exception e) {
            e.printStackTrace();
        }}

    public void cus1O(View view){
        try{
            FileInputStream fileIn=openFileInput("vehicleOPreset1.txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn);
            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            String s="";
            int charRead;
            while ((charRead=InputRead.read(inputBuffer))>0) {
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();
            oPresets(s);
            doEverything();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setsav2O(View view){
        try{
            FileOutputStream fileout = openFileOutput("vehicleOPreset2.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(BS.getSelectedItem().toString()+" "+str.getSelectedItem().toString()+" "+ap.getSelectedItem().toString()+" "+
                    rof.getText().toString()+" "+rending.isChecked()+" "+melta.isChecked()+" "+twin_linked.isChecked()+" "+
                    Pref_Enemy.isChecked()+" "+ordinance.isChecked());
            outputWriter.close();
            Button setsav2O = (Button) findViewById(R.id.setsav2O);
            setsav2O.setVisibility(View.INVISIBLE);
            Button cus2O = (Button) findViewById(R.id.cus2O);
            cus2O.setVisibility(View.VISIBLE);
            Toast.makeText(getBaseContext(), "Hold to delete preset", Toast.LENGTH_SHORT).show();
            doEverything();
        } catch (Exception e) {
            e.printStackTrace();
        }}

    public void cus2O(View view){
        try{
            FileInputStream fileIn=openFileInput("vehicleOPreset2.txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn);
            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            String s="";
            int charRead;
            while ((charRead=InputRead.read(inputBuffer))>0) {
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();
            oPresets(s);
            doEverything();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setsav3O(View view){
        try{
            FileOutputStream fileout;
            fileout = openFileOutput("vehicleOPreset3.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(BS.getSelectedItem().toString()+" "+str.getSelectedItem().toString()+" "+ap.getSelectedItem().toString()+" "+
                    rof.getText().toString()+" "+rending.isChecked()+" "+melta.isChecked()+" "+twin_linked.isChecked()+" "+
                    Pref_Enemy.isChecked()+" "+ordinance.isChecked());
            outputWriter.close();
            Button setsav3O = (Button) findViewById(R.id.setsav3O);
            setsav3O.setVisibility(View.INVISIBLE);
            Button cus3O = (Button) findViewById(R.id.cus3O);
            cus3O.setVisibility(View.VISIBLE);
            Toast.makeText(getBaseContext(), "Hold to delete preset", Toast.LENGTH_SHORT).show();
            doEverything();
        } catch (Exception e) {
            e.printStackTrace();
        }}

    public void cus3O(View view){
        try{
            FileInputStream fileIn=openFileInput("vehicleOPreset3.txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn);
            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            String s="";
            int charRead;
            while ((charRead=InputRead.read(inputBuffer))>0) {
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();
            oPresets(s);
            doEverything();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setsav4O(View view){
        try{
            FileOutputStream fileout;
            fileout = openFileOutput("vehicleOPreset4.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(BS.getSelectedItem().toString()+" "+str.getSelectedItem().toString()+" "+ap.getSelectedItem().toString()+" "+
                    rof.getText().toString()+" "+rending.isChecked()+" "+melta.isChecked()+" "+twin_linked.isChecked()+" "+
                    Pref_Enemy.isChecked()+" "+ordinance.isChecked());
            outputWriter.close();
            Button setsav4O = (Button) findViewById(R.id.setsav4O);
            setsav4O.setVisibility(View.INVISIBLE);
            Button cus4O = (Button) findViewById(R.id.cus4O);
            cus4O.setVisibility(View.VISIBLE);
            Toast.makeText(getBaseContext(), "Hold to delete preset", Toast.LENGTH_SHORT).show();
            doEverything();
        } catch (Exception e) {
            e.printStackTrace();
        }}

    public void cus4O(View view){
        try{
            FileInputStream fileIn=openFileInput("vehicleOPreset4.txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn);
            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            String s="";
            int charRead;
            while ((charRead=InputRead.read(inputBuffer))>0) {
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();
            oPresets(s);
            doEverything();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setsav1D(View view){
        try{
            FileOutputStream fileout;
            fileout = openFileOutput("vehicleDPreset1.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(armV.getSelectedItem().toString()+" "+is.getSelectedItem().toString());
            outputWriter.close();
            Button setsav1D = (Button) findViewById(R.id.setsav1D);
            setsav1D.setVisibility(View.INVISIBLE);
            Button cus1D = (Button) findViewById(R.id.cus1D);
            cus1D.setVisibility(View.VISIBLE);
            Toast.makeText(getBaseContext(), "Hold to delete preset", Toast.LENGTH_SHORT).show();
            doEverything();

        } catch (Exception e) {
            e.printStackTrace();
        }}

    public void cus1D(View view){
        try{
            FileInputStream fileIn=openFileInput("vehicleDPreset1.txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn);
            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            String s="";
            int charRead;
            while ((charRead=InputRead.read(inputBuffer))>0) {
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();
            dPresets(s);
            doEverything();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setsav2D(View view){
        try{
            FileOutputStream fileout;
            fileout = openFileOutput("vehicleDPreset2.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(armV.getSelectedItem().toString()+" "+is.getSelectedItem().toString());
            outputWriter.close();
            Button setsav2D = (Button) findViewById(R.id.setsav2D);
            setsav2D.setVisibility(View.INVISIBLE);
            Button cus2D = (Button) findViewById(R.id.cus2D);
            cus2D.setVisibility(View.VISIBLE);
            Toast.makeText(getBaseContext(), "Hold to delete preset", Toast.LENGTH_SHORT).show();
            doEverything();
        } catch (Exception e) {
            e.printStackTrace();
        }}

    public void cus2D(View view){
        try{
            FileInputStream fileIn=openFileInput("vehicleDPreset2.txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn);
            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            String s="";
            int charRead;
            while ((charRead=InputRead.read(inputBuffer))>0) {
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();
            dPresets(s);
            doEverything();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setsav3D(View view){
        try{
            FileOutputStream fileout;
            fileout = openFileOutput("vehicleDPreset3.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(armV.getSelectedItem().toString()+" "+is.getSelectedItem().toString());
            outputWriter.close();
            Button setsav3D = (Button) findViewById(R.id.setsav3D);
            setsav3D.setVisibility(View.INVISIBLE);
            Button cus3D = (Button) findViewById(R.id.cus3D);
            cus3D.setVisibility(View.VISIBLE);
            Toast.makeText(getBaseContext(), "Hold to delete preset", Toast.LENGTH_SHORT).show();
            doEverything();
        } catch (Exception e) {
            e.printStackTrace();
        }}

    public void cus3D(View view){
        try{
            FileInputStream fileIn=openFileInput("vehicleDPreset3.txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn);
            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            String s="";
            int charRead;
            while ((charRead=InputRead.read(inputBuffer))>0) {
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();
            dPresets(s);
            doEverything();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setsav4D(View view){
        try{
            FileOutputStream fileout;
            fileout = openFileOutput("vehicleDPreset4.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(armV.getSelectedItem().toString()+" "+is.getSelectedItem().toString());
            outputWriter.close();
            Button setsav4D = (Button) findViewById(R.id.setsav4D);
            setsav4D.setVisibility(View.INVISIBLE);
            Button cus4D = (Button) findViewById(R.id.cus4D);
            cus4D.setVisibility(View.VISIBLE);
            Toast.makeText(getBaseContext(), "Hold to delete preset", Toast.LENGTH_SHORT).show();
            doEverything();
        } catch (Exception e) {
            e.printStackTrace();
        }}

    public void cus4D(View view){
        try{
            FileInputStream fileIn=openFileInput("vehicleDPreset4.txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn);
            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            String s="";
            int charRead;
            while ((charRead=InputRead.read(inputBuffer))>0) {
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();
            dPresets(s);
            doEverything();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void doEverything(){
        try{
            Calculations calc = new Calculations();
            double theendofstuff1 = calc.calcExpHull(BS.getSelectedItem().toString(), str.getSelectedItem().toString(), Integer.parseInt(rof.getText().toString()), armV.getSelectedItem().toString(), is.getSelectedItem().toString(), melta.isChecked(), twin_linked.isChecked(), rending.isChecked(), Pref_Enemy.isChecked(), ordinance.isChecked());
            double theendofstuff2 = calc.calcExpExp(BS.getSelectedItem().toString(), str.getSelectedItem().toString(), ap.getSelectedItem().toString(), Integer.parseInt(rof.getText().toString()), armV.getSelectedItem().toString(), is.getSelectedItem().toString(), melta.isChecked(), twin_linked.isChecked(), rending.isChecked(), Pref_Enemy.isChecked(), ordinance.isChecked());
            theendofstuff1 = (double) Math.round(theendofstuff1 * 100000d) / 100000d;
            theendofstuff2 = (double) Math.round(theendofstuff2 * 100000d) / 100000d;
            String theendofstuff3;
            String theendofstuff4;
            theendofstuff3 = "" + theendofstuff1;
            theendofstuff4 = "" + theendofstuff2;
            expectedHull.setText(theendofstuff3);
            expectedExplode.setText(theendofstuff4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void oPresets(String s){
        String delims=" ";
        String[] tokens=s.split(delims);
        int index;
        BS.setSelection(Integer.parseInt(tokens[0])-1);
        str.setSelection(Integer.parseInt(tokens[1])-1);
        if((tokens[2]).equals("none")) {
            index = 0;
        }else{
            index = Integer.parseInt(tokens[2]);
        }
        ap.setSelection(index);
        rof.setText(tokens[3]);
        rending.setChecked(tokens[4].equals("true"));
        melta.setChecked(tokens[5].equals("true"));
        twin_linked.setChecked(tokens[6].equals("true"));
        Pref_Enemy.setChecked(tokens[7].equals("true"));
        ordinance.setChecked(tokens[8].equals("true"));
    }

    private void dPresets(String s){
        String delims=" ";
        String[] tokens=s.split(delims);
        int index;
        armV.setSelection(Integer.parseInt(tokens[0])-10);
        if((tokens[1]).equals("none")) {
            index = 0;
        }else{
            index = Integer.parseInt(tokens[1]);
        }
        is.setSelection(index);
    }

    private void setItems(String s){
        String delims=" ";
        String[] tokens=s.split(delims);
        int index;
        BS.setSelection(Integer.parseInt(tokens[0])-1);
        str.setSelection(Integer.parseInt(tokens[1])-1);
        if((tokens[2]).equals("none")) {
            index = 0;
        }else{
            index = Integer.parseInt(tokens[2]);
        }
        ap.setSelection(index);
        rof.setText(tokens[3]);
        rending.setChecked(tokens[4].equals("true"));
        melta.setChecked(tokens[5].equals("true"));
        twin_linked.setChecked(tokens[6].equals("true"));
        Pref_Enemy.setChecked(tokens[7].equals("true"));
        ordinance.setChecked(tokens[8].equals("true"));
        armV.setSelection(Integer.parseInt(tokens[9])-10);
        if((tokens[10]).equals("none")) {
            index = 0;
        }else{
            index = Integer.parseInt(tokens[10]);
        }
        is.setSelection(index);
    }

    public void gotoGraph(View view){
        if (Integer.parseInt(rof.getText().toString())!=0) {
            Intent goGraph = new Intent(getApplicationContext(), GraphVeh.class);
            goGraph.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            try {//this creates a save file for all the settings the user has set
                FileOutputStream fileout;
                fileout = openFileOutput("gotoGraph.txt", MODE_PRIVATE);
                OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                outputWriter.write(BS.getSelectedItem().toString() + " " + str.getSelectedItem().toString() + " " + ap.getSelectedItem().toString() + " " +
                        rof.getText().toString() + " " + rending.isChecked() + " " + melta.isChecked() + " " + twin_linked.isChecked() + " " +
                        Pref_Enemy.isChecked() + " " + ordinance.isChecked() + " " + armV.getSelectedItem().toString() + " " +
                        is.getSelectedItem().toString());
                outputWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            startActivity(goGraph);
        }else{
            Toast.makeText(getBaseContext(),"Number of shots must be greater than 0",Toast.LENGTH_LONG).show();
        }
    }

}


