import java.util.*;
import java.lang.*;

public class Tranzitie {
    private String argS;
    private String argA;
    private String rez;

 public Tranzitie(String as, String aa, String r){
     this.argS=as;
     this.argA=aa;
     this.rez=r;
 }

public String getArgS(){
    return this.argS;
}
public String getArgA(){
    return this.argA;
}
public String getRez(){
    return this.rez;
}

public String afiseaza(){
    return "delta("+this.argS+","+this.argA+")"+"="+this.rez;
}    
}
