package hotel.service.impl;

import hotel.dao.PaymentDao;
import hotel.model.AdvancedSearch;
import hotel.model.Payment;
import hotel.service.PaymentService;

import java.util.List;

public class PaymentServiceImpl implements PaymentService {

    private PaymentDao paymentDao;

    public PaymentServiceImpl(PaymentDao paymentDao){
        this.paymentDao = paymentDao;
    }

    @Override
    public List<Payment> getPaymentList() throws Exception {
        return paymentDao.getPaymentList();
    }

    @Override
    public boolean addPayment(Payment payment) throws Exception {
        return paymentDao.addPayment(payment);
    }

    @Override
    public Payment getPaymentById(Long paymentId) throws Exception {
        return paymentDao.getPaymentById(paymentId);
    }

    @Override
    public boolean updatePayment(Payment payment) throws Exception {
        return paymentDao.updatePayment(payment);
    }

    @Override
    public boolean deletePayment(Long paymentId) throws Exception {
        return paymentDao.deletePayment(paymentId);
    }

    @Override
    public List<Payment> advancedSearchPaymentData(AdvancedSearch advancedSearch) throws Exception {
        return paymentDao.advancedSearchPaymentData(advancedSearch);
    }
}
