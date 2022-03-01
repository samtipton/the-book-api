package com.thebook.thebookapi.repository

import com.thebook.thebookapi.data.entity.ScriptureEntity
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalArgumentException
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.TypedQuery

@Repository
@Transactional(readOnly = true)
open class ScriptureRepositoryImpl : ScriptureRepository {
    @PersistenceContext
    var em: EntityManager? = null

    private val versionTableMap = mapOf("kjv" to "KJVScripture")
    private var version = "kjv"

    override fun getScriptureById(startId: Int): List<ScriptureEntity> {
        val typedQuery: TypedQuery<out ScriptureEntity>
                = em!!.createQuery(
                "SELECT scripture FROM ${versionTableMap[version]} scripture WHERE id = $startId",
                ScriptureEntity::class.java
        )

        return listOf(typedQuery.singleResult)
    }

    override fun getScriptureById(startId: Int, length: Int?): List<ScriptureEntity> {
        val typedQuery: TypedQuery<out ScriptureEntity>
                = em!!.createQuery(
                "SELECT scripture FROM ${versionTableMap[version]} scripture WHERE id >= $startId AND id < ${startId}",
                ScriptureEntity::class.java
        )

        return typedQuery.resultList
    }

    override fun getScriptureByIds(startId: Int, endId: Int): List<ScriptureEntity> {
        val typedQuery: TypedQuery<out ScriptureEntity>
        = em!!.createQuery(
                "SELECT scripture FROM ${versionTableMap[version]} scripture WHERE id >= $startId AND id <= $endId",
                ScriptureEntity::class.java
        )

        return typedQuery.resultList
    }

    fun setVersion(version: String) {
        if (!versionTableMap.containsKey(version)) {
            throw IllegalArgumentException("invalid version: $version")
        }
        this.version = version
    }
}
