package com.project.ifood.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.AbstractAggregateRoot;

import com.project.ifood.domain.enums.OrderStatus;
import com.project.ifood.domain.event.OrderConfirmedEvent;
import com.project.ifood.domain.service.exception.BadRequestExcertpionService;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper=false)
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "tb_order")
public class Order extends AbstractAggregateRoot<Order> implements Serializable {
	private static final long serialVersionUID = -4564893146129034049L;

	@Id @EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String code;
	
	@PrePersist
	private void setCode() {
		setCode(UUID.randomUUID().toString());
	}
	
	private BigDecimal subtotal;
	private BigDecimal freightRate;
	private BigDecimal totalAmount;

	@Embedded
	private Address deliveryAddress;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status = OrderStatus.CREATED;
	
	@CreationTimestamp
	private OffsetDateTime createAt;
	private OffsetDateTime confirmationAt;
	private OffsetDateTime cancellationAt;
	private OffsetDateTime deliveryAt;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private PaymentMethod paymentMethod;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Restaurant restaurant;
	
	@ManyToOne
	@JoinColumn(name = "user_client_id", nullable = false)
	private Customer customer;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> items = new ArrayList<>();
	
	public void calculateTotalPrice() {
		items.forEach(OrderItem::calculateTotalPrice);
		
		this.subtotal = items.stream()
				.map(item -> item.getPriceTotal())
				.reduce(BigDecimal.ZERO,BigDecimal::add);
		
		this.totalAmount = this.subtotal.add(freightRate);
	}
	
	
	public void create() {
		setStatus(OrderStatus.CREATED);
		setCreateAt(OffsetDateTime.now());
	}
	
	public void confirm() {
		setStatus(OrderStatus.CONFIRMED);
		setConfirmationAt(OffsetDateTime.now());
		
		registerEvent(new OrderConfirmedEvent(this));
	}
	
	public void delivery() {
		setStatus(OrderStatus.DELIVERED);
		setDeliveryAt(OffsetDateTime.now());
	}
	
	public void cancel() {
		setStatus(OrderStatus.CANCELLED);
		setCancellationAt(OffsetDateTime.now());
	}
	
	private void setStatus(OrderStatus newStatus) {
		
		if((getStatus().cannotChangeTo(newStatus))) {
			throw new BadRequestExcertpionService(
					String.format("Status do pedido %d não pode ser alterado de %s para %s",
							this.id, getStatus().getDescription(), newStatus.getDescription()));
		}	
		
		this.status = newStatus;
	}

}
