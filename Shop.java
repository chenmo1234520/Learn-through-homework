import java.util.Scanner; //引入java包

public class Shop { // Shop3类

  public static void main(String[] args) {
    String[] shop = new String[] { "牙刷", "毛巾", "水杯", "苹果", "香蕉" }; // 创建数组 物品以及价格
    double[] price = { 8.8, 10.0, 18.8, 12.5, 15.5 };
    String flag;
    int index, num;
    // 提前声明需要数据
    Scanner sc = new Scanner(System.in); // 创建scanner 实例
    double siglePrice, totalPrice = 0.0; // 声明单次购买价格 以及总价
    // do...while 循环开始
    do {
      System.out.println("请输入物品编号：");
      index = sc.nextInt();
      // 通过scanner获取输入数值
      if (index >= 5 | index <= 0) {
        System.out.println("请输入正确编号!"); // 判断数据是否符合
        continue;
      }
      System.out.println("请输入物品数量：");
      num = sc.nextInt();
      // 通过scanner获取输入数量
      siglePrice = num * price[index - 1];
      // 计算单次价格 数量乘价格 （物品编号-1即当前价格索引）

      totalPrice += siglePrice; // 总价累加
      System.out.println("是否需要继续购买？请输入Y/N");
      flag = sc.next(); // 通过scanner获取输入字符
      char flag2 = flag.charAt(0); // 转为可用字符
      // 判定输入字符
      if (flag2 == 'y' | flag2 == 'Y') {
        System.out.println("本次消费" + num + "个" + shop[index - 1] + "共" + siglePrice + "元");
        // 输入为 Y 继续执行
      } else if (flag2 == 'N' | flag2 == 'n') {
        System.out.println("一共花费" + totalPrice + "元");
        break;
        // 输入为 N 退出循环
      } else {
        System.out.println("格式错误请重新输入!");
        // 不y不n输入错误重新开始
      }
    } while (true); // 不使用while判断条件 使用 if

  }

}