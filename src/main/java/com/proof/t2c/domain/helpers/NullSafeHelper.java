package com.proof.t2c.domain.helpers;

import java.util.function.Supplier;

public class NullSafeHelper {

	public static <T> T optional(Supplier<T> statement, T valueIfNull) {
		try {
			if (statement.get() != null) {
				return statement.get();
			}
			return valueIfNull;
		} catch (NullPointerException exc) {
			return valueIfNull;
		}
	}

}
