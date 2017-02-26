package com.killpredictions.killexpectations;

/**
 * Created by Derxe on 2/24/2017.
 */

class Calculations {
    private int cBalSkill;
    private int cStrength;
    private int cAP;
    private int cRoF;
    private int cToughness;
    private int cInvSaves;
    private int cArmSaves;
    private int cFeelNoPain;
    private int cAV;
    private boolean cRending;
    private boolean cShred;
    private boolean cTwinLinked;
    private boolean cPreferedEnemy;
    private boolean cInstantDeath;
    private boolean cOrdinance;
    private boolean cMelta;

    Calculations() {
        cAV=cBalSkill = cStrength = cAP = cRoF = cToughness = cInvSaves = cArmSaves = cFeelNoPain = 0;
        cOrdinance=cMelta=cRending = cShred = cTwinLinked = cPreferedEnemy = cInstantDeath = false;

    }

    private double saveFailedChance(int x) {
        if (x > 1) {
            return (((double) x - 1.0) / 6.0);
        } else {
            return 1.0 / 6.0;
        }
    }

    private int setSaves(int invs, int ars, int ap) {
        int x = ars;
        if (x >= ap) {
            x = 7;
        }
        if (invs < x) {
            x = invs;
        }
        return x;
    }

    private double finSavChan(int invs, int ars, int ap, boolean rend) {
        if (!rend) {
            return saveFailedChance(setSaves(invs, ars, ap));
        } else {
            return ((1 / (6 * getToWound(cStrength, cToughness, cPreferedEnemy, cShred))) *
                    saveFailedChance(setSaves(invs, ars, 2))) +
                    (((6 * getToWound(cStrength, cToughness, cPreferedEnemy, cShred) - 1) /
                            (6 * getToWound(cStrength, cToughness, cPreferedEnemy, cShred))) *
                            saveFailedChance(setSaves(invs, ars, ap)));
        }
    }


    private double finFeelNoPain(int fnp, boolean id) {
        if (!id) {
            return saveFailedChance(fnp);
        } else {
            return 1;
        }
    }

    private void setValues(int bs, int str, int ap, int rof, int tgh, int ars, int invs, int fnp, int armV, boolean rend, boolean shred, boolean twiLink, boolean prefEne, boolean id, boolean ordinance, boolean melta) {
        cBalSkill = bs;
        cStrength = str;
        cAP = ap;
        cRoF = rof;
        cToughness = tgh;
        cInvSaves = invs;
        cArmSaves = ars;
        cFeelNoPain = fnp;
        cRending = rend;
        cShred = shred;
        cAV=armV;
        cTwinLinked = twiLink;
        cPreferedEnemy = prefEne;
        cInstantDeath = setInstantDeath(cStrength, cToughness, id);
        cOrdinance=ordinance;
        cMelta=melta;
    }

    private boolean setInstantDeath(int cS, int cT, boolean id) {
        return id || (cS >= (cT * 2));
    }

    double calcAll(String bs, String str, String ap, int rof, String tgh, String ars, String invs, String fnp, boolean shred, boolean twiLink, boolean rend, boolean prefEne, boolean id) {
        setValues(getIntNum(bs), getIntNum(str), getIntNum(ap), rof, getIntNum(tgh), getIntNum(ars), getIntNum(invs), getIntNum(fnp),0, rend, shred, twiLink, prefEne, id, false, false);
        return cRoF *
                getBsChan(cBalSkill, cPreferedEnemy, cTwinLinked) *
                getToWound(cStrength, cToughness, cPreferedEnemy, cShred) *
                finSavChan(cInvSaves, cArmSaves, cAP, cRending) *
                finFeelNoPain(cFeelNoPain, cInstantDeath);
    }

    double calcExpHull(String bs, String str, int rof, String armV, String is, boolean melta, boolean twin_linked, boolean rending, boolean Pref_Enemy, boolean ordinance){
        setValues(getIntNum(bs), getIntNum(str), 0, rof, 0, 0, getIntNum(is), 0, getIntNum(armV), rending, false, twin_linked, Pref_Enemy, false, ordinance, melta);
        return cRoF *
                getBsChan(cBalSkill, cPreferedEnemy, cTwinLinked) *
                (glanceChan(0)+ordChan(0)) *
                finSavChan(cInvSaves, 7, 7, false);
    }

    double ordChan(int x){
        if(cOrdinance){
            return (1-glanceChan(x))*glanceChan(x);
        }
        return 0;
    }

    double calcExpExp(String bs, String str, String ap, int rof, String armV, String is, boolean melta, boolean twin_linked, boolean rending, boolean Pref_Enemy, boolean ordinance){
        setValues(getIntNum(bs), getIntNum(str), getIntNum(ap), rof, 0, 0, getIntNum(is), 0, getIntNum(armV), rending, false, twin_linked, Pref_Enemy, false, ordinance, melta);
        return cRoF *
                getBsChan(cBalSkill, cPreferedEnemy, cTwinLinked) *
                (penChan()
                        + ordChan(1)
                )*
                finSavChan(cInvSaves, 7, 7, false)*
                exploChan(cAP);
    }

    private double glanceChan(int pen){
        int x=(cAV+pen)-cStrength;
        if(x<=0){
            return 1;
        }
        if(cMelta){
            int count=0;
            for(int i1=1; i1<=6; i1++){
                for(int i2=1; i2<=6; i2++){
                    if(i1+i2>=x){
                        count++;
                    }
                }
            }
            return count/36.0;
        }
        if(cRending){
            if(x>6 && x<=9){
                x=x-6;
                return (1.0/6.0)*(4.0-x)/3.0;
            }
        }
        if(x>6){
            return 0;
        }
        return (7.0-x)/6.0;
    }

    private double penChan(){
        return glanceChan(1);
    }

    private double exploChan(int ap){
        if(ap==2) {
            return 1.0 / 6.0;
        }else if(ap==1){
            return 1.0 / 3.0;
        }else{
            return 0;
        }
    }

    private double getToWound(int s, int t, boolean prefe, boolean shre){
        double z;
        int d;
        d=s-t;
        if(d<3 && d>-3){
            z= (d+3.0)/6.0;
        }
        else if(d>=3) {
            z= 5.0/6.0;
        }
        else if(d==-3){
            z= 1.0/6.0;
        }
        else {
            z= 0.0;
        }
        if(!prefe && !shre){
            return z;
        }else if(shre) {
            return z + ((1 - z) * z);
        }else{
            return z + ((1.0/6.0) * z);
        }
    }

    private double basicBS(int x){
        return x/6.0;
    }

    private double getBsChan(int x, boolean prefe, boolean twilinked){
        if(!prefe && !twilinked) {
            if (x > 5) {
                return basicBS(5) + (basicBS(1) * basicBS(x - 5));
            } else {
                return basicBS(x);
            }
        }else if(twilinked){
            if (x > 5) {
                return basicBS(5)+(basicBS(1)*basicBS(5));
            }else {
                return basicBS(x)+((1-basicBS(x))*basicBS(x));
            }
        }else{
            if(x>5){
                return basicBS(5)+(basicBS(1)*basicBS(5));
            }else {
                return basicBS(x)+(basicBS(1)*basicBS(x));
            }
        }
    }

    private int getIntNum(String x){
        if (("none").equals(x.toLowerCase())){
            return 7;
        }
        else
        {
            return Integer.parseInt(x);
        }
    }
}
