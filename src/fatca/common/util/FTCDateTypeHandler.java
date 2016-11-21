package fatca.common.util;

import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;

/**
 * @File Name    : DateTypeHandler.java
 * @Package Name : fatca.common.util;
 * @author       : 
 * @Description  : 날짜 형태 조작
 * @History      : 
 */
public class FTCDateTypeHandler implements TypeHandlerCallback {

	/**
	 * Object setParameter
	 * 
	 * @param setter
	 * @param parameter
	 * @throws SQLException
	 */
	public void setParameter(ParameterSetter setter, Object parameter)
			throws SQLException {
		if (parameter == null) {
			setter.setNull(Types.BIGINT);
		} else {
			Date datetime = (Date) parameter;
			setter.setTimestamp(new java.sql.Timestamp(datetime.getTime()));
			//setter.setLong(datetime.getTime());
		}
	}

	/**
	 * Object getResult
	 * 
	 * @param getter
	 * @return str
	 * @throws SQLException
	 */
	public Object getResult(ResultGetter getter) throws SQLException {
		long millis = getter.getLong();
		if (getter.wasNull()) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh24:mm:ss");
		return sdf.format(new Date(millis));
	}

	/**
	 * Object valueOf
	 * 
	 * @param s
	 * @return str
	 */
	public Object valueOf(String s) {
		return s;
	}
}
