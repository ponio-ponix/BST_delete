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

        BinaryTree<Integer> root6 =
        new BinaryTree<>(
            10,
            null,
            new BinaryTree<>(15)
        );

        root6 = Solution.bstDelete(root6, 10);  // ★ ここで root6 を更新しろ


        if (root6 == null) {
            throw new RuntimeException("root が null になっている → 子1 × root の処理が壊れている");
        }
        if (!root6.data.equals(15)) {
            throw new RuntimeException("root.data が 15 でない → 正しく置き換えられていない");
        }
        if (root6.left != null) {
            throw new RuntimeException("root.left が null でない → 本来 null のはず");
        }
        if (root6.right != null) {
            throw new RuntimeException("root.right が null でない → 本来 null のはず");
        }

        System.out.println("OK: 子1 × root の delete は正しく動作している");





        // --- test6: child2（右部分木の最小 = 15）delete(10) ---
        BinaryTree<Integer> t6 =
            new BinaryTree<>(
                10,
                new BinaryTree<>(5),
                new BinaryTree<>(15)
            );

        t6 = Solution.bstDelete(t6, 10);

        if (t6.data == 15 &&
            t6.left.data == 5 &&
            t6.right == null) {
            System.out.println("OK: 子2 delete(10) case1");
        } else {
            System.out.println("NG: 子2 delete(10) case1");
        }


        // --- test7: 右部分木が {15 → 12}, successor=12 ---
        BinaryTree<Integer> t7 =
            new BinaryTree<>(
                10,
                new BinaryTree<>(5),
                new BinaryTree<>(
                    15,
                    new BinaryTree<>(12),
                    null
                )
            );

        t7 = Solution.bstDelete(t7, 10);

        // 期待：root=12, left=5, right=15（15のleftはnullにされる）
        if (t7.data == 12 &&
            t7.left.data == 5 &&
            t7.right.data == 15 &&
            t7.right.left == null) {

            System.out.println("OK: 子2 delete(10) case2");
        } else {
            System.out.println("NG: 子2 delete(10) case2");
        }


        // --- test8: 右部分木が {15 → 12 → 11}, successor=11 ---
        BinaryTree<Integer> t8 =
            new BinaryTree<>(
                10,
                new BinaryTree<>(5),
                new BinaryTree<>(
                    15,
                    new BinaryTree<>(
                        12,
                        new BinaryTree<>(11),
                        null
                    ),
                    null
                )
            );

        t8 = Solution.bstDelete(t8, 10);

        // 期待：root=11, left=5, right=15（12は右に残る構造）
        boolean ok8 =
            t8.data == 11 &&
            t8.left.data == 5 &&
            t8.right.data == 15 &&
            t8.right.left.data == 12 &&
            t8.right.left.right == null &&
            t8.right.left.left == null;

        if (ok8) {
            System.out.println("OK: 子2 delete(10) case3");
        } else {
            System.out.println("NG: 子2 delete(10) case3");
        }       

    }
}