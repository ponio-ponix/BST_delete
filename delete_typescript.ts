class BinaryTree<E>{
    data: E;
    left: BinaryTree<E>;
    right: BinaryTree<E>;

    constructor(data: E, left: BinaryTree<E> = null, right: BinaryTree<E> = null) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

}

type BT = BinaryTree<number>

function bstDelete(root: BT, key: number): BT {
    // 関数を完成させてください
    if (root == null) return root;
    let node: BT = searchKey(root, key);
    if (!exist(root, key)) return root;
    let parent: BT = findParent(root, node);

    // case1 keyNodeが葉ノード
    if (node.left == null && node.right == null) {
        if (parent.left == node) parent.left == null
        else if (parent.right == node) parent.right == null
    }
    // case 2: ノードNの左が空
    if (node.left == null) return transplant(root, parent, node, node.right)
    // case 3: ノードNの右が空
    else if (node.right == null) return transplant(root, parent, node, node.left)
    // case 4: 2つの子を持っている場合
    else {
        let successor: BT = findSuccessor(root, node);
        let successorP: BT = findParent(root, successor);

        if (successor != node.right) {
            root = transplant(root, successorP, successor, successor.right);
            successor.right = node.right;

        }
        root = transplant(root, parent, node, successor);
        successor.left = node.left;
    }
    return root;
}

function exist(root: BT, key: number): boolean {
    while (root != null) {
        if (root.data == key) return true;
        if (root.data > key) root = root.left;
        else root = root.right;
    }
    return false;
}

function searchKey(root: BT, key: number): BT {
    while (root != null) {
        if (root.data == key) return root;
        if (root.data > key) root = root.left;
        else root = root.right;
    }
    return root;
}

function findParent(root: BT, node: BT) {
    let iterator: BT = root;
    let parent: BT = null;
    while (iterator != node) {
        parent = iterator;
        iterator = iterator.data > node.data ? iterator.left : iterator.right;
    }
    return parent;
}

function findSuccessor(root: BT, node: BT) {
    let targetNode = node;
    if (targetNode == null) return null;
    if (targetNode.right != null) return minimumNode(targetNode.right);

    let successor: BT = null;
    let iterator: BT = root;

    while (iterator != null) {
        if (targetNode.data == iterator.data) return successor;
        if (targetNode.data < iterator.data && (successor == null || iterator.data < successor.data)) successor = iterator;
        if (targetNode.data < iterator.data) iterator = iterator.left;
        else iterator = iterator.right;
    }
    return successor;
}

function minimumNode(node: BT) {
    let iterator = node;
    while (iterator != null && iterator.left != null) iterator = iterator.left;
    return iterator;
}

function transplant(root: BT, parent: BT, node: BT, target: BT): BT{
    if (parent == null) return target;
    else if (parent.left == node) parent.left = target;
    else parent.right = target;
    return root;
}