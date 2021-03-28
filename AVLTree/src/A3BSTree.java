import java.util.Collection;
import java.util.Iterator;


public class A3BSTree <E extends Comparable<? super E>> implements Tree<E>{

	private Node root;
	private int size;

	public class Node{
		Node left;
		Node right;
		E value;


		public Node(E value) {
			this.value = value;
		}
	}

	public A3BSTree(){
		// TODO Auto-generated method stub
		this.size = 0;
	}

	public A3BSTree(E value){
		// TODO Auto-generated method stub
		root = new Node(value);
		this.size = 1;
	}

	@Override
	public boolean add(E e) {

		// if value if null then we need to throw an exception !
		if(e == null){
			throw new NullPointerException();
		}

		if(root == null) {
			size += 1;
			root = new Node(e);
			return true;
		} else {
			Node newNode = new Node(e);

			Node tempRoot = root;

			Node y = null;

			while(tempRoot != null){
				y = tempRoot;
				if(e.compareTo(tempRoot.value) < 0){
					tempRoot = tempRoot.left;
				} else {
					tempRoot = tempRoot.right;
				}
			}

			if(y == null){
				y = newNode;
				size += 1;
			} else if(e.compareTo(y.value) < 0){
				y.left = newNode;
				size += 1;
			} else if(e.compareTo(y.value) == 0) {
				return false;
			} else {
				y.right = newNode;
				size += 1;
			}

			return true;
		}
	}


	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub

		boolean flag = true;

		for(E e:c) {
			flag = add(e) && true;
		}

