package com.example.samplearchitecture.data.persistence.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcelable;

import com.example.samplearchitecture.data.api.entity.CatImage;
import com.example.samplearchitecture.util.Converter;

import auto.parcel.AutoParcel;

/**
 * Created by Sean on 12/29/2015.
 */
@AutoParcel
public abstract class CatItem implements Parcelable {

    public static final String TABLE = "cat_item";

    public static final String ID = "id";
    public static final String IMAGE_URL = "image_url";
    public static final String SOURCE_URL = "source_url";

    public abstract String id();
    public abstract String imageUrl();
    public abstract String sourceUrl();

    // Convert a cursor into a single CatItem
    public static final Converter<Cursor, CatItem> CURSOR_CONVERTER = cursor -> {
        String id = Db.getString(cursor, ID);
        String imageUrl = Db.getString(cursor, IMAGE_URL);
        String sourceUrl = Db.getString(cursor, SOURCE_URL);
        return new AutoParcel_CatItem(id, imageUrl, sourceUrl);
    };

    // Convert a cat api response into a CatItem
    public static final Converter<CatImage, CatItem> RESPONSE_CONVERTER = response -> {
        String id = response.id;
        String imageUrl = response.imageUrl;
        String sourceUrl = response.sourceUrl;
        return new AutoParcel_CatItem(id, imageUrl, sourceUrl);
    };

    public static final Converter<CatItem, ContentValues> VALUES_CONVERTER = catItem -> {
        return new Builder()
                .id(catItem.id())
                .imageUrl(catItem.imageUrl())
                .sourceUrl(catItem.sourceUrl())
                .build();
    };

    public static final class Builder {

        private final ContentValues values = new ContentValues();

        public Builder id(String id) {
            values.put(ID, id);
            return this;
        }

        public Builder imageUrl(String description) {
            values.put(IMAGE_URL, description);
            return this;
        }

        public Builder sourceUrl(String complete) {
            values.put(SOURCE_URL, complete);
            return this;
        }

        public ContentValues build() {
            return new ContentValues(values);
        }
    }

}
