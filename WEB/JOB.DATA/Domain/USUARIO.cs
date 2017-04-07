using AgendaCirurgicaBeta.Domain.Core;
using JOB.DATA.ValueObject;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;

namespace JOB.DATA.Domain
{
    public class USUARIO : EntityBase
    {
        /// <summary>
        /// ENTITY
        /// </summary>
        [JsonConstructor]
        protected USUARIO()
        {
            this.InicializaVariaveis();
        }

        public USUARIO(string NOME, CPF CPF, RG RG, DateTime DT_NASCIMENTO)
        {
            this.InicializaVariaveis();

            this.NOME = NOME;
            this.CPF = CPF;
            this.CPF = CPF;
            this.RG = RG;
            this.DT_NASCIMENTO = DT_NASCIMENTO;

            this.DT_INCLUSAO = DateTime.Now;
            this.DT_ORDENACAO = DateTime.Now;
        }

        public void AtualizaDados(string NOME, CPF CPF, RG RG, DateTime DT_NASCIMENTO)
        {
            this.InicializaVariaveis();

            this.NOME = NOME;
            this.CPF = CPF;
            this.CPF = CPF;
            this.RG = RG;
            this.DT_NASCIMENTO = DT_NASCIMENTO;

            this.DT_ALTERACAO = DateTime.Now;
            this.APROVADO = false;
        }

        public void Aprovar()
        {
            this.APROVADO = true;
            this.DT_APROVACAO = DateTime.Now;
        }

        private void InicializaVariaveis()
        {
            this.PERFIS_PROFISSIONAIS = new HashSet<PERFIL_PROFISSIONAL>();
        }

        public int ID_USUARIO { get; private set; }

        public string NOME { get; private set; }

        public CPF CPF { get; private set; }

        public RG RG { get; private set; }

        public DateTime DT_NASCIMENTO { get; private set; }

        public DateTime DT_INCLUSAO { get; private set; }

        public DateTime? DT_ALTERACAO { get; private set; }

        public DateTime? DT_APROVACAO { get; private set; }

        public DateTime DT_ORDENACAO { get; private set; }

        public bool APROVADO { get; private set; }

        public ENDERECO ENDERECO { get; private set; }

        public CONTATO CONTATO { get; private set; }

        public ICollection<PERFIL_PROFISSIONAL> PERFIS_PROFISSIONAIS { get; private set; }
    }
}