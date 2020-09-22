import java.util.Scanner;
public class fighting3 {
    public static void main(String[] args) {
        System.out.println("欢迎来到五子棋对战！");
        Scanner in = new Scanner(System.in);
        //3个索引，分别代表黑白空
        int black = 0;                                       //1
        int white = 1;                                      //2
        int empty = 2;                                      //3
        //创建的两种结果
        int[] result = new int[2];
        //创建的3种棋子字符
        char[] qiziJustMove = new char[]{'■', '□', ' '};
        //创建的检查索引
        int[][] checkConnectedDirection = new int[][]{{1, 0}, {0, 1}, {1, 1}, {1, -1}};
        //创建的方向检查索引的名字
        String[] direction = new String[]{"垂直", "水平", "斜向下", "斜向上"};
        //创建循环开关
        boolean play = true;                                //4
        //代码7-19为游戏开局前的准备
        //第一层循环开始
        while (play) {
            //创建了一个棋盘的大小
            System.out.println("请输入棋盘的大小，不得小于5，大于等于100");
            int sizi = in.nextInt();                         //5
            if (sizi < 5 || sizi >= 100) {
                System.out.println("非法的游戏范围，请重新输入");
                continue;
            }
            //创建一个二维数组，来代表棋盘中的每一个棋子，用一个for循环，让这个数组里的每一个棋子的值都为空
            int[][] qipan = new int[sizi][sizi];
            for (int i = 0; i < sizi; i++) {
                for (int j = 0; j < sizi; j++) {
                    qipan[i][j] = empty;
                }
            }
            //创建一个变量来制作棋盘的横向标示
            String header = "\t";                             //6
            for (int i = 0; i < sizi; i++) {
                header += (i + 1) + "\t";
            }
            //获取用户输入的双方棋手名称，然后创建一个数组来储存双方棋手的名字，再用一个欢迎语句来开始
            System.out.println("请输入黑方棋手名称: ");
            String blackName = "黑方： " + in.next();                        //7
            System.out.println("请输入白方棋手名称: ");
            String whiteName = "白方： " + in.next();                         //8
            String[] ruleName = new String[]{blackName, whiteName};
            System.out.println("欢迎黑方棋手: " + ruleName[black] + " 白方棋手: " + ruleName[white] + " 开始对战五子棋！");

            //创建一个变量角色颜色 先将它赋值为黑色
            int currRule = black;                                 //9

            //第二层循环开始
            while (true) {
                //创建两个变量备用
                int justMoveLine;                                //10
                int justMoveColumn;                               //11

                //第三层循环开始
                while (true) {
                    //创建两个变量来储存获取用户落子的地方并用上两个变量对这两个变量进行-1赋值操作
                    int lineToMove;                             //12
                    int columnToMove;                             //13
                    System.out.println(ruleName[currRule] + "下子");
                    System.out.println("请输入落子的行");
                    lineToMove = in.nextInt();
                    System.out.println("请输入落子的列");
                    columnToMove = in.nextInt();
                    justMoveLine = lineToMove - 1;
                    justMoveColumn = columnToMove - 1;
                    //规定用户落子的范围
                    if (justMoveLine < 0 || justMoveLine >= sizi - 1) {
                        System.out.println("行" + lineToMove + "超出了棋盘范围");
                        continue;
                    }
                    if (justMoveColumn < 0 || justMoveColumn >= sizi - 1) {
                        System.out.println("列" + columnToMove + "超出了棋盘范围");
                        continue;
                    }
                    if (qipan[justMoveLine][justMoveColumn] != empty) {
                        System.out.println("行" + lineToMove + "列" + columnToMove + "有子了");
                        continue;
                    }
                    //输出落子点，结束第三循环
                    System.out.println(ruleName[currRule] + "落子在: 行" + lineToMove + "列" + columnToMove);
                    break;
                }//第三循环结束

                //给刚刚用户下的子在棋盘这个数组中赋值
                qipan[justMoveLine][justMoveColumn] = currRule;
                //打印棋盘，将这个棋盘输出出来
                System.out.println(header);
                for (int i = 0; i < sizi; i++) {
                    String lineToPrint = "" + (i + 1) + "\t";                      //14
                    for (int j = 0; j < sizi; j++) {
                        lineToPrint += qiziJustMove[qipan[i][j]] + "\t";
                    }
                    System.out.println(lineToPrint + (i + 1));
                }
                System.out.println(header);
                //棋盘打印结束，接下来就是要检查是否有五连子了。

                //创建一个布尔变量作为开关，再创建一个int变量地址，用来标示检查的方向 0垂直 1水平 2斜向下 3斜向上
                boolean checkWinner = false;                               //15
                int currCheckCondition;                                   //16
                //我们要检查四个方向，分别是垂直，水平，斜向上，斜向下，这四个方向的索引在之前的checkConnectedDirection数组中有保存
                for (int i = 0; i < checkConnectedDirection.length && (!checkWinner); i++) {
                    //给刚刚创建的空余地址赋值循环变量i
                    currCheckCondition = i;

                    //创建两个新的变量来代表一个方向，共有四个方向，垂直，水平，斜向下，斜向上
                    int deltaLine = checkConnectedDirection[i][0];          //17
                    int deltaColumn = checkConnectedDirection[i][1];        //18
                    //创建新的一个变量，代表落子点，就一个连接，所以为1
                    int totalConnected = 1;                                //19
                    //对于每一个方向，都要以当前的落子点为起点，又有两个方向要检查
                    //以横着的方向为例子，横着要向前检查一次，如果遇到不是自己的子，或者遇到棋盘边界，就要再向后检查一次
                    //向前检查的时候，deltaline为1，deltacolumn为0
                    //向后检查的时候，deltaline为-1，deltacolumn为0
                    for (int j = 0; j < 2 && (!checkWinner); j++) {
                        //所以这里for要循环两次，每次循环时，delta乘以-1，代表来变换检查的方向
                        //比如水平方向，要检查两次，一次向左，一次向右，正好是delta乘以-1的关系
                        deltaLine *= -1;
                        deltaColumn *= -1;

                        //创建两个新的变量，作为检查点，储存用户的这个落子点
                        int lineToCheck = justMoveLine;                      //20
                        int columnToCheck = justMoveColumn;                 //21
                        //创建一个内while循环
                        while (true) {
                            //检查点根据delta进行变换
                            lineToCheck += deltaLine;
                            columnToCheck += deltaColumn;
                            //创建两个布尔值来确定检查点的范围正确
                            boolean lineIndexOK = lineToCheck >= 0 && lineToCheck < sizi;
                            boolean columnIndexOK = columnToCheck >= 0 && columnToCheck < sizi;
                            //如果落子点和向一个方向检查的检查点都是该角色的子的话，连接的子+1
                            if (lineIndexOK && columnIndexOK && qipan[lineToCheck][columnToCheck] == currRule) {
                                totalConnected++;
                                //如果有五个就胜利，前面的胜利结果就+1，然后检查开关变为true，结束这个if语句
                                if (totalConnected >= 5) {
                                    System.out.println(ruleName[currRule] + "胜出！ 五子连接方向为" + direction[currCheckCondition]);
                                    result[currRule]++;
                                    checkWinner = true;
                                    break;
                                }
                            } else {
                                break;
                            }
                        }

                    }
                }
                //如果这个执行，第二while循环结束
                if (checkWinner) {
                    break;
                }
                //黑色下完该白色下，所以
                currRule = (currRule + 1) % 2;
            }
            //输出哪一方赢了几次
            System.out.println(ruleName[0] + "获胜" + result[0] + "次" + ruleName[1] + "获胜" + result[1] + "次");
            System.out.println("是否再来一盘？");
            play = in.nextBoolean();
        }
    }
}
