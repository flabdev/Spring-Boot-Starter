package com.fissionlab.coe.config;

import jakarta.servlet.http.PushBuilder;

public class EndPointConfig {


public final  static  String  WELCOME = "/welcome";

public final  static  String API_V1= "/api/v1";
public  final  static  String API_V2= "/api/v2";

public  final  static  String GET_ALL_EMPLOYEES= "/employees";
public  final  static  String  SAVE_EMPLOYEE= "/employees";
public  final  static  String GET_EMPLOYEE_BY_ID= "/employees/{id}";

public  final  static  String UPDATE_EMPLOYEE= "/employees/{id}";

public  final  static  String DELETE_EMPLOYEE_BY_ID= "/employees/{id}";

public  final  static  String TODO_CONFIGURATOR = "/todos";

public  final  static  String   GET_BY_ID = "/{id}";

public  final  static  String  UPDATE_TODO= "/{id}";

public final  static  String DELETE_TODO_BY_ID= "/{id}";

public final  static  String USER_CONFIGURATOR= "/users";

public  final  static  String  GET_USER_BY_ID = "{id}";

}
