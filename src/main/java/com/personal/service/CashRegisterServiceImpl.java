package com.personal.service;

import static com.personal.util.Constant.*;

import com.personal.entity.Summary;
import com.personal.entity.Transaction;
import com.personal.repository.SummaryRepository;
import com.personal.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashRegisterServiceImpl implements CashRegisterService {

    private static final Logger logger = LoggerFactory.getLogger(CashRegisterServiceImpl.class);

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private SummaryRepository summaryRepository;

    @Override
    public boolean init() {
        List<Transaction> transactionList = transactionRepository.findAll();
        for (Transaction t : transactionList) {
            if (t.getId() != 1) {
                transactionRepository.delete(t);
            }
        }

        List<Summary> summaryList = summaryRepository.findAll();
        for (Summary s : summaryList) {
            if (s.getId() != 1) {
                summaryRepository.delete(s);
            }
        }

        return (findLastSummary().getId() == 1 & findLastTransaction().getId() == 1);
    }

    @Override
    public boolean save(Transaction transaction) {
        Summary s = null;
        Summary last = findLastSummary();
        if (transaction != null) {
            if (transactionRepository.save(transaction) != null) {
                if (transaction.getType().equals(PUT)) {
                    int one = last.getCountOne() + transaction.getOne();
                    int two = last.getCountTwo() + transaction.getTwo();
                    int five = last.getCountFive() + transaction.getFive();
                    int ten = last.getCountTen() + transaction.getTen();
                    int twenty = last.getCountTwenty() + transaction.getTwenty();
                    s = new Summary.Builder()
                            .countOne(one)
                            .countTwo(two)
                            .countFive(five)
                            .countTen(ten)
                            .countTwenty(twenty)
                            .valueOne(one * ONES)
                            .valueTwo(two * TWOS)
                            .valueFive(five * FIVES)
                            .valueTen(ten * TENS)
                            .valueTwenty(twenty * TWENTIES)
                            .build();
                } else if (transaction.getType().equals(TAKE)) {
                    int one =java.lang.Math.abs(last.getCountOne() - transaction.getOne());
                    int two = java.lang.Math.abs(last.getCountTwo() - transaction.getTwo());
                    int five = java.lang.Math.abs(last.getCountFive() - transaction.getFive());
                    int ten = java.lang.Math.abs(last.getCountTen() - transaction.getTen());
                    int twenty = java.lang.Math.abs(last.getCountTwenty() - transaction.getTwenty());
                    s = new Summary.Builder()
                            .countOne(one)
                            .countTwo(two)
                            .countFive(five)
                            .countTen(ten)
                            .countTwenty(twenty)
                            .valueOne(one * ONES)
                            .valueTwo(two * TWOS)
                            .valueFive(five * FIVES)
                            .valueTen(ten * TENS)
                            .valueTwenty(twenty * TWENTIES)
                            .build();
                } else {
                    return false;
                }

                s.setValueSum(s.getValueOne() + s.getValueTwo() + s.getValueFive() + s.getValueTen() + s.getValueTwenty());
            }
            return summaryRepository.save(s) != null;
        } else {
            return false;
        }
    }

    @Override
    public boolean change(Integer param) {
        Transaction t;
        t = firstOption(param);
        if (t == null) {
            t = secondOption(param);
        }
        return t!=null && save(t);
    }

    private Transaction firstOption(Integer param) {
        int twenties = 0, tens = 0, fives = 0, twos = 0, ones = 0;
        Transaction t = null;
        Summary s = findLastSummary();

        if (s.getCountTwenty() > 0) {
            if (s.getValueTwenty() <= param || param % TWENTIES == 0)
                for (int i = 0; i < s.getCountTwenty(); i++) {
                    if (param > 0) {
                        twenties += 1;
                        param -= TWENTIES;
                    } else {
                        break;
                    }
                }
        }

        if (s.getCountTen() > 0 && param > 0) {
            if (s.getValueTen() <= param || param % TENS == 0)
                for (int i = 0; i < s.getCountTen(); i++) {
                    tens += 1;
                    param -= TENS;
                }
        }

        if (s.getCountFive() > 0 && param > 0) {
            if (s.getValueFive() <= param || param % FIVES == 0)
                for (int i = 0; i < s.getCountFive(); i++) {
                    fives += 1;
                    param -= FIVES;
                }
        }

        if (s.getCountTwo() > 0 && param > 0) {
            if (s.getValueTwo() <= param || param % TWOS == 0)
                for (int i = 0; i < s.getCountTwo(); i++) {
                    twos += 1;
                    param -= TWOS;
                }
        }

        if (s.getCountOne() > 0 && param > 0) {
            if (s.getValueOne() <= param || param % ONES == 0)
                for (int i = 0; i < s.getCountOne(); i++) {
                    ones += 1;
                    param -= ONES;
                }
        }

        if (param == 0) {
            t = new Transaction.Builder().type("take").one(ones).two(twos).five(fives).ten(tens).twenty(twenties).build();
        }
        return t;
    }

    private Transaction secondOption(Integer param) {
        Transaction t = null;
        Summary s = findLastSummary();
        int[] counts = { s.getCountTwenty(), s.getCountTen(), s.getCountFive(), s.getCountTwo(), s.getCountOne() };
        int[] orig = counts.clone();
        int[] values = { s.getValueTwenty(), s.getValueTen(), s.getValueFive(), s.getValueTwo(), s.getValueOne() };
        int[] denominations = { TWENTIES, TENS, FIVES, TWOS, ONES };
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < counts.length; j++) {
                if (counts[j] > 0) {
                    if (values[j] <= param || param % denominations[j] == 0) {
                        counts[j] -= 1;
                        param -= denominations[j];
                        values[j] = denominations[j] * counts[j];
                    }
                }

                if (param <= 0) {
                    break;
                }
            }

            if (param == 0) {
                for (int k = 0; k < counts.length; k++) {
                    orig[k] -= counts[k];
                }

                t = new Transaction.Builder().type("take").one(orig[4]).two(orig[3]).five(orig[2]).ten(orig[1]).twenty(orig[0]).build();
                break;
            } else if (param > 0 & i == 19) {
                break;
            }
        }
        return t;
    }

    @Override
    public Transaction findLastTransaction() {
        List<Transaction> list = transactionRepository.findAll(new Sort(Sort.Direction.DESC, "id"));
        if (list.size() > 0) return list.get(0);
        return null;
    }

    @Override
    public Summary findLastSummary() {
        List<Summary> list = summaryRepository.findAll(new Sort(Sort.Direction.DESC, "id"));
        if (list.size() > 0) return list.get(0);
        return null;
    }

}
