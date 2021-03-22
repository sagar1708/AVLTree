import java.util.Collection;
import java.util.Iterator;


public class A3BSTree <E extends Comparable<? super E>> implements Tree<E>{
	
	private Node root;
	
	public class Node{
		Node left;
		Node right;
		E value;
		private int size;
		
		public Node(E value) {
			this.value = value;
		}
		
		public int getSize() {
			return size;
		}
	}
	
	public A3BSTree(){
		// TODO Auto-generated method stub
	}
	
	public A3BSTree(E value){
		// TODO Auto-generated method stub
		root = new Node(value);
	}

	@Override
	public boolean add(E e) {
		
		// if value if null then we need to throw an exception !
		if(e == null){
			throw new NullPointerException();
		}
		
		Node tempRoot = root;
		
		 if(e.compareTo(root.value) < 0) {
			 if(root.left == null) {
				 root.left = new Node(e);
			 } else {
				 root = root.left;
				 add(e);
			 }
		 } else {
			 if(root.right == null) {
				 root.right = new Node(e);
			 } else {
				 root = root.right;
				 add(e);
			 }
		 }
		 root = tempRoot;
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		inOrder(root);
		return null;
	}
	
	private void inOrder(Node root) {
		//--------------- Other Implementation --------------//
//		if(root == null) {
//			return;
//		}
//		inOrder(root.left);
//		System.out.println(root.value);
//		inOrder(root.right);
		
		if(root.left != null) {
			inOrder(root.left);
		}
		System.out.println(root.value);
		if(root.right != null) {
			inOrder(root.right);
		}
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return root.getSize();
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static void main(String[] args) {
		A3BSTree<Integer> tree1 = new A3BSTree<>(8);
		tree1.add(5);
		tree1.add(12);
		tree1.add(2);
		tree1.add(3);
		tree1.add(10);
		tree1.add(15);
		tree1.iterator();
		
		
	}

}

