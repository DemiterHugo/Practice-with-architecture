package com.example.musicademi.ui.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlin.properties.Delegates
import kotlin.properties.ReadWriteProperty

fun Context.toast(message:String){
    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachRoot: Boolean=true): View =
    LayoutInflater.from(context).inflate(layoutRes,this, attachRoot)

fun ImageView.loadUrl(url: String){
    Glide.with(context).load(url).into(this)
}

inline fun <reified T : Activity> Context.intentFor(body: Intent.() -> Unit): Intent =
    Intent(this, T::class.java).apply(body)

inline fun <reified T : Activity> Context.startActivity(body: Intent.() -> Unit) {
    startActivity(intentFor<T>(body))
}

  inline fun <VH : RecyclerView.ViewHolder, T> RecyclerView.Adapter<VH>.basicDiffUtil(
      initialValue: List<T>,
      crossinline areItemsTheSamee: (T, T) -> Boolean = { old, new -> old == new },
      crossinline areContentsTheSamee: (T, T) -> Boolean = { old, new -> old == new }
): ReadWriteProperty<Any?, List<T>> {
      return Delegates.observable(initialValue) { _, old, new ->
        DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                areItemsTheSamee(old[oldItemPosition], new[newItemPosition])

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                areContentsTheSamee(old[oldItemPosition], new[newItemPosition])

            override fun getOldListSize(): Int = old.size

            override fun getNewListSize(): Int = new.size
        }).dispatchUpdatesTo(this@basicDiffUtil)
    }
}

@Suppress("UNCHECKED_CAST")
inline fun <reified T:ViewModel>FragmentActivity.getViewModel(crossinline factory: () -> T):T{

    val vmFactory = object : ViewModelProvider.Factory {
        override fun <U : ViewModel?> create(modelClass: Class<U>): U {
            return factory() as U
        }
    }
    return ViewModelProvider(this,vmFactory).get()
}

