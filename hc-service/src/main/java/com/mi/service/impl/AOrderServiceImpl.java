package com.mi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mi.converter.AppointOrderDtoConverter;
import com.mi.dto.OrderDTO;
import com.mi.dto.OrderOneDTO;
import com.mi.dto.OrderTwoDTO;
import com.mi.dto.OrderZeroDTO;
import com.mi.entity.AppointOrder;
import com.mi.entity.AppointOrderCount;
import com.mi.entity.AppointOrderDetail;
import com.mi.enums.OrderStatusEnum;
import com.mi.enums.ResultEnum;
import com.mi.excel.AOrderReportDataDTO;
import com.mi.excel.AOrderReportParam;
import com.mi.exception.HctException;
import com.mi.mapper.AOrderDetailMapper;
import com.mi.mapper.AOrderMapper;
import com.mi.service.AOrderService;
import com.mi.utils.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @author : Rong
 * @date : 2020/4/24
 * @Desc:
 */
@Slf4j
@Service
public class AOrderServiceImpl  implements AOrderService {



    @Autowired
    private AOrderMapper aOrderMapper;

    @Autowired
    private AOrderDetailMapper aOrderDetailMapper;

    @Override
    public AOrderReportDataDTO selectByAOderRerpotDataDTO(AOrderReportParam param) {
        AOrderReportDataDTO reportDataDTO = new AOrderReportDataDTO();
        // 1. 获取当前订单统计数据
        BeanUtils.copyProperties(param,reportDataDTO);
        // 2. 获取订单数据列表
        List<AppointOrder> orders = this.selectByAOderRerpotData(param.getOrderDate(),param.getTime());
        // 3. 拼装数据
        reportDataDTO.setAppoints(orders);
        return reportDataDTO;
    }

    //获取导出订单列表数据
    @Override
    public List<AppointOrder> selectByAOderRerpotData(String orderDate, String time) {
        Example example = new Example(AppointOrder.class);
        Example.Criteria criteria = example.createCriteria();
        // 1. 判断统计周、月
        if (!StringUtils.isEmpty(orderDate) && orderDate.length() > 0){
            log.info("orderDate = {}",orderDate);
            // 查询本周
            if (orderDate .equals("0")){
                log.info("查询本周");
                criteria.andCondition("WEEK(DATE_FORMAT(order_date,'%Y-%m-%d')) = WEEK(NOW())");
            }
            // 查询上周
            if (orderDate .equals("1")){
                log.info("查询上周");
                criteria.andCondition("WEEK(DATE_FORMAT(order_date,'%Y-%m-%d')) = WEEK(now())-1");
            }
            // 查询本月
            if (orderDate .equals("2")){
                log.info("查询本月");
                criteria.andCondition(" DATE_FORMAT( order_date, '%Y%m' ) = DATE_FORMAT( CURDATE( ) ,'%Y%m' )");
            }
            // 查询上月
            if (orderDate .equals("3")){
                log.info("查询上月");
                criteria.andCondition("PERIOD_DIFF( date_format( now( ),'%Y%m' ),date_format( order_date, '%Y%m' ) ) =1");
            }
        }
        // 2. 判断日期范围
        // 判断开始时间
        else   if (!StringUtils.isEmpty(time) && time.length() > 0) {
            log.info("查询时间范围");
            // 截图时间范围 time=2020-05-07 - 2020-06-03
            // 截取开始时间
            String theStartTime = time.substring(0,10);
            // 截取结束时间
            String theEndTime = time.substring(13,23);
            log.info("theStartTime = {}  theEndTime = {}",theStartTime,theEndTime);
            criteria.andCondition("date_format(order_date,'%Y-%m-%d')").andBetween("orderDate",theStartTime,theEndTime);
        }
        else {
            log.info("查询默认");
            criteria.andCondition("WEEK(DATE_FORMAT(order_date,'%Y-%m-%d')) = WEEK(NOW())");
        }
        // 按时间降序
        example.setOrderByClause("order_date ASC");
        List<AppointOrder> orders = aOrderMapper.selectByExample(example);
        return orders;
    }

    // 订单列表分页
    @Override
    public PageInfo<AppointOrder> selectTotalPage(Integer offset, Integer pageSize, String orderDate,String time) {
        PageHelper.startPage(offset,pageSize);
        Example example = new Example(AppointOrder.class);
        Example.Criteria criteria = example.createCriteria();
        // 1. 判断统计周、月
        if (!StringUtils.isEmpty(orderDate) && orderDate.length() > 0){
            log.info("orderDate = {}",orderDate);
            // 查询本周
            if (orderDate .equals("0")){
                log.info("查询本周");
                criteria.andCondition("WEEK(DATE_FORMAT(order_date,'%Y-%m-%d')) = WEEK(NOW())");
            }
            // 查询上周
            if (orderDate .equals("1")){
                log.info("查询上周");
                criteria.andCondition("WEEK(DATE_FORMAT(order_date,'%Y-%m-%d')) = WEEK(now())-1");
            }
            // 查询本月
            if (orderDate .equals("2")){
                log.info("查询本月");
                criteria.andCondition(" DATE_FORMAT( order_date, '%Y%m' ) = DATE_FORMAT( CURDATE( ) ,'%Y%m' )");
            }
            // 查询上月
            if (orderDate .equals("3")){
                log.info("查询上月");
                criteria.andCondition("PERIOD_DIFF( date_format( now( ),'%Y%m' ),date_format( order_date, '%Y%m' ) ) =1");
            }

        }
        // 2. 判断日期范围
        // 判断开始时间
     else   if (!StringUtils.isEmpty(time) && time.length() > 0) {
            log.info("查询时间范围");
            // 截图时间范围 time=2020-05-07 - 2020-06-03
            // 截取开始时间
            String theStartTime = time.substring(0,10);
            // 截取结束时间
            String theEndTime = time.substring(13,23);
            log.info("theStartTime = {}  theEndTime = {}",theStartTime,theEndTime);
            criteria.andCondition("date_format(order_date,'%Y-%m-%d')").andBetween("orderDate",theStartTime,theEndTime);
        }
        else {
            log.info("查询默认");
            criteria.andCondition("WEEK(DATE_FORMAT(order_date,'%Y-%m-%d')) = WEEK(NOW())");
        }
        // 按时间降序
        example.setOrderByClause("order_date ASC");
        List<AppointOrder>  orderList = aOrderMapper.selectByExample(example);
        PageInfo<AppointOrder> pageInfo = new PageInfo<>(orderList);
        return pageInfo;
    }

