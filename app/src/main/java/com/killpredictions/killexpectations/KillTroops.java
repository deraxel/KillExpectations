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

public class KillTroops extends AppCompatActivity {
    Spinner BS, str, ap, tgh, as, is, fnp;
    EditText rof;
    TextView expectedkill;
    ArrayAdapter<CharSequence> ztt, zts;
    private CheckBox instaDeath, Shred, Pref_Enemy, rending, twin_linked;
    static final int READ_BLOCK_SIZE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kill_troops);

        Button gotoKillVehicle=(Button)findViewById(R.id.gotoVeh);//this button goes to a new activity called KillVehicle.java
        gotoKillVehicle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent goVehi = new Intent(getApplicationContext(), KillVehicle.class);
                goVehi.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                try{//this creates a save file for all the settings the user has set
                    FileOutputStream fileout;
                    fileout = openFileOutput("gotoTro.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
                    outputWriter.write(BS.getSelectedItem().toString()+" "+str.getSelectedItem().toString()+" "+ap.getSelectedItem().toString()+" "+
                            rof.getText().toString()+" "+rending.isChecked()+" "+Shred.isChecked()+" "+twin_linked.isChecked()+" "+
                            Pref_Enemy.isChecked()+" "+instaDeath.isChecked()+" "+tgh.getSelectedItem().toString()+" "+
                            as.getSelectedItem().toString()+" "+is.getSelectedItem().toString()+" "+fnp.getSelectedItem().toString());
                    outputWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                startActivity(goVehi);
            }
        });

        ztt = ArrayAdapter.createFromResource(this, R.array.balSkil, android.R.layout.simple_spinner_item);
        zts = ArrayAdapter.createFromResource(this, R.array.zs, android.R.layout.simple_spinner_item);
        ztt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        zts.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        BS = (Spinner) findViewById(R.id.BS);
        BS.setAdapter(ztt);
        str = (Spinner) findViewById(R.id.str);
        str.setAdapter(ztt);
        ap = (Spinner) findViewById(R.id.ap);
        ap.setAdapter(zts);
        tgh = (Spinner) findViewById(R.id.tgh);
        tgh.setAdapter(ztt);
        as = (Spinner) findViewById(R.id.as);
        as.setAdapter(zts);
        is = (Spinner) findViewById(R.id.is);
        is.setAdapter(zts);
        rof = (EditText) findViewById(R.id.rof);
        fnp = (Spinner) findViewById(R.id.fnp);
        fnp.setAdapter(zts);
        expectedkill=(TextView) findViewById(R.id.expectedkill);
        instaDeath=(CheckBox) findViewById(R.id.instaDeath);
        Shred=(CheckBox) findViewById(R.id.Shred);
        Pref_Enemy=(CheckBox) findViewById(R.id.Pref_Enemy);
        rending=(CheckBox) findViewById(R.id.rending);
        twin_linked=(CheckBox) findViewById(R.id.twin_linked);
        Pref_Enemy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doEverything();
            }
        });//when selected calculates all items
        rending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doEverything();
            }
        });
        Shred.setOnClickListener(new View.OnClickListener() {
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
        instaDeath.setOnClickListener(new View.OnClickListener() {
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

        try{//retrieves setting from previously set attributes and enters them into the setting block
            FileInputStream fileIn=openFileInput("gotoTro.txt");
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
            deleteFile("gotoTro.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }

        fnp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {//retrieves setting from previously set attributes and enters them into the setting block
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

        as.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                doEverything();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        tgh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        BS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                doEverything();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
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

        Button MEQ = (Button) findViewById(R.id.MEQ);//preset button to set values
        MEQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tgh.setSelection(3);
                as.setSelection(3);
                is.setSelection(0);
                fnp.setSelection(0);
                doEverything();
                Toast.makeText(getBaseContext(), "Space Marine Equivalent", Toast.LENGTH_SHORT).show();
            }
        });

        Button TEQ = (Button) findViewById(R.id.TEQ);
        TEQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tgh.setSelection(3);
                as.setSelection(2);
                is.setSelection(5);
                fnp.setSelection(0);
                doEverything();
                Toast.makeText(getBaseContext(), "Terminator Equivalent", Toast.LENGTH_SHORT).show();
            }
        });

        Button ResetEQ = (Button) findViewById(R.id.ResetEQ);//reset button
        ResetEQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BS.setSelection(0);
                str.setSelection(0);
                ap.setSelection(0);
                tgh.setSelection(0);
                as.setSelection(0);
                is.setSelection(0);
                fnp.setSelection(0);
                rof.setText("1");
                instaDeath.setChecked(false);
                Shred.setChecked(false);
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

        Button rofUP = (Button) findViewById(R.id.rofUP);//button to increase number of shots
        rofUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {//try because if no value is in present then the program will crash
                    rof.setText(String.valueOf(Integer.parseInt(rof.getText().toString()) + 1));
                    doEverything();
                } catch (Exception e) {
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
                } catch (Exception e) {
                    e.printStackTrace();
                    rof.setText("0");
                }
            }
        });

        try{//if the file exists i will set the save button to invisible and the preset button to visible
            openFileInput("troopOPreset1.txt");
            Button setsav1O = (Button) findViewById(R.id.setsav1O);
            setsav1O.setVisibility(View.INVISIBLE);
            Button cus1O = (Button) findViewById(R.id.cus1O);
            cus1O.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Button cus1O = (Button) findViewById(R.id.cus1O);//if the preset button is help down it is deleted here
        cus1O.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Button setsav1O = (Button) findViewById(R.id.setsav1O);
                setsav1O.setVisibility(View.VISIBLE);
                Button cus1O = (Button) findViewById(R.id.cus1O);
                cus1O.setVisibility(View.INVISIBLE);
                deleteFile("troopOPreset1.txt");
                doEverything();
                return true;
            }
        });

        try{
            openFileInput("troopOPreset2.txt");
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
                deleteFile("troopOPreset2.txt");
                doEverything();
                return true;
            }
        });
        try{
            openFileInput("troopOPreset3.txt");
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
                deleteFile("troopOPreset3.txt");
                doEverything();
                return true;
            }
        });
        try{
            openFileInput("troopOPreset4.txt");
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
                deleteFile("troopOPreset4.txt");
                doEverything();
                return true;
            }
        });
        try{
            openFileInput("troopDPreset1.txt");
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
                deleteFile("troopDPreset1.txt");
                doEverything();
                return true;
            }
        });

        try{
            openFileInput("troopDPreset2.txt");
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
                deleteFile("troopDPreset2.txt");
                doEverything();
                return true;
            }
        });
        try{
            openFileInput("troopDPreset3.txt");
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
                deleteFile("troopDPreset3.txt");
                doEverything();
                return true;
            }
        });
        try{
            openFileInput("troopDPreset4.txt");
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
                deleteFile("troopDPreset4.txt");
                doEverything();
                return true;
            }
        });


    }
    public void setsav1O(View view){//if the save button is pressed then the save button is hidden and the custom button appears while saving the settings to a file
        try{
            FileOutputStream fileout;
            fileout = openFileOutput("troopOPreset1.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(BS.getSelectedItem().toString()+" "+str.getSelectedItem().toString()+" "+ap.getSelectedItem().toString()+" "+
                    rof.getText().toString()+" "+rending.isChecked()+" "+Shred.isChecked()+" "+twin_linked.isChecked()+" "+
                    Pref_Enemy.isChecked()+" "+instaDeath.isChecked());
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

    public void cus1O(View view){//if the custom button is pressed then the saved file gets the setting a pushes them to the oPresets function
        try{
            FileInputStream fileIn=openFileInput("troopOPreset1.txt");
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
            FileOutputStream fileout;
            fileout = openFileOutput("troopOPreset2.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(BS.getSelectedItem().toString()+" "+str.getSelectedItem().toString()+" "+ap.getSelectedItem().toString()+" "+
                    rof.getText().toString()+" "+rending.isChecked()+" "+Shred.isChecked()+" "+twin_linked.isChecked()+" "+
                    Pref_Enemy.isChecked()+" "+instaDeath.isChecked());
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
            FileInputStream fileIn=openFileInput("troopOPreset2.txt");
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
            fileout = openFileOutput("troopOPreset3.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(BS.getSelectedItem().toString()+" "+str.getSelectedItem().toString()+" "+ap.getSelectedItem().toString()+" "+
                    rof.getText().toString()+" "+rending.isChecked()+" "+Shred.isChecked()+" "+twin_linked.isChecked()+" "+
                    Pref_Enemy.isChecked()+" "+instaDeath.isChecked());
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
            FileInputStream fileIn=openFileInput("troopOPreset3.txt");
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
            fileout = openFileOutput("troopOPreset4.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(BS.getSelectedItem().toString()+" "+str.getSelectedItem().toString()+" "+ap.getSelectedItem().toString()+" "+
                    rof.getText().toString()+" "+rending.isChecked()+" "+Shred.isChecked()+" "+twin_linked.isChecked()+" "+
                    Pref_Enemy.isChecked()+" "+instaDeath.isChecked());
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
            FileInputStream fileIn=openFileInput("troopOPreset4.txt");
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
            fileout = openFileOutput("troopDPreset1.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(tgh.getSelectedItem().toString()+" "+as.getSelectedItem().toString()
                    +" "+is.getSelectedItem().toString()+" "+fnp.getSelectedItem().toString());
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
            FileInputStream fileIn=openFileInput("troopDPreset1.txt");
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
            fileout = openFileOutput("troopDPreset2.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(tgh.getSelectedItem().toString()+" "+as.getSelectedItem().toString()
                    +" "+is.getSelectedItem().toString()+" "+fnp.getSelectedItem().toString());
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
            FileInputStream fileIn=openFileInput("troopDPreset2.txt");
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
            fileout = openFileOutput("troopDPreset3.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(tgh.getSelectedItem().toString()+" "+as.getSelectedItem().toString()
                    +" "+is.getSelectedItem().toString()+" "+fnp.getSelectedItem().toString());
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
            FileInputStream fileIn=openFileInput("troopDPreset3.txt");
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
            fileout = openFileOutput("troopDPreset4.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(tgh.getSelectedItem().toString()+" "+as.getSelectedItem().toString()
                    +" "+is.getSelectedItem().toString()+" "+fnp.getSelectedItem().toString());
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
            FileInputStream fileIn=openFileInput("troopDPreset4.txt");
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
    private void doEverything(){//this activates the class Calculations and inputs all settings and outputs the results is 5 decimal places
        try {
            Calculations calc = new Calculations();
            double theendofstuff = calc.calcAll(BS.getSelectedItem().toString(), str.getSelectedItem().toString(), ap.getSelectedItem().toString(), Integer.parseInt(rof.getText().toString()), tgh.getSelectedItem().toString(), as.getSelectedItem().toString(), is.getSelectedItem().toString(), fnp.getSelectedItem().toString(), Shred.isChecked(), twin_linked.isChecked(), rending.isChecked(), Pref_Enemy.isChecked(), instaDeath.isChecked());
            theendofstuff = (double) Math.round(theendofstuff * 100000d) / 100000d;
            String theendofstuff2;
            theendofstuff2 = "" + theendofstuff;
            expectedkill.setText(theendofstuff2);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private void oPresets(String s){//sets presets from a particular save file
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
        Shred.setChecked(tokens[5].equals("true"));
        twin_linked.setChecked(tokens[6].equals("true"));
        Pref_Enemy.setChecked(tokens[7].equals("true"));
        instaDeath.setChecked(tokens[8].equals("true"));
    }

    private void dPresets(String s){
        String delims=" ";
        String[] tokens=s.split(delims);
        int index;
        tgh.setSelection(Integer.parseInt(tokens[0])-1);
        if((tokens[1]).equals("none")) {
            index = 0;
        }else{
            index = Integer.parseInt(tokens[1]);
        }
        as.setSelection(index);
        if((tokens[2]).equals("none")) {
            index = 0;
        }else{
            index = Integer.parseInt(tokens[2]);
        }
        is.setSelection(index);
        if((tokens[3]).equals("none")) {
            index = 0;
        }else{
            index = Integer.parseInt(tokens[3]);
        }
        fnp.setSelection(index);
    }


    private void setItems(String s){//sets items from the save file from previously clicking on gotovehicle
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
        Shred.setChecked(tokens[5].equals("true"));
        twin_linked.setChecked(tokens[6].equals("true"));
        Pref_Enemy.setChecked(tokens[7].equals("true"));
        instaDeath.setChecked(tokens[8].equals("true"));
        tgh.setSelection(Integer.parseInt(tokens[9])-1);
        if((tokens[10]).equals("none")) {
            index = 0;
        }else{
            index = Integer.parseInt(tokens[10]);
        }
        as.setSelection(index);
        if((tokens[11]).equals("none")) {
            index = 0;
        }else{
            index = Integer.parseInt(tokens[11]);
        }
        is.setSelection(index);
        if((tokens[12]).equals("none")) {
            index = 0;
        }else{
            index = Integer.parseInt(tokens[12]);
        }
        fnp.setSelection(index);
    }

    public void gotoGraph(View view){
        if (Integer.parseInt(rof.getText().toString())!=0) {
            Intent goGraph = new Intent(getApplicationContext(), Graph.class);
            goGraph.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            try {//this creates a save file for all the settings the user has set
                FileOutputStream fileout;
                fileout = openFileOutput("gotoGraph.txt", MODE_PRIVATE);
                OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                outputWriter.write(BS.getSelectedItem().toString() + " " + str.getSelectedItem().toString() + " " + ap.getSelectedItem().toString() + " " +
                        rof.getText().toString() + " " + String.valueOf(rending.isChecked()) + " " + String.valueOf(Shred.isChecked()) + " " + String.valueOf(twin_linked.isChecked()) + " " +
                        String.valueOf(Pref_Enemy.isChecked()) + " " + String.valueOf(instaDeath.isChecked()) + " " + tgh.getSelectedItem().toString() + " " +
                        as.getSelectedItem().toString() + " " + is.getSelectedItem().toString() + " " + fnp.getSelectedItem().toString());
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
