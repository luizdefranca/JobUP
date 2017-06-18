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
            this.SUB_ESPECIALIDADES = new HashSet<SUB_ESPECIALIDADE>();
            this.PERFIS_PROFISSIONAIS = new HashSet<PERFIL_PROFISSIONAL>();
        }

        public int ID_ESPECIALIDADE { get; set; }
        public string DESCRICAO { get; set; }
        public bool EXIGIR_COMPROVACAO { get; set; }
        public string IMAGEM { get; set; }

        public ICollection<SUB_ESPECIALIDADE> SUB_ESPECIALIDADES { get; set; }
        public ICollection<PERFIL_PROFISSIONAL> PERFIS_PROFISSIONAIS { get; set; }
    }
}