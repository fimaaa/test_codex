package com.example.testcodex.ui.activity.topstories

import androidx.lifecycle.MutableLiveData
import com.example.testcodex.base.BaseViewModel
import com.example.testcodex.network.StoryAPI
import com.example.testcodex.ui.adapter.story.StoryAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import javax.inject.Inject

class TopStoriesViewModel:BaseViewModel() {
    @Inject
    lateinit var api: StoryAPI

    private lateinit var subscription: Disposable

    val favoriteStory = MutableLiveData<String>()

    val percentageProgress = MutableLiveData<Int>()
    val percentageMax = MutableLiveData<Int>()

    var totalStory = MutableLiveData<MutableList<String>>()

    val retryInternet = MutableLiveData<Int>()
    val dataOK = MutableLiveData<Int>()

    val listTopStoryAdapter = StoryAdapter()

    fun loadTopStory(){
        subscription = api.getTopStories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                totalStory.value = null
                percentageMax.value = 0
                percentageProgress.value = 0
                listTopStoryAdapter.resetData()
            }
            .doOnTerminate {
                if(totalStory.value?.size?:0 > 0){
                    loadStory()
                }
            }
            .subscribe(
                {result -> getListStory(result)},
                {err -> onError(err.message)}
            )
    }

    private fun getListStory(response:ResponseBody){
        var stringList = response.string()
        println("loadTopStory string start = $stringList")
        stringList = stringList.replace("[","")
        stringList = stringList.replace("]","")
        stringList  = stringList.replace(" ","")
        println("loadTopStory string end = $stringList")
        totalStory.value = stringList.split(",").toMutableList()
        percentageMax.value = totalStory.value?.size
    }

    private fun onError(msg:String?){
        println("onError loadTopStory = $msg")
    }

    private fun loadStory(){
        subscription = api.getStory("${totalStory.value?.get(0)?:""}.json")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                println("loadTopStory loadStory ke-0 = ${totalStory.value?.get(0)}")
            }
            .doOnTerminate {
                val progress = (percentageProgress.value?:0)+1
                percentageProgress.value =  progress
                favoriteStory.value = "${percentageProgress.value} of ${percentageMax.value}"
                totalStory.value?.removeAt(0)
                if(totalStory.value?.size?:0 > 0){
                    loadStory()
                }
            }
            .subscribe(
                {result -> listTopStoryAdapter.addData(result)},
                {err -> onError(err.message)}
            )
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}