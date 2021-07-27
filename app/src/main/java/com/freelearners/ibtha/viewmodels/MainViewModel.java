package com.freelearners.ibtha.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private final MutableLiveData<Integer> TAB_NUMBER ;

    public MainViewModel() {
        TAB_NUMBER = new MutableLiveData<>();
    }

    public void setTab(int tab){
        TAB_NUMBER.postValue(tab);
    }

    public LiveData<Integer> getTabLive(){
        return TAB_NUMBER;
    }
}
