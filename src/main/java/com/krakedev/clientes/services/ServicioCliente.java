package com.krakedev.clientes.services;

import java.util.ArrayList;

import com.krakedev.clientes.entidades.Cliente;

public class ServicioCliente {
	private ArrayList<Cliente> clientes = new ArrayList<Cliente>();

	public Cliente buscarPorCedula(String cedula) {

		for (Cliente c : clientes) {
			if (c.getCedula().equals(cedula)) {
				return c;
			}
		}

		return null;

	}

	public Cliente crear(Cliente cliente) {
		Cliente existente = buscarPorCedula(cliente.getCedula());

		if (existente != null) {
			return null;
		} else {
			clientes.add(cliente);
			return cliente;
		}
	}
}
