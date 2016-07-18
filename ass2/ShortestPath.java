/* ShortestPath.java
   CSC 226 - Fall 2014
   Assignment 3 - Template for Dijkstra's Algorithm
   
   This template includes some testing code to help verify the implementation.
   To interactively provide test inputs, run the program with
	java ShortestPath
	
   To conveniently test the algorithm with a large input, create a text file
   containing one or more test graphs (in the format described below) and run
   the program with
	java ShortestPath file.txt
   where file.txt is replaced by the name of the text file.
   
   The input consists of a series of graphs in the following format:
   
    <number of vertices>
	<adjacency matrix row 1>
	...
	<adjacency matrix row n>
	
   Entry A[i][j] of the adjacency matrix gives the weight of the edge from 
   vertex i to vertex j (if A[i][j] is 0, then the edge does not exist).
   Note that since the graph is undirected, it is assumed that A[i][j]
   is always equal to A[j][i].
	
   An input file can contain an unlimited number of graphs; each will be 
   processed separately.


   B. Bird - 08/02/2014
*/

import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;
import java.io.File;

import java.util.ArrayDeque;

import java.util.*;

//Do not change the name of the ShortestPath class
public class ShortestPath{

	/* ShortestPath(G)
		Given an adjacency matrix for graph G, return the total weight
		of a minimum weight path from vertex 0 to vertex 1.
		
		If G[i][j] == 0, there is no edge between vertex i and vertex j
		If G[i][j] > 0, there is an edge between vertices i and j, and the
		value of G[i][j] gives the weight of the edge.
		No entries of G will be negative.
	*/
	public static class Heap{

	/* ************************* DEBUGGING OPTIONS ************************* */
	/*
	   These govern the behavior of the testing code in main().
	   Some tests are slow or annoying, so you may want to disable them.
	*/
	
	/* Tests for heap properties */
	/* If these are set to true, the heap properties are checked after every 
	   insertion/deletion. If the properties do not hold, an error message is
	   printed and the program halts.
	   
	   Note that testing the heap properties is a O(n) operation, so these
	   tests can dramatically increase the running time of the program.
	*/
	   
	public static final boolean TestHeapAfterEachInsertion = false;
	public static final boolean TestHeapAfterEachDeletion = false;
	
	
	/* Threshold for printing the tree contents */
	/* If one of the tests above fails, the entire tree will be printed if its
	   size is at most this value
	*/
	public static final int PrintTreeSizeThreshold = 10;
	/* *********************** END DEBUGGING OPTIONS *********************** */
	
	
	/* *********************** METHODS TO IMPLEMENT ************************ */
	
	int size;
	HeapNode root;
	/* Constructor */
	/* (You may not require any additional code here) */
	public Heap(){
		/* Your code here */
	    root=null;
		size=0;
	}
	
	/* removeMinimum()
	   Remove and return the minimum element of the heap, restoring the heap
	   properties as necessary. This method should have O(log n) running 
	   time.
	*/
	public int removeMin(){

		/* Your code here */
		int min=root.element;
		if (size==1){
			root=null;
			size--;
			return min;
		}else{
			HeapNode current=findnode(size);
			root.element=current.element;
			HeapNode father=findparentnode(size);
			if(size%2==0){
				father.leftChild=null;
			}else{
				father.rightChild=null;
			}
			size--;
	
            bubbledown(root);
		}	
		
		
		
		return min;
	}
	
    public void bubbledown(HeapNode root){
		int temp;
		HeapNode smallChild;
		while(root.leftChild!=null){
			smallChild=root.leftChild;
			if(root.rightChild!=null && root.leftChild.element>root.rightChild.element){
				smallChild=root.rightChild;
			}
		    if(root.element>smallChild.element){
		    	temp=root.element;
		    	root.element=smallChild.element;
		    	smallChild.element=temp;
		        root=smallChild;	
		    }else{
		    	break;
		    }	
			
			
		}
    	
    	
    	
    	
    	
    }
	
	
	public HeapNode findnode(int size){
	Stack<String> l=new Stack<String>();
	HeapNode root1=root;
	int current=size;
	while(current>1){
	   if(current%2==1){
		   l.push("rightChild");
	   }else{
		   l.push("leftChild");
	   }
	   current=current/2;
	}
	while(l.size()>0){
       if(l.pop()=="rightChild"){
    			root1=root1.rightChild;
    	}else{
    			root1=root1.leftChild;
    	}       		         		 		 
    }	
    	
	return root1;	
    }

	
	
	
	public HeapNode findparentnode(int size){
		Stack<String> l=new Stack<String>();
		HeapNode root1=root;
		int current=size;
		while(current>1){
		   if(current%2==1){
			   l.push("rightChild");
		   }else{
			   l.push("leftChild");
		   }
		   current=current/2;
		}
		while(l.size()>1){
           if(l.pop()=="rightChild"){
        			root1=root1.rightChild;
        	}else{
        			root1=root1.leftChild;
        	}       		         		 		 
        }	
        	
		return root1;	
	}
	
