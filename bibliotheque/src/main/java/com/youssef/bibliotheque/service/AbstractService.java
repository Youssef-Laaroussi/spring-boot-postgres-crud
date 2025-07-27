package com.youssef.bibliotheque.service;

import java.util.List;

public interface AbstractService<T> {


    Integer save(T dto);

    List<T> findAll();

    T findById(Integer id);

    void deleteById(Integer id);

    Integer update(T dto);







}
