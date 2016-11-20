/*
 * Copyright (C) 2016 Bradley Campbell.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package paperparcel.internal;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.util.LongSparseArray;
import paperparcel.TypeAdapter;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
@SuppressWarnings({ "WeakerAccess", "unused" }) // Used by generated code
public final class LongSparseArrayAdapter<T> implements TypeAdapter<LongSparseArray<T>> {
  private final TypeAdapter<T> itemAdapter;

  public LongSparseArrayAdapter(TypeAdapter<T> itemAdapter) {
    this.itemAdapter = itemAdapter;
  }

  @NonNull @Override public LongSparseArray<T> readFromParcel(@NonNull Parcel source) {
    int size = source.readInt();
    LongSparseArray<T> sparseArray = new LongSparseArray<>(size);
    for (int i = 0; i < size; i++) {
      sparseArray.put(source.readLong(), itemAdapter.readFromParcel(source));
    }
    return sparseArray;
  }

  @Override public void writeToParcel(@NonNull LongSparseArray<T> value, @NonNull Parcel dest, int flags) {
    dest.writeInt(value.size());
    for (int i = 0; i < value.size(); i++) {
      long key = value.keyAt(i);
      dest.writeLong(key);
      itemAdapter.writeToParcel(value.get(key), dest, flags);
    }
  }
}
