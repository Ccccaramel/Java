package com.ding.office.controller;

import com.ding.office.constant.DictionaryCode;
import com.ding.office.controller.Base.BaseController;
import com.ding.office.info.BillStatisticsInfo;
import com.ding.office.service.BillService;
import com.ding.office.utils.R;
import com.ding.office.vo.BillVo;
import com.ding.office.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/bill")
public class BillController extends BaseController {
    @Autowired
    private BillService billService;

    @GetMapping("/searchBill")
    public R searchBill(Page page, BillVo billVo){
        HashMap<String,Object> result=new HashMap<>();
        billVo.setUser(getUserId());
        billVo.setStatus(DictionaryCode.BILL_STATUS_1);
        result.put("data",billService.search(page, billVo));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(billService.searchPage(billVo)*1.0/page.getSize()));
        }
        return R.success(result);
    }

    @PostMapping("/saveBill")
    public R saveBill(@RequestBody BillVo billVo){
        billVo.setUser(getUserId());
        if(billVo.isAdd()){
            billVo.setStatus(DictionaryCode.BILL_STATUS_1);
            billService.add(billVo);
            return R.success("账单保存成功!");
        }else{
            billService.update(billVo);
            return R.success("账单修改成功!");
        }
    }

    @PostMapping("/deleteBill")
    public R deleteBill(@RequestBody BillVo billVo){
        if(billVo.getId()==null){
            return R.fail("账单删除失败!");
        }
        billVo.setUser(getUserId());
        billVo.setStatus(DictionaryCode.BILL_STATUS_2);
        billService.delete(billVo);
        return R.success("账单删除成功!");
    }
    @GetMapping("/getBillTrendData")
    public R getBillTrendData(){
        HashMap<String,Object> result=new HashMap<>();
        List<String> nameList = new ArrayList<>();
        List<BigDecimal> valueList = new ArrayList<>();
        BillVo billVo = new BillVo();

        billVo.setStatus(DictionaryCode.BILL_STATUS_1);
        billVo.setUser(getUserId());
        billVo.setOrderByExpenseTime(true);
        billVo.setDesc(false);
        List<BillStatisticsInfo> infos = billService.getBillStatistics(billVo);
        for (BillStatisticsInfo info : infos) {
            nameList.add(info.getYear()+"/"+info.getMonth());
            valueList.add(info.getValueOfMonth());
        }
        result.put("nameList",nameList);
        result.put("creditList",valueList);
        return R.success(result);
    }


    @GetMapping("/getBillStatistics")
    public R getBillStatistics(){
        HashMap<String,Object> result=new HashMap<>();
        BillVo billVo = new BillVo();
        BigDecimal thisMonth=BigDecimal.valueOf(0);
        BigDecimal lastMonth=BigDecimal.valueOf(0);
        BigDecimal maxMonth=BigDecimal.valueOf(0);
        BigDecimal minMonth=BigDecimal.valueOf(0);
        BigDecimal total= BigDecimal.valueOf(0);

        billVo.setUser(getUserId());
        billVo.setStatus(DictionaryCode.BILL_STATUS_1);
        billVo.setOrderByExpenseTime(true);
        List<BillStatisticsInfo> infos = billService.getBillStatistics(billVo);
        Integer len = infos.size();

        Integer nowYear = LocalDateTime.now().getYear();  // 当前月份
        Integer nowMonth=LocalDateTime.now().getMonthValue();  // 当前月份

        if(infos ==null || infos.size()==0){
            // 没有数据,都是0
        }else if (infos.size()==1){  // 只有一个月份的数据,那么这个月份的数据是当前月份的数据,则上个月数据,最高消费月均为0
            thisMonth=infos.get(0).getValueOfMonth();  // 本月消费
            // 上月为0
            // 历史最高月消费为0
            // 历史最低月消费为0
            total=thisMonth;  // 总消费
        }else{   // 有两个月份的数据,比包含一个当前月分数据,其余全为历史数据
            boolean isLast=false;

            // 判断"当前月"的数据是否是当前月的
            BillStatisticsInfo thisBillInfo=infos.get(len-1);
            if(thisBillInfo.getYear().equals(nowYear)&&thisBillInfo.getMonth().equals((nowMonth))){  // 这个月有数据
                thisMonth = thisBillInfo.getValueOfMonth();  // 本月消费
                isLast=true;
            }

            // 判断"上个月"的数据是否是上个月的
            BillStatisticsInfo lastBillInfo=infos.get(len-2);
            if(lastBillInfo.getYear().equals(nowYear)&&lastBillInfo.getMonth().equals((nowMonth-1))){  // 上个月有数据
                lastMonth=lastBillInfo.getValueOfMonth();
            }

            minMonth=infos.get(0).getValueOfMonth();
            for(int i=0;i<len;i++){
                BillStatisticsInfo info = infos.get(i);
                total = total.add(info.getValueOfMonth());  // 统计总消费
                if(i==(len-1)&&isLast){  // infos已按时间排序,遍历至当前月的数据,跳过
                    break;
                }
                if(info.getValueOfMonth().compareTo(maxMonth) > 0){
                    maxMonth=info.getValueOfMonth();
                }
                if(info.getValueOfMonth().compareTo(minMonth) < 0){
                    minMonth=info.getValueOfMonth();
                }
            }
        }
        result.put("thisMonth",thisMonth);
        result.put("lastMonth",lastMonth);
        result.put("maxMonth",maxMonth);
        result.put("minMonth",minMonth);
        result.put("total",total);
        return R.success(result);
    }
}
