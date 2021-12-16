import java.util.*;
import java.lang.*;

public class Productii {
    private String psp;
    private String pdp;

public Productii(){
    this.psp="";
    this.pdp="";
}
public Productii(String ps, String pd){
    this.psp=ps;
    this.pdp=pd;
}
public String getPSP(){
    return this.psp;
}
public String getPDP(){
    return this.pdp;
}
public int getCardinalPDP(){
    return this.pdp.length();
}

public String afiseaza(){
    return this.psp+"->"+this.pdp;
}

}
