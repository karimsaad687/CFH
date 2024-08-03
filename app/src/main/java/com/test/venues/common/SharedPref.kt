package com.test.venues.common

import android.content.Context
import android.content.Context.MODE_PRIVATE

import android.content.SharedPreferences
import com.test.venues.R


class SharedPref {

    companion object{
        fun setIsLogin(context: Context, flag:Boolean){
            context.getSharedPreferences(context.getString(R.string.app_name), MODE_PRIVATE).edit().putBoolean("isLogin",flag).apply()
        }
        fun saveName(context: Context, name:String){
            context.getSharedPreferences(context.getString(R.string.app_name), MODE_PRIVATE).edit().putString("name",name).apply()
        }
        fun saveEmail(context: Context, email:String){
            context.getSharedPreferences(context.getString(R.string.app_name), MODE_PRIVATE).edit().putString("email",email).apply()
        }
        fun saveAge(context: Context, age:String){
            context.getSharedPreferences(context.getString(R.string.app_name), MODE_PRIVATE).edit().putString("age",age).apply()
        }

        fun isLogin(context: Context):Boolean{
            return context.getSharedPreferences(context.getString(R.string.app_name), MODE_PRIVATE).getBoolean("isLogin",false)
        }

        fun getName(context: Context):String?{
            return context.getSharedPreferences(context.getString(R.string.app_name), MODE_PRIVATE).getString("name","")
        }
        fun getEmail(context: Context): String? {
            return context.getSharedPreferences(context.getString(R.string.app_name), MODE_PRIVATE).getString("email","")
        }
        fun getAge(context: Context):String?{
            return context.getSharedPreferences(context.getString(R.string.app_name), MODE_PRIVATE).getString("age","")
        }
    }
}