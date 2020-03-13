package com.example.testcodex.network

import com.example.testcodex.model.response.CommentResponse
import com.example.testcodex.model.response.StoryResponse
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface StoryAPI {

//    @GET("/")
//    fun getMovies(
//        @Query("apikey") searchKey:String,
//        @Query("type") type:String,
//        @Query("s") titleMovie:String,
//        @Query("page") limitPage:Int
//    ): Observable<ResponseSearch>

    @GET("/v0/topstories.json?print=pretty")
    fun getTopStories(): Observable<ResponseBody>

    @GET("/v0/item/{idStory}")
    fun getStory(
        @Path("idStory") idStory:String
    ):Observable<StoryResponse>

    @GET("/v0/item/{idStory}")
    fun getComment(
        @Path("idStory") idStory:String
    ):Observable<CommentResponse>

}