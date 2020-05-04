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

    // 订单列表分页
    @Override
    public PageInfo<AppointOrder> selectTotalPage(Integer offset, Integer pageSize, String orderDate) {
        PageHelper.startPage(offset,pageSize);
        Example example = new Example(AppointOrder.class);
        Example.Criteria criteria = example.createCriteria();
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
        }else {
            criteria.andCondition("WEEK(DATE_FORMAT(order_date,'%Y-%m-%d')) = WEEK(NOW())");
        }
        List<AppointOrder>  orderList = aOrderMapper.selectByExample(example);
        PageInfo<AppointOrder> pageInfo = new PageInfo<>(orderList);
        return pageInfo;
    }

    @Override
    public OrderZeroDTO customerCount(String orderDate) {
        return aOrderMapper.customerCount(orderDate);
    }

    @Override
    public OrderOneDTO finishCount(String orderDate) {
        return aOrderMapper.finishCount(orderDate);
    }

    @Override
    public OrderTwoDTO cancelCount(String orderDate) {
        return aOrderMapper.cancelCount(orderDate);
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