/**
 * 
 */
package com.arjun.springhibernatejpa.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.UpdateTimestamp;

import com.manning.javapersistence.ch06.model.AuctionType;




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
    private LocalDate lastModified;
    
    @Column(insertable = false)
    @ColumnDefault("1.0")
    @Generated(
    		org.hibernate.annotations.GenerationTime.INSERT
    		)
    private BigDecimal initialPrice;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private AuctionType auctionType = AuctionType.HIGHEST_BID;
    
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
    
    public LocalDate getLastModified() {
    	return lastModified;
    }
    
    public AuctionType getAuctionType() {
        return auctionType;
    }

    public void setAuctionType(AuctionType auctionType) {
        this.auctionType = auctionType;
    }


}
