package com.devaria.todoapi.filter

import com.devaria.todoapi.authorization
import com.devaria.todoapi.bearer
import com.devaria.todoapi.util.JWTUtil
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthorizatorFilter(authenticationManager: AuthenticationManager, val jwtUtil: JWTUtil)
    : BasicAuthenticationFilter(authenticationManager) {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val authorization = request.getHeader(authorization)

        if(authorization != null && authorization.startsWith(bearer)){
            val authorizated = getAuthentication(authorization)
            SecurityContextHolder.getContext().authentication = authorizated
        }

        chain.doFilter(request,response)
    }

    private fun getAuthentication(authorization: String): UsernamePasswordAuthenticationToken{

    }


}