

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.List;
import java.util.Timer;

public class Main {
    private static Timer timer;
    private static int interval=10;//间隔多少s;
    private static String responseHeader;
    private static int length;
    private static int cnt=0;
    private static String cookie="[{\"domain\":\".amazon.com\",\"expirationDate\":1537891577,\"hostOnly\":false,\"httpOnly\":true,\"name\":\"a-ogbcbff\",\"path\":\"/\",\"secure\":false,\"session\":true,\"storeId\":\"0\",\"value\":\"deleted\",\"id\":0},{\"domain\":\".amazon.com\",\"expirationDate\":1537891577,\"hostOnly\":false,\"httpOnly\":true,\"name\":\"a-ogbcbff\",\"path\":\"/\",\"secure\":false,\"session\":true,\"storeId\":\"0\",\"value\":\"1\",\"id\":0},{\"domain\":\".amazon.com\",\"expirationDate\":1537891577,\"hostOnly\":false,\"httpOnly\":true,\"name\":\"ubid-main\",\"path\":\"/\",\"secure\":false,\"session\":true,\"storeId\":\"0\",\"value\":\"130-0526563-7251329\",\"id\":0},{\"domain\":\".amazon.com\",\"expirationDate\":1537891577,\"hostOnly\":false,\"httpOnly\":true,\"name\":\"session-token\",\"path\":\"/\",\"secure\":false,\"session\":true,\"storeId\":\"0\",\"value\":\"bukloCtd/en6Zyu1Y6ASX+q0CbbAjjxrY97/3D04bx/obtw4ty8pPk7B0NX7qcWBBldqW+0g6NQSQinc4qm3r3HOf4cbsR3DKE+ox022Scdz1igMH3UvBH3Ju1dcOHBoY/J9TLnvrv+qG1dbwPGWk2PjLsMl46rHZ9GInPJSZPAhlvxBGgKKhvRsn9tlZHRHd5tWNXGp/iafipYhS/+Py3i71wiw0xEdxbKOYwTQMlE=\",\"id\":0},{\"domain\":\".amazon.com\",\"expirationDate\":1537891577,\"hostOnly\":false,\"httpOnly\":true,\"name\":\"x-main\",\"path\":\"/\",\"secure\":false,\"session\":true,\"storeId\":\"0\",\"value\":\"z3cFoy?KN0vlFzPACdwrLmKzqqioyZZN\",\"id\":0},{\"domain\":\".amazon.com\",\"expirationDate\":1537891577,\"hostOnly\":false,\"httpOnly\":true,\"name\":\"at-main\",\"path\":\"/\",\"secure\":false,\"session\":true,\"storeId\":\"0\",\"value\":\"Atza|IwEBIKKssh8PQCh4z4coLz-IBM0jjUbCKscktWCbMf4MQgx36A2eW21vwlIottWTduwLJl_7KzuLGefNJ733hZWZ3aJiPIkby4affdknXsgTQQVpz1c68bADmZoUiHhi7dKYm9cu8Orcuo6Z4ttPg05yZ79L4OKID_r5m4dj286z4OhvUnJOM8Q_rhSRk77eyPMvembdYUHL67rRvg-YzReTGhx1vE-Jtx8KKzOkBvoEyTc2wURMFQSM0MaGuXg8vuYGsmFnkXmBsMNbH99F3TWzkxxaPXGtdaTXbOG35aO3XxP-gXwaXeO2SbdZL4PIY30GPXaJbvesig2huXkRj75WMDqRTCQOzhhjf7RKoVMMTno4ZmxvmhP0ftmYapvPYxAlbD0tsGjqTuL9JTKKBcXqjd9G\",\"id\":0},{\"domain\":\".amazon.com\",\"expirationDate\":1537891577,\"hostOnly\":false,\"httpOnly\":true,\"name\":\"sess-at-main\",\"path\":\"/\",\"secure\":false,\"session\":true,\"storeId\":\"0\",\"value\":\"r8Hr+sbl/Fh8F4XejsVwaLbYtUpgNW8PfJzZ6YME5rI=\",\"id\":0},{\"domain\":\".amazon.com\",\"expirationDate\":1537891577,\"hostOnly\":false,\"httpOnly\":true,\"name\":\"sst-main\",\"path\":\"/\",\"secure\":false,\"session\":true,\"storeId\":\"0\",\"value\":\"Sst1|PQE-CrjYcUYsWbVNtpBc5qPyC2xx_y0UAz-Ayg9YE2c278YcNQtu5muvxcX2RqFvU7BLUxXtuyKvaHIMbXxRog0c63-bDndmbOHbLTsGKct8bCIL4dbJd7xUrQ0ziRbc5oWz25ejHrOqo6zM1qX2BEqCUvN5bwRFVMEco3LhkAOTT6zlapzt6NUtpm_Yim3rWifc6-7CGFDgUo0oyn76VencmznX6l3t2upJ2JzmF6pqcY9F-7I3c7pHVXkF3COjTxYrtdzRvYesAIcF4W7HeDbT6y4XCy2CBKgV9E52spaG1yj0awgNf2AgVCcRyGFE5PFJ03T6Md22u944dXm8Nq4SZg\",\"id\":0},{\"domain\":\".amazon.com\",\"expirationDate\":1537891577,\"hostOnly\":false,\"httpOnly\":true,\"name\":\"lc-main\",\"path\":\"/\",\"secure\":false,\"session\":true,\"storeId\":\"0\",\"value\":\"en_US\",\"id\":0}]";
    private static Random rand=new Random();
    private static String url="https://www.amazon.com/gp/customer-reviews/R1AWO7CTYRU8I?ref=pf_vv_at_pdctrvw_srp";
    private static String chrome="Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.220 Safari/535.1";
    private static String safari="Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN) AppleWebKit/533.21.1 (KHTML, like Gecko) Version/5.0.5 Safari/533.21.1";
    private static String ie="Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; .NET CLR 2.0.50727; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; InfoPath.1; .NET4.0C; .NET4.0E; InfoPath.2)";
    private static String firefox="Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.24) Gecko/20111103 Firefox/3.6.24";
    private static String[] browsers={chrome,safari,ie,firefox};
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setTitle("系统登录界面");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setVisible(true);

//设置窗口的大小和位置
//        f.setSize(600, 800);
//        f.setLocation(200, 200);


