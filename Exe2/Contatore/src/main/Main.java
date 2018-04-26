package main;

import java.util.ArrayList;

import contatore.MyCounter;
import thread.*;

public class Main {

	public static void main(String[] args) {
		
		MyCounter mc = new MyCounter();
		
		int n = Integer.MAX_VALUE;
		
		ArrayList<Thread> threads = new ArrayList<Thread>();
		
		for(int i = 0; i<n; i++){
			System.out.println("dssdia");
			threads.add(new Thread(new ThreadI(mc)));
			threads.add(new Thread(new ThreadD(mc)));
		}
		
		for(Thread i : threads)
			i.start();
		
		
	}

}
