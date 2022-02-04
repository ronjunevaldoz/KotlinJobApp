package com.ronjunevaldoz.geoexam.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_pref")

@Singleton
class DataStoreManager @Inject constructor(
    private val context: Context
) {
    fun get(key: String): Flow<String> {
        val prefKey = stringPreferencesKey(key)
        return context.dataStore.data
            .map { pref ->
                // No type safety.
                pref[prefKey] ?: ""
            }
    }


    suspend fun save(key: String, value: String) {
        val prefKey = stringPreferencesKey(key)
        context.dataStore.edit { pref ->
            pref[prefKey] = value
        }
    }
}