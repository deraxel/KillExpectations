package com.killpredictions.killexpectations;


import android.graphics.BitmapRegionDecoder;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;


public class Graph extends AppCompatActivity {

    LineGraphSeries<DataPoint> series;
    LineGraphSeries<DataPoint> series2;
    static final int READ_BLOCK_SIZE = 100;
    String BS, str, ap, tgh, as, is, fnp, rof;
    boolean instaDeath, Shred, Pref_Enemy, rending, twin_linked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        storeEverything();
        GraphView graph = (GraphView) findViewById(R.id.theGraph);
        double y,z;
        series= new LineGraphSeries<DataPoint>();
        series.appendData(new DataPoint(0,1),true,getROF()*10);
        y=1.0;
        for(int x=1;x<=getROF();x=x+1){
            y=y-getChanceofOccurance(getROF(),x-1);
            series.appendData(new DataPoint(x,y),true,getROF()*10);
        }
        series.setColor(Color.GREEN);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(15);
        graph.addSeries(series);

        series2= new LineGraphSeries<DataPoint>();
        for(int x=0;x<=getROF();x=x+1){
            z=getChanceofOccurance(getROF(),x);
            series2.appendData(new DataPoint(x,z),true,getROF()*10);
        }
        series2.setColor(Color.RED);
        series2.setDrawDataPoints(true);
        series2.setDataPointsRadius(7);
        graph.addSeries(series2);

        // set manual X bounds
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(getROF());

        // set manual Y bounds
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(1);

        // legend
        series.setTitle("MINIMUM KILL CHANCE");
        series2.setTitle("EXACT KILL CHANCE");
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                String item=dataPoint+"";
                String[] token=item.split("/");
                token[0]=token[0].replaceAll("\\[","");
                token[1]=token[1].replaceAll("\\]","");
                token[0]=token[0].substring(0, token[0].length() - 2);
                double thing=Double.parseDouble(token[1]);
                thing=thing*100;
                int percent=(int) thing;
                if(token[0].equals("1")){
                    Toast.makeText(getBaseContext(), String.valueOf(percent) + "%" + " chance of at least " + token[0] + " kill!", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getBaseContext(), String.valueOf(percent) + "%" + " chance of at least " + token[0] + " kills!", Toast.LENGTH_LONG).show();
                }
            }
        });

        series2.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                String item=dataPoint+"";
                String[] token=item.split("/");
                token[0]=token[0].replaceAll("\\[","");
                token[1]=token[1].replaceAll("\\]","");
                token[0]=token[0].substring(0, token[0].length() - 2);
                double thing=Double.parseDouble(token[1]);
                thing=thing*100;
                int percent=(int) thing;
                if(token[0].equals("1")){
                    Toast.makeText(getBaseContext(), String.valueOf(percent) + "%" + " chance of " + token[0] + " kill!", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getBaseContext(), String.valueOf(percent) + "%" + " chance of " + token[0] + " kills!", Toast.LENGTH_LONG).show();
                }            }
        });
    }

    private String valuesForCalc(){
        String s="";
        try{//retrieves setting from previously set attributes and enters them into the setting block
            FileInputStream fileIn=openFileInput("gotoGraph.txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn);
            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            int charRead;
            while ((charRead=InputRead.read(inputBuffer))>0) {
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    private double getSingleChance(){
        Calculations calc=new Calculations();
        return calc.calcAll(BS, str, ap, 1, tgh, as, is, fnp, Shred, twin_linked, rending, Pref_Enemy, instaDeath);
    }

    private int getROF(){
        String[] tokens=valuesForCalc().split(" ");
        return Integer.parseInt(tokens[3]);
    }

    private double getChanceofOccurance(int n, int k){
        double probs = power(getSingleChance(),k)*power((1-getSingleChance()),(n-k));
        BigDecimal thing = BigDecimal.valueOf(probs);
        BigDecimal thing2=new BigDecimal(combinations(n,k));
        thing=thing.multiply(thing2);
        probs=thing.doubleValue();
        return probs;
    }

    private double power(double x, int p){
        double thing=1.0;
        for(int i=0;i<p;i=i+1){
            thing=thing*x;
        }
        return thing;
    }

    private BigInteger combinations(int n,int k){
        BigInteger x,y,z;
        x=factorial(n);
        y=factorial(k);
        z=factorial(n-k);
        z=y.multiply(z);
        x=x.divide(z);
        return x;
    }

    private BigInteger factorial(int x){
        long start=1;
        BigInteger result=BigInteger.valueOf(start);
        BigInteger starter=result;
        int endThing=-1;
        for(BigInteger i=starter;endThing==i.compareTo(BigInteger.valueOf(x+1));i=i.add(starter))
        {
            result=result.multiply(i);
        }
        return result;
    }

    private void storeEverything(){
        String[] tokens=valuesForCalc().split(" ");
        BS=tokens[0];str=tokens[1];ap=tokens[2];tgh=tokens[9];as=tokens[10];is=tokens[11];fnp=tokens[12];rof=tokens[3];
        instaDeath=tokens[8].equals("true");Shred=tokens[5].equals("true");Pref_Enemy=tokens[7].equals("true");
        rending=tokens[4].equals("true");twin_linked=tokens[6].equals("true");
    }

    public void goback(View view){
        deleteFile("gotoGraph.txt");
        finish();
    }
}

