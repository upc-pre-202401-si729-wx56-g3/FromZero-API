package com.acme.fromzeroapi.auth.infraestructure.hashing.bcrypt;

import com.acme.fromzeroapi.auth.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends HashingService, PasswordEncoder {
}
