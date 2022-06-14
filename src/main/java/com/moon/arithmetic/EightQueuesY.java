package com.moon.arithmetic;

/**
 * 八皇后问题 -- 回溯法
 *
 * @author yujiangtao
 * @date 2020/12/28 下午5:34
 */
public class EightQueuesY {

    private int[] rowColumn = new int[8];

    public static void main(String[] args) {
        EightQueuesY eightQueuesY = new EightQueuesY();
        eightQueuesY.matchRight(0);
    }

    public void matchRight(int row) {
        if(row == 8) {
            //System.out.println(Arrays.toString(rowColumn));
            printQueens(rowColumn);
            return;
        }
        for (int column = 0; column < 8; column++) {
            if(noneConflict(row, column)) {
                rowColumn[row] = column;
                matchRight(row + 1);
            }
        }
    }

    /**
     * 与其他的行列，对角线没有冲突
     * @param row
     * @param column
     * @return
     */
    private boolean noneConflict(int row, int column) {
        // 判断列是否有冲突
        for (int i = 0; i < row; i++) {
            if(rowColumn[i] == column) {
                // 有重复的
                return false;
            }
        }

        // 判断左上是否有冲突
        int leftUpRow = row - 1;
        int leftUpColumn = column - 1;
        while(leftUpRow >= 0 && leftUpColumn >= 0) {
            if(rowColumn[leftUpRow] == leftUpColumn) {
                return false;
            }
            leftUpRow--;
            leftUpColumn--;
        }

        // 判断右上是否有冲突
        int rightUpRow = row - 1;
        int rightUpColumn = column + 1;
        while(rightUpRow >= 0 && rightUpColumn <= 7) {
            if(rowColumn[rightUpRow] == rightUpColumn) {
                return false;
            }
            rightUpRow--;
            rightUpColumn++;
        }
        return true;
    }

    private void printQueens(int[] result) { // 打印出一个二维矩阵
        for (int row = 0; row < 8; ++row) {
            for (int column = 0; column < 8; ++column) {
                if (result[row] == column) {
                    System.out.print("Q ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