	/* add(e)
	   Add the provided element to the heap, restoring heap properties as
	   necessary. This method should have O(log n) running time.
	*/
	public void add(int element){
		/* Your code here */
		  HeapNode a=new HeapNode(element);
          size++;
          if(size==1){
             root=a;
          }else{
			 HeapNode root1=findparentnode(size);
			 HeapNode current=a;
             if(size%2==0){
             	root1.leftChild=current;
             	current.parent=root1;
             }else{
             	root1.rightChild=current;
             	current.parent=root1;
             }    
             bubbleup(current);   
          }
	}
	

    public void bubbleup(HeapNode current){
          if(current==root){
          	
          }else{
             HeapNode p=current.parent;
             if(current.element<p.element){
             
          	  int temp;
        	  temp=current.element;
        	  current.element=p.element;
        	  p.element=temp;
        	  current=p;
        	  bubbleup(p);
        	  }
          }  		
	}
	
	/* getSize()
	   Returns the total number of nodes in the tree.
	*/
	public int getSize(){
		/* Your code here */
		return size;
	}
	
	/* getRoot()
	   Return a pointer to the root of the heap (or null if the tree is 
	   empty).
	*/
	public HeapNode getRoot(){
		/* Your code here */
		return root;
	}

	
	/* HeapNode class */
	/* Do not change anything in the class definition below */
	/* If any contents of the TreeNode class are changed, your submission will
	   not be marked. */
	/* You may create a sub-class of HeapNode if you want to add functionality. */
	public static class HeapNode{
		//Integer value contained in this node.
		int element;
		//Pointer to the parent of this node (or null if this node is the root
		//of the tree).
		public HeapNode parent;
		//Pointers so the left and right children of this node (or null if the
		//child is missing).
		public HeapNode leftChild;
		public HeapNode rightChild;
		
		public HeapNode(int element){
			this.element = element;
		}
	}

	
}
	
	
	
	static int ShortestPath(int[][] G){
		int numVerts = G.length;
		int totalWeight = 0;
		int inf = (int)Double.POSITIVE_INFINITY;
		/* ... Your code here ... */
		int dist[]=new int[numVerts];
		for (int i=0 ;i<numVerts;i++){
			dist[i]=inf;
		}
		Heap H=new Heap();
		dist[0]=0;
		H.add(dist[0]);
		while (H.getSize()>0){
			int k=0;

			int q = H.removeMin();

			for(int i=0;i<numVerts;i++){
			    if (dist[i]==q){
					k=i;         //find min's first index
					break;
				}
			}
			//find neighbour
			for(int j=0;j<numVerts;j++){
			   if(G[k][j]>0){
				   if(dist[k]+G[k][j]<dist[j]){
					   dist[j]=dist[k]+G[k][j];
					   H.add(dist[j]);
				   }   
			   }
			}
		}
	
	    totalWeight=dist[1];
		return totalWeight;
		
	}
	
		
	/* main()
	   Contains code to test the ShortestPath function. You may modify the
	   testing code if needed, but nothing in this function will be considered
	   during marking, and the testing process used for marking will not
	   execute any of the code below.
	*/
	public static void main(String[] args){
		Scanner s;
		if (args.length > 0){
			try{
				s = new Scanner(new File(args[0]));
			} catch(java.io.FileNotFoundException e){
				System.out.printf("Unable to open %s\n",args[0]);
				return;
			}
			System.out.printf("Reading input values from %s.\n",args[0]);
		}else{
			s = new Scanner(System.in);
			System.out.printf("Reading input values from stdin.\n");
		}
		
		int graphNum = 0;
		double totalTimeSeconds = 0;
		
		//Read graphs until EOF is encountered (or an error occurs)
		while(true){
			graphNum++;
			if(graphNum != 1 && !s.hasNextInt())
				break;
			System.out.printf("Reading graph %d\n",graphNum);
			int n = s.nextInt();
			int[][] G = new int[n][n];
			int valuesRead = 0;
			for (int i = 0; i < n && s.hasNextInt(); i++){
				for (int j = 0; j < n && s.hasNextInt(); j++){
					G[i][j] = s.nextInt();
					valuesRead++;
				}
			}
			if (valuesRead < n*n){
				System.out.printf("Adjacency matrix for graph %d contains too few values.\n",graphNum);
				break;
			}
			long startTime = System.currentTimeMillis();
			
			int totalWeight = ShortestPath(G);
			long endTime = System.currentTimeMillis();
			totalTimeSeconds += (endTime-startTime)/1000.0;
			
			System.out.printf("Graph %d: Minimum weight of a 0-1 path is %d\n",graphNum,totalWeight);
		}
		graphNum--;
		System.out.printf("Processed %d graph%s.\nAverage Time (seconds): %.2f\n",graphNum,(graphNum != 1)?"s":"",(graphNum>0)?totalTimeSeconds/graphNum:0);
	}
}