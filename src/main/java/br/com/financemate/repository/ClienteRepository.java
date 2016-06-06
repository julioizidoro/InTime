/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.repository;

import br.com.financemate.model.Cliente;

/**
 *
 * @author Wolverine
 */
public class ClienteRepository extends AbstractRepository<Cliente> {
    
    public ClienteRepository() {
        super(Cliente.class);
    }
    
}
