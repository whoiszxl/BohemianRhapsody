package com.whoiszxl.wallet.service.impl;

import com.whoiszxl.base.enums.normal.SwitchStatusEnum;
import com.whoiszxl.base.utils.IdWorker;
import com.whoiszxl.base.utils.SpringContextUtil;
import com.whoiszxl.wallet.base.dao.UserAddressDao;
import com.whoiszxl.wallet.base.pojo.ZxlCurrency;
import com.whoiszxl.wallet.base.pojo.ZxlUserAddress;
import com.whoiszxl.wallet.base.pojo.walletvo.BtcRequest;
import com.whoiszxl.wallet.base.service.ZxlCurrencyService;
import com.whoiszxl.wallet.feign.BTCClient;
import com.whoiszxl.wallet.feign.WalletClient;
import com.whoiszxl.wallet.service.AddressDispenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @description: 地址分发代理服务实现
 * @author: whoiszxl
 * @create: 2019-09-15
 **/
@Service
public class AddressDispenseServiceImpl implements AddressDispenseService {

    @Autowired
    private ZxlCurrencyService zxlCurrencyService;

    @Autowired
    private UserAddressDao userAddressDao;

    @Autowired
    private IdWorker idWorker;

    @Override
    public ZxlUserAddress createOrGetAddress(BtcRequest btcRequest) {
        //获取参数
        Integer currencyId = btcRequest.getCurrencyId();
        String userId = btcRequest.getUserId();

        //判断地址是否已经生成，已经生成直接返回，未生成则调用feign创建获取
        ZxlUserAddress zxlUserAddress = userAddressDao.findByUserIdAndCurrencyIdAndStatus(userId, currencyId, SwitchStatusEnum.STATUS_OPEN.getStatusCode());
        if(zxlUserAddress != null) {
            return zxlUserAddress;
        }

        //未生成需要调用远程接口
        List<ZxlCurrency> allCurrencyList = zxlCurrencyService.getAllCurrencyList();
        for (ZxlCurrency zxlCurrency : allCurrencyList) {
            if(zxlCurrency.getId().equals(currencyId)) {
                //获取beanClient的名称与实例
                String beanClientName = SpringContextUtil.getWalletFeignName(zxlCurrency.getCurrencyName());
                WalletClient walletClient = (WalletClient) SpringContextUtil.getBean(beanClientName);

                //调用远程服务创建地址
                BtcRequest requestParam = BtcRequest.builder().userId(userId).build();
                String newAddress = walletClient.getNewAddress(requestParam);

                //保存到数据库
                ZxlUserAddress dbParams = ZxlUserAddress.builder()
                        .id(idWorker.nextId() + "")
                        .userId(userId)
                        .currencyId(currencyId)
                        .rechargeAddress(newAddress)
                        .status(SwitchStatusEnum.STATUS_OPEN.getStatusCode())
                        .updatedAt(LocalDateTime.now())
                        .createdAt(LocalDateTime.now())
                        .build();
                userAddressDao.save(dbParams);

                return ZxlUserAddress.builder().currencyId(currencyId).rechargeAddress(newAddress).status(SwitchStatusEnum.STATUS_OPEN.getStatusCode()).build();
            }
        }
        return null;
    }
}
