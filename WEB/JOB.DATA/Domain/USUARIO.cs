using AgendaCirurgicaBeta.Domain.Core;
using JOB.DATA.Enum;
using JOB.DATA.ValueObject;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using JOB.HELPERS.Validation;

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

        public USUARIO(Guid ID_USUARIO, string NOME, CPF CPF, RG RG, DateTime DT_NASCIMENTO)
        {
            this.InicializaVariaveis();

            this.ID_USUARIO = ID_USUARIO;
            this.NOME = NOME;
            this.CPF = CPF;
            this.CPF = CPF;
            this.RG = RG;
            this.DT_NASCIMENTO = DT_NASCIMENTO;

            this.DT_INCLUSAO = DateTime.Now;
            this.DT_ORDENACAO = DateTime.Now;

            this.ATIVO = true;
            this.DT_ATIVACAO = DateTime.Now;
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

        public void AdicionarContato(Telefone FIXO, Telefone CELULAR, Email EMAIL)
        {
            if (FIXO != null) this.FIXO = FIXO;
            this.CELULAR = CELULAR;
            this.EMAIL = EMAIL;
        }

        public void AtualizarContato(Telefone FIXO, Telefone CELULAR)
        {
            if (FIXO != null) this.FIXO = FIXO;
            this.CELULAR = CELULAR;
            //this.EMAIL = EMAIL;
        }

        public void AdicionarEndereco(EnumUF UF, string CEP, string LOGRADOURO, string COMPLEMENTO, string BAIRRO, string CIDADE)
        {
            AssertionConcern.AssertArgumentNotEmptyNotNull(CEP, "CEP");
            AssertionConcern.AssertArgumentNotEmptyNotNull(LOGRADOURO, "LOGRADOURO");
            AssertionConcern.AssertArgumentNotEmptyNotNull(BAIRRO, "BAIRRO");
            AssertionConcern.AssertArgumentNotEmptyNotNull(CIDADE, "CIDADE");

            this.UF = UF;
            this.CEP = CEP;
            this.LOGRADOURO = LOGRADOURO;
            this.COMPLEMENTO = COMPLEMENTO;
            this.BAIRRO = BAIRRO;
            this.CIDADE = CIDADE;
        }

        public void Aprovar()
        {
            this.APROVADO = true;
            this.DT_APROVACAO = DateTime.Now;
        }

        public void Ativar()
        {
            this.ATIVO = true;
            this.DT_ATIVACAO = DateTime.Now;
        }

        public void Bloquear()
        {
            this.BLOQUEADO = true;
        }

        public void Desbloquear()
        {
            this.BLOQUEADO = false;
        }

        public void Desativar()
        {
            this.ATIVO = false;
            this.DT_ATIVACAO = DateTime.Now;
        }

        private void InicializaVariaveis()
        {
            this.PERFIS_PROFISSIONAIS = new HashSet<PERFIL_PROFISSIONAL>();
        }

        public Guid ID_USUARIO { get; private set; }
        public string NOME { get; private set; }
        public CPF CPF { get; private set; }
        public RG RG { get; private set; }
        public DateTime DT_NASCIMENTO { get; private set; }
        public DateTime DT_INCLUSAO { get; private set; }
        public DateTime? DT_ALTERACAO { get; private set; }
        public DateTime? DT_APROVACAO { get; private set; }
        public DateTime? DT_ATIVACAO { get; private set; }
        public DateTime DT_ORDENACAO { get; private set; }
        public bool APROVADO { get; private set; }
        public bool ATIVO { get; private set; }
        public bool BLOQUEADO { get; private set; }
        //public ENDERECO ENDERECO { get; private set; }
        //public CONTATO CONTATO { get; private set; }

        public EnumUF UF { get; private set; }
        public string CEP { get; private set; }
        public string LOGRADOURO { get; private set; }
        public string COMPLEMENTO { get; private set; }
        public string BAIRRO { get; private set; }
        public string CIDADE { get; private set; }

        public Telefone FIXO { get; private set; }
        public Telefone CELULAR { get; private set; }
        public Email EMAIL { get; private set; }

        public ICollection<PERFIL_PROFISSIONAL> PERFIS_PROFISSIONAIS { get; private set; }
        public ICollection<OFERTA_SERVICO> OFERTAS_SERVICO { get; private set; }
        public ICollection<PROPOSTA_SERVICO> PROPOSTAS_SERVICO { get; private set; }
    }
}