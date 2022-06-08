package com.arjun.codeseven.model;

import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.internal.constraintvalidators.bv.number.sign.NegativeOrZeroValidatorForBigDecimal;

@Embeddable
@AttributeOverride(name = "name", column = @Column(name = "DIMENSIONS_NAME"))
@AttributeOverride(name = "symbol", column = @Column(name = "DIMENSIONS_SYMBOL"))
public class Dimensions extends Measurement {

	public static Dimensions centimeters(BigDecimal height, BigDecimal width, BigDecimal depth) {
		
		return new Dimensions("centimeters", "cm", height, width, depth);
	}

	public static Dimensions inches(BigDecimal height, BigDecimal width, BigDecimal depth) {
		return new Dimensions("inches", "in", height, width, depth);
	}
	
    @NotNull
    private BigDecimal depth;

    @NotNull
    private BigDecimal height;

    @NotNull
    private BigDecimal width;
    
    public Dimensions() {
    	
    }
    
	public Dimensions(String dimension_name, String dimension_symbol, BigDecimal height, BigDecimal width, BigDecimal depth) {
		
		super(dimension_name, dimension_symbol);
		this.height = height;
		this.depth = depth;
		this.width = width;
		
	}

	public BigDecimal getDepth() {
		return depth;
	}

	public void setDepth(BigDecimal depth) {
		this.depth = depth;
	}

	public BigDecimal getHeight() {
		return height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	public BigDecimal getWidth() {
		return width;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
	}

}
