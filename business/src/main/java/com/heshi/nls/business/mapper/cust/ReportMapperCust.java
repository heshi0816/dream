package com.heshi.nls.business.mapper.cust;

import java.math.BigDecimal;

public interface ReportMapperCust {

    Integer queryOnlineCount();


    Integer queryRegisterCount();

    Integer queryFiletransCount();

    Integer queryFiletransSecond();

    Integer queryOrderCount();

    BigDecimal queryOrderAmount();
}
