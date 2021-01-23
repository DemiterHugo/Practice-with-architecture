package com.demiter.data.source

interface LocationDataSource {
    suspend fun findLastRegion(): String?
}