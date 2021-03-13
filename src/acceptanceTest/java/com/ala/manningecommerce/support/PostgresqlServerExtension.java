package com.ala.manningecommerce.support;

import java.util.function.Supplier;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.PostgreSQLContainer;


public final class PostgresqlServerExtension implements BeforeAllCallback {

	private static final String IMAGE = "postgres";

	private static PostgreSQLContainer<?> containerInstance = null;

	private final Supplier<PostgreSQLContainer<?>> container = () -> {
		if (PostgresqlServerExtension.containerInstance != null) {
			return PostgresqlServerExtension.containerInstance;
		}
		return PostgresqlServerExtension.containerInstance = container();
	};

	private <T extends PostgreSQLContainer<T>> T container() {
		T container = new PostgreSQLContainer<T>(IMAGE).self();
		return container;
	}

	@Override
	public void beforeAll(ExtensionContext context) throws Exception {
		this.container.get().start();
		System.setProperty("DB_URL", container.get().getJdbcUrl());
		System.setProperty("DB_USERNAME", container.get().getUsername());
		System.setProperty("DB_PASSWORD", container.get().getPassword());

	}
}
