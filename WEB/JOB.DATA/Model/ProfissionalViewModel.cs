using JOB.DATA.Domain;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace JOB.WEB.Models
{
    public class ProfissionalViewModel
    {
        public ProfissionalViewModel()
        {
            this.OUTROS_PERFIS = new List<ProfissionalViewModel>();
            this.AVALIACOES = new List<AvaliacaoViewModel>();
            this.SERVICOS = new List<ServicoViewModel_api>();
        }

        public Guid ID_USUARIO { get; set; }

        [Display(Name = "Nome")]
        public string NOME { get; set; }

        [Display(Name = "Dt. Nascimento")]
        [DataType(DataType.Date)]
        public DateTime DT_NASCTO { get; set; }

        public int ID_ESPECIALIDADE { get; set; }

        [Display(Name = "Especialidade")]
        public string DESC_ESPECIALIDADE { get; set; }

        [Display(Name = "Resumo Currículo")]
        public string RESUMO_CURRICULO { get; set; }

        [Display(Name = "Usuário desde")]
        [DataType(DataType.Date)]
        public DateTime DT_INCLUSAO { get; set; }

        [Display(Name = "Bairro")]
        public string BAIRRO { get; set; }

        [Display(Name = "Cidade")]
        public string CIDADE { get; set; }

        [Display(Name = "Estado")]
        public string ESTADO { get; set; }

        /// <summary>
        /// Destaque de perfil ativado
        /// </summary>
        [Display(Name = "Destaque")]
        public bool PERFIL_DESTAQUE { get; set; }

        /// <summary>
        /// Media de todas as avalicoes recebidas pelo profissional neste perfil
        /// </summary>
        [Display(Name = "Media das Avaliacoes")]
        public double MEDIA_AVALIACOES_FEITAS { get; set; }

        /// <summary>
        /// Total de propostas aceitas por este profissional neste perfil
        /// </summary>
        public int? QTD_PROPOSTAS_ACEITAS { get; set; }

        /// <summary>
        /// Data de priorizacao padrao na lista de perfis (mais novos primeiro ou quem pagou para ter destaque)
        /// </summary>
        public DateTime DT_ORDENACAO { get; set; }

        /// <summary>
        /// outros perfis cadastrados para este profissional
        /// </summary>
        public List<ProfissionalViewModel> OUTROS_PERFIS { get; set; }

        /// <summary>
        /// avaliacoes feitas para este perfil profissional
        /// </summary>
        public List<AvaliacaoViewModel> AVALIACOES { get; set; }

        /// <summary>
        /// servicos dos quais o profissional fez uma proposta
        /// </summary>
        public List<ServicoViewModel_api> SERVICOS { get; set; }
    }

    public class CadastroProfissionalViewModel
    {
        public CadastroProfissionalViewModel()
        {
            ESPECIALIDADES = new List<ESPECIALIDADE>();
        }

        public int ID_ESPECIALIDADE { get; set; }

        [Display(Name = "Descrição *")]
        [DataType(DataType.MultilineText)]
        public string RESUMO_CURRICULO { get; set; }

        [Display(Name = "Especialidade *")]
        public List<ESPECIALIDADE> ESPECIALIDADES { get; set; }
    }
}