package com.devaria.todoapi.controller

import com.devaria.todoapi.dto.ErrorDto
import com.devaria.todoapi.dto.LoginDto
import com.devaria.todoapi.dto.LoginResponseDto
import com.devaria.todoapi.util.JWTUtil
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/login")
class LoginController {

    private val LOGIN_TESTE = "adimin@admin.com"
    private val SENHA_TESTE = "1234@"

    @PostMapping
    fun login(@RequestBody dto: LoginDto) : ResponseEntity<Any> {
        try{
            val status = HttpStatus.BAD_REQUEST
            if(dto == null || dto.login.isNullOrBlank() || dto.login.isNullOrEmpty() || 
                    dto.senha.isNullOrBlank() || dto.senha.isNullOrEmpty() ||
                    dto.login != LOGIN_TESTE || dto.senha != SENHA_TESTE) {
               return ResponseEntity(ErrorDto(status.value(), "Parâmetros de entrada inválidos"), status)
            }

            val idUser = 1
            val token = JWTUtil().generateToken(idUser.toString())

            val userTest = LoginResponseDto("User test", LOGIN_TESTE, token)
            return ResponseEntity(userTest, HttpStatus.OK)

        } catch (e: Exception){
            return ResponseEntity(ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Não foi possível efetuar o login, tente novamente."), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}