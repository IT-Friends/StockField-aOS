package com.evangers.stockfield.data.util

import android.content.SharedPreferences
import javax.inject.Inject
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class SharedPreferenceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : SharedPreference {
    companion object {
        private const val keyInitialOpen = "keyInitialOpen"
    }

    override var initialOpen: Long by LongPreference(sharedPreferences, keyInitialOpen, 0L)
}

class BooleanPreference(
    private val preferences: SharedPreferences,
    private val name: String,
    private val defaultValue: Boolean
) : ReadWriteProperty<Any, Boolean> {

    override fun getValue(thisRef: Any, property: KProperty<*>): Boolean {
        return preferences.getBoolean(name, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Boolean) {
        preferences.edit { putBoolean(name, value) }
    }
}

open class StringPreference(
    private val preferences: SharedPreferences,
    private val name: String,
    private val defaultValue: String?
) : ReadWriteProperty<Any, String?> {

    override fun getValue(thisRef: Any, property: KProperty<*>): String? {
        return preferences.getString(name, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String?) {
        preferences.edit { putString(name, value) }
    }
}

class NonNullStringPreference(
    preferences: SharedPreferences,
    name: String,
    defaultValue: String
) : StringPreference(preferences, name, defaultValue) {
    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        return super.getValue(thisRef, property)!!
    }
}

open class IntPreference(
    private val preferences: SharedPreferences,
    private val name: String,
    private val defaultValue: Int
) : ReadWriteProperty<Any, Int> {

    override fun getValue(thisRef: Any, property: KProperty<*>): Int {
        return preferences.getInt(name, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Int) {
        preferences.edit { putInt(name, value) }
    }
}

open class LongPreference(
    private val preferences: SharedPreferences,
    private val name: String,
    private val defaultValue: Long
) : ReadWriteProperty<Any, Long> {

    override fun getValue(thisRef: Any, property: KProperty<*>): Long {
        return preferences.getLong(name, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Long) {
        preferences.edit { putLong(name, value) }
    }
}

// 효율 별로 좋지 않으나 신경쓸 정도로는 느리지 않아 Preference 로 구현함
open class IntListPreference(
    private val preferences: SharedPreferences,
    private val name: String
) : ReadWriteProperty<Any, List<Int>> {

    override fun getValue(thisRef: Any, property: KProperty<*>): List<Int> {
        return preferences.getString(name, "")!!.split(",").filter { it.isNotBlank() }
            .map { it.toInt() }
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: List<Int>) {
        preferences.edit { putString(name, value.joinToString(separator = ",")) }
    }
}

inline fun SharedPreferences.edit(
    action: SharedPreferences.Editor.() -> Unit
) {
    val editor = edit()
    action(editor)
    editor.apply()
}