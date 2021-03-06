﻿using JOB.DATA.Domain;
using JOB.DATA.Enum;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace JOB.WEB.Models
{
    public class ServicoViewModel_api
    {
        [Key]
        public Guid ID_SERVICO { get; set; }

        public Guid ID_USUARIO { get; set; }
        public Guid ID_PROFISSIONAL { get; set; }

        /// <summary>
        /// Nome do Profissional
        /// </summary>
        public string NOME { get; set; }

        public int ID_ESPECIALIDADE { get; set; }

        [Display(Name = "Especialidade")]
        public string DESC_ESPECIALIDADE { get; set; }

        public int? ID_SUB_ESPECIALIDADE { get; set; }

        [Display(Name = "SubEspecialidade")]
        public string DESC_SUB_ESPECIALIDADE { get; set; }

        [Display(Name = "Data Cadastro")]
        [DataType(DataType.DateTime)]
        public DateTime DT_CADASTRO { get; set; }

        [Required]
        [Display(Name = "Título")]
        public string DS_TITULO { get; set; }

        [Required]
        [Display(Name = "Observações")]
        public string DS_OBSERVACOES { get; set; }

        [Display(Name = "Valor Sugerido")]
        public double? VL_SUGERIDO { get; set; }

        /// <summary>
        /// Expectativa de Prazo
        /// </summary>
        [Required]
        [Display(Name = "Expectativa de Prazo")]
        public EnumTempoServico TEMPO_SERVICO { get; set; }

        /// <summary>
        /// Expectativa de Prazo
        /// </summary>
        [Display(Name = "Expectativa de Prazo")]
        public string TEMPO_SERVICO_DESC { get; set; }

        /// <summary>
        /// Propostas recebidas do profissional (serviço privado) ou dos profissionais (serviço publico)
        /// </summary>
        public List<PropostaViewModel> PROPOSTAS { get; set; }

        /// <summary>
        /// Oferta oferecida para um determinado profissional (apenas em servicos privados)
        /// </summary>
        public List<OfertaViewModel> OFERTAS { get; set; }
    }

    public class ServicoViewModel_full
    {
        public ServicoViewModel_full()
        {
            ID_SERVICO = Guid.NewGuid();

            ESPECIALIDADES = new List<ESPECIALIDADE>();
            SUB_ESPECIALIDADES = new List<SUB_ESPECIALIDADE>();
        }

        [Key]
        public Guid ID_SERVICO { get; set; }

        public Guid ID_USUARIO { get; set; }

        public int ID_ESPECIALIDADE { get; set; }

        [Display(Name = "Especialidade")]
        public string DESC_ESPECIALIDADE { get; set; }

        [Required]
        [Display(Name = "Especialidade *")]
        public List<ESPECIALIDADE> ESPECIALIDADES { get; set; }

        public int? ID_SUB_ESPECIALIDADE { get; set; }

        [Display(Name = "SubEspecialidade")]
        public string DESC_SUB_ESPECIALIDADE { get; set; }

        [Display(Name = "SubEspecialidade *")]
        public List<SUB_ESPECIALIDADE> SUB_ESPECIALIDADES { get; set; }

        [Display(Name = "Data Cadastro")]
        [DataType(DataType.DateTime)]
        public DateTime DT_CADASTRO { get; set; }

        [Required]
        [Display(Name = "Título *")]
        public string DS_TITULO { get; set; }

        [Required]
        [DataType(DataType.MultilineText)]
        [Display(Name = "Observações *")]
        public string DS_OBSERVACOES { get; set; }

        [DataType(DataType.Currency)]
        [Display(Name = "Valor Sugerido")]
        public double? VL_SUGERIDO { get; set; }

        [Required]
        [Display(Name = "Expectativa de Prazo *")]
        public EnumTempoServico TEMPO_SERVICO { get; set; }

        public bool POSSUI_PROPOSTA { get; set; }
    }
}