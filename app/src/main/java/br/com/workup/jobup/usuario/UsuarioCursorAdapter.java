package br.com.workup.jobup.usuario;

/**
 * Created by Renevalda Maria on 09/04/2017.
 */
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.br.jobup.R;

import br.com.workup.jobup.dao.UsuarioSQLHelper;

/**
 * Created by Renevalda Maria on 14/11/2016.
 */
public class UsuarioCursorAdapter extends CursorAdapter {
    public UsuarioCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtNome = (TextView) view.findViewById(R.id.txtNome);
        TextView txtEmail = (TextView) view.findViewById(R.id.txtEmail);
        txtNome.setText(cursor.getString(cursor.getColumnIndex(UsuarioSQLHelper.COLUNA_NOME)));
        txtEmail.setText(cursor.getString(cursor.getColumnIndex(UsuarioSQLHelper.COLUNA_EMAIL)));

        int status = cursor.getInt(cursor.getColumnIndex(UsuarioSQLHelper.COLUNA_STATUS));
        if (status == Usuario.Status.EXCLUIR.ordinal()) {
            txtNome.setTextColor(Color.RED);
        } else {
            txtNome.setTextColor(Color.BLACK);
        }
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_usuario, null);
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
