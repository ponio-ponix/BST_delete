public class Main {
    public static void main(String[] args) {


        // --- test1: rootが1個だけ、かつ削除 ---
        BinaryTree<Integer> root = new BinaryTree<>(10);

        BinaryTree<Integer> result = Solution.bstDelete(root, 10);

        if (result == null) {
            System.out.println("OK");
        } else {
            System.out.println("NG");
        }

         // --- test2: root=10, left=5 を削除 ---
        BinaryTree<Integer> root3 = new BinaryTree<>(10);
        root3.left = new BinaryTree<>(5);

        BinaryTree<Integer> result3 = Solution.bstDelete(root3, 5);

        if (result3.left == null && result3.data == 10) {
            System.out.println("OK: rootの右葉の削除 成功");
        } else {
            System.out.println("NG: rootの右葉の削除 失敗");
        }

        // --- test3: root=10, right=15 を削除 ---
        BinaryTree<Integer> root4 = new BinaryTree<>(10);
        root4.right = new BinaryTree<>(15);

        BinaryTree<Integer> result4 = Solution.bstDelete(root4, 15);

        if (result4.right == null && result4.data == 10) {
            System.out.println("OK: rootの右葉の削除 成功");
        } else {
            System.out.println("NG: rootの右葉の削除 失敗");
        }


        // --- test4: 空の木から削除 ---
        BinaryTree<Integer> emptyRoot = null;
        BinaryTree<Integer> resultEmpty = Solution.bstDelete(emptyRoot, 10);

        if (resultEmpty == null) {
            System.out.println("OK: 空の木の削除 成功");
        } else {
            System.out.println("NG: 空の木の削除 失敗");
        }

        // --- test5: keyが存在しない場合（10, 5, 15から 999 を削除） ---
        BinaryTree<Integer> root5 = new BinaryTree<>(10);
        root5.left = new BinaryTree<>(5);
        root5.right = new BinaryTree<>(15);

        BinaryTree<Integer> result5 = Solution.bstDelete(root5, 999);

        boolean sameRef   = (result5 == root5);
        boolean sameLeft  = (result5.left != null && result5.left.data == 5);
        boolean sameRight = (result5.right != null && result5.right.data == 15);

        if (sameRef && sameLeft && sameRight) {
            System.out.println("OK: 存在しないkeyの削除 成功");
        } else {
            System.out.println("NG: 存在しないkeyの削除 失敗");
        }
    }
}