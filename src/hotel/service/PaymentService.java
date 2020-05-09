package hotel.service;

import hotel.model.AdvancedSearch;
import hotel.model.Payment;

import java.util.List;

public interface PaymentService {

    List<Payment> getPaymentList() throws Exception;

    boolean addPayment(Payment payment) throws Exception;

    Payment getPaymentById(Long paymentId) throws Exception;

    boolean updatePayment(Payment payment) throws Exception;

    boolean deletePayment(Long paymentId) throws Exception;

    List<Payment> advancedSearchPaymentData(AdvancedSearch advancedSearch) throws Exception;
}
