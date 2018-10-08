package com.example.krcm110.myapplication.com.view.recyclerview

interface MultipleType<T> {
    fun getLayoutId(item: T, position: Int): Int
}