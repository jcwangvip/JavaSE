package demo.array;

/**
 * 三维数组
 *
 * @author jcwang
 */
public class TwoArrayDemo {

    public static void main(String[] args) {

        int[][] arr = {{22, 0, 15, 1, 32, 2, 20, 3, 18, 4}
                , {12, 21, 25, 19, 33}
                , {14, 58, 34, 24, 66}
        };

        int length = arr.length;
        System.out.println("数组长度 = " + length);
        for (int i = 0; i < length; i++) {
            int[] ints = arr[i];
            int length1 = ints.length;
            System.out.println("第" + i + "层的数组长度 = " + length1);
            for (int j = 0; j < length1; j++) {
                int anInt1 = ints[j];
                System.out.println("每个数组" + i + j + "的值 = " + anInt1);
            }
        }

    }
}
