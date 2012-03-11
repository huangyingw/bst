public class BinTreeNode {
	private int data;
	private BinTreeNode leftChild, rightChild;

	BinTreeNode(int item, BinTreeNode left, BinTreeNode right) {
		data = item;
		leftChild = left;
		rightChild = right;
	}

	int GetData() {
		return data;
	}

	BinTreeNode GetLeft() {
		return leftChild;
	}

	BinTreeNode GetRight() {
		return rightChild;
	}

	void SetData(int item) {
		data = item;
	}

	void SetLeft(BinTreeNode L) {
		leftChild = L;
	}

	void SetRight(BinTreeNode R) {
		rightChild = R;
	}
}
