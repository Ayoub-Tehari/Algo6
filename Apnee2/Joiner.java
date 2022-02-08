import java.io.*;
import java.util.Scanner;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;


class Joiner {
    String f1,f2;
    PrintWriter out;

    Joiner(String f1, String f2, String res) {
        this.f1 = f1;
        this.f2 = f2;
        try {
        out = new PrintWriter(new FileOutputStream(res));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

int countLines_f1 (){
        int z1=0;
        Scanner in1 ;
        try{
            FileInputStream inp1 = new FileInputStream(f1);
        
        in1 = new Scanner(inp1);
        
        while(in1.hasNext()){
            in1.nextLine();
            z1++;
        }
        in1.close();
	return z1;
	}catch (Exception s){
        System.out.println(s);
	}
        return z1;
    }
int countLines_f2 (){
        int z2=0;
        Scanner in2 ;
        try{
            FileInputStream inp2 = new FileInputStream(f2);
        
        in2 = new Scanner(inp2);
        
        while(in2.hasNext()){
            in2.nextLine();
            z2++;
        }
        in2.close();
	return z2;
	}catch (Exception s){
        System.out.println(s);
	}
        return z2;
    }
    int countLines (){
        return (this.countLines_f1()*this.countLines_f2());
    }
    
    public void ChangeOutput(String res) {
        try {
        out = new PrintWriter(new FileOutputStream(res));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void NestedJoin() {
        Scanner in1, in2;
        String t1,t2 ;
        String [] t2_splited;
        String [] t1_splited;
        
        long startTime = System.currentTimeMillis();
        try {
        in1 = new Scanner(new FileInputStream(f1)) ;
        in2 = new Scanner(new FileInputStream(f2));

        while (in1.hasNext()) {
            t1 = in1.nextLine();
            t1_splited = t1.split("\t");
            while (in2.hasNext()) {
                t2 = in2.nextLine();
                t2_splited = t2.split("\t");
                if (t1_splited[1].equals(t2_splited[0])) {
                    out.print(t1+"\t");
                    out.println(t2);
                }
            }
            in2.reset();
            in2= new Scanner(new FileInputStream(f2));
        }
        in1.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        out.close();
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Computation time, nested loop join: "+totalTime+" ms");
    }
    
    public void HashJoin() {
        Scanner in1, in2;
        String t1,t2, element;
        String [] t2_splited;
        String [] t1_splited;
        String key;
        
        Hashtable<String, String> hashed_t2 = new Hashtable<String, String>();

        long startTime = System.currentTimeMillis();
        
//        out.println("To be implemented !!");
//        System.out.println("To be implemented !!");
        try {
        	in1 = new Scanner(new FileInputStream(f1));
        	in2 = new Scanner(new FileInputStream(f2));
        
        while(in2.hasNext()) {
        	t2=in2.nextLine();
        	t2_splited=t2.split("\t");
        	hashed_t2.put(t2_splited[0], t2);
        }

        while (in1.hasNext()) {
        	t1 = in1.nextLine();
        	t1_splited = t1.split("\t");
        	key = t1_splited[1];
        	if (hashed_t2.containsKey(key)) {
        		element = hashed_t2.get(key);
        		t2_splited = element.split("\t");
        		if(t1_splited[1].equals(t2_splited[0])) {
        			out.println(t1 + "\t" + element);
        		}
        	}
        		
        }
       
        out.close();
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Computation time, hash join: "+totalTime+" ms");

        }catch (Exception e) {
        	System.out.println(e);
        }
        
    }
    
    public void NestedSelect(int i) {
    	Scanner in1;
    	String t1;
    	
    	String[] t1_splited;
    	LinkedList<String> liste = new LinkedList<String>();
    	System.out.println(liste.size());
    	try {
    		in1 = new Scanner (new FileInputStream (f1));    		
    		while (in1.hasNext()) {
    			t1 = in1.nextLine();
    			t1_splited = t1.split("\t");
    			if (!liste.contains(t1_splited[i])){
    				liste.add(t1_splited[i]);
    			}else{
    				
    					liste.remove(t1_splited[i]);
    				    				
    			}
    		}
    		while(liste.size()>0){
    			out.println(liste.poll());
    		}
    		liste.clear();
		out.close();
    	} catch (Exception e) {
    		System.out.println(e);
    	}
    	
    	
    }
    
    public void HashSelect(int i) {
    	Scanner in;
    	String t;
    	String[] t_splited;
    	Hashtable <String, String> hashTable = new Hashtable <String, String>();

    	try {
    		in = new Scanner (new FileInputStream(f1));
    		while (in.hasNext()) {
    			t = in.nextLine();
    			t_splited = t.split("\t");
    			if (! hashTable.containsKey(t_splited[i])){
    				hashTable.put(t_splited[i], t_splited[i]);
    			}
    		}
    		
    		for (Enumeration<String> ee = hashTable.elements(); ee.hasMoreElements();) {
    			out.println(ee.nextElement());
    		}
    		in.close();
    	} catch (Exception e) {
    		System.out.println(e);
    	}
    
    }
    
    public void NestedMinus() {
    	Scanner in1, in2;
    	String t1, t2;
    	boolean counter = false;
    	
    	try {
    		in1 = new Scanner(new FileInputStream(f1));
    		in2 = new Scanner(new FileInputStream(f2));
    		while(in1.hasNext()) {
    			counter = true;
    			t1 = in1.nextLine();
    			while(in2.hasNext() && counter) {
        			t2 = in2.nextLine();
        			if (t1.equals(t2)){
        				counter =false;
        			}
    			}
    			if (counter) {
    				out.println(t1);
    			}
    			in2.reset();
    			in2 = new Scanner(new FileInputStream(f2));

    		}
    		out.close();
    	}catch (Exception e) {
    		System.out.println(e);
    	}
    }
    
    public void HashMinus() {
    	Scanner in1, in2;
    	String t1, t2;
    	Hashtable<String, String> hashTable = new Hashtable <String, String>();
    	
    	try {
    		in1 = new Scanner(new FileInputStream(f1));
    		in2 = new Scanner(new FileInputStream(f2));
    		while(in1.hasNext()) {
    			t1 = in1.nextLine();
    			hashTable.put(t1, t1);
    		}
  		
    		while(in2.hasNext()) {
    			t2 = in2.nextLine();
    			if (hashTable.containsKey(t2)){
    				if ( hashTable.containsValue(t2)){
    					if (hashTable.remove(t2) == null) {
    						System.out.println("removing issue");
    					}
    				}
    				
    			}
    			
    		}
    		for (Enumeration<String> e = hashTable.elements(); e.hasMoreElements();) {
    			try {
    				out.println(e.nextElement());
    			} catch (Exception eu){
    				System.out.println(eu);
    			}
    		}
    		out.close();
    	}catch (Exception e) {
    		System.out.println(e);
    	}
    }
    
}
