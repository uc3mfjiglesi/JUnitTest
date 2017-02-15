package com.ccs.junittest.rv;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ccs.junittest.R;

public class MainActivity extends RecyclerViewActivity {
    private static final String[] items={"lorem", "ipsum", "dolor",
            "sit", "amet",
            "consectetuer", "adipiscing", "elit", "morbi", "vel",
            "ligula", "vitae", "arcu", "aliquet", "mollis",
            "etiam", "vel", "erat", "placerat", "ante",
            "porttitor", "sodales", "pellentesque", "augue", "purus"};

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setLayoutManager(new LinearLayoutManager(this));

        Drawable divider=getResources().getDrawable(R.drawable.item_divider);

        getRecyclerView().addItemDecoration(new HorizontalDividerItemDecoration(divider));
        setAdapter(new IconicAdapter());
    }

    class IconicAdapter extends RecyclerView.Adapter<RowHolder> {
        @Override
        public RowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return(new RowHolder(getLayoutInflater()
                    .inflate(R.layout.row, parent, false)));
        }

        @Override
        public void onBindViewHolder(RowHolder holder, int position) {
            holder.bindModel(items[position]);
        }

        @Override
        public int getItemCount() {
            return(items.length);
        }
    }

    static class RowHolder extends RecyclerView.ViewHolder {
        TextView label=null;
        TextView size=null;
        ImageView icon=null;
        String template=null;

        RowHolder(View row) {
            super(row);

            label=(TextView)row.findViewById(R.id.label);
            size=(TextView)row.findViewById(R.id.size);
            icon=(ImageView)row.findViewById(R.id.icon);

            template=size.getContext().getString(R.string.size_template);
        }

        void bindModel(String item) {
            label.setText(item);
            size.setText(String.format(template, item.length()));

            if (item.length()>4) {
                icon.setImageResource(R.mipmap.delete);
            }
            else {
                icon.setImageResource(R.mipmap.ok);
            }
        }
    }
}
