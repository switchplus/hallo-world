import java.util.Scanner;

public class fighting4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("欢迎来到五子棋对战～");
        //创建角色
        int black = 0;
        int white = 1;
        int empty = 2;
        //创建棋子字符
        char[] qizi = new char[]{'X', 'Q', ' '};
        //创建记录赢的次数的数组
        int[] result = new int[2];
        //创建一个记录每个方向的两种变量的一个二维数组
        int[][] checkDirectionVariable = new int[][]{{1, 0}, {0, 1}, {1, 1}, {1, -1}};
        //创建一个数组，来表示方向名称
        String[] directionName = new String[]{"垂直", "水平", "斜向下", "斜向上"};
        //创建一个开关，控制游戏开始或结束
        boolean play = true;
        while (play) {
            System.out.println("请输入棋盘的大小范围，不得小于5，或者大于等于100");
            int size = in.nextInt();
            if (size < 5 || size >= 100) {
                System.out.println("非法的游戏范围，请重新输入");
                continue;
            }
            //创建一个棋盘二维数组，并赋值
            int[][] qipan = new int[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    qipan[i][j] = empty;
                }
            }
            //创建一个横向坐标变量
            String header = "\t";
            for (int i = 0; i < size; i++) {
                header += "" + (i + 1) + "\t";
            }
            //创建允许用户输入的游戏id
            System.out.println("请输入黑方棋手名称: ");
            String blackName = "黑方: " + in.next();
            System.out.println("请输入白方棋手名称");
            String whiteName = " 白方: " + in.next();
            String[] ruleName = new String[]{blackName, whiteName};
            System.out.println("欢迎" + ruleName[black] + ruleName[white] + " 开始五子棋比赛！");
            //创建一个角色颜色变量，先赋值为黑
            int currRule = black;

            //接下来要用到循环语句来实现打印棋盘，输出用户下的每一步棋还有检查胜利条件等问题
            while (true) {
                int realLine;
                int realColumn;
                while (true) {
                    System.out.println("请输入要落子的行");
                    int inputLine = in.nextInt();
                    System.out.println("请输入要落子的列");
                    int inputColumn = in.nextInt();
                    realLine = inputLine - 1;
                    realColumn = inputColumn - 1;
                    if (realLine < 0 || realLine > size - 1) {
                        System.out.println("输入的行: " + inputLine + " 超出了棋盘范围，请重新输入");
                        continue;
                    }
                    if (realColumn < 0 || realColumn > size - 1) {
                        System.out.println("输入的列: " + inputColumn + " 超出了棋盘范围，请重新输入");
                        continue;
                    }
                    if (qipan[realLine][realColumn] != empty) {
                        System.out.println("行: " + realLine + " 列: " + realColumn + " 已经有子了，请重新输入");
                        continue;
                    }
                    System.out.println(ruleName[currRule] + " 落子在：行" + inputLine + "列" + inputColumn);
                    break;

                }
                qipan[realLine][realColumn] = currRule;
                //打印棋盘
                System.out.println(header);
                for (int i = 0; i < size; i++) {
                    String lineToPrint = "" + (i + 1) + "\t";
                    for (int j = 0; j < size; j++) {
                        lineToPrint += qizi[qipan[i][j]] + "\t";
                    }
                    System.out.println(lineToPrint+(i+1));
                }
                System.out.println(header);

                boolean whoIsWinner =false;
                //创建一个变量代表检查方向地址 0垂直 1水平 2斜向下 3斜向上
                int checkDirection;
                for (int i=0;i<checkDirectionVariable.length&&(!whoIsWinner);i++){
                    checkDirection=i;
                    //创建两个变量代表一个方向
                    int detalLine=checkDirectionVariable[i][0];
                    int detalColumn=checkDirectionVariable[i][0];
                    //创建一个检查有多少个子连接的变量
                    int totalConnected=1;
                    for (int j=0;j<2&&(!whoIsWinner);j++){
                        //乘以-1用来变换方向，比如先向前然后向后
                        detalLine*=-1;
                        detalColumn*=-1;

                        //创建两个变量作为检查点，之前只不过是检查的条件而已
                        int checkToLine =realLine;
                        int checkToColumn=realColumn;
                        //创建内while循环
                        while (true){
                            checkToLine+=detalLine;
                            checkToColumn+=detalColumn;
                            //创建两个布尔值来判断检察点

                        }



                    }



                }


            }


        }


    }
}
