/*
 * Copyright (c) 2019 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.cheesefinder

import android.annotation.SuppressLint
import android.os.Bundle
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_cheeses.*
import io.reactivex.ObservableEmitter



class CheeseActivity : BaseSearchActivity() {

    private val cheeseAdapter = CheeseAdapter()

    @SuppressLint("CheckResult")
    override fun onStart() {
        super.onStart()
        val searchTextObservable = createButtonClickObservable()
        searchTextObservable
                .subscribe(object :Consumer<String>{
                    override fun accept(query: String) {
                        showResult(cheeseSearchEngine.search(query))
                    }
                })


        val source = Observable.create { emitter: ObservableEmitter<Int> ->
            emitter.onNext(100)
            emitter.onNext(200)
            emitter.onNext(300)
        }

        Observable.create<String> {
            it.onNext("hi")
            it.onNext("rx")
        }.subscribe()
                .
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())



        Observable.create()
                .subscribe()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createButtonClickObservable()
    }

    fun makeObservable(){

    }



    private fun createButtonClickObservable() : Observable<String> {
       return Observable.create<String>{emmiter->
           searchButton.setOnClickListener {
               emmiter.onNext(queryEditText.text.toString())
           }
           //Observable이 제거될때 버튼의 clickListner를 제거해주는게 메모리 릭 방지
           emmiter.setCancellable{
               searchButton.setOnClickListener(null)
           }
       }
    }



}