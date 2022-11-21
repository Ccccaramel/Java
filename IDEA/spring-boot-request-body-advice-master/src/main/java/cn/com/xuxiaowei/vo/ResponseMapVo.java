package cn.com.xuxiaowei.vo;

import lombok.Data;

import java.util.Map;

/**
 * 响应数据
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class ResponseMapVo {

    /**
     * 接口名称
     */
    private String interfaceName;

    /**
     * 原文
     * <p>
     * 加密字符串长度最小为 16，小于 16 可能加密失败
     */
    private Map<String, Object> text;

    /**
     * 密文
     */
    private String ciphertext;

}
