package com.lonar.vendor.vendorportal.dao;
 
import java.util.List;
 
import javax.sql.DataSource;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
 
import com.lonar.vendor.vendorportal.model.LtPoLineTaxes;
import com.lonar.vendor.vendorportal.model.ServiceException;
 
@Repository
@PropertySource(value = "classpath:queries/poLineQueries.properties", ignoreResourceNotFound = true)
public class LtMastPoLinesTaxesDaoImpl implements LtMastPoLinesTaxesDao {
	@Autowired
	private Environment env;
	private JdbcTemplate jdbcTemplate;
	private JdbcTemplate getJdbcTemplate() {
		// TODO Auto-generated method stub
		return jdbcTemplate;
	}
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
 
//	@Override
//	public List<LtPoLineTaxes> getAllPoLinesByLineId(Long id) throws ServiceException {
//		String query = env.getProperty("getAllPoLinesByTaxesLineId");
//		List<LtPoLineTaxes> list=   jdbcTemplate.query(query, new Object[]{id }, 
//				 new BeanPropertyRowMapper<LtPoLineTaxes>(LtPoLineTaxes.class)); 
//		return list;
//	}
	
	@Override
	public List<LtPoLineTaxes> getAllPoLinesByLineId(Long id) throws ServiceException {
		String query = env.getProperty("getAllPoLinesByTaxesLineId");
		List<LtPoLineTaxes> list=   jdbcTemplate.query(query, new Object[]{id,id }, 
				 new BeanPropertyRowMapper<LtPoLineTaxes>(LtPoLineTaxes.class)); 
		return list;
	}
	
	@Override
	public void deleteTax(Long poLineId) {
		String query = " DELETE * FROM LT_PO_LINE_TAXES WHERE PO_LINE_ID = ? ";
		int res=jdbcTemplate.update(query,poLineId);
 
		/*if(res!= 0)
			return true;
		else
			return false;*/
		
	}
 
}