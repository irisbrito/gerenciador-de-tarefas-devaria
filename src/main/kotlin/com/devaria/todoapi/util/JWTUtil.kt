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

    fun isTokenValid(token : String) : Boolean {
        val claims = getClaims(token)

        if(claims != null){
            val idUser = claims.subject
            if(!idUser.isNullOrEmpty() && !idUser.isNullOrBlank()){
                return true
            }
        }

        return false
    }

    fun getClaims(token: String): Claims? = try {
            Jwts.parser().setSigningKey(securityKey.toByteArray()).parseClaimsJws(token).body
        }catch(e: Exception){
            null
        }

    fun getUserId(token : String) : String? {
        val claims = getClaims(token)
        return claims?.subject
    }
}