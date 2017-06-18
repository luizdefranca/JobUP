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
            this.PERFIL_DESTAQUE = false;

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

        public void AtivarDestaque()
        {
            this.DT_ORDENACAO = DateTime.Now;
            this.PERFIL_DESTAQUE = true;
        }

        public void MovimentarMoeda(Int16 VALOR)
        {
            if ((this.MOEDA + VALOR) < 0) throw new Exception("Você não possui moedas suficientes para realizar essa transação");

            this.MOEDA += VALOR;
        }

        private void InicializaVariaveis()
        {
            this.PERFIS_PROFISSIONAIS = new HashSet<PERFIL_PROFISSIONAL>();
            this.OFERTAS_SERVICO = new HashSet<OFERTA_SERVICO>();
            this.PROPOSTAS_SERVICO = new HashSet<PROPOSTA_SERVICO>();
            this.HISTORICOS_MOEDA = new HashSet<HISTORICO_MOEDA>();
        }

        public Guid ID_USUARIO { get; set; }
        public string NOME { get; set; }
        public CPF CPF { get; set; }
        public RG RG { get; set; }
        public DateTime DT_NASCIMENTO { get; set; }
        public DateTime DT_INCLUSAO { get; set; }
        public DateTime? DT_ALTERACAO { get; set; }
        public DateTime? DT_APROVACAO { get; set; }
        public DateTime? DT_ATIVACAO { get; set; }
        public DateTime DT_ORDENACAO { get; set; }
        public bool APROVADO { get; set; }
        public bool ATIVO { get; set; }
        public bool BLOQUEADO { get; set; }
        public bool PERFIL_DESTAQUE { get; set; }

        public EnumUF UF { get; set; }
        public string CEP { get; set; }
        public string LOGRADOURO { get; set; }
        public string COMPLEMENTO { get; set; }
        public string BAIRRO { get; set; }
        public string CIDADE { get; set; }

        public Telefone FIXO { get; set; }
        public Telefone CELULAR { get; set; }
        public Email EMAIL { get; set; }

        public Int16 MOEDA { get; set; }

        public ICollection<PERFIL_PROFISSIONAL> PERFIS_PROFISSIONAIS { get; set; }
        public ICollection<OFERTA_SERVICO> OFERTAS_SERVICO { get; set; }
        public ICollection<PROPOSTA_SERVICO> PROPOSTAS_SERVICO { get; set; }
        public ICollection<HISTORICO_MOEDA> HISTORICOS_MOEDA { get; set; }
    }
}