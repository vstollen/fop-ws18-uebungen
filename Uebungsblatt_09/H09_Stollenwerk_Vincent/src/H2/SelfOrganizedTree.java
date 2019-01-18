package H2;

public class SelfOrganizedTree<T extends Comparable<T>> extends BinaryTree<T> {

	@Override
	public boolean search(T value) {
		/*
		 * TODO H2.1, H2.2, H2.3
		 */

		if (root.value.equals(value)) {
			return true;
		}

		if (root.left.value.equals(value)) {
			zickRotation();
			return true;
		}

		if (root.right.value.equals(value)) {
			zackRotation();
			return true;
		}

		return false;
	}

	/**
	 *  Performs a zick rotation (right rotation)
	 */
	private void zickRotation() {

		BinaryTreeNode<T> oldRoot = root;
		BinaryTreeNode<T> leftRootChild = root.left;

		oldRoot.left = leftRootChild.right;
		leftRootChild.right = oldRoot;

		root = leftRootChild;
	}

	/**
	 * Performs a zack rotation (left rotation)
	 */
	private void zackRotation() {

		BinaryTreeNode<T> oldRoot = root;
		BinaryTreeNode<T> rightRootChild = root.right;

		oldRoot.right = rightRootChild.left;
		rightRootChild.left = oldRoot;

		root = rightRootChild;
	}

}
