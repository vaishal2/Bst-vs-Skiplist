package dictionary;

import dictionary.DictionaryInterface.DictionaryAVL;

 
class avlnew implements DictionaryAVL
{
    Node2 root;
 
    int height(Node2 N)
    {
        if (N == null)
             return 0;
         return N.height;
    }
 
    int max(int a, int b)
    {
        return (a > b) ? a : b;
    }
 
    Node2 rightRotate(Node2 y)
    {
        Node2 x = y.left;
        Node2 T2 = x.right;
 
        x.right = y;
        y.left = T2;
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;
        return x;
    }
 
    Node2 leftRotate(Node2 x)
    {
        Node2 y = x.right;
        Node2 T2 = y.left;
 
        y.left = x;
        x.right = T2;
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;
        return y;
    }
 
    int getBalance(Node2 N)
    {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }
 
    public Node2 insert(Node2 node, int key, int value)
    {
        if (node == null)
            return (new Node2(key, value));
 
        if (key < node.key)
            node.left = insert(node.left, key, value);
        else if (key > node.key)
            node.right = insert(node.right, key, value);
        else
            return node;
 
        node.height = 1 + max(height(node.left),
                              height(node.right));
 
        int balance = getBalance(node);
 
        // Left Left Case
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);
 
        // Right Right Case
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);
 
        // Left Right Case
        if (balance > 1 && key > node.left.key)
        {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
 
        // Right Left Case
        if (balance < -1 && key < node.right.key)
        {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
 
        return node;
    }
 
    Node2 minValueNode(Node2 node)
    {
        Node2 current = node;
        while (current.left != null)
           current = current.left;
 
        return current;
    }
 
    public Node2 deleteNode(Node2 root, int key)
    {
        if (root == null)
            return root;
        if (key < root.key)
            root.left = deleteNode(root.left, key);
        else if (key > root.key)
            root.right = deleteNode(root.right, key);
        else
        {
            if ((root.left == null) || (root.right == null))
            {
                Node2 temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;
                if (temp == null)
                {
                    temp = root;
                    root = null;
                }
                else
                    root = temp; 
            }
            else
            {
                Node2 temp = minValueNode(root.right);
                root.key = temp.key;
                root.right = deleteNode(root.right, temp.key);
            }
        }
 
        if (root == null)
            return root;
 
        root.height = max(height(root.left), height(root.right)) + 1;
 
        int balance = getBalance(root);
 
        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);
 
        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0)
        {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
 
        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);
 
        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0)
        {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
 
        return root;
    }
 
    void inorder(Node2 node)
    {
        if (node == null)
            return;
        inorder(node.left);
        System.out.print("\nKEY: "+ node.key + "  VALUE: " + node.value);
        inorder(node.right);
    }
    
    public int search(Node2 r, int key)
    {
        boolean found = false;
        while ((r != null) && !found)
        {
            int rkey = r.key;
            if (key < rkey)
                r = r.left;
            else if (key > rkey)
                r = r.right;
            else
            {
                found = true;
                System.out.println("Key found with value: "+r.value);
                return 0;
            }
        }
        System.out.println("Key doesnt exist");
        return 0;
    }
    
    public int closestkeyAfter(Node2 r, int key)
    {
        boolean found = false;
        while ((r != null) && !found)
        {
            int rkey = r.key;
            if (key < rkey)
                r = r.left;
            else if (key > rkey)
                r = r.right;
            else if(key==rkey)
            {
                found = true;
                if(r.right != null)
                {
                		rkey = r.right.key;
                		System.out.print("\nClosest key after "+ key + " is " + rkey);
                		return 0;
                }
            }
        }
        System.out.println("Key doesnt exist");
        return 0;
    }
    
    void preorder(Node2 node)
    {
        if (node == null)
            return;
        System.out.print("\nKEY: "+ node.key + "  VALUE: " + node.value);
        preorder(node.left);
        preorder(node.right);
    }
    
    void postorder(Node2 node)
    {
        if (node == null)
            return;
        postorder(node.left);
        postorder(node.right);
        System.out.print("\nKEY: "+ node.key + "  VALUE: " + node.value);
    }
}