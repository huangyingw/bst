class BinaryTree {
	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree();
		tree.leftHeight = tree.rightHeight = 0;

		tree.Insert(tree.root, 53);
		tree.Insert(tree.root, 17);
		tree.Insert(tree.root, 78);
		tree.Insert(tree.root, 9);
		tree.Insert(tree.root, 45);
		tree.Insert(tree.root, 65);
		tree.Insert(tree.root, 94);
		tree.Insert(tree.root, 23);
		tree.Insert(tree.root, 81);
		tree.Insert(tree.root, 88);

		tree.LevelOrder(tree.root, 0);
		// tree.Remove(78, tree.root);
		// // cout<<"after remove:"<<endl;
		// tree.currentHeight = 0;
		// tree.SetHeight(tree.root, 0);
		// tree.PrintBSTVer(tree.root);
		tree.preTraverse(tree.root);
		System.out.println();
		tree.inTraverse(tree.root);
		System.out.println();
		tree.posTraverse(tree.root);
	}

	private int currentHeight;
	private int deep;
	private int leftHeight;
	private int RefValue;
	private int rightHeight;
	private BTreeNode root;
	private int sumDeep;

	public BinaryTree() {
		// TODO Auto-generated constructor stub
	}

	public BinaryTree(int[] pOrder, int[] iOrder, Boolean preOrPos) {
		super();
		if (preOrPos)
			CreateBTreePre(pOrder, iOrder, null);
		else
			CreateBTreePos(pOrder, iOrder, null);
	}

	public void CreateBTreePos(int[] posOrder, int[] inOrder, BTreeNode root) {

	}

	public BTreeNode CreateBTreePos(int[] posOrder, int[] inOrder, int posLeft,
			int posRight, int inLeft, int inRight) {
		int parent, leftSize, rightSize;
		if (posLeft <= posRight && inLeft <= inRight) {
			parent = inLeft;
			while (parent < inRight && inOrder[parent] != posOrder[posRight])
				parent++;
			BTreeNode root = new BTreeNode(inOrder[parent], null, null);
			leftSize = parent - inLeft;
			rightSize = inRight - parent;
			if (leftSize > 0) {
				root.leftChild = CreateBTreePos(posOrder, inOrder, posLeft,
						posLeft + leftSize - 1, inLeft, parent - 1);
			}
			if (rightSize > 0) {
				root.rightChild = CreateBTreePos(posOrder, inOrder, posLeft
						+ leftSize, posLeft + leftSize + rightSize - 1,
						parent + 1, inRight);
			}
			return root;
		}
		return null;
	}

	public void CreateBTreePre(int[] preOrder, int[] inOrder, BTreeNode root) {

	}

	public BTreeNode CreateBTreePre(int[] preOrder, int[] inOrder, int preLeft,
			int preRight, int inLeft, int inRight) {
		int parent, leftSize, rightSize;
		if (preLeft <= preRight && inLeft <= inRight) {

			parent = inLeft;
			while (parent <= inRight && inOrder[parent] != preOrder[preLeft])
				parent++;
			BTreeNode root = new BTreeNode(inOrder[parent], null, null);
			leftSize = parent - inLeft;
			rightSize = inRight - parent;
			if (leftSize > 0) {
				root.leftChild = CreateBTreePre(preOrder, inOrder, preLeft + 1,
						preLeft + leftSize, inLeft, parent - 1);
			}
			if (rightSize > 0) {
				root.rightChild = CreateBTreePre(preOrder, inOrder, preLeft
						+ leftSize + 1, preRight, parent + 1, inRight);
			}
			return root;
		}
		return null;
	}

	void destroy(BTreeNode current) {
	}

	BTreeNode Insert(BTreeNode current, int item) {
		if (current == null) {
			current = new BTreeNode(item, null, null);
			if (root == null)
				root = current;
		} else {
			if (item < current.data) {
				current.leftChild = Insert(current.leftChild, item);
			} else {
				current.rightChild = Insert(current.rightChild, item);
			}
		}
		return current;
	}

	void inTraverse(BTreeNode current) {
		if (current != null) {
			inTraverse(current.leftChild);
			System.out.print(current.data + ",");
			inTraverse(current.rightChild);
		}
	}

	int IsEmpty() {
		return root == null ? 1 : 0;
	}

	BTreeNode LeftChild(BTreeNode current) {
		return root != null ? current.leftChild : null;
	}

	void LevelOrder(BTreeNode current, int indent) {
		if (current.rightChild != null) {
			LevelOrder(current.rightChild, indent + 1);
		}
		for (int i = 0; i < indent; i++)
			System.out.print("\t");
		System.out.println(current.data);

		if (current.leftChild != null) {
			LevelOrder(current.leftChild, indent + 1);
		}
	}

	BTreeNode Min(BTreeNode ptr) {
		if (null != ptr) {
			if (null != ptr.leftChild) {
				Min(ptr.leftChild);
			}
		}
		return ptr;
	}

	// int PrintBSTHor(BinTreeNode current) {
	// Queue<BinTreeNode > Q;
	// Q.InitQueue();
	// if(null!=current) {
	// Q.EnQueue(current);
	// while(!Q.QueueEmpty()) {
	// current=Q.DeQueue();
	// if(currentHeight<current.deep) {
	// currentHeight=current.deep;
	// out<<endl;
	// }
	// out<<current.data<<",";
	// if(null!=current.leftChild) {
	// Q.EnQueue(current.leftChild);
	// }
	// if(null!=current.rightChild) {
	// Q.EnQueue(current.rightChild);
	// }
	// }
	// }
	// return 0;
	// }

	BTreeNode Parent(BTreeNode current) {
		return root == null || root == current ? null : Parent(current);
	}

	void posTraverse(BTreeNode current) {
		if (current != null) {
			posTraverse(current.leftChild);
			posTraverse(current.rightChild);
			System.out.print(current.data + ",");
		}
	}

	void preTraverse(BTreeNode current) {
		if (current != null) {
			System.out.print(current.data + ",");
			preTraverse(current.leftChild);
			preTraverse(current.rightChild);
		}
	}

	int PrintBSTVer(BTreeNode aa) {
		deep += 1;
		sumDeep += deep;
		for (int i = 0; i < deep; i++)
			System.out.println("\t" + aa.data);
		if (null != aa.leftChild) {
			PrintBSTVer(aa.leftChild);
		}
		if (null != aa.rightChild) {
			PrintBSTVer(aa.rightChild);
		}
		deep -= 1;
		return deep;
	}

	// ostream & operator<<(ostream &out,BinaryTree&Tree) {
	// out<<"preorder traversal of bianry tree"<<endl;
	// Tree.Traverse(Tree.root,out);
	// out<<endl;
	// return out;
	// }

	void Remove(int x, BTreeNode ptr) {
		BTreeNode temp;
		if (null != ptr) {
			if (x < ptr.data) {
				Remove(x, ptr.leftChild);
			} else if (x > ptr.data) {
				Remove(x, ptr.rightChild);
			} else if (ptr.leftChild != null && ptr.rightChild != null) {
				temp = Min(ptr.rightChild);
				ptr.data = temp.data;
				Remove(ptr.data, ptr.rightChild);
			} else {
				temp = ptr;
				if (ptr.leftChild == null) {
					ptr = ptr.rightChild;
					SetHeight(ptr, temp.deep);
				} else if (ptr.rightChild == null) {
					ptr = ptr.leftChild;
					SetHeight(ptr, temp.deep);
				}
			}
		}
	}

	BTreeNode RightChild(BTreeNode current) {
		return root != null ? current.rightChild : null;
	}

	void SetHeight(BTreeNode ptr, int height) {
		if (null != ptr) {
			ptr.deep = height;
			if (null != ptr.leftChild) {
				SetHeight(ptr.leftChild, ptr.deep + 1);
			}
			if (null != ptr.rightChild) {
				SetHeight(ptr.rightChild, ptr.deep + 1);
			}
		}
	}

}
