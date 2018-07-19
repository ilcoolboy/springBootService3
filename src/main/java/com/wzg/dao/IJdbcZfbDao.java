package com.wzg.dao;

import java.math.BigDecimal;
import java.util.List;

public interface IJdbcZfbDao {
	void saveZfb(final String name, final BigDecimal account);
	List getAll();
}
