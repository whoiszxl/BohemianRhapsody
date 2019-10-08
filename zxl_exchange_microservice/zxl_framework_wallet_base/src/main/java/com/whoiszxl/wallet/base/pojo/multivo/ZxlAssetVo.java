package com.whoiszxl.wallet.base.pojo.multivo;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @description: 资产关联查询时用的vo
 * @author: whoiszxl
 * @create: 2019-10-08
 **/
@Data
@SecondaryTables({
        @SecondaryTable(name="zxl_currency",
                pkJoinColumns = @PrimaryKeyJoinColumn(name="id", referencedColumnName="id")
        ),
        @SecondaryTable(name="zxl_user_balance",
                pkJoinColumns = @PrimaryKeyJoinColumn(name="currency_id", referencedColumnName="currency_id")
        )}
)
public class ZxlAssetVo {

    private String currencyName;
    private BigDecimal currencyDecimalsNum;
    private BigDecimal currencyBuyFee;
    private BigDecimal currency_sell_fee;
    private String currency_url;

    private BigDecimal max_withdraw;
    private BigDecimal min_withdraw;
    private BigDecimal fee_withdraw;
    private BigDecimal all_balance;
    private BigDecimal lock_balance;
    private BigDecimal usable_balance;
}
