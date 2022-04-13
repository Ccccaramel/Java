package com.muke.onlineedu.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@TableName("gm_power")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GMPower implements Serializable {
    private Integer powerNumber;
    private String powerClass;
}
