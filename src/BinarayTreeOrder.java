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
			int preleft, int preright, int inleft, int inright) {
		int parent, leftsize, rightsize;
		if (preleft <= preright && inleft <= inright) {
			for (parent = inleft; parent <= inright; parent++) {
				if (inorder[parent] == preorder[preleft])
					break;
			}
			leftsize = parent - inleft;
			rightsize = inright - parent;
			if (leftsize > 0) {
				printPosOrder(preorder, inorder, preleft + 1, preleft
						+ leftsize, inleft, parent - 1);
			}
			if (rightsize > 0) {
				printPosOrder(preorder, inorder, preleft + leftsize + 1,
						preright, parent + 1, inright);
			}
			sb.append(inorder[parent]).append(",");
		}
	}
}