using AgendaCirurgicaBeta.Domain.Core;
using JOB.HELPERS.Validation;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;

namespace JOB.DATA.Domain
{
    public class PERFIL_PROFISSIONAL : EntityBase
    {
        /// <summary>
        /// ENTITY
        /// </summary>
        [JsonConstructor]
        protected PERFIL_PROFISSIONAL()
        {
            this.InicializaVariaveis();
        }

        public PERFIL_PROFISSIONAL(int ID_USUARIO, int ID_ESPECIALIDADE, string RESUMO_CURRICULO)
        {
            AssertionConcern.AssertArgumentNotEmptyNotNull(RESUMO_CURRICULO, "RESUMO_CURRICULO");

            this.InicializaVariaveis();

            this.ID_USUARIO = ID_USUARIO;
            this.ID_ESPECIALIDADE = ID_ESPECIALIDADE;
            this.RESUMO_CURRICULO = RESUMO_CURRICULO;
        }

        public void AtualizaValores(string RESUMO_CURRICULO)
        {
            AssertionConcern.AssertArgumentNotEmptyNotNull(RESUMO_CURRICULO, "RESUMO_CURRICULO");

            this.RESUMO_CURRICULO = RESUMO_CURRICULO;
        }

        public void Aprovar()
        {
            this.APROVADO = true;
            this.DT_APROVACAO = DateTime.Now;
        }

        private void InicializaVariaveis()
        {
            this.AVALIACOES = new HashSet<AVALIACAO>();
            this.FORMACOES = new HashSet<FORMACAO>();
        }

        public int ID_USUARIO { get; private set; }
        public int ID_ESPECIALIDADE { get; private set; }
        public DateTime? DT_APROVACAO { get; private set; }
        public bool APROVADO { get; private set; }
        public string RESUMO_CURRICULO { get; private set; }

        public USUARIO USUARIO { get; private set; }
        public ESPECIALIDADE ESPECIALIDADE { get; private set; }

        public ICollection<AVALIACAO> AVALIACOES { get; private set; }
        public ICollection<FORMACAO> FORMACOES { get; private set; }
    }
}
