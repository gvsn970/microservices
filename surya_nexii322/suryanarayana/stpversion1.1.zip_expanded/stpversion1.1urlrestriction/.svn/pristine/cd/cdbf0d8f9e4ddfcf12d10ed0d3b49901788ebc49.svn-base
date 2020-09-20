package com.nexiilabs.stp.bench;

import java.util.List;

import com.nexiilabs.stp.user.UserResponseDTO;

public interface BenchRepository {
	List<BenchListResponseDTO> getBenchResourcesList(int expireIn, int id);

	UserResponseDTO expiredResourceReassign(int resourceId, int userId);
}
