/**
 * 根据前序和中序打印后序
 * 
 * @author Administrator
 * 
 */
public class BinarayTreeOrder {

	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		int[] inorder = { 1, 5, 4, 2, 3, 8, 7 };
		int[] preorder = { 2, 5, 1, 4, 8, 3, 7 };
		printPosOrder(preorder, inorder, 0, 6, 0, 6);
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb.toString());
	}

	public static void printPosOrder(int[] preorder, int[] inorder,
			int preLeft, int preRight, int inLeft, int inRight) {
		int parent, leftsize, rightsize;
		if (preLeft <= preRight && inLeft <= inRight) {
			for (parent = inLeft; parent <= inRight; parent++) {
				if (inorder[parent] == preorder[preLeft])
					break;
			}
			leftsize = parent - inLeft;
			rightsize = inRight - parent;
			if (leftsize > 0) {
				printPosOrder(preorder, inorder, preLeft + 1, preLeft
						+ leftsize, inLeft, parent - 1);
			}
			if (rightsize > 0) {
				printPosOrder(preorder, inorder, preLeft + leftsize + 1,
						preRight, parent + 1, inRight);
			}
			sb.append(inorder[parent]).append(",");
		}
	}
}