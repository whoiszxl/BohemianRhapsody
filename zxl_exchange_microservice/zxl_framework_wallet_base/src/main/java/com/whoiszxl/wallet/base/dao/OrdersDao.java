package com.whoiszxl.wallet.base.dao;

import com.whoiszxl.wallet.base.pojo.ZxlOrders;
import com.whoiszxl.wallet.base.pojo.ZxlUserBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description: orders dao层
 * @author: whoiszxl
 * @create: 2019-08-16
 **/
public interface OrdersDao extends JpaRepository<ZxlOrders, String>,JpaSpecificationExecutor<ZxlOrders> {

}
