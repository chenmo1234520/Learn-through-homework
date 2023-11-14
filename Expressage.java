import java.util.*;

/* 模拟物流快递系统程序设计 */
public class Expressage {
    public static void main(String[] args) {
        Order order = new Order(); // order 创建实例对象
        order.Choice(); // 选择功能函数

    }
}

class Order {
    int orderID; // 内层map id
    int ID; //修改时的索引
    int index;//生成是的编号 从1开始
    String orderName; //订单名
    String orderAddress;//快递地址
    int orderPhone; //快递手机号
    int orderTime; //快递运行时间
    int orderStatus;//快递状态
    HashMap<Integer, HashMap<String, Object>> hs = new HashMap<>(); //外层map value为嵌套HashMap

    //value类型为Object 后续强制类型转换 即可在map存储多种数据的 Value
    HashMap<String, Object> hsObj;//内层map 

    Scanner sc = new Scanner(System.in); //输入实例对象


    /* 成员变量 getter 和 setter */
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getID() {
        return ID;
    }

    public int setID(int i) {
        return this.ID = i;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(Object orderName) {
        this.orderName = (String) orderName; //object强制转换为 String
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(Object orderAddress) {
        this.orderAddress = (String) orderAddress; //object强制转换为 String
    }

    public int getOrderPhone() {
        return orderPhone;
    }

    public void setOrderPhone(Object orderPhone) {
        this.orderPhone = (int) orderPhone; //object强制转换为 int
    }

    public int getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Object orderTime) {
        this.orderTime = (int) orderTime;//object强制转换为 int
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Object orderStatus) {
        this.orderStatus = (int) orderStatus;//object强制转换为 int
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
         this.index = index;
    }

    /* 
     * 订单状态匹配函数
     */
    String OrderStatus(int orderStatus2) {
        switch (orderStatus2) {
            case 0:
                return "未发货";
            case 1:
                return "已发货";
            case 2:
                return "配送中";
            case 3:
                return "已签收";
            case 4:
                return "已取消";
            default:
                return "未知";
        }

    }
/* 
 * 初始函数 选择功能
 */
    void Choice() {
        System.out.println("1.下单 2.查询 3.取消 4.修改 5.退出");
        int choice = sc.nextInt();
        try { //异常捕获
            switch (choice) {
                case 1:
                    placeOrder(false); //传入false 表示 修改而不是 新增下单
                    break;
                case 2:
                    queryOrder();//调用查询函数
                    break;
                case 3:
                    cancelOrder();//调用关闭订单函数
                    break;
                case 4:
                    editOrder();//调用修改订单函数
                    break;
                case 5:
                    System.out.println("退出成功");
                    System.exit(0); //退出系统
                    break;
                default:
                    System.out.println("输入错误");
                    break;
            }

        } catch (Exception e) {

            System.out.println("输入错误,请输入正确格式");
            //异常提示
            Choice();//重新选择功能
        }

    }
/* 
 * 
 * 显示(输出)目前所有的订单编号以及状态
 */
    void alreadyExist() {

        for (int i = 1; i <= hs.size(); i++) {
            System.out.print("编号: " + i);
            System.out.println(" 状态: " + this.OrderStatus((int) hs.get(i).get("orderStatus")));

        }
    }
/* 
 * 修改订单函数
 */
    void editOrder() {
        System.out.println("请输入快递编号");
        try {//异常捕获
            this.setID(sc.nextInt());//设置要修改的 编号

        } catch (Exception e) {

            System.out.println("输入错误,请输入正确编号");
        }

        this.placeOrder(true); //传入true 表示修改
    }

    /* 
     * 查询订单函数
     */
    void queryOrder() {
        alreadyExist();//首先输出已存在订单
        System.out.println("请输入快递编号");
        try {//异常捕获
            setOrderID(sc.nextInt());

        } catch (Exception e) {
            System.out.println("输入错误,请输入正确编号");
            queryOrder();
        }

        if (hs.get(orderID) == null) {
            System.out.println("您还没有下单");
            placeOrder(false); //没有该订单 跳到添加（下单）

        }
        //设置将要展示的订单为查询到订单的指
        this.setOrderName(hs.get(orderID).get("orderName"));
        this.setOrderAddress(hs.get(orderID).get("orderAddress"));
        this.setOrderTime(hs.get(orderID).get("orderTime"));
        this.setOrderPhone(hs.get(orderID).get("orderPhone"));
        this.setOrderStatus(hs.get(orderID).get("orderStatus"));

        Cat();//展示查询到的订单
        Choice();//查询后 重新选择功能
    }
/* 
 * 取消订单函数
 */
    void cancelOrder() { 
        try {//输入值复活异常
            System.out.println("请输入要取消的快递编号");
            setOrderID(sc.nextInt());//设置编号
        } catch (Exception e) {
            System.out.println("输入错误,请输入正确编号");
            cancelOrder();//捕获输入错误，重新输入
        }

        if (hs.get(orderID) == null) {
            System.out.println("您还没有下单");
            placeOrder(false);//通过编号获取不到该订单 使用新增订单函数

        }

        hs.get(orderID).put("orderStatus", 4);//将对应编号订单状态设置为取消
        this.setOrderStatus(4);//将将要展示 订单状态设置为取消
        System.out.println(getOrderID() + "号订单取消成功成功!");
        this.Cat();// 显示当前订单状态

        Choice();//回到选择功能函数
    }

    /* 
     * 展示当前订单
     */
    void Cat() {
        System.out.println("当前编号:" + this.orderID);
        System.out.println("当前名称:" + this.orderName);
        System.out.println("当前地址:" + this.orderAddress);
        System.out.println("当前订单存在:" + this.orderTime + "天");
        System.out.println("当前电话:" + this.orderPhone);
        System.out.println("当前状态:" + this.OrderStatus(this.orderStatus));
        Choice();
    }

    /* 
     * 下单/修改 订单函数
     */
    void placeOrder(boolean a) { //布尔值接受参数判断 新增或修改
        hsObj = new HashMap<>(); //生产新的 map
        try {//数值异常捕获
            System.out.println("请输入订单名称");
            orderName = sc.next();
            System.out.println("请输入订单地址");
            orderAddress = sc.next();
            System.out.println("请输入订单电话");
            orderPhone = sc.nextInt();
            System.out.println("请输入订单时间(第几天)");
            orderTime = sc.nextInt();
            System.out.println("请输入订单状态(0.未发货/1.发货/2.配送/3.签收/4.取消)");
            orderStatus = sc.nextInt();
            //输入对应的数据

        } catch (Exception e) {
            System.out.println("输入错误，请重新输入"); //数据异常提示

        }

        hsObj.put("orderName", orderName);
        hsObj.put("orderAddress", orderAddress);
        hsObj.put("orderPhone", orderPhone);
        hsObj.put("orderTime", orderTime);
        hsObj.put("orderStatus", orderStatus);
/* 
 * 添加数据到内层map
 */
        if (a != true) {
            //参数为true 为新增订单
            this.setIndex(this.getIndex() + 1);
            //新增订单编号默认+1 
            hsObj.put("orderID", this.getIndex());

            this.hs.put(this.getIndex(), hsObj);  //将编号作为外层map的key 讲内层map 作为外层map的值(value)
            System.out.println("下单成功");
        } else { //参数不为true 为修改订单
            //通过之前设置的编号 修改数据 (修改订单函数)
            hsObj.put("orderID", this.getID());
            this.hs.put(this.getID(), hsObj);//将之前设置的编号作为外层map的key 讲内层map 作为外层map的值(value)
            System.out.println("修改成功");
        }
        Choice();//继续选择功能
    }

}
