package com.malik.rentindacademy.data

import com.malik.rentindacademy.model.Class
import com.malik.rentindacademy.model.FakeClassDataSource

class ClassRepository {
    fun getClass(): List<Class> {
        return FakeClassDataSource.dummyClass
    }

    fun searchedClass(query: String): List<Class> {
        return FakeClassDataSource.dummyClass.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }

    fun getDetailClass(index: Int): Class {
        return FakeClassDataSource.dummyClass[index - 1]
    }
}