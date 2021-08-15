package com.manuh.share.pulainterview.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OptionManager(context: Context) {

    companion object {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "option_index")
        val OPTION_INDEX = intPreferencesKey("OPTION_INDEX")
    }

    suspend fun storeIndex(index: Int, context: Context) {
        context.dataStore.edit { settings ->
            settings[OPTION_INDEX] = index
        }
    }

    val indexFlow: Flow<Int> = context.dataStore.data
        .map { preferences ->
            // No type safety.
            preferences[OPTION_INDEX] ?: 0
        }
}