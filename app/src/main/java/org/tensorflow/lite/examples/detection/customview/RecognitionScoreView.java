/* Copyright 2019 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package org.tensorflow.lite.examples.detection.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.speech.tts.TextToSpeech;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

import org.tensorflow.lite.examples.detection.CameraActivity;
import org.tensorflow.lite.examples.detection.tflite.Detector.Recognition;

public abstract class RecognitionScoreView extends View implements ResultsView, TextToSpeech.OnInitListener {
  private static final float TEXT_SIZE_DIP = 14;
  private final float textSizePx;
  private final Paint fgPaint;
  private final Paint bgPaint;
  private List<Recognition> results;
  private TextToSpeech mTTs;

  public RecognitionScoreView(final Context context, final AttributeSet set) {
    super(context, set);

    textSizePx =
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, TEXT_SIZE_DIP, getResources().getDisplayMetrics());
    fgPaint = new Paint();
    fgPaint.setTextSize(textSizePx);

    bgPaint = new Paint();
    bgPaint.setColor(0xcc4285f4);
  }

  TextToSpeech test;

  public TextToSpeech getmTTs() {
    return mTTs;
  }

  public void setmTTs(TextToSpeech mTTs) {
    this.mTTs = mTTs;
    mTTs.setLanguage(Locale.UK);
  }

  TextToSpeech ts = getmTTs();

  @Override
  public void setResults(final List<Recognition> results) {
    this.results = results;
    postInvalidate();
  }


  @Override
  public void onDraw(final Canvas canvas) {
    final int x = 10;
    int y = (int) (fgPaint.getTextSize() * 1.5f);

    canvas.drawPaint(bgPaint);

    if (results != null) {
      for (final Recognition recog : results) {
        canvas.drawText(recog.getTitle() + ": " + recog.getConfidence(), x, y, fgPaint);
        String toSpeak = recog.getTitle();
        String toSpeak1 = recog.getConfidence().toString();
        mTTs.speak(toSpeak, TextToSpeech.QUEUE_FLUSH,null);
        test.speak(toSpeak1, TextToSpeech.QUEUE_FLUSH,null);

        y += (int) (fgPaint.getTextSize() * 1.5f);
      }
    }
  }
}
