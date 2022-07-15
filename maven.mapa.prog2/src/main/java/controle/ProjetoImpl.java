package controle;

import exceptions.DadoConsultadoException;
import java.util.ArrayList;
import modelo.Projeto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProjetoImpl implements ProjetoDAO {

    private static final Set < Projeto > projetos = new HashSet < Projeto > ();

    @Override
    public void adicionar(Projeto projeto) {
        projetos.add(projeto);
    }

    @Override
    public List<Projeto> listar() {
        List<Projeto> projetoList = new ArrayList();
        projetoList.addAll(projetos);
        return projetoList;
    }
    
    @Override
    public Projeto consultarPorNome(String nome) throws DadoConsultadoException {
        for(Projeto projeto : projetos){
            if(projeto.getNome().equalsIgnoreCase(nome)){
                return projeto;
            }
        }
        throw new DadoConsultadoException("Projeto com o nome -> '" +nome+ "' não encontrado!");
    }
    
    @Override
    public Projeto alterar(String nome, Projeto projetoNovo) throws DadoConsultadoException {
        Projeto projetoEncontrado = consultarPorNome(nome);
        projetoEncontrado.setNome(projetoNovo.getNome());
        projetoEncontrado.setObjetivo(projetoNovo.getObjetivo());
        projetoEncontrado.setNecessidades(projetoNovo.getNecessidades());
        projetoEncontrado.setDataInicio(projetoNovo.getDataInicio());
        projetoEncontrado.setDataFinal(projetoNovo.getDataFinal());
        return projetoEncontrado;
    }

    @Override
    public void excluir(Projeto projeto) throws DadoConsultadoException, UnsupportedOperationException {
        if (projetos.contains(projeto)){
            projetos.remove(projeto);
            return;
        }
    }

    @Override
    public void excluir(String nome) throws DadoConsultadoException, UnsupportedOperationException {
        Projeto projetoEcontrado = consultarPorNome(nome);
        this.excluir(projetoEcontrado);
    }
}
