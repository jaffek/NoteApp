package com.affek.noteapp.di

import android.app.Application
import androidx.room.Room
import com.affek.noteapp.feature_note.data.data_source.NoteDatabase
import com.affek.noteapp.feature_note.data.repository.NoteRepositoryImpl
import com.affek.noteapp.feature_note.domain.repository.NoteRepository
import com.affek.noteapp.feature_note.domain.use_case.AddNoteUseCase
import com.affek.noteapp.feature_note.domain.use_case.DeleteNoteUseCase
import com.affek.noteapp.feature_note.domain.use_case.GetNoteUseCase
import com.affek.noteapp.feature_note.domain.use_case.GetNotesUseCase
import com.affek.noteapp.feature_note.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNoteDatabase(app:Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DTABASE_NAME
        ).build()

    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotesUseCase(repository),
            deleteNote = DeleteNoteUseCase(repository),
            addNote = AddNoteUseCase(repository),
            getNote = GetNoteUseCase(repository)
        )
    }
}