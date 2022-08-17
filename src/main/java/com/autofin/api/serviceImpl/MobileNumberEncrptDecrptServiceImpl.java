package com.autofin.api.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autofin.api.bean.MobileNumberEncrptDecrptBean;
import com.autofin.api.dao.MobileNumberEncrptDecrptDao;
import com.autofin.api.service.MobileNumberEncrptDecrptService;

@Service
public class MobileNumberEncrptDecrptServiceImpl implements MobileNumberEncrptDecrptService{

	static final Logger logger = LoggerFactory.getLogger(MobileNumberEncrptDecrptServiceImpl.class);

//	@Autowired
//	MobileNumberEncrptDecrptDao mobileNumberEncrptDecrptDao;

	@Override
	public MobileNumberEncrptDecrptBean getSecretekey(MobileNumberEncrptDecrptBean mobileNumberEncrptDecrptBean) {
		return null;//mobileNumberEncrptDecrptDao.getSecretekey(mobileNumberEncrptDecrptBean);
}
}