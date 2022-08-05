package com.deutsche.nace.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "nace")
public class Nace {

	@Id
	@Column(name = "order_id", nullable = false)
	private String orderId;
	
	@Column(name = "level_id")
	private String level;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "parent")
	private String parent;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "this_item_includes")
	private String thisItemIncludes;
	
	@Column(name = "this_item_also_includes")
	private String thisItemAlsoIncludes;
	
	@Column(name = "rulings")
	private String rulings;
	
	@Column(name = "this_item_excludes")
	private String thisItemExcludes;
	
	@Column(name = "reference_to_isic_rev4")
	private String referenceToIsicRev4;

}
