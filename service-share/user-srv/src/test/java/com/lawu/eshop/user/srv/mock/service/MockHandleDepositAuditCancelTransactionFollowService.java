package com.lawu.eshop.user.srv.mock.service;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.service.TransactionFollowService;

/**
 * @author meishuquan
 * @date 2017/7/12
 */
@Service
public class MockHandleDepositAuditCancelTransactionFollowService implements TransactionFollowService {


    @Override
    public void receiveNotice(Object notification, long storeTimestamp) {

    }

    @Override
    public void sendCallback(Object reply) {

    }

    public void executeNotice(Object notification) {
        // TODO Auto-generated method stub
        
    }
}
