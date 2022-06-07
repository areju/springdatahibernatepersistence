/**
 * 
 */
package com.arjun.springhibernatejpa.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import com.arjun.springhibernatejpa.converter.MonetaryAmountConverter;





/**
 * @author arjun
 *
 */
@Entity
public class Item {
	
    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    private Long id;
    
    @Access(AccessType.PROPERTY)
    @Column(name = "ITEM_NAME") // Mappings are still expected here!
    private String name;
    
    @OneToMany(mappedBy = "item",
            cascade = CascadeType.PERSIST,
            orphanRemoval = true) // Includes CascadeType.REMOVE
    private Set<Bid> bids = new HashSet<>();
    
    @NotNull
    @Basic(fetch = FetchType.LAZY) // Defaults to EAGER
    private String description;


    @Formula(
            "CONCAT(SUBSTR(DESCRIPTION, 1, 12), '...')"
    )
    private String shortDescription;

    @Formula(
            "(SELECT AVG(B.AMOUNT) FROM BID B WHERE B.ITEM_ID = ID)"
    )
    private BigDecimal averageBidAmount;

    @Column(name = "IMPERIALWEIGHT")
    @ColumnTransformer(
            read = "IMPERIALWEIGHT / 2.20462",
            write = "? * 2.20462"
    )
    private double metricWeight;
    
    @CreationTimestamp
    private LocalDate createdOn;
    
    @UpdateTimestamp
    private LocalDateTime lastModified;
    
    @NotNull
    @Type(
    		type = "monetary_amount_eur")
    @Columns(columns = {
    		@Column(name="INITIALPRICE_AMOUNT"),
    		@Column(name="INITTIALPRICE_CURRENCY", length = 3)
    })
    private MonetaryAmount initialPrice;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private AuctionType auctionType = AuctionType.HIGHEST_BID;
    
    @NotNull
    @Type(
    		type = "monetary_amount_usd"
    )
    @Columns( columns = {
    		@Column(name = "BUYNOWPRICE_AMOUNT"),
    		@Column(name = "BUYNOWPRICE_CURRENCY", length = 3)
    })
    private MonetaryAmount buyNowPrice;
    
    
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name =
                !name.startsWith("AUCTION: ") ? "AUCTION: " + name : name;
    }

    public Set<Bid> getBids() {
        return Collections.unmodifiableSet(bids);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getShortDescription() {
        return shortDescription;
    }

    public BigDecimal getAverageBidAmount() {
        return averageBidAmount;
    }

    public double getMetricWeight() {
        return metricWeight;
    }

    public void setMetricWeight(double metricWeight) {
        this.metricWeight = metricWeight;
    }
    
    public LocalDate getCreatedOn() {
        return createdOn;
    }
    
    public LocalDateTime getLastModified() {
    	return lastModified;
    }
    
    public AuctionType getAuctionType() {
        return auctionType;
    }

    public void setAuctionType(AuctionType auctionType) {
        this.auctionType = auctionType;
    }
    
    public MonetaryAmount getInitialPrice() {
    	return initialPrice;
    }
    
    public void setInitialPrice(MonetaryAmount initialPrice) {
    	this.initialPrice = initialPrice;
    }

	public MonetaryAmount getBuyNowPrice() {
		return buyNowPrice;
	}

	public void setBuyNowPrice(MonetaryAmount buyNowPrice) {
		this.buyNowPrice = buyNowPrice;
	}
    
}
