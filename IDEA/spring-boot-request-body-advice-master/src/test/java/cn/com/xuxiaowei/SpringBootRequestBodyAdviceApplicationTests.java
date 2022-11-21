package cn.com.xuxiaowei;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class SpringBootRequestBodyAdviceApplicationTests {

    @Test
    void contextLoads() {

        String key = "9dd963975afa28a8";
        String iv = "f1d84ddc2ce249ab";

        String text = "1234567890";

        AES aes = new AES(Mode.CTS, Padding.PKCS5Padding, key.getBytes(), iv.getBytes());
        String ciphertext = aes.encryptBase64(text);

    }

}
