import java.util.*;

public class Default {
	
    public static void main(String[] args) {
    	
    	int n = 8;
    	Set<Integer> l = new HashSet<Integer>();
    	int[][] a = {{2,1},{3,2},{4,3},{5,4},{6,5},{7,6},{8,7}};

    	System.out.println("prerequisites");
    	System.out.println(prerequisites(n,l,a));
    	System.out.println();
    	
    	
    	String s1 = "AAWEFAWPAOAWEDLEOEPDLSFAOAWEVNKPAPWQODLOQLOAWEOQNLMNOPP";
    	String s2 = "POOL";

    	System.out.println("substring");
    	System.out.println(s1);
    	System.out.println(substring(s1,s2));
    	System.out.println();
    	
    	
    	int[] h = {3,2,4,5,7,6,1,3,8,9,11,10,7,5,2,6};

    	System.out.println("rectangle");
    	System.out.println(rectangle(h));
    	System.out.println();
    	
    }
    
    public static Boolean prerequisites(int n, Set<Integer> l, int [][] a) {
    	
    	Boolean p = true;
    	HashMap<Integer, Integer> prereqs = new HashMap<Integer, Integer>();
    	for (int[] i : a) {
    		prereqs.put(i[0],i[1]);
    	}

    	l.add(n);
    	if (prereqs.containsKey(prereqs.get(n))) {
    		if (l.contains(prereqs.get(n))) {return p = false;} else {p = prerequisites(prereqs.get(n),l,a);}
    	}
    	
    	return p;
    	
    }

    public static String substring(String s1, String s2) {
    	
    	HashMap<Character, Integer> c = new HashMap<Character, Integer>();
    	Queue<Character> a = new LinkedList<Character>();
    	Queue<Character> b = new LinkedList<Character>();
    	Queue<Character> d = new LinkedList<Character>();
    	
    	int shortest = s1.length();
    	int temp = 0;
    	boolean met = false;
    	String substring = b.toString();
    	
    	for(int i = 0; i < s2.length(); i++) {
    		Character l = s2.charAt(i);
    		if(!c.containsKey(l)) {c.put(l, 1);}
    		else {c.put(l, (c.get(l) + 1));}
	    }

		System.out.println(c.keySet() + " " + c.values());
		
    	for(int i = 0; i < s1.length(); i++) {
    		Character t = s1.charAt(i);
    		a.add(t);
    		temp++;
    		if(c.containsKey(t)) {
    			c.put(t,c.get(t) - 1);
    		}
    		
    		boolean loop = true;
    		b.clear();
    		b.addAll(a);
			
			for(Character j : b) {
				if(loop) {
					if(!c.containsKey(j)) {a.poll(); temp--;}
					else if(c.get(j) <= -1) {c.put(j, c.get(j) + 1); a.poll(); temp--;}
					else {loop = false;}
					
					if(temp < shortest) {
						met = true;
						for(Integer k : c.values()) {
							if(!(k <= 0)) {met = false;}
						}
						if(met) {
							d.clear();
							d.addAll(a);
							shortest = temp;
						}
					}
					
				}
			}
    	}
    	
    	substring = d.toString();
    	return substring;
    	
    }
    
    public static int rectangle(int[] h) {
    	int l = 0;
    	int t = 0;
    	int s = 1;
    	for(int i = 0; i < h.length; i++) {
    		t += h[i];
    		for(int j = i + 1; s != 0 && (j < h.length); j += s) {
    			if(j <= 0) s = 0;
    			else if(h[j] < h[i]) {if(s == -1) {s = 0;} s *= -1; j = i;}
    			else t += h[i];
    			
    		}
    		if(t > l) l = t;
    		t = 0;
        	s = 1;
    	}
    	return l;
    }
    
}