﻿using System;
using System.ComponentModel.DataAnnotations;

namespace JOB.WEB.Models
{
    public class ProfissionalViewModel
    {
        public Guid ID_USUARIO { get; set; }
        public string NOME { get; set; }

        [Display(Name = "Dt. Nascimento")]
        [DataType(DataType.Date)]
        [DisplayFormat(DataFormatString = "{0:yyyy-MM-dd}", ApplyFormatInEditMode = true)]
        public DateTime DT_NASCTO { get; set; }

        public int ID_ESPECIALIDADE { get; set; }
        public string DESC_ESPECIALIDADE { get; set; }
        public string RESUMO_CURRICULO { get; set; }
    }
}