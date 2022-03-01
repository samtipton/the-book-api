package com.thebook.thebookapi.repository

import com.thebook.thebookapi.data.entity.ScriptureEntity

interface ScriptureRepository {
    fun getScriptureById(startId: Int): List<ScriptureEntity>
    fun getScriptureById(startId: Int, length: Int?): List<ScriptureEntity>
    fun getScriptureByIds(startId: Int, endId: Int): List<ScriptureEntity>
}