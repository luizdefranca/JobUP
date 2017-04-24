using System;
using System.ComponentModel.DataAnnotations;

namespace JOB.WEB.Models
{
    public class JobViewModel
    {
        [Key]
        public Int64 ID_JOB { get; set; }

        [Required]
        public Guid ID_USUARIO_CLIENTE { get; set; }

        public Guid ID_USUARIO_PROFISSIONAL { get; set; }

        public string nome { get; set; }

        public string especialidade { get; set; }

        
        public int ID_ESPECIALIDADE { get; set; }

        [Required]
        [Display(Name ="Data do Job")]
        [DataType(DataType.Date)]
        public DateTime DT_JOB { get; set; }

        [Required]
        [Display(Name = "Título")]
        public String TITULO { get; set; } //100

        [Display(Name = "Observações")]
        [DataType(DataType.MultilineText)]
        public String OBSERVACOES { get; set; } //1000

        [Display(Name = "Valor Sugerido")]
        [DataType(DataType.Currency)]
        public double? VALOR_SUGERIDO { get; set; }

        public bool JOB_ACEITO { get; set; }
    }
}