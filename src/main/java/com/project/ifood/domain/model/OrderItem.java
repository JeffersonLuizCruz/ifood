package com.project.ifood.domain.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class OrderItem {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private BigDecimal unitPrice;
	private BigDecimal priceTotal;
	private Integer quantity;
	private String observation;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Order order;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Product product;

	public void calculateTotalPrice() {
		BigDecimal unitPrice = this.getUnitPrice();
		Integer quantity = this.getQuantity();

		if (unitPrice == null) {
			unitPrice = BigDecimal.ZERO;
		}

		if (quantity == null) {
			quantity = 0;
		}

		this.setPriceTotal(unitPrice.multiply(new BigDecimal(quantity)));
	}
}