        Container con = f.getContentPane();//生成一个容器
        con.setLayout(new GridLayout(7, 1));

        JPanel pan1 = new JPanel();//生成一个新的版面
        JLabel title = new JLabel("欢迎登陆差评都不见系统");
        title.setFont(new Font("宋体", Font.BOLD, 20));
        pan1.add(title);
        con.add(pan1);
//最上面的登陆文字

        JPanel pan2 = new JPanel();//生成一个新的版面
        JLabel name = new JLabel("网址");
        pan2.add(name);
        TextArea urlT = new TextArea();
        urlT.setColumns(60);
        urlT.setRows(10);
        pan2.add(urlT);
        con.add(pan2);
//用户名及其文本框放置在第二个版面上


        JPanel pan3 = new JPanel();//生成一个新的版面
        JLabel pass = new JLabel("间隔时间 单位s");
        pan3.add(pass);
        TextField numOfTime = new TextField(15);
        pan3.add(numOfTime);
        con.add(pan3);
//密码及其密码域放在第三个版面上


        JPanel pan4 = new JPanel();
        JButton b_log = new JButton("开始");
        pan4.add(b_log);
        JButton b_exit = new JButton("停止");
        pan4.add(b_exit);
        JLabel num=new JLabel();
        pan4.add(num);
        con.add(pan4);
//登陆和退出这两个按钮放在第四个版面上

//        JPanel pan5 = new JPanel();
//        con.add(pan5);
//        JPanel pan6 = new JPanel();
//        con.add(pan6);
//        JPanel pan7 = new JPanel();
//        con.add(pan7);
        b_log.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cnt=0;
                    timer=new Timer();
                    try {
                        timer.schedule(new TimerTask() {
                            public void run() {
                                int index = rand.nextInt(4);
                                String result = sendGet(urlT.getText(), browsers[index]);
                                num.setText("第"+cnt+"次 "+"长度"+length);
                               System.out.println(responseHeader);
                            }
                        }, 500, Integer.valueOf(numOfTime.getText()) * 1000);
                    }catch (Exception exception)
                    {
                        exception.printStackTrace();
                    }
                }
            }
        );
        b_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.cancel();
            }
        });
    }

    public static String sendGet(String url,String browser) {
        BufferedReader in = null;
        cnt++;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty( "scheme","https");
            connection.setRequestProperty("Content-Type","text/plain; charset=utf-8");
//            connection.setRequestProperty("Cookie",cookie);
            connection.setRequestProperty( "Accept" ,"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",browser);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段=
            responseHeader=map.toString();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            length=sb.toString().length();
            return sb.toString();
        } catch (Exception e) {
            System.out.println("http Exception"+e);
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }
}
