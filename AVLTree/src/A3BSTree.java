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
		
		if(o == null){
			throw new NullPointerException();
		}
		
		 
		// TODO Auto-generated method stub
		return false;
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
		
//		boolean flag = true;
//		
//		System.out.println(e.compareTo(tempRoot.value) < 0);
//		System.out.println();
//		System.out.println(e.compareTo(tempRoot.value) == 0);
//		System.out.println("tempRoot.value :- " + tempRoot.value + "   e :- " + e);
//		if(e.compareTo(tempRoot.value) == 0) {
//			System.out.println("here we go !");
//			flag = true;
//			return flag;
//		} else if(e.compareTo(tempRoot.value) < 0) {
//			if(tempRoot.left == null) {
//				System.out.println("1");
//				return false;
//			} else {
////				tempRoot = tempRoot.left;
//				helper(e,tempRoot.left); // 10 10
//			}
//		} else {
//			if(tempRoot.right == null) {
//				System.out.println("2");
//				return false;
//			} else {
////				tempRoot = tempRoot.right;
//				helper(e,tempRoot.right); // 10 12
//			}
//		}
//		
////		return flag;
//		return true;
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
		System.out.println();
		System.out.println(tree1.contains(100));
		tree1.iterator();
		
		
	}

}

