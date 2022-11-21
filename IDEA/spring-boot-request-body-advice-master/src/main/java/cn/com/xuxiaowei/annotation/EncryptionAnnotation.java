package cn.com.xuxiaowei.annotation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

/**
 * {@link Controller}、{@link RestController} 数据加密
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Documented
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EncryptionAnnotation {

    /**
     * 接口名
     *
     * @return 返回 接口名
     */
    String interfaceName();

}
