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

        public PERFIL_PROFISSIONAL(Guid ID_USUARIO, int ID_ESPECIALIDADE, string RESUMO_CURRICULO)
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

        public Guid ID_USUARIO { get; set; }
        public int ID_ESPECIALIDADE { get; set; }
        public DateTime? DT_APROVACAO { get; set; }
        public bool APROVADO { get; set; }
        public string RESUMO_CURRICULO { get; set; }

        public USUARIO USUARIO { get; set; }
        public ESPECIALIDADE ESPECIALIDADE { get; set; }

        public ICollection<AVALIACAO> AVALIACOES { get; set; }
        public ICollection<FORMACAO> FORMACOES { get; set; }
    }
}
