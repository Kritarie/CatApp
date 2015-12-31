package com.example.samplearchitecture.util;

import android.content.ContentValues;
import android.support.annotation.NonNull;

import com.squareup.sqlbrite.BriteDatabase;

import java.util.List;

/**
 * Created by Sean on 12/30/2015.
 */
public final class DbUtil {

    public static void insert(@NonNull BriteDatabase db, @NonNull String table, @NonNull ContentValues... values) {
        int count = values.length;
        BriteDatabase.Transaction transaction = db.newTransaction();
        try {
            for (int i = 0; i < count; i++) {
                db.insert(table, values[i]);
            }
            transaction.markSuccessful();
        } finally {
            transaction.end();
        }
    }

    public static void insert(@NonNull BriteDatabase db, @NonNull String table, @NonNull List<? extends ContentValues> values) {
        int count = values.size();
        BriteDatabase.Transaction transaction = db.newTransaction();
        try {
            for (int i = 0; i < count; i++) {
                db.insert(table, values.get(i));
            }
            transaction.markSuccessful();
        } finally {
            transaction.end();
        }
    }

}
