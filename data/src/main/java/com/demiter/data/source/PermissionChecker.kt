package com.demiter.data.source

interface PermissionChecker{

    enum class Permission{ COARSE_LOCATION }

    suspend fun check(permission: Permission): Boolean
}