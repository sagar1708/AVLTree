import java.util.Collection;
import java.util.Iterator;


public class A3BSTree <E extends Comparable<? super E>> implements Tree<E>{

	private Node root;
	private int size;
	private int height;

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
		this.height = -1;
	}

	public A3BSTree(E value){
		// TODO Auto-generated method stub
		root = new Node(value);
		this.size = 1;
		this.height = -1;
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
	}
		
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
		
		
//		size += 1;
//		Node tempRoot = root;
//		if(root == null) {
////			root.size += 1;
//			height += 1;
//			root = new Node(e);
//			return true;
//		}else if(e.compareTo(root.value) < 0) {
//			if(root.left == null) {
//				root.left = new Node(e);
////				root.size += 1;
//				height += 1;
//				root = tempRoot;
//				return true;
//			} else {
////				root.size += 1;
//				root = root.left;
//				add(e);
//			}
//		} else if(e.compareTo(root.value) > 0){
//			if(root.right == null) {
//				root.right = new Node(e);
////				root.size += 1;
//				height += 1;
//				root = tempRoot;
//				return true;
//			} else {
////				root.size += 1;
//				root = root.right;
//				add(e);
//			}
//		} 
//		size -= 1;
//		root = tempRoot;
//		return false;
		//		root = addHelper(e,tempRoot);
		//		System.out.println(root.value);
		//		return true;
	}

	//	private Node addHelper(E e,Node tempRoot) {
	//		if(tempRoot == null) {
	//			return new Node(e);
	//		} else if(e.compareTo(tempRoot.value) < 0) {
	//			tempRoot.left = addHelper(e,tempRoot.left);
	//		} else if(e.compareTo(tempRoot.value) > 0) {
	//			tempRoot.right = addHelper(e, tempRoot.right);
	//		} else {
	//			// Nothing as we don't want duplicate in our BST
	//			;
	//		}
	//		return tempRoot;
	//	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Object o) {
		
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
	      while(tempRoot.left != null){
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

//		E e = (E) o;
//		if(!contains(o)) return false;
//		Node tempRoot = root; 
//		root = helperDelete(e,tempRoot);
//		
//		return true;
		
//		  E e = (E) o;
//		    Node tempRoot = root;
//		    if(e == null){
//		      return false;
//		    } else if(e.compareTo(root.value) < 0){
//		      root = root.left;
//		      remove(o);
//		    } else if(e.compareTo(root.value) > 0){
//		      root = root.right;
//		      remove(o);
//		    } else {
//		      if(root.right == null && root.left == null){
//		    	  root = tempRoot;
//		    	  return true;
//		      } else if(root.right == null){
//		    	root = tempRoot;
//		        root = root.left;
//		        return true;
//		      } else if(root.left == null){
//		        root = root.right;
//		        root = tempRoot;
//		        return true;
//		      } else {
//		        root.value = findMax(root.left);
//		        Object o1 = root.value;
//		        root = root.left;
//		        remove(o1);
//		      }
//		    }
//		    root = tempRoot;
//		    return false;
	}

	private Node helperDelete(E e,Node tempRoot) {
		if(e == null){
			throw new NullPointerException();
		}
//		else if(tempRoot == null) {
//			// in this state we 
//			System.out.println("not element found !");
//		} 
		else if (e.compareTo(tempRoot.value) < 0) {
			tempRoot.left =  helperDelete(e,tempRoot.left);
		} else if(e.compareTo(tempRoot.value) > 0) {
			tempRoot.right = helperDelete(e,tempRoot.right);
		} else { // we found the node to be deleated
			if(tempRoot.left == null && tempRoot.right == null) {
				return null;
			} else if(tempRoot.right == null) {
				return tempRoot.left;
			} else if(tempRoot.left == null) {
				return tempRoot.right;
			} else {
				tempRoot.value = findMax(tempRoot.left);
				tempRoot.left = helperDelete(tempRoot.value, tempRoot.left);
			}
		}
		return tempRoot;
	}

	private E findMax(Node tempRoot) {
		while(tempRoot.right != null) {
			tempRoot = tempRoot.right;
		}
		return tempRoot.value;
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
		
		
		
		return height;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	public static void main(String[] args) {
		A3BSTree<Integer> tree1 = new A3BSTree<>();
		
		for(int i=1;i<=100000;i++) {
			System.out.println(tree1.add(i));
		}
		
		System.out.println(tree1.size);
		
		for(int i=1;i<=100020;i++) {
			System.out.println(tree1.remove(i));
		}
		System.out.println(tree1.size);
		
		
//		System.out.println("size "+tree1.size());
//		tree1.add(5);
//		System.out.println("size "+tree1.size());
//		tree1.add(5);
//		System.out.println("size "+tree1.size());
//		tree1.add(5);
//		System.out.println("size "+tree1.size());
//		tree1.add(12);
//		System.out.println("size "+tree1.size());
//		tree1.add(2);
//		System.out.println("size "+tree1.size());
//		tree1.add(3);
//		System.out.println("size "+tree1.size());
//		tree1.add(10);
//		System.out.println("size "+tree1.size());
//		tree1.add(15);
//		System.out.println("size "+tree1.size());
//		tree1.iterator();
//		System.out.println("size "+tree1.size());
//		System.out.println();
//		System.out.println("height :- " + tree1.height);
//		System.out.println(tree1.contains(100));
//		tree1.iterator();
//		System.out.println(tree1.remove(100));
//		tree1.iterator();
//		System.out.println(tree1.remove(8));
//		tree1.iterator();
//		System.out.println(tree1.remove(5));
//		tree1.iterator();
//		System.out.println(tree1.remove(12));
//		System.out.println("size "+tree1.size());
//		tree1.iterator();
//		System.out.println(tree1.remove(2));
//		tree1.iterator();
//		System.out.println(tree1.remove(3));
//		tree1.iterator();
//		System.out.println(tree1.remove(10));
//		System.out.println("size "+tree1.size());
//		tree1.iterator();
//		System.out.println(tree1.remove(15));
//		tree1.iterator();

		//		System.out.println(tree1.remove(8));
		//		tree1.iterator();
		//		tree1.iterator();


	}
}