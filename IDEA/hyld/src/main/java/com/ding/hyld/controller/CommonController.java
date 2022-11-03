package com.ding.hyld.controller;

import com.ding.hyld.utils.R;
import com.ding.hyld.utils.RsaUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common")
public class CommonController {

    @GetMapping("/getPublicKey")
    public R getPublicKey(){
        return R.success(RsaUtils.getRsaKeyPair());
    }
}
