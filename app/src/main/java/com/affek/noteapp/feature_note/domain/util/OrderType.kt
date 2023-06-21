package com.affek.noteapp.feature_note.domain.util

import androidx.room.Index

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}
