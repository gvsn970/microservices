package com.nexiilabs.stp.bench;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexiilabs.stp.user.UserResponseDTO;

@Service
public class BenchServiceImpl implements BenchService {
	@Autowired
	BenchRepository benchRepository;

	@Override
	public List<BenchListResponseDTO> getBenchResourcesList(int expireIn,int id) {
		return benchRepository.getBenchResourcesList(expireIn,id);
	}

	@Override
	public UserResponseDTO expiredResourceReassign(int resourceId, int userId) {
		return benchRepository.expiredResourceReassign(resourceId,userId);
	}
}
