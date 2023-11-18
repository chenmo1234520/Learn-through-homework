import java.util.HashSet; //导入HashSet
import java.util.Scanner;//导入输入Scanner
import java.util.regex.Matcher; //导入正则验证
import java.util.regex.Pattern;//导入正则表达式

class Login {
  String username; // 用户名
  String password;// 密码
  String repad;// 密码验证
  String birthday;// 生日
  String telnum;// 电话号码
  String email;// 邮箱
  Scanner sc;// scanner
  int choice; // 最初选择 注册或查看
  HashSet<String> husername = new HashSet<>(); // 用户名set
  HashSet<String> hpassword = new HashSet<>(); // 用户密码set
  HashSet<String> hbirthday = new HashSet<>(); // 用户出生日期set
  HashSet<String> htelnum = new HashSet<>(); // 用户电话号码set
  HashSet<String> hemail = new HashSet<>(); // 用户邮箱set

  void Choice() {// 选择功能
    this.sc = new Scanner(System.in); // 创建输入 Scanner实例对象
    System.out.println("1.注册 2.查看注册列表");
    try {// 异常检测
      choice = sc.nextInt();
    } catch (Exception e) {
      System.out.println("请输入正确格式！"); // 异常输出提示
      Choice();
    }
    if (choice == 1) {
      begin();// 开始注册
    } else if (choice == 2) {
      Cat(); // 查看注册列表
    }
  }

  void Cat() {// 查看注册列表
    System.out.println("注册列表");
    if (husername.size() <= 0) {// 用户名set为空则没有注册用户
      System.out.println("暂无用户注册");
      Choice(); // 重新选择
    } else {
      for (String string : husername) {// foreach 用户名set输出用户名
        System.out.println(string);

      }

      Choice();// 重新选择
    }

  }

  void begin() {// 开始注册
    this.sc = new Scanner(System.in);// 创建scanner实例对象
    System.out.println("开始注册");
    System.out.println("请输入用户名");
    username = sc.nextLine();// scanner 实例对象通过nextline 接收一个字符串作为用户名
    this.verify(username, husername, "用户名"); // 向验证函数传参验证 三个参数(需要验证的数据 ,验证成功要向哪个set添加,要验证数据类型【用户/密码.....】)
    System.out.println("请输入您的密码");
    password = sc.nextLine();// scanner 实例对象通过nextline 接收一个字符串作为密码
    System.out.println("请再次输入您的密码");
    repad = sc.nextLine();// scanner 实例对象通过nextline 接收一个字符串作为验证密码
    Checkpwd(repad);// 密码检查函数
    System.out.println("请输入您的出生日期 格式:yyyy-mm-dd");
    birthday = sc.nextLine();// scanner 实例对象通过nextline 接收一个字符串作为出生日期
    this.verify(email, hemail, "birthday");// 向验证函数传参验证 三个参数(需要验证的数据 ,验证成功要向哪个set添加,要验证数据类型【用户/密码.....】)
    System.out.println("请输入您的电话号码");
    telnum = sc.next();// scanner 实例对象通过nextline 接收一个字符串作为电话号码
    this.verify(telnum, htelnum, "电话");// 向验证函数传参验证 三个参数(需要验证的数据 ,验证成功要向哪个set添加,要验证数据类型【用户/密码.....】)
    System.out.println("请输入您的邮箱");
    email = sc.nextLine();// scanner 实例对象通过nextline 接收一个字符串作为邮箱
    this.verify(email, hemail, "邮箱");// 向验证函数传参验证 三个参数(需要验证的数据 ,验证成功要向哪个set添加,要验证数据类型【用户/密码.....】)
  }

  boolean Checkbirthday() { // 出生日期正则校验
    String patternString = "\\d{4}-\\d{2}-\\d{2}"; // 正则表达式字符串 \d代表数字 \\转义\ {n}代表格式 无开头结尾限制
    Pattern pattern = Pattern.compile(patternString); // 创建正则表达式
    Matcher matcher = pattern.matcher(birthday);// 进行正则条件验证 传入要验证的字符串
    boolean isMatch = matcher.matches();// 正则验证结果返回一个布尔值
    if (!isMatch) { // 不满足返回false 并提示输出 满足则返回true
      System.out.println("请输入正确的生日格式");
      return false;
    } else {
      return true;
    }
  }

