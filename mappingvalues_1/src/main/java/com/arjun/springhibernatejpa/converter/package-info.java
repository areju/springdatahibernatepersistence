
@org.hibernate.annotations.TypeDefs({
	
	@org.hibernate.annotations.TypeDef(
			name = "monetary_amount_usd",
			typeClass = MonetaryAmountUserType.class,
			parameters = {@org.hibernate.annotations.Parameter(name = "convertTo", value = "USD")}),
	@org.hibernate.annotations.TypeDef(
			name = "monetary_amount_eur",
			typeClass = com.arjun.springhibernatejpa.converter.MonetaryAmountUserType.class,
			parameters = {@org.hibernate.annotations.Parameter(name = "convertTo", value= "EUR")})
})
package com.arjun.springhibernatejpa.converter;

