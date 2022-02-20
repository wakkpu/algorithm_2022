

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node {
	String value;
	Node left;
	Node right;
	
	public Node(String value) {
		this.value = value;
	}
}

class BinaryTree {
	public Node root;
	
	public void	createNode(String value, String left, String right) {
		// root 생성
		if(root == null) {
			root = new Node(value);
			if(left.equals("."))
				root.left = null;
			else
				root.left = new Node(left);
			
			if(right.equals("."))
				root.right = null;
			else
				root.right = new Node(right);
			
		// root가 아닌 경우 node 놓을 자리 찾아야 함
		} else {
			searchNode(root, value, left, right);
		}
	}
	
	public void searchNode(Node node, String value, String left, String right) {
		// 도착한 node가 null이라면(놓을 수 없는 자리라면) 이 자리에 node를 놓을 수 없다
		if(node == null) {
			return;
			
		// 넣을 자리를 찾았다면
		} else if(node.value.equals(value)){
			// 이 node의 left가 있다면
			if(!(left.equals(".")))
				node.left = new Node(left);
			// 이 node의 right가 있다면
			if(!(right.equals(".")))
				node.right = new Node(right);
			
		// 못 찾았으면 계속 재귀 탐색
		} else {
			searchNode(node.left, value, left, right);
			searchNode(node.right, value, left, right);
		}
	}
	
	// 전위: root -> left -> right
	public void preOrder(Node node) {
		if(node != null) {
			System.out.print(node.value);
			if(node.left != null)
				preOrder(node.left);
			if(node.right != null)
				preOrder(node.right);
		}
	}
	
	// 중위: left -> root -> right
	public void inOrder(Node node) {
		if(node != null) {
			if(node.left != null)
				inOrder(node.left);
			System.out.print(node.value);
			if(node.right != null)
				inOrder(node.right);
		}
	}
	
	// 후위: left -> right -> root
	public void postOrder(Node node) {
		if(node != null) {
			if(node.left != null)
				postOrder(node.left);
			if(node.right != null)
				postOrder(node.right);
			System.out.print(node.value);
		}
	}
}

public class BOJ_1991 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(input.readLine());
		
		int t = Integer.parseInt(tokens.nextToken());
		
		BinaryTree bt = new BinaryTree();
		
		for(int i=0; i<t; i++) {
			tokens = new StringTokenizer(input.readLine(), " ");
			String root = tokens.nextToken();
			String left = tokens.nextToken();
			String right = tokens.nextToken();
			bt.createNode(root, left, right);
		}
		
		bt.preOrder(bt.root);
		System.out.println();
		bt.inOrder(bt.root);
		System.out.println();
		bt.postOrder(bt.root);
	}

}
