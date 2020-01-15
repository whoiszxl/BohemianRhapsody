package com.whoiszxl.usercenter.service.impl;

import com.whoiszxl.core.constant.RedisKeyNameConstant;
import com.whoiszxl.core.dao.MemberAddressDao;
import com.whoiszxl.core.entity.MemberAddress;
import com.whoiszxl.core.entity.TradeCoin;
import com.whoiszxl.core.entity.base.Result;
import com.whoiszxl.core.enums.normal.SwitchStatusEnum;
import com.whoiszxl.core.utils.DateUtil;
import com.whoiszxl.core.utils.RedisUtils;
import com.whoiszxl.usercenter.service.AddressDispenseService;
import com.whoiszxl.usercenter.service.TradeCoinService;
import com.whoiszxl.xwallet.core.client.WalletFeignClientService;
import com.whoiszxl.xwallet.core.entity.btc.WalletRequest;
import com.whoiszxl.xwallet.core.entity.common.FeignGetAddressRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @description: 地址分发代理服务实现
 * @author: whoiszxl
 * @create: 2020-01-14
 **/
@Slf4j
@Service
public class AddressDispenseServiceImpl implements AddressDispenseService {

    @Autowired
    private TradeCoinService tradeCoinService;

    @Autowired
    private MemberAddressDao memberAddressDao;

    @Autowired
    private WalletFeignClientService walletFeignClientService;

    @Autowired
    private RedisUtils redisUtils;


    @Override
    public MemberAddress createOrGetAddress(WalletRequest btcRequest) {
        //获取参数
        Long coinId = btcRequest.getCoinId();
        Long memberId = btcRequest.getMemberId();

        //判断地址是否已经生成，已经生成直接返回，未生成则调用feign创建获取
        MemberAddress memberAddress = memberAddressDao.findByMemberIdAndCoinIdAndStatus(memberId, coinId, SwitchStatusEnum.STATUS_OPEN.getStatusCode());
        if(memberAddress != null) {
            return memberAddress;
        }

        //未生成需要调用远程接口
        List<TradeCoin> allCoinList = tradeCoinService.getAllCoinList();
        for (TradeCoin tradeCoin : allCoinList) {
            if(tradeCoin.getId().equals(coinId)) {
                //获取beanClient的名称与实例
                String createAddressUri = (String) redisUtils.hGet(RedisKeyNameConstant.WALLET_IPADDR, tradeCoin.getCoinName());
                createAddressUri = createAddressUri + "/" + tradeCoin.getCoinName().toLowerCase() + "/getNewAddress";
                Result<String> newAddress = null;
                try {
                    newAddress = walletFeignClientService.getNewAddress(new URI(createAddressUri), new FeignGetAddressRequest());
                } catch (URISyntaxException e) {
                    log.error("远程创建地址失败:", e);
                }

                if(newAddress != null && newAddress.getCode() == 0) {
                    //保存到数据库
                    MemberAddress dbParams = MemberAddress.builder()
                            .memberId(memberId)
                            .coinId(coinId)
                            .rechargeAddress(newAddress.getData())
                            .status(SwitchStatusEnum.STATUS_OPEN.getStatusCode())
                            .updatedAt(DateUtil.getCurrentDate())
                            .createdAt(DateUtil.getCurrentDate())
                            .build();
                    memberAddressDao.save(dbParams);
                    return MemberAddress.builder().coinId(coinId).rechargeAddress(newAddress.getData()).status(SwitchStatusEnum.STATUS_OPEN.getStatusCode()).build();
                }
            }
        }
        return null;
    }

}
