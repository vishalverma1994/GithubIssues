package com.github.issuetracker.db.converter

import androidx.room.TypeConverter
import com.github.issuetracker.model.LabelsData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListTypeConverter {

    @TypeConverter
    fun fromLabelsDataSetList(value: String): List<LabelsData> {
        val listType = object : TypeToken<ArrayList<LabelsData>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toLabelsDataSetList(list: List<LabelsData>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromAssigneesDataSetList(value: String): List<Any> {
        val listType = object : TypeToken<ArrayList<Any>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toAssigneesDataSetList(list: List<Any>): String {
        return Gson().toJson(list)
    }


}