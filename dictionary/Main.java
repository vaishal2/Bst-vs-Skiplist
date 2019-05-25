package dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

import dictionary.skiplist.SkipListImpl;

public class Main {

	public static void avl()  throws IOException
	{
		int key, value;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        avlnew tree = new avlnew();
        System.out.println("Commands: (d)elete; (i)nsert; (s)earch; (c)losestkeyafter; (p)rint; (q)uit;"); 
		System.out.println("Command: ");
		String command = in.readLine();
		char c = command.charAt(0);  

		while (! command.equals("q"))
		{
			switch(c)
			{
				case 'p':
				case 'P':
					System.out.print("\n\nPreOrder Traversal: ");
					tree.preorder(tree.root);
					System.out.print("\n\nInOrder Traversal: ");
					tree.inorder(tree.root);
					System.out.print("\n\nPostOrder Traversal: ");
					tree.postorder(tree.root);
					break;

				case 'i': 
				case 'I':
						System.out.print("Key to insert: ");
						try {
							key = Integer.parseInt(in.readLine());
							System.out.print("Value to insert: ");
							value = Integer.parseInt(in.readLine());
							tree.root = tree.insert(tree.root, key, value);
						}
						catch(NumberFormatException f) {
							System.out.println("Invalid number.");
						}
						catch(IOException io) {
							System.out.println("Invalid input.");
						}
						break;
            
				case 's':
				case 'S':
						System.out.print("Key to find: ");
						try {
							key = Integer.parseInt(in.readLine());
							System.out.println();
							tree.search(tree.root, key);
						}
						catch(NoSuchElementException e) {
							System.out.println("Node not found");
						}
						catch(NumberFormatException f) {
							System.out.println("Invalid number.");
						}
						catch(IOException io) {
							System.out.println("Invalid input.");
						}
						break;

				case 'd':
				case 'D':
						System.out.print("Key to delete: ");
						try
						{
							key = Integer.parseInt(in.readLine());
						    tree.root = tree.deleteNode(tree.root, key);
						    System.out.print("\nTree after deletion ");
							tree.inorder(tree.root);
						}
						catch(NoSuchElementException e) {
							System.out.println("Node not found");
						}
						catch(NumberFormatException f) {
							System.out.println("Invalid number.");
						}
						catch(IOException io) {
							System.out.println("Invalid input.");
						}
						break;
						
				case 'c':
				case 'C':
						System.out.print("Key to find the closest key to: ");
						try {
							key = Integer.parseInt(in.readLine());
							System.out.println();
							tree.closestkeyAfter(tree.root, key);
						}
						catch(NoSuchElementException e) {
							System.out.println("Node not found");
						}
						catch(NumberFormatException f) {
							System.out.println("Invalid number.");
						}
						catch(IOException io) {
							System.out.println("Invalid input.");
						}
						break;
  
			} //switch

			System.out.println("\nCommand: ");
			command = in.readLine();
			c=command.charAt(0);
		} 
	}
	
	public static void skiplist() throws IOException
	{
		skiplist<Integer> sl;
	    sl = new SkipListImpl();
	    int i;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Commands: (d)elete; (i)nsert; (s)earch; (c)losestkeyafter; (p)rint; (q)uit;"); 
		System.out.println("Command: ");
		String command = in.readLine();
		char c = command.charAt(0);  

		while (! command.equals("q"))
		{
			switch(c)
			{
				case 'p':
				case 'P':
						sl.print();
						break;

				case 'i': 
				case 'I':
						System.out.print("Key to insert: ");
						try {
							i = Integer.parseInt(in.readLine());
							sl.insert(i);
						}
						catch(NumberFormatException f) {
							System.out.println("Invalid number.");
						}
						catch(IOException io) {
							System.out.println("Invalid input.");
						}
						break;
            
				case 's':
				case 'S':
						System.out.print("Key to find: ");
						try {
							i = Integer.parseInt(in.readLine());
							sl.search(i);	
						}
						catch(NoSuchElementException e) {
							System.out.println("Node not found");
						}
						catch(NumberFormatException f) {
							System.out.println("Invalid number.");
						}
						catch(IOException io) {
							System.out.println("Invalid input.");
						}
						break;

				case 'd':
				case 'D':
						System.out.print("Key to delete: ");
						try
						{
							i = Integer.parseInt(in.readLine());
							sl.delete(i);
						}
						catch(NoSuchElementException e) {
							System.out.println("Node not found");
						}
						catch(NumberFormatException f) {
							System.out.println("Invalid number.");
						}
						catch(IOException io) {
							System.out.println("Invalid input.");
						}
						break;
						
				case 'c':
				case 'C':
						System.out.print("Key to find the closest key to: ");
						try {
							i = Integer.parseInt(in.readLine());
							System.out.println();
							sl.closestkeyafter(i);
						}
						catch(NoSuchElementException e) {
							System.out.println("Node not found");
						}
						catch(NumberFormatException f) {
							System.out.println("Invalid number.");
						}
						catch(IOException io) {
							System.out.println("Invalid input.");
						}
						break;
  
			} //switch

			System.out.println("\nCommand: ");
			command = in.readLine();
			c=command.charAt(0);
		} 
	}
	
	public static void main(String[] args) throws Exception
    {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
		System.out.println("Press 'A' for avl programming and 'S' for skiplist programming");
		String command = in.readLine();
		char c = command.charAt(0);  

		while (! command.equals("q"))
		{
			switch(c)
			{
				case 'A':
				case 'a':avl();
						break;
						
				case 'S':
				case 's':skiplist();
						break;
		
			}
}
    }
}
