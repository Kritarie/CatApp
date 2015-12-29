package com.example.samplearchitecture.ui.home.recycler;

/**
 * Created by seanamos on 12/28/15.
 */
public class HomeItem<T> {

    private T data;

    public HomeItem(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

}
