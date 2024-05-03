package com.mewsinsa.auth.jwt.redis.repository;

import com.mewsinsa.auth.jwt.redis.dto.RedisAccessToken;
import org.springframework.data.repository.CrudRepository;

// 아이디: 리프레시 토큰 value
public interface RedisAccessTokenRepository extends CrudRepository<RedisAccessToken, String> {

}
