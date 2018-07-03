package co.nz.equifax.task.services;

import org.springframework.stereotype.Service;

import co.nz.equifax.utils.Util;

@Service
public class BusinessServiceImpl implements BusinessService {

	@Override
	public String getScore() {
		long score=Long.valueOf(Util.getRandomKeyNumereic(3));
		if(score > 100)
			return String.valueOf(score); 
		else 
			return getScore();
	}
}
