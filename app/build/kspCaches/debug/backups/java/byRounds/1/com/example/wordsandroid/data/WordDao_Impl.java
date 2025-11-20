package com.example.wordsandroid.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class WordDao_Impl implements WordDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Word> __insertionAdapterOfWord;

  private final EntityDeletionOrUpdateAdapter<Word> __updateAdapterOfWord;

  public WordDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWord = new EntityInsertionAdapter<Word>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `words` (`id`,`english`,`chinese`,`stage`,`nextReviewTime`,`isInVocabularyBook`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Word entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getEnglish());
        statement.bindString(3, entity.getChinese());
        statement.bindLong(4, entity.getStage());
        statement.bindLong(5, entity.getNextReviewTime());
        final int _tmp = entity.isInVocabularyBook() ? 1 : 0;
        statement.bindLong(6, _tmp);
      }
    };
    this.__updateAdapterOfWord = new EntityDeletionOrUpdateAdapter<Word>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `words` SET `id` = ?,`english` = ?,`chinese` = ?,`stage` = ?,`nextReviewTime` = ?,`isInVocabularyBook` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Word entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getEnglish());
        statement.bindString(3, entity.getChinese());
        statement.bindLong(4, entity.getStage());
        statement.bindLong(5, entity.getNextReviewTime());
        final int _tmp = entity.isInVocabularyBook() ? 1 : 0;
        statement.bindLong(6, _tmp);
        statement.bindString(7, entity.getId());
      }
    };
  }

  @Override
  public Object insertAll(final List<Word> words, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfWord.insert(words);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final Word word, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfWord.handle(word);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Word>> getDueWords(final long currentTime) {
    final String _sql = "SELECT * FROM words WHERE nextReviewTime <= ? ORDER BY nextReviewTime ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, currentTime);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"words"}, new Callable<List<Word>>() {
      @Override
      @NonNull
      public List<Word> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfEnglish = CursorUtil.getColumnIndexOrThrow(_cursor, "english");
          final int _cursorIndexOfChinese = CursorUtil.getColumnIndexOrThrow(_cursor, "chinese");
          final int _cursorIndexOfStage = CursorUtil.getColumnIndexOrThrow(_cursor, "stage");
          final int _cursorIndexOfNextReviewTime = CursorUtil.getColumnIndexOrThrow(_cursor, "nextReviewTime");
          final int _cursorIndexOfIsInVocabularyBook = CursorUtil.getColumnIndexOrThrow(_cursor, "isInVocabularyBook");
          final List<Word> _result = new ArrayList<Word>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Word _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpEnglish;
            _tmpEnglish = _cursor.getString(_cursorIndexOfEnglish);
            final String _tmpChinese;
            _tmpChinese = _cursor.getString(_cursorIndexOfChinese);
            final int _tmpStage;
            _tmpStage = _cursor.getInt(_cursorIndexOfStage);
            final long _tmpNextReviewTime;
            _tmpNextReviewTime = _cursor.getLong(_cursorIndexOfNextReviewTime);
            final boolean _tmpIsInVocabularyBook;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsInVocabularyBook);
            _tmpIsInVocabularyBook = _tmp != 0;
            _item = new Word(_tmpId,_tmpEnglish,_tmpChinese,_tmpStage,_tmpNextReviewTime,_tmpIsInVocabularyBook);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<Word>> getAllWords() {
    final String _sql = "SELECT * FROM words";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"words"}, new Callable<List<Word>>() {
      @Override
      @NonNull
      public List<Word> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfEnglish = CursorUtil.getColumnIndexOrThrow(_cursor, "english");
          final int _cursorIndexOfChinese = CursorUtil.getColumnIndexOrThrow(_cursor, "chinese");
          final int _cursorIndexOfStage = CursorUtil.getColumnIndexOrThrow(_cursor, "stage");
          final int _cursorIndexOfNextReviewTime = CursorUtil.getColumnIndexOrThrow(_cursor, "nextReviewTime");
          final int _cursorIndexOfIsInVocabularyBook = CursorUtil.getColumnIndexOrThrow(_cursor, "isInVocabularyBook");
          final List<Word> _result = new ArrayList<Word>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Word _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpEnglish;
            _tmpEnglish = _cursor.getString(_cursorIndexOfEnglish);
            final String _tmpChinese;
            _tmpChinese = _cursor.getString(_cursorIndexOfChinese);
            final int _tmpStage;
            _tmpStage = _cursor.getInt(_cursorIndexOfStage);
            final long _tmpNextReviewTime;
            _tmpNextReviewTime = _cursor.getLong(_cursorIndexOfNextReviewTime);
            final boolean _tmpIsInVocabularyBook;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsInVocabularyBook);
            _tmpIsInVocabularyBook = _tmp != 0;
            _item = new Word(_tmpId,_tmpEnglish,_tmpChinese,_tmpStage,_tmpNextReviewTime,_tmpIsInVocabularyBook);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<Word>> getVocabularyWords() {
    final String _sql = "SELECT * FROM words WHERE isInVocabularyBook = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"words"}, new Callable<List<Word>>() {
      @Override
      @NonNull
      public List<Word> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfEnglish = CursorUtil.getColumnIndexOrThrow(_cursor, "english");
          final int _cursorIndexOfChinese = CursorUtil.getColumnIndexOrThrow(_cursor, "chinese");
          final int _cursorIndexOfStage = CursorUtil.getColumnIndexOrThrow(_cursor, "stage");
          final int _cursorIndexOfNextReviewTime = CursorUtil.getColumnIndexOrThrow(_cursor, "nextReviewTime");
          final int _cursorIndexOfIsInVocabularyBook = CursorUtil.getColumnIndexOrThrow(_cursor, "isInVocabularyBook");
          final List<Word> _result = new ArrayList<Word>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Word _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpEnglish;
            _tmpEnglish = _cursor.getString(_cursorIndexOfEnglish);
            final String _tmpChinese;
            _tmpChinese = _cursor.getString(_cursorIndexOfChinese);
            final int _tmpStage;
            _tmpStage = _cursor.getInt(_cursorIndexOfStage);
            final long _tmpNextReviewTime;
            _tmpNextReviewTime = _cursor.getLong(_cursorIndexOfNextReviewTime);
            final boolean _tmpIsInVocabularyBook;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsInVocabularyBook);
            _tmpIsInVocabularyBook = _tmp != 0;
            _item = new Word(_tmpId,_tmpEnglish,_tmpChinese,_tmpStage,_tmpNextReviewTime,_tmpIsInVocabularyBook);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM words";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
