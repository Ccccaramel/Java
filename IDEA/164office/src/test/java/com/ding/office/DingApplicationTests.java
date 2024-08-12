package com.ding.office;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DingApplicationTests {

    /**
     * 由于数据库账号信息已被加密
     * 在测试时编辑配置!
     */

    @Autowired
    private StringEncryptor stringEncryptor;

    /**
     * 测试加密时记得解开配置文件 jasypt.encryptor.password 注解
     */
//    @Test
//    public void encryptTest(){
//        // 加密 -基础类型
//        String userName = stringEncryptor.encrypt("123");
//        System.out.println("加密："+userName);
//
//        // 解密 -基础类型
//        String decUserName = stringEncryptor.decrypt(userName);
//        System.out.println("解密："+decUserName);
//
//        // 加密 -对象
//        UpdateLogInfo updateLogInfo = new UpdateLogInfo();
//        updateLogInfo.setId(1);
//        updateLogInfo.setCreateTime(LocalDateTime.now());
//        updateLogInfo.setText("333");
//        updateLogInfo.setCreateTimeStr("2020-02-20");
//        updateLogInfo.setNote("4444");
//        updateLogInfo.setCreateTime(LocalDateTime.now());
//        String updateLogInfoStrEn = stringEncryptor.encrypt(updateLogInfo.toString());
//        System.out.println("加密："+updateLogInfoStrEn);
//
//        // 解密 -对象
//        String updateLogInfoStrDe = stringEncryptor.decrypt(updateLogInfoStrEn);
//        System.out.println(updateLogInfoStrDe);
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule()); // ...,如果包含时间类型的属性的话,加上它吧
//        UpdateLogInfo newUpdateLogInfo =objectMapper.convertValue(JSONObject.parse(updateLogInfoStrDe),UpdateLogInfo.class);
//        System.out.println("解密："+newUpdateLogInfo.toString());
//    }

    /**
     * 测试加密时记得解开配置文件 jasypt.encryptor.password 注解
     * 为配置文件中的 mysql 配置信息加密
     */
    @Test
    public void encryptConfig(){
//        // mysql > username
//        String userNameEnc = stringEncryptor.encrypt("***");
//        System.out.println("username 加密："+userNameEnc);
//        String userNameDec = stringEncryptor.decrypt(userNameEnc);
//        System.out.println("username 解密："+userNameDec);
//
//        // mysql > password
//        String passwordEnc = stringEncryptor.encrypt("***");
//        System.out.println("password 加密："+passwordEnc);
//        String passwordDec = stringEncryptor.decrypt(passwordEnc);
//        System.out.println("password 解密："+passwordDec);
//
//        // 云 mysql > password
//        String cloudPasswordEnc = stringEncryptor.encrypt("***");
//        String cloudPasswordEnc = "txVHlG5vtWXu0AaPRbx30Z8Vp2CPhWmXwes+Zp0rXzFPBAlghzGffg6t29jxJqae";
//        System.out.println("云 password 加密："+cloudPasswordEnc);
//        String cloudPasswordDec = stringEncryptor.decrypt(cloudPasswordEnc);
//        System.out.println("云 password 解密："+cloudPasswordDec);
//
//        // 163邮箱 > username
//        String emailUserNameEnc = stringEncryptor.encrypt("***");
//        System.out.println("163邮箱 username 加密："+emailUserNameEnc);
//        String emailUserNameDec = stringEncryptor.decrypt(emailUserNameEnc);
//        System.out.println("163邮箱 username 解密："+emailUserNameDec);
//
//        // 163邮箱 > password
//        String emailPasswordEnc = stringEncryptor.encrypt("***");
//        System.out.println("163邮箱 password 加密："+emailPasswordEnc);
//        String emailPasswordDec = stringEncryptor.decrypt(emailPasswordEnc);
//        System.out.println("163邮箱 password 解密："+emailPasswordDec);
    }

//    @Autowired(required = false)
//    private JavaMailSender javaMailSender; // 引入Spring Mail依赖后，会自动装配到IOC容器。用来发送邮件
//    @Autowired(required = false)
//    private MailProperties mailProperties;
//    @Test
//    public void testEmail() {
//        String code = "6760"; // 验证码
//
//        String from = "**164office@163.com";
//        String to = "444543565@qq.com";
//        String title = "【164office】用户邮箱验证";
//        String userName = "麦克";
//        String html = "<html>\n" +
//                "\t<body>\n" +
//                "\t\t<br>\n" +
//                "\t\t<h1>以下是您的验证码：</h1>\n" +
//                "\t\t<h2 style=\"color: #66ccff;\">&emsp;"+code+"</h2>\n" +
//                "\t\t<hr/>\n" +
//                "\t\t<h3>尊敬的用户"+userName+"，您好：</h3>\n" +
//                "\t\t<h3>&emsp;我们（164office.cn-荒野社区平台）收到了来自您的绑定邮箱请求，请使用上面的验证码验证您的账号归属。</h3>\n" +
//                "\t\t<h3>&emsp;该验证码将在1分钟后过期，请及时输入。</h3>\n" +
//                "\t\t<br>\n" +
//                "\t\t<h3>&emsp;为保证账号安全，请勿泄漏此验证码。</h3>\n" +
//                "\t\t<h3>&emsp;祝在【164office】收获愉快！</h3>\n" +
//                "\t\t<h3>&emsp;(〃￣︶￣)人(￣︶￣〃)</h3>\n" +
//                "\t\t<hr/>\t\n" +
//                "\t\t<h3>如果您有任何疑问请<a href='https://www.164office.cn'>访问平台</a>留言询问</h3>\n" +
//                "\t</body>\n" +
//                "</html>";
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper = null;
//        try {
//            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
//            // 邮件发送来源
//            mimeMessageHelper.setFrom(from);
//            // 邮件发送目标
//            mimeMessageHelper.setTo(to);
//            // 设置标题
//            mimeMessageHelper.setSubject(title);
//            // 设置内容，并设置内容 html 格式为 true
//            mimeMessageHelper.setText(html, true);
//            javaMailSender.send(mimeMessage);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//    }

//    @Test
//    public void testIP(){
//        try {
//            RestTemplate restTemplate = new RestTemplate();
//            String url ="https://apis.map.qq.com/ws/location/v1/ip?key=VQPBZ-GZIKU-QNPV7-B7MD5-PPA2F-TMBES&ip=183.250.252.12";
//            QQMapIPInfo qqMapIPInfo = restTemplate.getForObject(url,QQMapIPInfo.class);
//            System.out.println(qqMapIPInfo.toString());
//        }catch (HttpClientErrorException e){
//            System.out.println("http客户端请求出错了！");
//        }
//    }
}
