package cn.com.xuxiaowei.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 老师
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TeacherBo extends Request {

    private String username;

    private String password;

}
