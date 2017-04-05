using JOB.HELPERS.Validation;
using Newtonsoft.Json;
using System.Collections.Generic;

namespace JOB.DATA.Domain
{
    public class ESPECIALIDADE
    {
        /// <summary>
        /// ENTITY
        /// </summary>
        [JsonConstructor]
        protected ESPECIALIDADE()
        {
            this.InicializaVariaveis();
        }

        public ESPECIALIDADE(int ID_ESPECIALIDADE, string DESCRICAO, bool EXIGIR_COMPROVACAO)
        {
            AssertionConcern.AssertArgumentNotEmptyNotNull(DESCRICAO, "DESCRICAO");

            this.InicializaVariaveis();

            this.ID_ESPECIALIDADE = ID_ESPECIALIDADE;
            this.DESCRICAO = DESCRICAO;
            this.EXIGIR_COMPROVACAO = EXIGIR_COMPROVACAO;
        }

        public void AtualizaDados(string DESCRICAO, bool EXIGIR_COMPROVACAO)
        {
            AssertionConcern.AssertArgumentNotEmptyNotNull(DESCRICAO, "DESCRICAO");

            this.DESCRICAO = DESCRICAO;
            this.EXIGIR_COMPROVACAO = EXIGIR_COMPROVACAO;
        }

        private void InicializaVariaveis()
        {
            //this.GUIAS = new HashSet<GUIA>();
        }

        public int ID_ESPECIALIDADE { get; private set; }
        public string DESCRICAO { get; private set; }
        public bool EXIGIR_COMPROVACAO { get; private set; }

        public ICollection<PERFIL_PROFISSIONAL> PERFIL_PROFISSIONAL { get; private set; }
    }
}