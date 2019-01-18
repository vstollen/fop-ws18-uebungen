package H2;

public class SelfOrganizedTree<T extends Comparable<T>> extends BinaryTree<T> {

	@Override
	public boolean search(T value) {

		BinaryTreeNode<T> pointer = root;

		if (value.compareTo(root.value) == 0) {
			return true;
		}

		if (value.compareTo(root.value) < 0) {
			pointer = root.left;
		}

		if (value.compareTo(root.value) > 0) {
			pointer = root.right;
		}

		BinaryTreeNode<T> grandGrandParent = null;
		BinaryTreeNode<T> grandparent = null;
		BinaryTreeNode<T> parent = root;

		while (pointer != null) {
			if (value.compareTo(pointer.value) == 0) {

				if (grandparent == null) {
					if (value.compareTo(root.value) < 0) {
						root = zickRotation(root);
					} else if (value.compareTo(root.value) > 0) {
						root = zackRotation(root);
					}
				} else {
					if (value.compareTo(grandparent.left.value) < 0) {

						grandparent = zickRotation(grandparent);
						grandparent = zickRotation(grandparent);

						insertNode(value, grandGrandParent, grandparent);
					} else if (value.compareTo(grandparent.right.value) > 0) {

						grandparent = zackRotation(grandparent);
						grandparent = zackRotation(grandparent);

						insertNode(value, grandGrandParent, grandparent);
					} else if (value.compareTo(parent.value) > 0) {

						parent = zackRotation(parent);
						grandparent.left = parent;

						grandparent = zickRotation(grandparent);

						insertNode(value, grandGrandParent, grandparent);
					} else if (value.compareTo(parent.value) < 0) {

						parent = zickRotation(parent);
						grandparent.right = parent;

						grandparent = zackRotation(grandparent);
						insertNode(value, grandGrandParent, grandparent);
					}
				}
				return true;
			}

			grandGrandParent = grandparent;
			grandparent = parent;
			parent = pointer;

			if (value.compareTo(pointer.value) < 0) {
				pointer = pointer.left;
			} else {

				if (value.compareTo(pointer.value) > 0) {
					pointer = pointer.right;
				}
			}
		}
		return false;
	}

	/**
	 * Inserts node based of it's value
	 * @param value
	 * @param parent
	 * @param insertNode
	 */
	private void insertNode(T value, BinaryTreeNode<T> parent, BinaryTreeNode<T> insertNode) {
		if (parent == null) {
			root = insertNode;
		} else if (value.compareTo(parent.value) < 0) {
			parent.left = insertNode;
		} else {
			parent.right = insertNode;
		}
	}

	/**
	 * Performs a zick rotation (right rotation)
	 * @param root root node
	 * @return root node after rotation
	 */
	private BinaryTreeNode<T> zickRotation(BinaryTreeNode<T> root) {

		BinaryTreeNode<T> oldRoot = root;
		BinaryTreeNode<T> leftRootChild = root.left;

		oldRoot.left = leftRootChild.right;
		leftRootChild.right = oldRoot;

		root = leftRootChild;

		return root;
	}

	/**
	 * Performs a zack rotation (left rotation)
	 * @param root root node
	 * @return root node after rotation
	 */
	private BinaryTreeNode<T> zackRotation(BinaryTreeNode<T> root) {

		BinaryTreeNode<T> oldRoot = root;
		BinaryTreeNode<T> rightRootChild = root.right;

		oldRoot.right = rightRootChild.left;
		rightRootChild.left = oldRoot;

		root = rightRootChild;

		return root;
	}

}
