import java.lang.*;
import java.util.*;

public class Automat {

    private List<String> stari;
    private List<String> alfabet;
    private String stareStart;
    private List<String> stariFinale;
    private List<Tranzitie> tranzitii;

public Automat(){
    this.stari=new ArrayList<String>();
    this.alfabet=new ArrayList<String>();
    this.stareStart="";
    this.stariFinale=new ArrayList<String>();
    this.tranzitii=new ArrayList<Tranzitie>();
}
public Automat(List<String> stari, List<String> alfabet, String start,List<String> stariFinale, List<Tranzitie> tranzitii){
    this.stari=stari;
    this.alfabet=alfabet;
    this.stareStart=start;
    this.stariFinale=stariFinale;
    this.tranzitii=tranzitii;
}
public List<String> getStari(){
    return this.stari;
}
public List<String> getAlfabet(){
    return this.alfabet;
}
public String getStareStart(){
    return this.stareStart;
}
public List<String> getStariFinale(){
    return this.stariFinale;
}
public List<Tranzitie> getTranzitii(){
    return this.tranzitii;
}
public void setStari(List<String> s){
    this.stari=s;
}
public void setAlfabet(List<String> a){
    this.alfabet=a;
}
public void setStareStart(String q){
   this.stareStart=q;
}
public void setStariFinale(List<String> f){
    this.stariFinale=f;
}
public void setTranzitii(List<Tranzitie> d){
    this.tranzitii=d;
}
    
}
