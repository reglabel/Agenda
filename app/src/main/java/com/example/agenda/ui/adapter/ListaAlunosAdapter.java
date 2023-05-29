package com.example.agenda.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.agenda.R;
import com.example.agenda.model.Aluno;
import com.example.agenda.ui.views.ViewHolderAlunoItem;

import java.util.ArrayList;
import java.util.List;

public class ListaAlunosAdapter extends BaseAdapter {
    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;

    public ListaAlunosAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int posicao) {
        return alunos.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return alunos.get(posicao).getId();
        //return 0;
    }

    @Override
    public View getView(int posicao, View convertView, ViewGroup viewGroup) {
        Aluno alunoDevolvido = alunos.get(posicao);
        return criaView(convertView, viewGroup, alunoDevolvido);
    }

    private View criaView(View convertView, ViewGroup viewGroup, Aluno aluno) {
        View view;
        ViewHolderAlunoItem holder;
        if( convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_aluno, viewGroup, false);
            holder = new ViewHolderAlunoItem(view);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolderAlunoItem) view.getTag();
        }
        vinculaInformacao(holder, aluno);
        return view;
    }

    private static void vinculaInformacao(ViewHolderAlunoItem holder, Aluno aluno) {
        holder.nome.setText(aluno.getNome());
        holder.telefone.setText(aluno.getTelefone());
    }

    public void atualiza(List<Aluno> alunos) {
        this.alunos.clear();
        this.alunos.addAll(alunos);
        notifyDataSetChanged();
    }

    public void remove(Aluno aluno) {
        this.alunos.remove(aluno);
        notifyDataSetChanged();
    }
}
