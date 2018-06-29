package co.nz.equifax.task.services;

import org.springframework.stereotype.Service;

import co.nz.equifax.utils.Util;

@Service
public class BusinessServiceImpl implements BusinessService {

	@Override
	public String getScore() {
		return Util.getRandomKeyNumereic(3);
	}
}
