using JOB.HELPERS.Validation;
using Newtonsoft.Json;
using System;

namespace JOB.DATA.Domain
{
    public class FORMACAO
    {
        /// <summary>
        /// ENTITY
        /// </summary>
        [JsonConstructor]
        protected FORMACAO()
        {
            this.InicializaVariaveis();
        }

        public FORMACAO(Guid ID_USUARIO, int ID_ESPECIALIDADE, string INSTITUICAO, string NOME_CURSO, Int16 ANO_FORMACAO)
        {
            AssertionConcern.AssertArgumentNotEmptyNotNull(INSTITUICAO, "INSTITUICAO");
            AssertionConcern.AssertArgumentNotEmptyNotNull(NOME_CURSO, "NOME_CURSO");

            this.InicializaVariaveis();

            this.ID_USUARIO = ID_USUARIO;
            this.ID_ESPECIALIDADE = ID_ESPECIALIDADE;
            this.INSTITUICAO = INSTITUICAO;
            this.NOME_CURSO = NOME_CURSO;
            this.ANO_FORMACAO = ANO_FORMACAO;
        }

        public void AtualizaDados(string INSTITUICAO, string NOME_CURSO, Int16 ANO_FORMACAO)
        {
            AssertionConcern.AssertArgumentNotEmptyNotNull(INSTITUICAO, "INSTITUICAO");
            AssertionConcern.AssertArgumentNotEmptyNotNull(NOME_CURSO, "NOME_CURSO");

            this.INSTITUICAO = INSTITUICAO;
            this.NOME_CURSO = NOME_CURSO;
            this.ANO_FORMACAO = ANO_FORMACAO;

            this.APROVADO = false;
        }

        public void Aprovar()
        {
            this.APROVADO = true;
            this.DT_APROVACAO = DateTime.Now;
        }

        private void InicializaVariaveis()
        {
            //this.GUIAS = new HashSet<GUIA>();
        }

        public Guid ID_USUARIO { get; private set; }
        public int ID_ESPECIALIDADE { get; private set; }
        public int ID_FORMACAO { get; private set; }
        public string INSTITUICAO { get; private set; }
        public string NOME_CURSO { get; private set; }
        public Int16 ANO_FORMACAO { get; private set; }
        public DateTime? DT_APROVACAO { get; private set; }
        public bool APROVADO { get; private set; }

        public PERFIL_PROFISSIONAL PERFIL_PROFISSIONAL { get; private set; }
    }
}