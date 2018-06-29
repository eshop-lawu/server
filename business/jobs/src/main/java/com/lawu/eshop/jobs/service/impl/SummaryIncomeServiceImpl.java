package com.lawu.eshop.jobs.service.impl;

import com.lawu.eshop.jobs.service.PropertyTransactionDetailService;
import com.lawu.eshop.jobs.service.SummaryIncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SummaryIncomeServiceImpl implements SummaryIncomeService {

    @Autowired
    private PropertyTransactionDetailService propertyTransactionDetailService;

    @Override
    public void execute() {

        propertyTransactionDetailService.summaryIncome();

    }
}
