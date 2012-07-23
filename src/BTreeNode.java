public class BTreeNode {
	int data;
	int deep;
	BTreeNode leftChild;
	BTreeNode rightChild;

	BTreeNode(int item, BTreeNode left, BTreeNode right) {
		data = item;
		leftChild = left;
		rightChild = right;
	}

	int GetData() {
		return data;
	}

	BTreeNode GetLeft() {
		return leftChild;
	}

	BTreeNode GetRight() {
		return rightChild;
	}

	void SetData(int item) {
		data = item;
	}

	void SetLeft(BTreeNode L) {
		leftChild = L;
	}

	void SetRight(BTreeNode R) {
		rightChild = R;
	}
}
