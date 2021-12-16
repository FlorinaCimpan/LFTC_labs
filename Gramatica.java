import java.util.*;

public class Gramatica {
    private  List<String> neterminale; // neterminale : S,A
    private List<String> terminale; // terminale : a
    private List <Productii> productii;
    private String start;

public Gramatica(){
    this.neterminale=new ArrayList<String>();
    this.terminale=new ArrayList<String>();
    this.productii = new ArrayList<Productii>();
    this.start="";
}
public Gramatica(List<String> neterminale,List<String> terminale, List<Productii> productii, String start){
    this.neterminale=neterminale;
    this.terminale=terminale;
    this.productii=productii;
    this.start=start;
}

public List<String> getNeterminale(){
    return this.neterminale;
}
public List<String> getTerminale(){
    return this.terminale;
}
public String getStart(){
    return this.start;
}
public List<Productii> getProductii(){
    return this.productii;
}
public void SetNeterminale(List<String> n){
    this.neterminale=n;
}
public void setTerminale(List<String> a){
    this.terminale=a;
}
public void setSimbolStart(String s){
    this.start=s;
}
public void setProductii(List<Productii> prod){
    this.productii=prod;
}

public List<Productii> getProductiiNeterminal(String n){

    List<Productii> productii = getProductii();
    List<Productii> productiiNeterminal = new ArrayList<Productii>();
    for (int i=0; i<productii.size(); i++){
        Productii productie=productii.get(i);
        if(productie.getPSP().equals(n)){
            productiiNeterminal.add(productie);
        }
    }
    return productiiNeterminal;
}
public boolean cautaNeterminal(String n){
    boolean gasit=false;
    List<String> neterm=this.getNeterminale();
    for(int i=0; i<neterm.size();i++){
        if(neterm.get(i).equals(n)){
                  gasit=true;
                  break;
        }
    }
    return gasit;

}
 public boolean cautaTerminal(String s){
    boolean gasit=false;
    List<String> term=this.getTerminale();
    for (int i=0; i<term.size();i++){
        if(term.get(i).equals(s)){
             gasit=true;
             break;
        }
    }
    return gasit;
}


public boolean esteRegulara(){
    boolean ok=true;
    boolean ok1=false;
    List<Productii> listaP = new ArrayList<Productii>();
    listaP=this.getProductii();

    for(int i=0; i<listaP.size();i++){
        Productii productie=new Productii();
        productie=listaP.get(i);
        if(productie.getPSP().equals("@"))
            ok=false;
        if(productie.getCardinalPDP()>2){
            ok=false;
        }
        else if(productie.getCardinalPDP()==2){
                 String simbol1=productie.getPDP().substring(0,1);
                 String simbol2=productie.getPDP().substring(1,2);
                 if((cautaNeterminal(simbol1)==true) ||(cautaTerminal(simbol2)==true)){
                     ok=false;
                 }
        }
          else if(productie.getCardinalPDP()==1){
                  String simbol=productie.getPDP();
                  if((cautaNeterminal(simbol)==true)&&!(simbol.equals("@"))){
                      ok=false;
                  }
        }
    }

    List<String> neterminale=this.getNeterminale();
    for(int i=0; i<neterminale.size(); i++) {
        String nett = neterminale.get(i);
        if (nett.equals(this.getStart())) {
            for (int j = 0; j < listaP.size(); j++) {
                Productii p = listaP.get(j);
                if(p.getPDP().equals("@")){
                    ok1=true;
                }
            }
        }
    }

         if(ok==true){
              List<String> ne=this.getNeterminale();
              for(int i=0; i<ne.size(); i++){
                  String net=ne.get(i);
                  if(!(net.equals(this.getStart()))){
                      for(int j=0; j<listaP.size();j++){
                           Productii p=listaP.get(j);
                           if(net.equals(p.getPSP())){
                           if (p.getPDP().equals("@")){
                               ok=false;
                           }
                           }
                      }
                  }
                  else{
                      for(int j=0; j<listaP.size();j++){
                          Productii p=listaP.get(j);
                          if(p.getCardinalPDP()==2){
                          String simbol1=p.getPDP().substring(0,1);
                          String simbol2=p.getPDP().substring(1,2);
                              if(simbol2.equals(this.getStart()) && ok1==true){
                                  ok=false;
                              }}
                          else if(p.getCardinalPDP()==1){
                              String simbol2=p.getPDP();
                              if(simbol2.equals(this.getStart())&& ok1==true){
                                  ok=false;
                              }
                          }

                      }
                  }
              }
         }

    return ok;

}

}
