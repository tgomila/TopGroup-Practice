package com.tg.practice.DAOInterface;

import java.util.List;

public interface BasicDaoInterface<M> {
	
	List<M> getAll();

    M alta(M model);

    void baja(Long id);

    void modificar(M model);

    M buscar(Long id);
}
