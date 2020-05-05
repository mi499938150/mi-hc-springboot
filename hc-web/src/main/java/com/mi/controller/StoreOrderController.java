package com.mi.controller;

import com.github.pagehelper.PageInfo;
import com.mi.converter.OrderVoConverter;
import com.mi.dto.OrderDTO;
import com.mi.dto.OrderOneDTO;
import com.mi.dto.OrderTwoDTO;
import com.mi.dto.OrderZeroDTO;
import com.mi.entity.AppointOrder;
import com.mi.entity.AppointOrderCount;
import com.mi.excel.AOrderReportDataDTO;
import com.mi.excel.AOrderReportParam;
import com.mi.form.AppointOrderForm;
import com.mi.mapper.AOrderMapper;
import com.mi.service.AOrderService;
import com.mi.utils.ResponseVoUtil;
import com.mi.utils.TimeUtil;
import com.mi.vo.CommonResponse;
import com.mi.vo.OrderVo;
import lombok.extern.slf4j.Slf4j;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.Time;
import java.util.*;

/**
 * @author : Rong
 * @date : 2020/4/24
 * @Desc: 订单控制器
 */
@RestController
@RequestMapping("/aOrder")
@Slf4j
public class StoreOrderController {

    @Autowired
    private AOrderService aOrderService;

    @Autowired
    private AOrderMapper aOrderMapper;


    @ResponseBody
    @RequestMapping(value = "importTheOrderData",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public void  importTheOrderData( AOrderReportParam param,HttpServletResponse response) throws Exception {
        log.info("使用importTheOrderData");
        log.info("param = {}",param);
        Map<String, Object> beans = new HashMap<String, Object>();
        AOrderReportDataDTO orderReportDataDTO = aOrderService.selectByAOderRerpotDataDTO(param);
        File template  = ResourceUtils.getFile("classpath:templates/excel/order_report_template.xlsx");
        String uniqueDate = TimeUtil.dataformat3(new Date());
        beans.put("results",orderReportDataDTO);
        XLSTransformer transformer = new XLSTransformer();
        String distFile = System.getProperty("java.io.tmpdir") + "/orderReport_"+uniqueDate+".xlsx";
        log.warn(" path = {} , benas = {} , distFile = {}",template.getPath(),beans,distFile);
        log.info(" beansGet = {}",beans.get("results"));
        transformer.transformXLS(template.getPath(),beans,distFile);
        response.setContentType("application/xls");
        response.setCharacterEncoding("UTF-8");
        String contentDisposition = "attachment;filename*=UTF-8''"+ URLEncoder.encode("订单统计记录表.xlsx", "UTF-8");
        response.setHeader("Content-Disposition", contentDisposition);
        StreamUtils.copy(new FileInputStream(new File(distFile)), response.getOutputStream());

    }

    /**
     * 订单分页
     * @param offset
     * @param size
     * @param orderDate
     * @param orderDate
     * @return
     */
    @GetMapping(value = "/selectTotalPages")
    public CommonResponse selectTotalPages(@RequestParam(value = "page",defaultValue = "0")Integer offset,
                                           @RequestParam(value = "size",defaultValue = "10")Integer size,
            @RequestParam(value = "orderDate",required = false)String orderDate,
                                           @RequestParam(value = "time",required = false)String time){
        log.info("【使用selectTotalPages】");
        log.info(" orderDate = {}  time={}" ,orderDate, time);
        PageInfo<AppointOrder> pageInfo = aOrderService.selectTotalPage(offset,size,orderDate,time);
        return ResponseVoUtil.success(pageInfo);
    }


    /**
     * 统计所有订单金额
     * @param orderDate
     * @return
     */
    @GetMapping(value = "/selectOrderCount")
    public CommonResponse selectOrderCount(@RequestParam(value = "orderDate",required = false)String orderDate,
                                           @RequestParam(value = "time",required = false)String time){
        log.info("【使用selectOrderCount】");
        log.info("  orderDate = {} time = {} ",orderDate,time);
        // 1. 获取订单 为 0 的客户总数量
        OrderZeroDTO zeroDTO = aOrderService.customerCount(orderDate,time);
//        log.info("[客户总数据量] = {}",zeroDTO.getCutomerCount());
        // 2. 获取订单为 1 的 完成订单数据和总金额
        OrderOneDTO oneDTO = aOrderService.finishCount(orderDate,time);
//        log.info("[完成订单总数据量] = {} [完成订单总金额] = {}",oneDTO.getFinishCount(),oneDTO.getFinishAmount());
        // 3. 获取订单为 2 的 取消订单数据和总金额
        OrderTwoDTO twoDTO = aOrderService.cancelCount(orderDate,time);
//        log.info("[取消订单总数据量] = {} [取消订单总金额] = {}",twoDTO.getCancelCount(),twoDTO.getCancelAmount());
        // 4. 进行数据转化拼接
        OrderVo orderVo = OrderVoConverter.convert(zeroDTO,oneDTO,twoDTO);
//        log.info("[orderVo] = {} ",orderVo);
        if (orderVo !=null){
            return  ResponseVoUtil.success(orderVo);
        }
        return  ResponseVoUtil.success();
    }


    /**
     * 订单分页
     * @param offset
     * @param size
     * @param nickName
     * @param orderStatus
     * @return
     */
    @GetMapping(value = "pagesAOrders")
    public CommonResponse pagesAOrders (@RequestParam(value = "page",defaultValue = "0")Integer offset,
                                        @RequestParam(value = "size",defaultValue = "10")Integer size
                                        ,@RequestParam(value = "nickName",required = false) String nickName
                                        ,@RequestParam(value = "orderStatus",required = false) Integer orderStatus){
        log.info("【使用pagesAOrders】");
        log.info(" nickName = {} , orderStatus = {}",nickName,orderStatus);
        PageInfo<OrderDTO> orderDTOPageInfo = aOrderService.selectByPages(offset,size,nickName,orderStatus);
        return ResponseVoUtil.success(orderDTOPageInfo);
    }

    /**
     * 删除一个订单
     * @param orderId
     * @return
     */
    @DeleteMapping(value ="/deleteOrder/{id}")
    public CommonResponse deleteOrder(@PathVariable("id") String orderId){
        log.info("【使用deleteOrder】 = {}",orderId);
        int isDelete = aOrderService.deleteByOne(orderId);
        return ResponseVoUtil.success(isDelete);
    }

    @PostMapping(value = "/batchDelAOrder/{orderIds}")
    public CommonResponse batchDelAOrder(@PathVariable("orderIds") String orderIds){
        log.info("【使用batchDelAOrder】 = {}",orderIds);
        List<String> idsList = new ArrayList<String>();
        String [] strIds = orderIds.split(",");
        for (String str : strIds){
            idsList.add(str);
        }
        log.info("idsList = {}",idsList);

        int isBatch = aOrderService.batchDelAOrder(idsList);
        return ResponseVoUtil.success(isBatch);
    }


    @PostMapping(value = "/finishOrder")
    public CommonResponse finishOrder(@RequestBody OrderDTO orderDTO){
        log.info("【使用finishOrder】 = {}",orderDTO);
        int isFinish = aOrderService.finishOrder(orderDTO);
        return ResponseVoUtil.success(isFinish);
    }

    @PostMapping(value = "/cancelOrder")
    public CommonResponse cancelOrder(@RequestBody OrderDTO orderDTO){
        log.info("【使用cancelOrder】 = {}",orderDTO);
        int isCancel = aOrderService.cancelOrder(orderDTO);
        return ResponseVoUtil.success(isCancel);
    }
}