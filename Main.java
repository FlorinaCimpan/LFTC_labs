import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {

public static Gramatica citesteGramatica() throws IOException{
    Gramatica G=new Gramatica();
    try{
         BufferedReader br=new BufferedReader(new FileReader("inputGramatica"));
         String linie = br.readLine();
         String[] neterminale=linie.split(" ");
         List<String> listaNeterminale=new ArrayList<String>();
         for(int i=0; i<neterminale.length; i++){
             listaNeterminale.add(neterminale[i]);
         }

         linie=br.readLine();
         String [] terminale =linie.split(" ");
         List<String> listaTerminale =new ArrayList<String>();
         for(int i=0; i<terminale.length; i++){
                     listaTerminale.add(terminale[i]);
                 }

         String start="";
         linie=br.readLine();
         linie.trim();
         start=linie;
         
         List<Productii> listaProductii = new ArrayList<Productii>();
         while ((linie=br.readLine())!=null){
             String [] prod=linie.split(" ");
             String ps="";
             String pd="";
             ps=prod[0];
             for(int i=1;i<prod.length;i++){
                pd=pd+prod[i];
             }
             Productii pr=new Productii(ps,pd);
             listaProductii.add(pr);

         }
            G=new Gramatica(listaNeterminale,listaTerminale,listaProductii,start);
    }catch(IOException ex){
        System.out.println(ex.getMessage());
    }
    return G;
}
public static void PrelucreazaGramatica(Gramatica G) throws IOException{
        List<String> listaNeterminale = G.getNeterminale();
        List<String> listaTerminale = G.getTerminale();
        List<Productii> listaProductii = G.getProductii();

        System.out.println("Optiuni:");
        System.out.println("1: Afiseaza multimea neterminalelor");
        System.out.println("2: Afiseaza multimea terminalelor");
        System.out.println("3: Afiseaza multimea productiilor");
        System.out.println("4: Afiseaza productiile unui neterminal");
        System.out.println("5: Verifica daca gramatica este regulara");
        System.out.println("6: Afisează simbol start");

    System.out.println("0: Inapoi ");
        int op=-1;
        while(op!=0){
            System.out.println("Introduceti optiunea dorita");
            BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
            op=Integer.parseInt(br1.readLine());
            if(op==1){
                System.out.println("Multimea neterminalelor este:");
                for(int i=0; i<listaNeterminale.size();i++){
                  System.out.println(listaNeterminale.get(i));
                }
            }
            else if(op==2){
                System.out.println("Multimea terminalelor este:");
                for(int i=0;i<listaTerminale.size();i++){
                    System.out.println(listaTerminale.get(i));
                }

            }
            else if(op==3){
                System.out.println("Multimea productiilor este:");
                    for(int i=0; i<listaProductii.size();i++){
                        System.out.println(listaProductii.get(i).afiseaza());
                    }
                }
                else if(op==4){
                    System.out.println("Introduceti un neterminal: ");
                    String neterminal =br1.readLine().trim();
                    List<Productii> listaProductiiNeterminal=G.getProductiiNeterminal(neterminal);
                    System.out.println("Productiile neterminalului "+neterminal+" sunt:");
                    for(int i=0; i<listaProductiiNeterminal.size();i++){
                        System.out.println(listaProductiiNeterminal.get(i).afiseaza());
                    }
                }
            else if(op==6){
                System.out.println(G.getStart());
            }
              else if(op==5){
                if (G.esteRegulara()==true){
             System.out.println("Gramatica este regulara");
         }
          else {System.out.println("Gramatica nu este regulara");}
    }
            }

}
public static Automat citesteAutomat() throws IOException{
        Automat M=new Automat();
        try{
           BufferedReader br=new BufferedReader(new FileReader("inputAutomat"));
           String linie=br.readLine();
           String[] stari=linie.split(" ");
           List<String> listaStari=new ArrayList<String>();
           for(int i=0; i<stari.length; i++){
               listaStari.add(stari[i]);
           }

           linie=br.readLine();
           String[] alfabet=linie.split(" ");
           List<String> listaAlfabet=new ArrayList<String>();
           for(int i=0; i<alfabet.length; i++){
                listaAlfabet.add(alfabet[i]);
           }

           linie=br.readLine();
           String stareInitiala=linie.trim();

           linie=br.readLine();
           String[] fin=linie.split(" ");
           List<String> stariFinale=new ArrayList<String>();
           for(int i=0; i<fin.length; i++){
               stariFinale.add(fin[i]);
           }

            List<Tranzitie> listaTranzitii=new ArrayList<Tranzitie>();
            while((linie=br.readLine())!=null){
                 String[] d = new String[30];
                 d=linie.split(" ");
                 Tranzitie t=new Tranzitie(d[0],d[1],d[2]);
                listaTranzitii.add(t);

           }

            M = new Automat(listaStari,listaAlfabet,stareInitiala,stariFinale,listaTranzitii);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return M;
    }

public static void PrelucreazaAutomat(Automat M) throws IOException{
    System.out.println("Optiuni: ");
    System.out.println("1. Afiseaza multimea starilor");
    System.out.println("2. Afiseaza alfabetul");
    System.out.println("3. Afiseaza tranzitiile");
    System.out.println("4. Afiseaza multimea starilor finale");
    System.out.println("0. Inapoi ");
    int op=-1;
    while(op!=0){
        BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
        op=Integer.parseInt(br1.readLine());
        if(op==1){
            System.out.println("Multimea starilor este:");
            List<String> multimeStari = M.getStari();
            for(int i=0; i<multimeStari.size(); i++){
                System.out.println(multimeStari.get(i));
            }
        }
        else if (op==2){
            System.out.println("Alfabetul este:");
            List<String> alfabet=M.getAlfabet();
            for(int i=0; i<alfabet.size();i++){
                System.out.println(alfabet.get(i));
            }
        }
        else if (op==3){
                System.out.println("Tranzitiile sunt:");
                List<Tranzitie> tranzitii=M.getTranzitii();
                for(int i=0;i<tranzitii.size();i++){
                    System.out.println(tranzitii.get(i).afiseaza());
                }
        }
        else if(op==4){
            System.out.println("Multimea starilor finale este:");
            List<String> stariFinale=M.getStariFinale();
            for(int i=0;i<stariFinale.size();i++){
                System.out.println(stariFinale.get(i));
            }
        }
    }

}

public static Automat genereazaAutomatDelaGramatica(Gramatica G){
    Automat M=new Automat();
    if(G.esteRegulara()==true){
    M.setAlfabet(G.getTerminale()); // alfabetul=terminalele
    M.setStareStart(G.getStart());// stareStart <- simbolStart
    List<String> Q = G.getNeterminale();
    Q.add("K"); // la neterminale adaugam K
    M.setStari(Q);
    List<String> F=new ArrayList<String>();
    List<Productii> prodS= G.getProductiiNeterminal(G.getStart());
    boolean gasit=false;
    for(int i=0;i<prodS.size();i++){
        if(prodS.get(i).getPDP().equals("@")){
            gasit=true;
        }
    }
    if(gasit==true){
        F.add("K");
        F.add(G.getStart());
    }
    else F.add("K");
    M.setStariFinale(F);
    List<Tranzitie> listaTranzitii=new ArrayList<Tranzitie>();
    List<String> prod=G.getNeterminale();
    for(int i=0; i<prod.size();i++){
        String p=prod.get(i);
        List<Productii> listap=G.getProductiiNeterminal(p);
        for(int j=0;j<listap.size();j++){
              int k=listap.get(j).getCardinalPDP();
            if(k==2){
                Tranzitie t=new Tranzitie(p,listap.get(j).getPDP().substring(0,1),listap.get(j).getPDP().substring(1,2));
                listaTranzitii.add(t);
            }
            else{  k=listap.get(j).getCardinalPDP();
                if((k==1)&&!(listap.get(j).getPDP().equals("@"))){
                      Tranzitie t=new Tranzitie(p,listap.get(j).getPDP().substring(0,1),"K");
                      listaTranzitii.add(t);
            }
            }
        }
    }

     M.setTranzitii(listaTranzitii);
    }

    return M;
}
public static Gramatica genereazaGramaticaDelaAutomat(Automat M){

    Gramatica G=new Gramatica();
    G.SetNeterminale(M.getStari()); //neterminalele <- starile
    G.setTerminale(M.getAlfabet()); //terminalele <- alfabetul
    G.setSimbolStart(M.getStareStart()); // simboluldeStart<-starea de start
    List<Productii> listap=new ArrayList<Productii>();
    List<String> listaStariFinale=M.getStariFinale();
    Productii p;
    //daca in starile finale exista starea de inceput adaugam in productii : stareStart -> @
    if(listaStariFinale.contains(M.getStareStart())){
        p=new Productii(M.getStareStart(),"@");
        listap.add(p);
    }
    List<Tranzitie> listaTranzitii=M.getTranzitii();
        for(int i=0; i<listaTranzitii.size();i++){   
            if(listaStariFinale.contains(listaTranzitii.get(i).getRez())){
                p=new Productii(listaTranzitii.get(i).getArgS(),listaTranzitii.get(i).getArgA());
                listap.add(p);
            }
            String pd="";
                 pd=listaTranzitii.get(i).getArgA()+listaTranzitii.get(i).getRez();
                 p=new Productii(listaTranzitii.get(i).getArgS(),pd);
                 listap.add(p);
            
        }
        G.setProductii(listap);

    return G;
}
public static void readGramatica() throws IOException {
    int op=-1;
    while(op!=0) {
        System.out.println("Optiuni: ");
        System.out.println("1. Citeste Gramatica din fisier");
        System.out.println("2. Citeste Gramatica de la tastatura ");
        System.out.println("0. Inapoi ");

        System.out.println("Introduceti optiunea dorita:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        op = Integer.parseInt(br.readLine());
        Gramatica G;
        if (op == 1) {
            System.out.println("Gramatica citita cu succes!");
            G = citesteGramatica();
            PrelucreazaGramatica(G);
        }
        if (op == 2) {
            System.out.println("Introduceti neterminalele: ");
            List<String> listaNeterminale=new ArrayList<String>();
            String  lines = br.readLine();

            String[] strs = lines.trim().split("\\s+");

            for (int i = 0; i < strs.length; i++) {
                listaNeterminale.add(strs[i]);
            }
            System.out.println("Introduceti terminalele: ");
            List<String> listaTerminale=new ArrayList<String>();
            String  linesT = br.readLine();

            String[] strsT = linesT.trim().split("\\s+");

            for (int i = 0; i < strsT.length; i++) {
                listaTerminale.add(strsT[i]);
            }
            System.out.println("Introduceti start: ");
            String start="";
            lines=br.readLine();
            lines.trim();
            start=lines;

            System.out.println("Introduceti productiile: ");
            List<Productii> listaProductiiT = new ArrayList<Productii>();
            String  linesP = br.readLine();
            while (linesP !=null){
                String [] prod=linesP.split(" ");
                if(prod[0]=="0"){return;}
                String ps="";
                String pd="";
                ps=prod[0];
                for(int i=1;i<prod.length;i++){
                    pd=pd+prod[i];
                }
                Productii pr=new Productii(ps,pd);

                listaProductiiT.add(pr);

            }
            G=new Gramatica(listaNeterminale,listaTerminale,listaProductiiT,start);
            PrelucreazaGramatica(G);

        }
    }
}
public static void main(String args[]) throws IOException {

     int op=-1;
     while(op!=0){
     System.out.println("Optiuni: ");
     System.out.println("1. Citeste Gramatica");
     System.out.println("2. Citeste Automat ");
     System.out.println("3. Genereaza automat de la gramatica");
     System.out.println("4. Genereaza gramatica de la automat");
     System.out.println("0. Exit ");

        System.out.println("Introduceti optiunea dorita:");
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        op=Integer.parseInt(br.readLine());
        Gramatica G;
         Automat M;
         if(op==1){
             readGramatica();
        }
        else if(op==2){
            M=citesteAutomat();
            PrelucreazaAutomat(M);
        }
         else if(op==3){
               G=citesteGramatica();
               if(G.esteRegulara()==true){
               M=genereazaAutomatDelaGramatica(G);
               PrelucreazaAutomat(M);}
             else System.out.println("Nu se poate genera automatul deoarece gramatica nu este regulara");
        }
         else if(op==4){
             M=citesteAutomat();
             G=genereazaGramaticaDelaAutomat(M);
             PrelucreazaGramatica(G);
         }
     }
}
}
