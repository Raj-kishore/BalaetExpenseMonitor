package com.nexfort.balaetexpensemonitor;

import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;

public class HintAdapter   extends ArrayAdapter<Object> {



public HintAdapter(Context theContext, List<Object> objects, int theLayoutResId) {
   super(theContext, theLayoutResId, objects);
}

@Override
public int getCount() {
   // don't display last item. It is used as hint.
   int count = super.getCount();
   return count > 0 ? count - 1 : count;
}
}