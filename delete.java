import java.util.ArrayList;

class BinaryTree<E>{
    public E data;
    public BinaryTree<E> left;
    public BinaryTree<E> right;

    public BinaryTree() {}
    public BinaryTree(E data) { this.data = data; }
    public BinaryTree(E data, BinaryTree<E> left, BinaryTree<E> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}

class Solution{
    public static BinaryTree<Integer> bstDelete(BinaryTree<Integer> root, int key){
        // 関数を完成させてください
        BinaryTree<Integer> target = searchNode(root, key);
        if (target == null) return root;

        BinaryTree<Integer> parent = searchparent(root, target);

        boolean hasLeft  = (target.left  != null);
        boolean hasRight = (target.right != null);

        // --- 子0だけ実装する（今日の範囲はここだけ） ---
        if (!hasLeft && !hasRight) {

            // case 2: root が target で子0
            if (parent == null) {
                return null;
            }

            // case 5: target が root以外 + 子0
            if (parent.left == target) parent.left = null;
            else if (parent.right == target) parent.right = null;

            return root;
        }

        BinaryTree<Integer> child = (target.left != null) ? target.left : target.right;

    
        if (!hasLeft ^ !hasRight) {
            // 子1：
            if(parent == null){
                //子ノードがそのままrootになる
                root = child;
                return root;
            }else{
                if(parent.left == target){
                    parent.left = child;
                    return root;
                }else{
                    parent.right = child;
                    return root;
                }
            }            

        }

        if (hasLeft && hasRight) {
            // 子2：
            BinaryTree<Integer> successor = target;
            BinaryTree<Integer> successorParent = target;

            
            
            while(successor.left != null){
                BinaryTree<Integer> temp = successor;

                successor = successor.left;
                successorParent = temp;
            }

            if(successor == successorParent){
                // root = child;
            }






            int succValue = successor.data;


            // System.out.println(successor.data);
            // if(successor.rigth != null){
            //     BinaryTree<Integer> successorChild = ;
            // }

            // BinaryTree<Integer> successorParent = findParent(successor, target.right);
            // System.out.println(successorParent.data);
            target.data = succValue;

            deleteSuccessorEdge(successorParent, successor);



            return root;
        }

        return root;
    }

    // public static BinaryTree<Integer> findParent(BinaryTree<Integer> successor, BinaryTree<Integer> target){
    //     BinaryTree<Integer> iterator = target;
    //     while(iterator.left != successor){
    //         iterator = iterator.left;
    //     }
    //     return iterator;

    // }


    public static void deleteSuccessorEdge(BinaryTree<Integer> successorParent, BinaryTree<Integer> successor){

        BinaryTree<Integer> child = successor.right;

        if (successorParent.left == successor){
            successorParent.left = child;
        }
        else if (successorParent.right == successor){
            successorParent.right = child;
        }else {
            throw new IllegalStateException("parent の子に successor がいない。ロジックがおかしい");
        }

    }



    // public static BinaryTree<Integer> findMin(BinaryTree<Integer> target){
    //     while(target.data != null){
    //         if(target.left == null) return target;
    //         else target = target.left;
    //     } 
    //     return null;

    // }




    //searchparentは残して良い
    public static BinaryTree<Integer> searchparent(BinaryTree<Integer> it, BinaryTree<Integer> target){
        //もしtatgetが根nodeだったら
        // if()

        BinaryTree<Integer> parent = null;
        while(it != null){
            if(it.data == target.data) return parent;
            if(it.data > target.data){
                parent = it;
                it = it.left;
            }
            else if(it.data < target.data){
                parent = it;
                it = it.right;
            }
        }
        return null;
    }




    //searchNodeは残して良い
    public static BinaryTree<Integer> searchNode(BinaryTree<Integer> it, int key){
        while(it != null){
            if(it.data == key) return it;
            else if(it.data < key) it = it.right;
            else it = it.left;
            // System.out.println("setchの中のit" + it.data);
        }
        return null;
    }


}