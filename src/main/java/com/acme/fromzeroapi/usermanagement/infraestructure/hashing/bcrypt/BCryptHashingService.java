package com.acme.fromzeroapi.usermanagement.infraestructure.hashing.bcrypt;

import com.acme.fromzeroapi.usermanagement.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends HashingService, PasswordEncoder {
}
