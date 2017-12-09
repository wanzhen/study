package com.glz.study.structures.binarytree;

public class BiTree<T> {
   private Node root;
	public void createTree() {
             
	}
	
	
	public void addNode(Node node,T t) {
		if(root == null){
			root = new Node(t);
			return ;
		}
	}

	private static class Node<T> {
		T element;
		Node left;
		Node right;
		Node parent;
         
		public Node(T element) {
			this.element = element;
		}

		public T getElement() {
			return element;
		}

		public void setElement(T element) {
			this.element = element;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
		}

		public Node getParent() {
			return parent;
		}

		public void setParent(Node parent) {
			this.parent = parent;
		}
	}
}
