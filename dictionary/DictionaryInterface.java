package dictionary;

public class DictionaryInterface
{

	public interface DictionaryAVL{
   
		public abstract int search(Node2 r, int key);
	
		public abstract int closestkeyAfter(Node2 r, int key);
    
		public abstract Node2 insert(Node2 node, int key, int value);
    
		public abstract Node2 deleteNode(Node2 root, int key);
	}

	public interface SkippableList<T extends Comparable<? super T>> {
		
		int LEVELS = 5;

		boolean delete(T Del);
    
		void print();
    
		void insert(T Keys);
    
		Skip<T> search(T data);
                
                Skip<T> closestkeyafter(T data);
	}

}