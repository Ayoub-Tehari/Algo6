import java.io.*;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

public class Cilule {
	String mot;
	int occurs;
	public void Cilule(String m, int o) {
		this.mot=m;
		this.occurs=o;
	}
	public void Cilule() {
		this.mot="";
		this.occurs=0;
	}
}

class Pipo {
    String f1; // the learning file
    Scanner in1; // the scanner associated to the file
    Hashtable<String,Hashtable<String,Integer>> LangModel; // the language model
    Random generator;
    //HashTable<String,Integer> Table_freq;
    ArrayList<Cilule> Table_freq;
    
    Pipo(String f1) {
        this.f1 = f1;
        try {in1 = new Scanner(new FileInputStream(f1)); }
        catch (Exception e) {System.out.println(e);}
        LangModel = new Hashtable<String,Hashtable<String,Integer>>();
        //Table_freq = new HashTable<String,Integer>();
        Table_freq = new ArrayList<Cilule>();
        generator=new Random(); // Seed to be given... Eventually
    }
    
    public void newWorsSeq(String w1, String w2) {
        //System.out.println(" "+w1+"  "+w2+" ");
        // This is were you need to update the language model (hash of hashes)
        if(LangModel.containsKey(w1)) {
        	if(LangModel.get(w1).containsKey(w2)) {
        		int counter = LangModel.get(w1).get(w2);
        		counter++;
        		LangModel.get(w1).put(w2, counter);
        	}else {
        		LangModel.get(w1).put(w2, 1);
        	}
        }else {
        	LangModel.put(w1, new Hashtable<String,Integer>());
        	LangModel.get(w1).put(w2, 1);
        }

    }
    
    public void Learn() {
        String word1;
        word1="."; // A ghost word beeing before the first word of the text
        try {
            while (in1.hasNext()) {
                String word2 = in1.next();
                // System.out.println(word2);
                if (word2.matches("(.*)[.,!?<>=+-/]")) {
                    // word2 is glued with a punctuation mark
                    String[] splitedWord= word2.split("(?=[.,!?<>=+-/])|(?<=])");
                    for (String s : splitedWord) {
                        newWorsSeq(word1,s); // update de language model
                        word1=s;
                        
                    }
                } else { // word2 is a single word
                    newWorsSeq(word1,word2); // update de language model
                    word1=word2;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void Talk(int nbWord) {
        // Taking advantage of the generative skills of the language model
        System.out.println("Compte rendu de l'apnée 3 algo 6 :");
        System.out.println();
//        System.out.println("To be implemented !!");
        String word1=":";
        String word2=":";
        for (int i=0; i<nbWord; i++) {
        	int taille = 0;
        	for (String sw : LangModel.get(word1).keySet()) {
        		taille += LangModel.get(word1).get(sw);
        	}
        	
        	int choix = generator.nextInt(taille);
        	int sum =0;
        	Iterator<String> keys = LangModel.get(word1).keySet().iterator();
        	do {
        		word2 = keys.next();
        		sum += LangModel.get(word1).get(word2);
        		
        	} while (sum < choix);
        	word1=word2;
        	System.out.print(word2 + " ");
        	if((i%15)==0) System.out.println();
        }
        System.out.println();
        System.out.println("Fin du compte rendu");
    }
    
    
    
    public void sortHashTable(budget) {
    	Map<String, Integer> sorted = budget
    	        .entrySet()
    	        .stream()
    	        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
    	        .collect(
    	            toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
    	                LinkedHashMap::new));
    	return sorted;
    }
    
    
    public void ajouter(String word) {
    	 if(Table_freq.containsKey(word)) {
         	
         	int counter = LangModel.get(word);
         		counter++;
         		Table_freq.put(word, counter);
         	
         }else {
        	 Table_freq.put(word, 1);
         }
    }
    
    
    public void frequences_table(){
    	String word1;
        word1="."; // A ghost word beeing before the first word of the text
        try {
            while (in1.hasNext()) {
                String word2 = in1.next();
                // System.out.println(word2);
                if (word2.matches("(.*)[.,!?<>=+-/]")) {
                    // word2 is glued with a punctuation mark
                    String[] splitedWord= word2.split("(?=[.,!?<>=+-/])|(?<=])");
                    for (String s : splitedWord) {
                        this.ajouter(s);
                    }
                } else { // word2 is a single word
                	this.ajouter(word2);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
