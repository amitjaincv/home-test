package com.intercom.hometest.repository;

import java.util.Set;

public interface IRepository<T> {

    void create(T t);

    Set<T> readAll();
}
