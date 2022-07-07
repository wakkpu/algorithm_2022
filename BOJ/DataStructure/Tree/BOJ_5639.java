package BOJ.DataStructure.Tree;

import java.io.*;
import java.util.*;

public class BOJ_5639 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		ArrayList<Integer> pre = new ArrayList<>();
		
		while(br.ready()) {
			String str = br.readLine();
			pre.add(Integer.parseInt(str));
		}
		
		preToPost(pre);
	}
	
	public static void preToPost(ArrayList<Integer> pre) {
		
		if(pre == null || pre.size() == 0)
			return;
		
		int root = pre.get(0);
		
		ArrayList<Integer> left  = new ArrayList<Integer>();
		ArrayList<Integer> right = new ArrayList<Integer>();
		
		for(int i=1; i<pre.size(); i++) {
			if(pre.get(i) < root) {
				left.add(pre.get(i));
			} else if(pre.get(i) > root) {
				right.add(pre.get(i));
			}
		}
		
		if(left.size() <= 1 && right.size() <= 1) {
			if(left.size() == 1)
				System.out.println(left.get(0));
			
			if(right.size() == 1)
				System.out.println(right.get(0));
			
			System.out.println(root);
			return;
		}
		
		preToPost(left);
		preToPost(right);
		System.out.println(root);
	}
}