  void Checkpwd(String p) { // 密码验证 接受验证的密码

    if (this.password.equals(p)) {// 将接收的密码与密码比较
      hpassword.add(this.password);// 为true 则在密码set 添加该密码

    } else {
      System.out.println("请输入正确密码!");// 为false 提示信息并重新输入 重新检查
      this.repad = sc.nextLine();

      Checkpwd(this.repad);

    }
  }

  boolean Checktelnum() {// 电话号码正则校验
    String patternString = "^(13|15|17|18)\\d{9}$"; // 正则表达式字符串 ^以要求开头 $以要求结尾 |或者 以13 15 17 18 开头 后跟9个数字\d 以9个数字结尾
    Pattern pattern = Pattern.compile(patternString);// 创建正则表达式
    Matcher matcher = pattern.matcher(String.valueOf(telnum));// 进行正则条件验证 传入要验证的字符串
    boolean isMatch = matcher.matches();// 正则验证结果返回一个布尔值
    if (!isMatch) {// 不满足返回false 并提示输出 满足则返回true
      System.out.println("请输入正确的手机号码格式");
      return false;
    } else {
      return true;
    }

  }

  boolean Checkemail() {// 邮箱正则验证
    String patternString = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";// 邮箱正则表达式 \w字母 以一个或多个数字字母开头 中间@ 转义点\.
    Pattern pattern = Pattern.compile(patternString);// 创建正则表达式
    Matcher matcher = pattern.matcher(email);// 进行正则条件验证 传入要验证的字符串
    boolean isMatch = matcher.matches();// 正则验证结果返回一个布尔值
    if (!isMatch) {// 不满足返回false 并提示输出 满足则返回true
      System.out.println("请输入正确的邮箱格式");
      return false;
    } else {
      return true;
    }

  }

  void verify(String newd, HashSet<String> oldd, String type) {// 输入验证函数
    if (type.equals("birthday")) {// 如果类型为出生日期 不需要判断重复
      if (Checkbirthday()) {// 通过正则表达式函数验证出生日日期
        oldd.add(newd);// 验证产生过向出入日期set添加 数据

      } else {// 验证不通过重新输入
        birthday = sc.nextLine();
        this.verify(email, hemail, "birthday");// 再次验证
      }
    } else {// 其余类型需要判断重复
      if (oldd.contains(newd)) {// 判断使用已经具有该数据
        System.out.println("已经有用户使用该" + type + "了"); // 具有该数据进行输出提示

        if (type.equals("电话")) { // 通过接收的类型分别进行提示 重新验证
          System.out.println("请重新输入其他手机号");
          telnum = sc.next();
          this.verify(telnum, htelnum, "电话");
        } else if (type.equals("邮箱")) {
          System.out.println("请重新输入其他邮箱");
          email = sc.next();
          this.verify(email, hemail, "邮箱");
        } else {
          Choice();// 特殊预防
        }

      } else {// 判断 set没有该数据
        if (type.equals("电话")) {// 通过接收类型向相应的set添加相应数据
          if (Checktelnum()) {
            oldd.add(newd);

          } else {
            this.telnum = sc.next();// 验证不通过重新输入并重新验证
            this.verify(telnum, htelnum, "电话");
          }
        } else if (type.equals("邮箱")) {
          if (Checkemail()) {
            oldd.add(newd);
            System.out.println("注册成功");// 最后一次验证完成注册成功
            Choice();// 选择功能

          } else {// 验证不通过重新输入并重新验证
            this.email = sc.nextLine();
            this.verify(email, hemail, "邮箱");
          }
        } else {
          oldd.add(newd);// 不需要验证类型直接添加到相应set

        }

      }

    }
  }

}

public class Register {
  public static void main(String[] args) {
    Login l = new Login(); // 创建实例对象

    l.Choice();// 调用选择功能函数
  }
}