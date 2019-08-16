package com.whoiszxl.wallet.service;

import com.whoiszxl.wallet.pojo.ZxlCurrency;
import com.whoiszxl.wallet.pojo.request.PageRequest;

import java.util.List;

/**
 * <p>
 * 币种表 服务类
 * </p>
 *
 * @author whoiszxl
 * @since 2019-08-15
 */
public interface ZxlCurrencyService {

    /**
     * 通过开关状态查找币种列表
     * @param pageRequest
     * @return
     */
    List<ZxlCurrency> queryPageByStatus(PageRequest pageRequest);

}
