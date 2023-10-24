import java.util.Scanner; //导入输入包

class Student {
  boolean[] voteFlag; // 定义是否投票的数组
  int result; // 定义投票数字编号
  int all; // 定义参与投票总数
  int[] voteCount; // 投票计数数组
  int[] overCount; // 索引学生编号对应 所投票学生编号索引
  String[] StudentList; // 学生姓名数组列表
  int giveUpCount; // 放弃投票计数
  StringBuffer giveUp = new StringBuffer();// 放弃投票动态字符串
  int x; // 已投票个数

  public Student() {

  }

  public Student(String[] StudentList, int[] voteCount, int[] overCount, boolean[] voteFlag) {
    this.StudentList = StudentList;
    this.voteCount = voteCount;
    this.overCount = overCount;
    this.voteFlag = voteFlag;
    // 数据初始化 接受main内数据
  }

  int flagNext() {
    // 计算出投票个数
    x = 0;
    for (int i = 0; i < voteFlag.length; i++) {
      if (voteFlag[i]) {
        x++;
      }
    }
    return x;

  }

  int flagQuit() {
    // 退出投票
    for (int i = 0; i < voteFlag.length; i++) {
      if (!voteFlag[i]) {
        overCount[i] = -1; // 未投票算作弃票
        this.giveUpCount++;

      }
    }
    return giveUpCount;

  }

  void numShow() {
    // 用来输出 学生编号方便输入 编号
    for (int j = 0; j < StudentList.length; j++) {
      if (j % 2 == 0) {// 换行
        System.out.println();
      }

      System.out.print(j + 1 + "号" + StudentList[j] + (overCount[j] == 0 ? "同学 " : "同学(已投票/弃票) "));// 三元表达式显示提示投票状态
    }
    System.out.println();
  }

  void Choice(Scanner sc) { // 开始选择投票者编号
    flagNext(); // 算出已投票个数
    if (x < 10) { // 判断是否 全部学生投票完毕
      numShow(); // 显示学生编号
      System.out.println("请输入即将投票的学生！编号");
      int ChoiceNum = sc.nextInt() - 1; // 输入的学生编号赋值为 编号-1（索引）
      if (!voteFlag[ChoiceNum]) { // 判断是否已经投票
        Vote(StudentList[ChoiceNum], ChoiceNum, sc); // 执行头片函数 传入参数学生姓名 索引
        System.out.println("是否继续投票？ y/n");

        String Continue = sc.next(); // 用户输入赋值
        if (Continue.equals("y") || Continue.equals("Y")) {
          // 用户输入判断是否继续 输入 选择投票学生编号
          Choice(sc); // 继续选择投票学生编号
        } else {
          flagQuit(); // 退出 剩余归为弃票
        }
      } else { // 已经投票
        System.out.println("请勿重复投票");
        Choice(sc); // 重新选择投票学生编号

      }
    }
  }

  void Vote(String name, int i, Scanner sc) {// 投票
    this.voteFlag[i] = true; // 设置为已投票

    numShow();// 显示学生编号
    System.out.println(this.StudentList[i] + "同学请输入编号开始投票   0为弃票");

    this.result = sc.nextInt(); // 输入赋值投票编号

    if (this.result != 0) { // 不弃票时
      this.all++; // 总投票数+1
      this.voteCount[result - 1]++; // 对应索引投票
      this.overCount[i] = result - 1; // 对应编号学生所投的 学生编号
      System.out.println(StudentList[i] + "同学把票投给" + this.StudentList[result - 1] + ",感谢你的投票");

    } else {
      System.out.println(StudentList[i] + "同学投给弃票了");

      this.giveUp.append(name + "同学,"); // 字符数组拼接
      this.giveUpCount++; // 弃票数增加
      this.overCount[i] = -1; // 对应索引 改为弃票

    }

  }

}

public class Text4 {
  public static void main(String[] args) {// 主要函数
    Scanner sc = new Scanner(System.in);
    String[] StudentList = { "st1", "st2", "st3", "st4", "st5", "st6", "st7", "st8", "st9", "st10" }; // 学生姓名数组列表
    int[] voteCount = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, };// 投票计数数组
    int[] overCount = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, };// 索引学生编号对应 所投票学生编号索引
    boolean[] voteFlag = { false, false, false, false, false, false, false, false, false, false };// 定义是否投票的数组
    Student stu = new Student(StudentList, voteCount, overCount, voteFlag);// 创建实例对象传参数

    stu.Choice(sc); // 开始选择即将投票学生的编号

    System.out.println("一共" + stu.all + "人投票");
    for (int i = 0; i < voteCount.length; i++) { // 结果循环输出
      String countmax = overCount[i] == -1 ? "弃票了 " : "投给了" + StudentList[overCount[i]]; // 三元表达式判断显示弃票 或 投票

      System.out.println(StudentList[i] + countmax + "获得"
          + voteCount[i] + "票");
    }
    System.out.println("" + stu.giveUp + stu.giveUpCount + "人放弃投票");

  }
}
