package com.devaria.todoapi.util

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwt
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component

@Component
class JWTUtil {

    private val securityKey = "ChaveDeSegurança123"

    fun generateToken(idUser : String) : String {
        return Jwts.builder() // cria
            .setSubject(idUser) // seta o body
            .signWith(SignatureAlgorithm.HS512, securityKey.toByteArray()) // qual é o algoritmo de criptografia e a chave
            .compact() // compacta tudo em uma string
    }

    fun getClaims(token: String): Claims? {
        return try {
            Jwts.parser().setSigningKey(securityKey.toByteArray()).parseClaimsJws(token).body
        }catch(e: Exception){
            null
        }
    }
}