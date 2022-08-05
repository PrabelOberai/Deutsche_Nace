package com.deutsche.nace.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
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
	
	@Column(name = "description", length = 1000)
	private String description;
	
	@Column(name = "this_item_includes", length = 1000)
	private String thisItemIncludes;
	
	@Column(name = "this_item_also_includes", length = 1000)
	private String thisItemAlsoIncludes;
	
	@Column(name = "rulings", length = 1000)
	private String rulings;
	
	@Column(name = "this_item_excludes", length = 1000)
	private String thisItemExcludes;
	
	@Column(name = "reference_to_isic_rev4")
	private String referenceToIsicRev4;

}
