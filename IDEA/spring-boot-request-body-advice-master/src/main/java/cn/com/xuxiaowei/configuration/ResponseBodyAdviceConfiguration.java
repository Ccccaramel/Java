package cn.com.xuxiaowei.configuration;

import cn.com.xuxiaowei.annotation.EncryptionAnnotation;
import cn.com.xuxiaowei.vo.ResponseMapVo;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * {@link Controller}、{@link RestController} 响应处理
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@ControllerAdvice
public class ResponseBodyAdviceConfiguration implements ResponseBodyAdvice<ResponseMapVo> {

    @SneakyThrows
    @Override
    public boolean supports(@NonNull MethodParameter returnType, @NonNull Class<? extends HttpMessageConverter<?>> converterType) {

        Class<?> parameterType = returnType.getParameterType();

        // 精确判断
        if (parameterType.isAssignableFrom(ResponseMapVo.class)) {
            return returnType.hasMethodAnnotation(EncryptionAnnotation.class);
        }

        return false;
    }

    @SneakyThrows
    @Override
    public ResponseMapVo beforeBodyWrite(ResponseMapVo body, @NonNull MethodParameter returnType,
                                         @NonNull MediaType selectedContentType,
                                         @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                         @NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response) {

        if (body == null) {
            return null;
        }

        // 获取注解
        EncryptionAnnotation methodAnnotation = returnType.getMethodAnnotation(EncryptionAnnotation.class);

        assert methodAnnotation != null;

        // 用于查询数据库（配置文件）中的 key与iv
        String interfaceName = methodAnnotation.interfaceName();

        // 根据上面的 interfaceName 与 currentUser 查询数据库（配置文件）中的 key与iv
        String key = "9dd963975afa28a8";
        String iv = "f1d84ddc2ce249ab";

        body.setInterfaceName(interfaceName);

        Object text = body.getText();

        // 原文为 null
        // 加密字符串长度最小为 16
        if (text == null) {
            return body;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String textStr = objectMapper.writeValueAsString(text);

        AES aes = new AES(Mode.CTS, Padding.PKCS5Padding, key.getBytes(), iv.getBytes());
        String ciphertext = aes.encryptBase64(textStr);

        // 原文 置空
        body.setText(null);
        // 设置 密文
        body.setCiphertext(ciphertext);

        return body;
    }

}