    @Override
    public OrderZeroDTO customerCount(String orderDate,String time) {
        // 声明开始时间和结束时间
        if (!StringUtils.isEmpty(time) && time.length() > 0) {
            log.info("查询时间范围");
            // 截图时间范围 time=2020-05-07 - 2020-06-03
            // 截取开始时间
         String    theStartTime = time.substring(0,10);
            // 截取结束时间
         String    theEndTime = time.substring(13,23);
            log.info("theStartTime = {}  theEndTime = {}",theStartTime,theEndTime);
            return aOrderMapper.customerCount(orderDate,theStartTime,theEndTime);
        }

        return aOrderMapper.customerCount(orderDate,null,null);
    }

    @Override
    public OrderOneDTO finishCount(String orderDate,String time) {

        if (!StringUtils.isEmpty(time) && time.length() > 0) {
            log.info("查询时间范围");
            // 截图时间范围 time=2020-05-07 - 2020-06-03
            // 截取开始时间
            String    theStartTime = time.substring(0,10);
            // 截取结束时间
            String    theEndTime = time.substring(13,23);
            log.info("theStartTime = {}  theEndTime = {}",theStartTime,theEndTime);
            return aOrderMapper.finishCount(orderDate,theStartTime,theEndTime);
        }

        return aOrderMapper.finishCount(orderDate,null,null);
    }

    @Override
    public OrderTwoDTO cancelCount(String orderDate,String time) {

        if (!StringUtils.isEmpty(time) && time.length() > 0) {
            log.info("查询时间范围");
            // 截图时间范围 time=2020-05-07 - 2020-06-03
            // 截取开始时间
            String    theStartTime = time.substring(0,10);
            // 截取结束时间
            String    theEndTime = time.substring(13,23);
            log.info("theStartTime = {}  theEndTime = {}",theStartTime,theEndTime);
            return aOrderMapper.cancelCount(orderDate,theStartTime,theEndTime);
        }

        return aOrderMapper.cancelCount(orderDate,null,null);
    }

    // 订单取消
    @Override
    public int cancelOrder(OrderDTO orderDTO) {
        // 判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[取消订单]订单状态不正确,orderId={}, orderStatus={}",
                    orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new HctException(ResultEnum.ORDER_OWNER_ERROR);
        }
        AppointOrder appointOrder = new AppointOrder();
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        orderDTO.setUpdateTime(TimeUtil.getNowDate(new java.util.Date()));
        BeanUtils.copyProperties(orderDTO,appointOrder);
        return aOrderMapper.updateByPrimaryKeySelective(appointOrder);
    }

    // 订单完成
    @Override
    public int finishOrder(OrderDTO orderDTO ) {
        // 完成订单操作
        // 判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[取消订单]订单状态不正确,orderId={}, orderStatus={}",
                    orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new HctException(ResultEnum.ORDER_OWNER_ERROR);
        }
        AppointOrder appointOrder = new AppointOrder();
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        orderDTO.setUpdateTime(TimeUtil.getNowDate(new java.util.Date()));
        BeanUtils.copyProperties(orderDTO,appointOrder);
        return aOrderMapper.updateByPrimaryKeySelective(appointOrder);
    }

    //删除
    @Override
    public int deleteByOne(String orderId) {
        return aOrderMapper.deleteByOne(orderId);
    }

    //分页
    @Override
    public PageInfo<OrderDTO> selectByPages(Integer offset, Integer pageSize, String nickName, Integer orderStatus) {
        PageHelper.startPage(offset,pageSize);
//        Example example = new Example(AppointOrder.class);
//        Example.Criteria criteria = example.createCriteria();
        // 1. 查询keywords 关键字
//        if (!StringUtils.isEmpty(nickName) && nickName.length() > 0) {
//            log.info("nickName = {}",nickName);
//            criteria.andLike("nickName","%"+nickName+"%");
//        }
//        // 2. 判断状态关键字
//        if (!StringUtils.isEmpty(orderStatus) && orderStatus != null) {
//            log.info("orderStatus = {}",orderStatus);
//            criteria.andLike("orderStatus","%"+orderStatus+"%");
//        }
        // 3. 开始分页
        List<AppointOrder> orders = aOrderMapper.selectByPages(nickName,orderStatus);
        PageInfo<AppointOrder> orderPageInfo =new PageInfo<>(orders);
        List<OrderDTO> orderDTOList = AppointOrderDtoConverter.convert(orderPageInfo.getList());
        PageInfo<OrderDTO> orderDTOPageInfo = new PageInfo<>(orderDTOList);
        return orderDTOPageInfo;
    }

    //批量删除
    @Override
    public int batchDelAOrder(List<String> orderIds) {
        return aOrderMapper.batchDelAOrder(orderIds);
    }


}