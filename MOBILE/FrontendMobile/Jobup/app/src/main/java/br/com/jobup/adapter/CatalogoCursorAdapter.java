package br.com.jobup.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import br.com.jobup.R;

/**
 * Created by Renevalda Maria on 05/05/2017.
 */
public class CatalogoCursorAdapter extends CursorAdapter {
    public CatalogoCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtNome = (TextView) view.findViewById(R.id.txtNome);
        TextView txtEmail = (TextView) view.findViewById(R.id.txtBairro);
        txtNome.setText(cursor.getString(cursor.getColumnIndex(null)));
        txtEmail.setText(cursor.getString(cursor.getColumnIndex(null)));
        RatingBar rtbEstrelas = (RatingBar) view.findViewById(R.id.rtbEstrelas);
        rtbEstrelas.setRating(cursor.getFloat(cursor.getColumnIndex(null)));
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_catalogo, null);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        ListView listView = (ListView) parent;
        int color = listView.isItemChecked(position) ?
                Color.argb(0xFF, 0x31, 0xB6, 0xE7) :
                Color.TRANSPARENT;
        v.setBackgroundColor(color);
        return v;
    }
}