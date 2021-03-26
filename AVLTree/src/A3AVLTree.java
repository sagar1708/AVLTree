import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;
import java.lang.Object;

public class A3AVLTree <E extends Comparable<? super E>> implements Tree<E>{ //consider extending A3BSTree

	private Node root;
	private int size;

	public class Node{
		Node left;
		Node right;
		E value;
		int balanceFactor;
		int height;


		public Node(E value) {
			this.value = value;
			this.height = 0;
			this.balanceFactor = 0;
		}
	}
	public A3AVLTree(){
		// TODO Auto-generated method stub
	}

	//	public A3AVLTree(E value) {
	//		root = new Node(value);
	//		this.size = 1;
	//	}

	private Node rrRotation(Node y) {
		Node x = y.left;
		Node z = x.right;

		x.right = y;
		y.left = z;

		y.height = max(heightOfNode(y.left) , heightOfNode(y.right));
		x.height = max(heightOfNode(x.left) , heightOfNode(x.right));

		return x;
	}

	private Node lrRotation(Node x) {
		Node y = x.right;
		Node z = x.left;

		y.left = x;
		x.right = z;

		x.height = max(heightOfNode(x.left) , heightOfNode(x.right));
		y.height = max(heightOfNode(y.left) , heightOfNode(y.right));

		return x;
	}

	private int max(int a,int b) {
		return (a>b) ? a:b;
	}

	private int balancing(Node n) {
		if(n == null) {
			return 0;
		}

		int ans = heightOfNode(n.left) - heightOfNode(n.right);
		return ans;
	}

	private int heightOfNode(Node n) {
		if(n == null) {
			return 0;
		}
		return n.height;
	}

	@Override
	public boolean add(E e) {
		// TODO Auto-generated method stub
		// if value if null then we need to throw an exception !
		Stack<Node> stack = new Stack<Node>();
		

		Node tempRoot = root;
		if(root == null) {
			size += 1;
			root = new Node(e);
			root.height = max(heightOfNode(root.left) , heightOfNode(root.right)) + 1;
		} else {

			Node newNode = new Node(e);

			Node y = null;

			while(tempRoot != null){
				y = tempRoot;
				if(e.compareTo(tempRoot.value) < 0){
					stack.push(tempRoot);
					tempRoot = tempRoot.left;

				} else {
					stack.push(tempRoot);
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
			
			Node walkBackNode;
			int lHeight,rHeight;
//			System.out.println(stack.pop().value);
			while(!stack.empty()) {
				walkBackNode = stack.pop();
				if(walkBackNode.left == null) {
					lHeight = -1;
				} else {
					lHeight = walkBackNode.left.height;
				}
				
				if(walkBackNode.right == null) {
					rHeight = -1;
				} else {
					rHeight = walkBackNode.right.height;
				}
				
				walkBackNode.balanceFactor = rHeight - lHeight;
				
				walkBackNode.height = 1 + max(lHeight,rHeight);
				
				if((walkBackNode.balanceFactor < -1) || walkBackNode.balanceFactor > 1) {
					// tree is violating the rules of AVL tree
					break;
				}
			}
		}
		return true;
	}
			
//			y.balanceFactor = balancing(y);
//			
//			
//			
//
//			y.balanceFactor = max(heightOfNode(y.left) , heightOfNode(y.right)) + 1;
//			int balance = balancing(y);
//			if(balance > 1 && e.compareTo(y.left.value) < 0) {
//				rrRotation(y);
//				return true;
//			}
//			if(balance < -1 && e.compareTo(y.right.value) > 0) {
//				lrRotation(y);
//				return true;
//			}
//			if(balance > 1 && e.compareTo(y.left.value) > 0) {
//				y.left = lrRotation(y.left);
//				rrRotation(y);
//				return true;
//			}
//			if(balance < -1 && e.compareTo(y.right.value) < 0) {
//				y.right = rrRotation(y.right);
//				lrRotation(y); 
//				return true;
//			}
//			return true;
//		}
//		return true;
//	}




//	private Node addHelper(Node node,E e) {
//		if(node == null) {
//			node =  (new Node(e));
//			return node;
//		}
//		if(e.compareTo(node.value) < 0)
//			node.left = addHelper(node.left,e);
//		if(e.compareTo(node.value) > 0)
//			node.right = addHelper(node.right, e);
//		else
//			return node;
//
//		node.height =  1 + max(heightOfNode(node.left) , heightOfNode(node.right));
//		int balance = balancing(node);
//
//		if(balance > 1) {
//			if(e.compareTo(node.left.value) < 0) {
//				return rrRotation(node);
//			} else if(e.compareTo(node.left.value) > 0 ) {
//				node.left = lrRotation(node.left);
//				return rrRotation(node);
//			}
//		}
//
//		if(balance < -1) {
//			if(e.compareTo(node.right.value) > 0) {
//				return lrRotation(node);
//			} else if(e.compareTo(node.right.value) < 0 ) {
//				node.right = rrRotation(node.right);
//				return lrRotation(node);
//			}
//		}
//		return node;
//
//	}

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
		// TODO Auto-generated method stub
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
			// element not found in the tree
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

		Node tempRoot = root;
		inOrder(tempRoot);
		return null;
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
		A3AVLTree<Integer> tree1 = new A3AVLTree<>();

		int[] arr = {200,20,45,0,1000,56,2,28,999,15,6,49,752};

		for(int i=0;i<=12;i++) {
			tree1.add(arr[i]);
			//			System.out.println("Size :- "+tree1.size);
		}

		//		System.out.println(tree1.root.value);
		tree1.iterator();
		//		tree1.height();
		System.out.println("height :- " + tree1.height());
		//		for(int i = 0;i<12;i++) {
		//			tree1.remove(arr[i]);
		//			System.out.println("height :- " + tree1.height());
		//			System.out.println("Size :- "+tree1.size);
		//		}
	}

}
