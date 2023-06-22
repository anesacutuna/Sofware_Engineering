package com.anesa.saloon.helpers

import retrofit2.Response

class Utils {

    fun isUserRegistered(userId : Int?): Boolean {
        if(userId==null || userId==0)
            return false
        else
            return true
    }

    fun isApiCallSuccessful(response: Response<*>): Boolean {
        if(response.isSuccessful){
            return true
        }else{
            return false
        }
    }
}