package com.personal.service;

import com.personal.entity.Summary;
import com.personal.entity.Transaction;

public interface CashRegisterService {

    boolean init();

    boolean save(Transaction transaction);

    boolean change(Integer param);

    Transaction findLastTransaction();

    Summary findLastSummary();

}
