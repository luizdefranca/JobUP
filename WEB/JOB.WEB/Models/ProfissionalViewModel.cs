﻿using JOB.DATA.Domain;
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

        [Display(Name = "Úsuário desde")]
        [DataType(DataType.Date)]
        public DateTime DT_INCLUSAO { get; set; }

        [Display(Name = "Bairro")]
        public string BAIRRO { get; set; }

        [Display(Name = "Cidade")]
        public string CIDADE { get; set; }

        [Display(Name = "Estado")]
        public string ESTADO { get; set; }

        public List<ProfissionalViewModel> OUTROS_PERFIS { get; set; }
        public List<AvaliacaoViewModel> AVALIACOES { get; set; }
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