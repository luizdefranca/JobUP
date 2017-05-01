using JOB.DATA.Domain;
using JOB.DATA.Enum;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace JOB.WEB.Models
{
    public class ServicoViewModel
    {
        public ServicoViewModel()
        {
            ESPECIALIDADES = new List<ESPECIALIDADE>();
            SUB_ESPECIALIDADES = new List<SUB_ESPECIALIDADE>();
        }

        [Key]
        public Guid ID_SERVICO { get; set; }

        public Guid ID_USUARIO { get; set; }

        [Required]
        public string NOME { get; set; }

        public int ID_ESPECIALIDADE { get; set; }

        [Display(Name = "Especialidade")]
        public string DESC_ESPECIALIDADE { get; set; }

        [Required]
        [Display(Name = "Especialidade")]
        public List<ESPECIALIDADE> ESPECIALIDADES { get; set; }

        public int? ID_SUB_ESPECIALIDADE { get; set; }

        [Display(Name = "SubEspecialidade")]
        public string DESC_SUB_ESPECIALIDADE { get; set; }

        [Display(Name = "SubEspecialidade")]
        public List<SUB_ESPECIALIDADE> SUB_ESPECIALIDADES { get; set; }

        [Display(Name = "Data Cadastro")]
        [DataType(DataType.DateTime)]
        public DateTime DT_CADASTRO { get; set; }

        [Required]
        [Display(Name = "Público")]
        public bool PUBLICO { get; set; }

        [Required]
        [Display(Name = "Título")]
        public string DS_TITULO { get; set; }

        [Required]
        [Display(Name = "Observações")]
        public string DS_OBSERVACOES { get; set; }

        [Display(Name = "Valor Sugerido")]
        public double? VL_SUGERIDO { get; set; }

        [Required]
        [Display(Name = "Expectativa de Prazo")]
        public EnumTempoServico TEMPO_SERVICO { get; set; }
    }
}