package com.arjun.springhibernatejpa.converter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Currency;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;
import org.hibernate.usertype.DynamicParameterizedType;

import com.arjun.springhibernatejpa.model.MonetaryAmount;

public class MonetaryAmountUserType implements CompositeUserType,DynamicParameterizedType{
	
	private Currency convertTo;
	

	@Override
	public void setParameterValues(Properties parameters) {
		
		String convertToParamenter = parameters.getProperty("convertTo");
		this.convertTo = Currency.getInstance(
				convertToParamenter != null ? convertToParamenter : "USD");
				
	}

	@Override
	public String[] getPropertyNames() {
		return new String[] {"value", "currency" };
	}

	@Override
	public Type[] getPropertyTypes() {
		return new Type[] { StandardBasicTypes.BIG_DECIMAL, StandardBasicTypes.CURRENCY};
	}

	@Override
	public Object getPropertyValue(Object component, int property) throws HibernateException {
		MonetaryAmount amount = (MonetaryAmount) component;
		if (property == 0) 
			return amount.getValue();
		else
			return amount.getCurrency();
	}

	@Override
	public void setPropertyValue(Object component, int property, Object value) throws HibernateException {
		throw new UnsupportedOperationException(
				"Monteary amount is immutable");
		
	}

	@Override
	public Class returnedClass() {
		return MonetaryAmount.class;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		return x == y || !(x == null  || y == null) || x.equals(y) ;
	}

	@Override
	public int hashCode(Object x) throws HibernateException {
		return x.hashCode();
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner)
			throws HibernateException, SQLException {
		
		BigDecimal amount = rs.getBigDecimal(names[0]);
		if(rs.wasNull())
			return null;
		Currency currency = Currency.getInstance(rs.getString(names[1]));
		
		return new MonetaryAmount(amount, currency);
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
			throws HibernateException, SQLException {
		
		if(value == null) {
			st.setNull(index, StandardBasicTypes.BIG_DECIMAL.sqlType());
			st.setNull(index + 1, StandardBasicTypes.CURRENCY.sqlType());
		}else {
			MonetaryAmount amount = (MonetaryAmount) value;
			MonetaryAmount dbamount = convert(amount,convertTo);
			st.setBigDecimal(index, dbamount.getValue());
			st.setString(index+1, convertTo.getCurrencyCode());
		}
		
	}
	
	public MonetaryAmount convert(MonetaryAmount amount, Currency currency) {
		return new MonetaryAmount(amount.getValue().multiply(new BigDecimal(2)), currency);
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Serializable disassemble(Object value, SharedSessionContractImplementor session) throws HibernateException {
		return value.toString();
	}

	@Override
	public Object assemble(Serializable cached, SharedSessionContractImplementor session, Object owner)
			throws HibernateException {
		return MonetaryAmount.fromString((String) cached);
	}

	@Override
	public Object replace(Object original, Object target, SharedSessionContractImplementor session, Object owner)
			throws HibernateException {
		return original;
	}

}