		return flag;
	}

	@Override
	public boolean remove(Object o) {

		@SuppressWarnings("unchecked")
		E e = (E) o;

		if(e == null) {
			throw new NullPointerException();
		}

		Node tempRoot = root;
		Node previous = null;

		while(tempRoot != null && e.compareTo(tempRoot.value) != 0){
			previous = tempRoot;
			if( e.compareTo(tempRoot.value) < 0){
				tempRoot = tempRoot.left;
			}
			else{
				tempRoot = tempRoot.right;
			}
		}
		if( tempRoot == null){
			return false;
		}

		if(tempRoot.left == null || tempRoot.right == null){
			Node newCurr;
			if(tempRoot.left == null){
				newCurr = tempRoot.right;
			} else {
				newCurr = tempRoot.left;
			}

			if(previous == null){
				size -= 1;
				return true;
			}

			if(tempRoot == previous.left){
				previous.left = newCurr;
			} else {
				previous.right = newCurr;
			}
		} else {
			Node p = null;
			Node temp;

			temp = tempRoot.right;
			while(temp.left != null){
				p = temp;
				temp = temp.left;
			}

			if(p != null){
				p.left = temp.right;
			} else {
				tempRoot.right = temp.right;
			}

			tempRoot.value = temp.value;
		}

		size -= 1;
		return true;
	}

	@Override 
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		Node tempRoot = root;

		@SuppressWarnings("unchecked")
		E e = (E) o;
		return helper(e,tempRoot);

	}

	private boolean helper(E e,Node tempRoot) { 

		if(tempRoot == null) {
			return false;
		} else if(e.compareTo(tempRoot.value) == 0) {
			return true;
		}
		else if (e.compareTo(tempRoot.value) > 0)
		{
			return helper(e,tempRoot.right);
		} else {
			return helper(e,tempRoot.left);
		} 
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub

		return new InorderIterator();
		
//		Node tempRoot = root;
//		inOrder(tempRoot);
//		return null;
	}
	
	private class InorderIterator implements Iterator<E>
	{
		public InorderIterator()
		{
			inorder();
			
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public E next() {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	private void inorder() {
				Traversal(root);
		  }  
	
	 private void Traversal(Node root)
	    {
	        Node current, pre;
	 
	        if (root == null)
	            return;
	 
	        current = root;
	        while (current != null)
	        {
	            if (current.left == null)
	            {
	                System.out.println(current.value + " ");
	                current = current.right;
	            }
	            else {
	             
	                pre = current.left;
	                while (pre.right != null
	                       && pre.right != current)
	                    pre = pre.right;
	 
	                if (pre.right == null) {
	                    pre.right = current;
	                    current = current.left;
	                }
	 
	                else
	                {
	                    pre.right = null;
	                    System.out.println(current.value + " ");
	                    current = current.right;
	                } 
	 
	            }
	 
	        } 
	    }

	private void inOrder(Node tempRoot) {
		//--------------- Other Implementation --------------//
		//		if(root == null) {
		//			return;
		//		}
		//		inOrder(root.left);
		//		System.out.println(root.value);
		//		inOrder(root.right);

		if(tempRoot == null) {
			System.out.println("No element exists in the BSTree !");
		} else {
			if(tempRoot.left != null) {
				inOrder(tempRoot.left);
			}
			System.out.print(tempRoot.value + "  ");
			if(tempRoot.right != null) {
				inOrder(tempRoot.right);
			}
		}


	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		if(root == null) {
			return -1;
		} else {
			Node tempRoot = root;
			return heightHelper(tempRoot);
		}
	}

	private int heightHelper(Node node) {
		int lHeight = -1;
		int rHeight = -1;

		if(node.left != null) {
			lHeight = heightHelper(node.left) ;
		}
		if(node.right != null) {
			rHeight = heightHelper(node.right) ;
		}

		if(lHeight > rHeight) {
			return lHeight+1;
		} else {
			return rHeight+1;
		}

	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

		public static void main(String[] args) {
			A3BSTree<Integer> tree1 = new A3BSTree<>();
	////		tree1.add(1);
	//////		tree1.add(2);
	//		int[] arr = {200,20,45,0,1000,56,2,28,999,15,6,49,752};
	//		for(int i=0;i<=12;i++) {
	//			tree1.add(arr[i]);
	////			System.out.println();
	//		}
	//		System.out.println("Height :- ");
	//		System.out.println(tree1.height());
	//		tree1.iterator();
	////		System.out.println(tree1.contains(1));
	////		System.out.println(tree1.size);
	//
					for(int i=1;i<=100000;i++) {
						System.out.println(tree1.add(i));
					}
					tree1.iterator();
	////		System.out.println(tree1.size);
	//
	//
	//		//		System.out.println("size "+tree1.size());
	//		//		tree1.add(5);
	//		//		System.out.println("size "+tree1.size());
	//		//		tree1.add(5);
	//		//		System.out.println("size "+tree1.size());
	//		//		tree1.add(5);
	//		//		System.out.println("size "+tree1.size());
	//		//		tree1.add(12);
	//		//		System.out.println("size "+tree1.size());
	//		//		tree1.add(2);
	//		//		System.out.println("size "+tree1.size());
	//		//		tree1.add(3);
	//		//		System.out.println("size "+tree1.size());
	//		//		tree1.add(10);
	//		//		System.out.println("size "+tree1.size());
	//		//		tree1.add(15);
	//		//		System.out.println("size "+tree1.size());
	//		//		tree1.iterator();
	//		//		System.out.println("size "+tree1.size());
	//		//		System.out.println();
	//		//		System.out.println("height :- " + tree1.height);
	//		//		System.out.println(tree1.contains(100));
	//		//		tree1.iterator();
	//		//		System.out.println(tree1.remove(100));
	//		//		tree1.iterator();
	//		//		System.out.println(tree1.remove(8));
	//		//		tree1.iterator();
	//		//		System.out.println(tree1.remove(5));
	//		//		tree1.iterator();
	//		//		System.out.println(tree1.remove(12));
	//		//		System.out.println("size "+tree1.size());
	//		//		tree1.iterator();
	//		//		System.out.println(tree1.remove(2));
	//		//		tree1.iterator();
	//		//		System.out.println(tree1.remove(3));
	//		//		tree1.iterator();
	//		//		System.out.println(tree1.remove(10));
	//		//		System.out.println("size "+tree1.size());
	//		//		tree1.iterator();
	//		//		System.out.println(tree1.remove(15));
	//		//		tree1.iterator();
	//
	//		//		System.out.println(tree1.remove(8));
	//		//		tree1.iterator();
	//		//		tree1.iterator();
	//
	//
		}
}