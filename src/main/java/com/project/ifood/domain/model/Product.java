package com.project.ifood.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
@Entity
public class Product implements Serializable{
	private static final long serialVersionUID = 1524345699491374384L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String description;
	@Column(nullable = false)
	private BigDecimal price;
	@Column(nullable = false)
	private Boolean isActive;
	
	@Valid
	@NotNull(message = "Entidade restaurant é obrigatório!")
	//@JsonBackReference // https://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion
	@ManyToOne
	@JoinColumn(nullable = false)
	private Restaurant restaurant;
}