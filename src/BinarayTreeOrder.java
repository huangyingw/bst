/**
 * 根据前序和中序打印后序
 * 
 * @author Administrator
 * 
 */
public class BinarayTreeOrder {

	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		int[] inOrderArr = { 1, 5, 4, 2, 3, 8, 7 };
		int[] preOrderArr = { 2, 5, 1, 4, 8, 3, 7 };
		printPosOrder(preOrderArr, inOrderArr);
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb.toString());
	}

	public static void printPosOrder(int[] preOrder, int[] inOrder) {
		printPosOrder(preOrder, inOrder, 0, preOrder.length - 1, 0,
				inOrder.length - 1);
	}

	public static void printPosOrder(int[] preOrder, int[] inOrder,
			int preLeft, int preRight, int inLeft, int inRight) {
		int parent, leftSize, rightSize;
		if (preLeft <= preRight && inLeft <= inRight) {
			parent = inLeft;
			while (parent <= inRight && inOrder[parent] != preOrder[preLeft])
				parent++;
			leftSize = parent - inLeft;
			rightSize = inRight - parent;
			if (leftSize > 0) {
				printPosOrder(preOrder, inOrder, preLeft + 1, preLeft
						+ leftSize, inLeft, parent - 1);
			}
			if (rightSize > 0) {
				printPosOrder(preOrder, inOrder, preLeft + leftSize + 1,
						preRight, parent + 1, inRight);
			}
			sb.append(inOrder[parent]).append(",");
		}
	}

	public static void printPreOrder(int[] posOrder, int[] inOrder,
			int posLeft, int posRight, int inLeft, int inRight) {
		int parent, leftSize, rightSize;
		if (posLeft <= posRight && inLeft <= inRight) {
			parent = inLeft;
			while (parent <= inRight && inOrder[parent] != posOrder[posRight])
				parent++;
			leftSize = parent - inLeft;
			rightSize = inRight - parent;
			if (leftSize > 0) {
				printPreOrder(posOrder, inOrder, posLeft + 1, posLeft
						+ leftSize, inLeft, parent - 1);
			}
			if (rightSize > 0) {
				printPreOrder(posOrder, inOrder, posLeft + leftSize + 1,
						posRight, parent + 1, inRight);
			}
			sb.append(inOrder[parent]).append(",");
		}
	}
}
