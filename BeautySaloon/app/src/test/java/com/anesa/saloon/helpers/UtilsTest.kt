package com.anesa.saloon.helpers

import junit.framework.TestCase
import org.junit.Assert
import retrofit2.Response

class UtilsTest : TestCase() {

    //test if user id is saved and user is registered
    fun testIsUserRegistered() {
        //given
        val userId = 0

        //when
        val result = Utils().isUserRegistered(userId)

        //then
        Assert.assertEquals(false, result)
    }

    //test if user id is saved and user is registered
    fun testIsUserRegisteredWhenUserIdNull() {
        //given
        val userId = null

        //when
        val result = Utils().isUserRegistered(userId)

        //then
        Assert.assertEquals(false, result)
    }

    //test if user id is saved and user is registered
    fun testIsUserRegisteredWhenUserIdIsValue() {
        //given
        val userId = 3

        //when
        val result = Utils().isUserRegistered(userId)

        //then
        Assert.assertEquals(true, result)
    }

    //test if api response is successfull when it is successful
    fun testIsApiRespnseSuccessfulIfItIs() {
        //given
        val response: Response<Any> = Response.success(200)

        //when
        val result = Utils().isApiCallSuccessful(response)

        //then
        assertEquals(true, result)
    }

    //test if api response is successfull when its not successful
    fun testIsApiRespnseSuccessfulIfItsNot() {
        //given
        val response: Response<Any> = Response.error(404, okhttp3.ResponseBody.create(null, ""))

        //when
        val result = Utils().isApiCallSuccessful(response)

        //then
        assertEquals(false, result)
    }


}