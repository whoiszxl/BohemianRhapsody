package com.whoiszxl.wallet.base.dao;

import com.whoiszxl.wallet.base.pojo.ZxlTransactions;
import com.whoiszxl.wallet.base.pojo.ZxlUserBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description: transactions dao层
 * @author: whoiszxl
 * @create: 2019-08-16
 **/
public interface TransactionsDao extends JpaRepository<ZxlTransactions, String>,JpaSpecificationExecutor<ZxlTransactions> {

}
