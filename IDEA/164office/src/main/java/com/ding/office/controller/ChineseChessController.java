package com.ding.office.controller;

import com.ding.office.controller.Base.BaseController;
import com.ding.office.service.ChineseChessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chineseChess")
public class ChineseChessController extends BaseController {
    @Autowired
    private ChineseChessService chineseChessService;

}