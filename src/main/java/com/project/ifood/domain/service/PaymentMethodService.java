package com.project.ifood.domain.service;

import java.util.List;

import com.project.ifood.domain.model.PaymentMethod;

public interface PaymentMethodService {
	
	PaymentMethod save(PaymentMethod paymentMethod);
	PaymentMethod update(Long id, PaymentMethod paymentMethod);
	List<PaymentMethod> findAll();
	PaymentMethod findById(Long id);
    void deleteById(Long id);
    PaymentMethod checkIfPaymentMethodExists(Long id);
}
