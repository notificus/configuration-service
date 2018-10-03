package configuration.api;

public final class Routes {
    public static final String BASE_ROUTE = "/";
    public static final String USERS_ROUTE = BASE_ROUTE + "users";
    public static final String USERS_PATH_VARIABLE = "/{cip}";
    public static final String USER_ROUTE = USERS_ROUTE + USERS_PATH_VARIABLE;
    public static final String USER_CONFIGURATIONS_ROUTE = USER_ROUTE + "/configurations";
}

