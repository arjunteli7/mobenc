//package com.autofin.api.daoImpl;
//
//import java.sql.CallableStatement;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.persistence.EntityManager;
//import javax.transaction.Transactional;
//
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import org.hibernate.jdbc.Work;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import com.autofin.api.bean.MobileNumberEncrptDecrptBean;
//import com.autofin.api.dao.MobileNumberEncrptDecrptDao;
//
//import oracle.jdbc.OracleTypes;
//
//@Repository
//@Transactional
//public class MobileNumberEncrptDecrptDaoImpl implements MobileNumberEncrptDecrptDao{
//	static final Logger logger = LoggerFactory.getLogger(MobileNumberEncrptDecrptDaoImpl.class);
//
//	Session session = null;
//	Transaction transaction = null;
//	String errorSuccessMsg = null;
//	CallableStatement prepareCall = null;
//	Connection conn = null;
//	String mode = null;
//	ResultSet resultSet = null;
//	Map<String, Object> map = new HashMap<String, Object>();
//	List<Object[]> listObj = null;
//
//	public Session getSession() {
//		return session;
//	}
//
//	public void setSession(Session session) {
//		this.session = session;
//	}
//
//	@Autowired
//	private EntityManager entityManager;
//
//	@Override
//	public MobileNumberEncrptDecrptBean getSecretekey(MobileNumberEncrptDecrptBean mobileNumberEncrptDecrptBean) {
//		logger.info("Entry :: getSecretekey");
//		try {
//			session = entityManager.unwrap(Session.class);
//			session.doWork(new Work() {
//				public void execute(Connection connection) throws SQLException {
//					CallableStatement callableStatement = connection.prepareCall("{CALL Get_vend_sec_key(?,?)}");
//					callableStatement.setString(1, mobileNumberEncrptDecrptBean.getVendorCode());
//					callableStatement.registerOutParameter(2, OracleTypes.VARCHAR);
//					callableStatement.execute();
//					mobileNumberEncrptDecrptBean.setSecretekey(callableStatement.getObject(2).toString());
//				}
//			});
//
//		} catch (Exception e) {
//			logger.error(e.toString());
//		}
//		logger.info("Exit :: getSecretekey");
//		return mobileNumberEncrptDecrptBean;
//
//	}
//}	