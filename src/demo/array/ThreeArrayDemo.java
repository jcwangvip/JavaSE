package demo.array;

import java.util.Arrays;

/**
 * 三维数组
 *
 * @author jcwang
 */
public class ThreeArrayDemo {

    public static void main(String[] args) {

        int[][][] array = new int[][][]{                    // 创建并初始化数组
                {{1, 2, 3}, {4, 5, 6}},
                {{7, 8, 9}, {10, 11, 12}},
                {{13, 14, 15}, {16, 17, 18}}
        };
        print(array);

    }

    private static void print(int[][][] array) {
        int length = array.length;
        System.out.println("数组长度 = " + length);
        for (int i = 0; i < length; i++) {
            int[][] ints = array[i];
            int length1 = ints.length;
            System.out.println("第" + " i = " + i + "层的数组长度 = " + length1);
            for (int j = 0; j < length1; j++) {
                int[] anInt = ints[j];
                int length2 = anInt.length;
                System.out.println("第 j = " + j + "层的数组长度 = " + length2);
                System.out.println();
                for (int k = 0; k < length2; k++) {
                    int i1 = array[i][j][k];
                    System.out.println("每个数组" + i + j + k + "的值 = " + i1);
                }
            }
        }
    }
}
