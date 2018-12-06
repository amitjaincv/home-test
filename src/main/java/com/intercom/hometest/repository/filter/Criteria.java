package com.intercom.hometest.repository.filter;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public interface Criteria<T> {
    public List<T> applyCriteria(List<T> t, SearchCriteria criteria);
}
