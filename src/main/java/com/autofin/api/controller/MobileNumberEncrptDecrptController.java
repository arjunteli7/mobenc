package com.autofin.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.autofin.api.bean.MobileNumberEncrptDecrptBean;
import com.autofin.api.custom.exception.AESEncrypterDecrypter;
import com.autofin.api.custom.exception.Base64EncoderDecoder;
import com.autofin.api.service.MobileNumberEncrptDecrptService;

@RestController
public class MobileNumberEncrptDecrptController {

		static final Logger logger = LoggerFactory.getLogger(MobileNumberEncrptDecrptController.class);

		@Autowired
		MobileNumberEncrptDecrptService mobileNumberEncrptDecrptService;

		@GetMapping(path = "/getKey", produces = "application/json")
		public String getKey() {
			String bytesKey = RandomStringUtils.randomAlphabetic(32);
			return bytesKey;
			
		}
		@PostMapping(path = "/mobileNumberEncrptDecrpt", consumes = "application/json", produces = "application/json")
		public ResponseEntity<Map<String, Object>> mobileNumberEncrptDecrpt(@RequestBody MobileNumberEncrptDecrptBean req) {
			logger.info("Entry :: cashierHandOverDetails");
			MobileNumberEncrptDecrptBean bean = new MobileNumberEncrptDecrptBean();
			Map<String, Object> dataResponse = new HashMap<>();
			Base64EncoderDecoder encoder = new Base64EncoderDecoder();
			
			try {
				//bean = mobileNumberEncrptDecrptService.getSecretekey(req);
				bean.setSecretekey("hosoxMgdsgoJxfKWOeQntHRuHifhQadv");
				if (req.getFlag().equals("E")) {
					byte[] encryptedString;
					try {
						encryptedString = AESEncrypterDecrypter.encrypt(req.getMobileNumber(),bean.getSecretekey().getBytes());
						
						String encData = new String(encoder.encode(encryptedString));
						dataResponse.put("Response", encData);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else {
					byte[] decryptedString;
					try {
						byte[] dcycData = encoder.decode(req.getMobileNumber().getBytes());
						decryptedString = AESEncrypterDecrypter.decrypt(dcycData,bean.getSecretekey().getBytes());
						String E_decode = new String(encoder.encode(decryptedString));
						byte[] E_decode1 = encoder.decode(E_decode.getBytes());
						dataResponse.put("Response", new String(E_decode1));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				logger.info("Exit :: mobileNumberEncrptDecrpt");
				return new ResponseEntity<Map<String, Object>>(dataResponse, HttpStatus.OK);
			} catch (Exception e) {
				logger.error(e.toString());
				return new ResponseEntity<Map<String, Object>>(dataResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}
}