package com.example.testcodex.ui.activity.detailstories

import androidx.lifecycle.MutableLiveData
import com.example.testcodex.base.BaseViewModel
import com.example.testcodex.model.response.CommentResponse
import com.example.testcodex.model.response.StoryResponse
import com.example.testcodex.network.StoryAPI
import com.example.testcodex.ui.adapter.komentar.KomentarAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailStoriesViewModel:BaseViewModel() {
    @Inject
    lateinit var api: StoryAPI

    private lateinit var subscription: Disposable

    val titleStory = MutableLiveData<String>()
    val authorStory = MutableLiveData<String>()
    val textStory = MutableLiveData<String>()
    val listComment = MutableLiveData<MutableList<String?>>()
    val isFavorite = MutableLiveData<Boolean>()

    val commentAdapter = KomentarAdapter()

    fun bind(story:StoryResponse?, isFavorite:Boolean){
        titleStory.value = story?.title
        authorStory.value = "By : ${story?.by}"
        textStory.value = story?.url
        listComment.value = story?.kids?.toMutableList()
        this.isFavorite.value = isFavorite
        loadComment()
    }

    fun changeFavorite(){
        isFavorite.value = !(isFavorite.value?:false)
    }

    private fun loadComment(){
        subscription = api.getComment("${listComment.value?.get(0)?:""}.json")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {

            }
            .doOnTerminate {
                listComment.value?.removeAt(0)
                if(listComment.value?.size?:0 > 0) {
                    loadComment()
                }
            }
            .subscribe(
                {result -> commentAdapter.addData(result)},
                {err -> commentAdapter.addData(CommentResponse(0,"Error",0,err.message,0,"",null)) }
            )
    }


    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}