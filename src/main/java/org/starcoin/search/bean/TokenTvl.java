package org.starcoin.search.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;

public class TokenTvl extends TokenTvlAmount{

    @JSONField(name="total_reserve_in_usd")
    private BigDecimal tvl;

    public TokenTvl(String tokenName, BigDecimal tvlAmount,BigDecimal tvl) {
        super(tokenName,tvlAmount);
        this.tvl = tvl;
    }

    public BigDecimal getTvl() {
        return tvl;
    }

    public void setTvl(BigDecimal tvl) {
        this.tvl = tvl;
    }

    @Override
    public String toString() {
        return "TokenTvl{" +
                "tvl=" + tvl +
                "} " + super.toString();
    }
}
