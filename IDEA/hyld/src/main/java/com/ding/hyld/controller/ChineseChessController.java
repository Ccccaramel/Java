package com.ding.hyld.controller;

import com.ding.hyld.constant.DictionaryCode;
import com.ding.hyld.controller.Base.BaseController;
import com.ding.hyld.entity.ChineseChess;
import com.ding.hyld.service.ChatService;
import com.ding.hyld.service.ChineseChessService;
import com.ding.hyld.utils.R;
import com.ding.hyld.vo.ChatVo;
import com.ding.hyld.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("/chineseChess")
public class ChineseChessController extends BaseController {
    @Autowired
    private ChineseChessService chineseChessService;

}