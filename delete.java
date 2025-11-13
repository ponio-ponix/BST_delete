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

        // --- 子1・子2は今日は空ブロックでOK ---
        if (hasLeft ^ hasRight) {
            // 子1：今日はやらない → 空
            return root;
        }

        if (hasLeft && hasRight) {
            // 子2：今日はやらない → 空
            return root;
        }

        return root;
    }

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


// 	•	今日やるのは次の2点だけ：
// 	1.	bstDelete 内で 子0 / 子1 / 子2 の分岐だけを「コメントと空ブロック」で用意
// 	2.	そのうち 子0（葉）だけの処理を終える（1ケースだけでOK）

// → ここまでできたら終了。勢いが残っていても深追いしない。


