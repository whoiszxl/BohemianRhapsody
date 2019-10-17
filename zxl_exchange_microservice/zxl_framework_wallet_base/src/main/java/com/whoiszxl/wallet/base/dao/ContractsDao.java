package com.whoiszxl.wallet.base.dao;

import com.whoiszxl.wallet.base.pojo.ZxlContracts;
import com.whoiszxl.wallet.base.pojo.ZxlUserBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description: contracts dao层
 * @author: whoiszxl
 * @create: 2019-08-16
 **/
public interface ContractsDao extends JpaRepository<ZxlContracts, String>,JpaSpecificationExecutor<ZxlContracts> {

}
