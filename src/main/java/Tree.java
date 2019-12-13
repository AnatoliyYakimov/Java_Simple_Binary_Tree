import java.util.function.Consumer;

enum TraverseOrder {
	INFIX,
	POSTFIX,
	PREFIX
}


public class Tree<T extends Comparable<T>> {
	class Node<T extends Comparable<T>> {
		private T data;
		private Node<T> left = null;
		private Node<T> right = null;

		public Node(T data) {
			this.data = data;
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public Node<T> getLeft() {
			return left;
		}

		public void setLeft(Node<T> left) {
			this.left = left;
		}

		public Node<T> getRight() {
			return right;
		}


		public void setRight(Node<T> right) {
			this.right = right;
		}

	}

	private Node<T> root = null;

	public Tree() {
	}
	
	public Tree(T data) {
		root = new Node<>(data);
	}
	
	public void add(T data) {
		if (root == null) {
			root = new Node<>(data);
		}
		else {
			add(root, data);
		}
	}

	public void remove(T key) {
		if (root.data == key) {
			root = append(root.left, root.right);
		} else {
			int compare = root.data.compareTo(key);
			if (compare > 0) {
				root.left = remove(root.left, key);
			} else{
				root.right = remove(root.right, key);
			}
		}
	}

	public Tree<T> append(Tree<T> other) {
		root = append(this.root, other.root);
		return this;
	}

	public void traverse(Consumer<T> consumer) {
		traverse(TraverseOrder.INFIX, consumer);
	}

	public void traverse(TraverseOrder order, Consumer<T> consumer) {
		switch (order) {
			case INFIX: traverseInfix(root, consumer); break;
			case PREFIX: traversePrefix(root, consumer); break;
			case POSTFIX: traversePostfix(root, consumer); break;
		}
	}

	private void traversePostfix(Node<T> node, Consumer<T> consumer) {
		if (node == null) {
			return;
		}
		traversePostfix(node.left, consumer);
		traversePostfix(node.right, consumer);
		consumer.accept(node.data);
	}

	private void traversePrefix(Node<T> node, Consumer<T> consumer) {
		if (node == null) {
			return;
		}
		consumer.accept(node.data);
		traversePrefix(node.left, consumer);
		traversePrefix(node.right, consumer);
	}

	private void traverseInfix(Node<T> node, Consumer<T> consumer) {
		if (node == null) {
			return;
		}
		traverseInfix(node.left, consumer);
		consumer.accept(node.data);
		traverseInfix(node.right, consumer);
	}


	private Node<T> append(Node<T> first, Node<T> second) {
		if (first == null) {
			return second;
		}
		if (second == null) {
			return first;
		}

		int compare = first.data.compareTo(second.data);

		if (compare > 0) {
			first.left = append(first.left, second);
		}
		else {
			first.right = append(first.right, second);
		}

		return first;
	}

	private Node<T> remove(Node<T> node, T key) {
		if (node == null) {
			return null;
		}
		int compare = node.data.compareTo(key);
		if (compare == 0) {
			return append(node.left, node.right);
		}
		if (compare > 0) {
			node.left = remove(node.left, key);
		} else {
			node.right = remove(node.right, key);
		}
		return node;
	}

	private void add(Node<T> node, T data) {
		int compare = node.data.compareTo(data);
		if (compare == 0) {
			node.data = data;
			return;
		}
		if (compare < 0) {
			if (node.right == null) {
				node.right = new Node<>(data);
				return;
			}
			add(node.right, data);
		}
		if (compare > 0) {
			if (node.left == null) {
				node.left = new Node<>(data);
				return;
			}
			add(node.left, data);
		}
	}
}
