package com.gggw.service.counter.kospimpl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.gggw.result.SisapResult;
import com.gggw.service.counter.AbstractKospService;
import com.gggw.service.counter.service.CounterService0002;

/**
 * ClassName:KospCRHMALL0002Impl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date:     2016-9-27 下午3:47:33 <br/>
 * @author   cgw
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Service
public class KospCRHMALL0002Impl extends AbstractKospService implements
	CounterService0002 {

	@Override
	public SisapResult excute(String userName, Map<String, Object> params) {

		System.out.println("=======================KospCRHMALL0002Impl :  excute     " + userName);
		return null;
	}

}

