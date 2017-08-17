/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.android.tools.appbundle.bundle2installable;

import com.android.tools.appbundle.bundle2installable.util.Pair;
import com.android.tools.appbundle.bundle2installable.util.ZipUtils;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * Generates a zip file with assets, resources and libs in a correct place.
 *
 * This is not yet APK split as it has to be processed by the aapt2.
 */
public class PreSplitGenerator {

  private ZipOutputStream outputStream;

  PreSplitGenerator(String outputFile) throws FileNotFoundException {
    this.outputStream = new ZipOutputStream(new FileOutputStream(outputFile));
  }

  public void writePreSplit(ZipFile moduleZip, List<Pair<ZipEntry, String>> entriesToWrite)
      throws IOException, FileNotFoundException {
    for (Pair<ZipEntry, String> entry : entriesToWrite) {
      ZipUtils.writeEntry(entry.getSecond(), outputStream,
          moduleZip.getInputStream(entry.getFirst()));
    }
    outputStream.close();
  }
}
