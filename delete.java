import java.util.Arrays;

class BinaryTree{
    public int data;
    public BinaryTree left;
    public BinaryTree right;

    public BinaryTree(int data){
        this.data = data;
    }

    public BinaryTree(int data, BinaryTree left, BinaryTree right){
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public void printInOrder(){
        this.inOrderwalk(this);
        System.out.println("");
    }

    public void inOrderwalk(BinaryTree tRoot){
        if(tRoot != null){
            inOrderwalk(tRoot.left);
            System.out.print(tRoot.data+ " ");
            inOrderwalk(tRoot.right);
        }
    }
}

class BinarySearchTree{
    public BinaryTree root;

    public BinarySearchTree(int[] arr){
        Arrays.sort(arr);
        this.root = sortedBSTHelper(arr, 0, arr.length-1);
    }

    public static BinaryTree sortedBSTHelper(int[] arr, int start, int end){
        if(start == end) return new BinaryTree(arr[start], null, null);

        int mid = (int)Math.floor((start+end)/2);
        BinaryTree left=null;
        if(start < mid) left = sortedBSTHelper(arr, start, mid-1);
        BinaryTree right = null;
        if(end > mid) right = sortedBSTHelper(arr, mid+1, end);
        BinaryTree root = new BinaryTree(arr[mid], left, right);
        return root;
    }

    public boolean keyExist(int key){
        BinaryTree iterator = this.root;
        while(iterator != null){
            if(iterator.data == key) return true;
            else if(iterator.data > key) iterator = iterator.left;
            else iterator = iterator.right;
        }
        return false;
    }

    public BinaryTree search(int key){
        BinaryTree iterator = this.root;
        while(iterator != null){
            if(iterator.data == key) return iterator;
            if(iterator.data > key) iterator = iterator.left;
            else iterator = iterator.right;
        }
        return null;
    }

    public BinaryTree insert(int data){
        BinaryTree iterator = this.root;
        while(iterator != null){
            if(iterator.data > data && iterator.left == null) iterator.left = new BinaryTree(data);
            else if(iterator.data < data && iterator.right == null) iterator.right = new BinaryTree(data);
            iterator = (iterator.data > data)? iterator.left: iterator.right;
        }
        return this.root;
    }

    public void transplant(BinaryTree nodeParent, BinaryTree node, BinaryTree target){
        if (nodeParent == null) this.root = target;
        else if (nodeParent.left == node) nodeParent.left = target;
        else nodeParent.right = target;
    }    
    public void deleteNode(int key){
        if (this.root == null) return;
        BinaryTree node = this.search(key);
        if (!this.keyExist(key)) return;

        BinaryTree parent = this.findParent(node);
        // case 1: ノードNが葉ノード
        // 親ノードからnodeへの参照をnullに変更してnodeを削除します。
        if (node.left == null && node.right == null) {
            if(parent.left.data == key) parent.left = null;
            else if(parent.right.data == key) parent.right = null;
        }
        // case 2: ノードNの左が空
        if (node.left == null) this.transplant(parent, node, node.right);
        // case 3: ノードNの右が空
        else if (node.right == null) this.transplant(parent, node, node.left);
        // case 4: 2つの子を持っている場合
        else{
            BinaryTree successor = this.findSuccessor(node);
            BinaryTree successorP = this.findParent(successor);

            // case 4 後続ノードSがすぐ右側にいる場合 : この場合、ノードNが後続ノードSの親になっているため、case4は必要ありません。単純にNの親であるPの部分木とSを移植すればokです。
            // 特別なケース (case 4) 後続ノードSがすぐ右側にいない場合 : この場合、後続Sの親も変更しなければいけません。
            if (successor != node.right){
                // 後続ノードSをSの右部分木で移植します。Sをアップデートします。
                this.transplant(successorP, successor, successor.right);
                // Sの右側はノードNの右側になっている必要があります。
                successor.right = node.right;

            }
            // ノードNを後続Sで移植します。Sの左部分木をノードNの左部分木にします。
            this.transplant(parent, node, successor);
            successor.left = node.left;
        }
    }      
    

    public BinaryTree findParent(BinaryTree node){
        BinaryTree iterator = this.root;
        BinaryTree parent = null;
        while (iterator != node){
            parent = iterator;
            iterator = iterator.data > node.data ? iterator.left: iterator.right;
        }    
        return parent;
    }
    
    public BinaryTree findSuccessor(BinaryTree node){
        // 部分木
        BinaryTree targetNode = node;
        // keyがBST内に存在しない場合、nullを返します。
        if (targetNode == null) return null;
        // keyのノードの右にある最小値を探します。
        if (targetNode.right != null) return this.minimumNode(targetNode.right);

        BinaryTree successor = null;
        BinaryTree iterator = this.root;

        while (iterator != null) {
            if (targetNode.data == iterator.data) return successor;
            // successorを左方向へずらしていきます。
            if (targetNode.data < iterator.data && (successor == null || iterator.data < successor.data)) successor = iterator;
            if (targetNode.data < iterator.data) iterator = iterator.left;
            else iterator = iterator.right;
        }    
        return successor;
    }

    public BinaryTree minimumNode(BinaryTree node){
        BinaryTree iterator = node;
        while (iterator != null && iterator.left != null) iterator = iterator.left;
        return iterator;
    }
    

    public void printSorted(){
        this.root.printInOrder();
    }
}

class Main{
    public static void main(String[] args){
        
        BinarySearchTree balancedBST = new BinarySearchTree(new int[]{4,43,36,46,32,7,97,95,34,8,96,35,85,1010,232});
        balancedBST.printSorted();
        balancedBST.deleteNode(43);
        balancedBST.printSorted();
        balancedBST.deleteNode(7);
        balancedBST.printSorted();
        // balancedBST.deleteNode(4);
        // balancedBST.printSorted();
        // balancedBST.deleteNode(1010);
        // balancedBST.printSorted();
        // // 存在しない0をdeleteNodeします。
        // balancedBST.deleteNode(0);
        // balancedBST.printSorted();      
    }
}