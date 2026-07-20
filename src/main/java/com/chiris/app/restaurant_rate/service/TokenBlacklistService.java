package com.chiris.app.restaurant_rate.service;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

// Como el JWT es stateless, "cerrar sesion" no invalida nada por si solo:
// el token seguiria siendo valido hasta su expiracion. Esta blacklist en memoria
// guarda los tokens cerrados hasta su fecha de expiracion para que el filtro
// de autenticacion los rechace aunque la firma siga siendo valida.
@Service
public class TokenBlacklistService {

    private final Map<String, Date> blacklist = new ConcurrentHashMap<>();

    public void blacklist(String token, Date expiration) {
        blacklist.put(token, expiration);
    }

    public boolean isBlacklisted(String token) {
        Date expiration = blacklist.get(token);
        if (expiration == null) {
            return false;
        }
        if (expiration.before(new Date())) {
            blacklist.remove(token);
            return false;
        }
        return true;
    }
}
