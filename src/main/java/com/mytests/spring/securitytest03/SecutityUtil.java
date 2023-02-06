package com.mytests.spring.securitytest03;

import org.springframework.security.core.Authentication;

public class SecutityUtil {
		public boolean checkUser(Authentication authentication, int id) {
				return true;
		}
}