package com.mewsinsa.auth.jwt.redis.repository;

import com.mewsinsa.auth.jwt.redis.dto.RedisRefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RedisRefreshTokenRepository extends CrudRepository<RedisRefreshToken, String> {

}
